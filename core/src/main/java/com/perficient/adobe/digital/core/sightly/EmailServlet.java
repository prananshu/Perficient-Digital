package com.perficient.adobe.digital.core.sightly;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import com.adobe.acs.commons.email.EmailService;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.perficient.adobe.services.CustomerService;


@Component(metatype = true, label = "My Email Service", 
description = "Email service component")
@Service

@Properties({
    @Property(name = "sling.servlet.resourceTypes", value = "digital/components/content/contact-us"),
    @Property(name = "sling.servlet.selectors", value = "data"),
    @Property(name = "sling.servlet.extensions", value = "html"),
    @Property(name = "sling.servlet.methods", value = "POST")
})
public class EmailServlet extends SlingAllMethodsServlet {
	

	/* OSGi Service References */
	@Reference
	private EmailService emailService;
	
	@Reference
	private CustomerService custService;
	
	private static final long serialVersionUID = 1L;

	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException
    {
		
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String workEmail = request.getParameter("workEmail");
        String pageNamePath = "";
        
        custService.addCust(firstName, lastName, workEmail);
        
		try {
			
			
			Resource res1 = request.getResource();
		    
		    PageManager pageManager= res1.getResourceResolver().adaptTo(PageManager.class);

		    Page currentPage = pageManager.getContainingPage(res1);
		    
		    if(currentPage.getAbsoluteParent(2)!=null){
		    	response.getOutputStream().println(currentPage.getAbsoluteParent(2).getName()+"  !!!! Page");
		    	pageNamePath = currentPage.getAbsoluteParent(2).getContentResource().getPath();
		    }
			
			
			String resourcePath = pageNamePath+"/footerContactus/footer_social/contactUs";
			ResourceResolver resourceResolver = request.getResourceResolver();
			Resource res = resourceResolver.getResource(resourcePath);
			ValueMap properties = res.adaptTo(ValueMap.class);
			String rec = properties.get("emailRecipents", (String) null);

		
        
		// Get the OSGi Email Service
		//EmailService emailService = (EmailService) sling.getService(EmailService.class);
		// Specify the template file to use (this is an absolute path in the JCR)
		String templatePath = "/etc/notification/email/en.html";

		//Set the dynamic vaiables of your email template
		Map<String, String> emailParams = new HashMap<String,String>();

		//  Customize the sender email address - if required
		emailParams.put("name",firstName+" "+lastName);
		emailParams.put("workEmail",workEmail);
		emailParams.put("recipientName","Prananshu");

		// Array of email recipients
		String[] recipients = rec.split(",");

		// emailService.sendEmail(..) returns a list of all the recipients that could not be sent the email
		// An empty list indicates 100% success
		
		List<String> failureList = emailService.sendEmail(templatePath, emailParams, recipients);
	
	    response.setContentType("text/html");
	    response.getOutputStream().println("hi hello!!!");
	    
	    
	    	
	    
	    resourceResolver.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }
}
