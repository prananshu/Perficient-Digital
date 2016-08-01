package com.perficient.adobe.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.time.DateUtils;

import com.adobe.acs.commons.email.EmailService;

@Component(immediate = true, metatype = true, label = "Email Scheduler Service")
@Service(value = Runnable.class)
@Property(name = "scheduler.expression", value = "0 0/1 * 1/1 * ? *") // Every 1 min
public class EmailSchedulerImpl implements Runnable {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailSchedulerImpl.class);

	@Reference
	private SlingRepository repository;

	@Reference
	private EmailService emailService;

	// No of guides osgi config property
	@Property(label = "No of Guides", description = "Number of guides", value = "1")
	public static final String NO_GUIDES = "noOfGuides";
	private int no_Of_guides;

	// Guide detail path osgi config property
	@Property(label = "Guide detail path", description = "Guide detail path", value = "content/digital")
	public static final String GUIDE_PATH = "guideDetailPath";
	private String guide_Detail_Path;

	Session session = null;

	protected void activate(ComponentContext componentContext){
		configure(componentContext.getProperties());
	}

	protected void configure(Dictionary<?, ?> properties) {
		this.no_Of_guides = PropertiesUtil.toInteger(properties.get(NO_GUIDES),1);
		this.guide_Detail_Path = PropertiesUtil.toString(properties.get(GUIDE_PATH),"content/digital");
		LOGGER.info("No of Guides >>>>", no_Of_guides);
		LOGGER.info("Path Guide Detail >>>>", guide_Detail_Path);
	}

	@Override
	public void run() {
		try {
			session = repository.login(new SimpleCredentials("admin","admin".toCharArray()));

			//Create a node that represents the root node
			Node root = session.getRootNode(); 
			Node guideDetailPath = root.getNode(guide_Detail_Path);

			//get guide detail pages
			Map<String, List<String>> guides = new HashMap<String, List<String>>();
			guides = getGuides(guideDetailPath,no_Of_guides);

			if(guides!=null){

				List<String> title = guides.get("title");
				String guideTitle[] = title.toArray(new String[title.size()]);

				List<String> path = guides.get("path");
				String guidePath[] = path.toArray(new String[path.size()]);

				for (String title1: guideTitle) {           
					LOGGER.info("Guide Title >>> "+title1); 
				}

				for (String path1: guidePath) {           
					LOGGER.info("Guide Path >>> "+path1); 
				}

				String body ="";

				for(int i=0;i<title.size();i++){

					String path1 = "http://www.google.com";
					String title1 = title.get(i);

					body = body+"<div><a href="
							+path1+">"+title1+"</a></div></br>";
				}

				LOGGER.info("Email body >>> "+body);

				//get customer email
				Node digital = root.getNode("content/digital");
				List<String> recipients = new ArrayList<String>();
				recipients = getCustEmails(digital);

				String emails[] = recipients.toArray(new String[recipients.size()]);

				for (String rec: emails) {           
					LOGGER.info("Recipient Email >>>> "+rec); 
				}

				String templatePath = "/etc/notification/email/gd.html";

				//Set the dynamic vaiables of your email template
				Map<String, String> emailParams = new HashMap<String,String>();

				//  Customize the sender email address - if required
				emailParams.put("body",body);

				// emailService.sendEmail(..) returns a list of all the recipients that could not be sent the email
				// An empty list indicates 100% success

				List<String> failureList = emailService.sendEmail(templatePath, emailParams, emails);
				if(failureList.size()==0){
					LOGGER.info("Email sent successfully...");
				}

			}
			else
				LOGGER.info("No Recent Guides...");

			session.save(); 
			session.logout();

		} catch (LoginException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}


	}

	//get guide detail pages
	private static Map<String, List<String>> getGuides(Node guideDetailPath, int nofGuides) {

		Map<String,List<String>> map = null;
		List<String> title = new ArrayList<String>();
		List<String> path = new ArrayList<String>();

		NodeIterator digitalChildNodes;
		try {
			digitalChildNodes = guideDetailPath.getNodes();

			while (digitalChildNodes.hasNext() && nofGuides != 0) {
				Node pageNode = digitalChildNodes.nextNode();

				if(pageNode.getProperty("jcr:primaryType").getString().equals("cq:Page")){

					Node n=pageNode.getNode("jcr:content");
					if(n.getProperty("cq:template").getString().equals("/apps/digital/templates/guide-detail-temp")){
						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

						//get current date time with Date()
						Date todayDate = new Date();
						Calendar todaysDate = Calendar.getInstance();

						String string = n.getProperty("jcr:created").getString();
						Calendar jcrDate = n.getProperty("jcr:created").getDate();


						String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
						Date jcrCreatedDate;
						try {

							//jcrCreatedDate = new SimpleDateFormat(pattern).parse(string);

							//System.out.println("jcr:date "+dateFormat.format(jcrCreatedDate));

							// recent guide (jcr:created date matches with today's date)
							//							if(dateFormat.format(todayDate).equals(dateFormat.format(jcrCreatedDate))){
							//				    		  path.add(pageNode.getPath());
							//				    		  title.add(n.getProperty("jcr:title").getString());
							//				    		  nofGuides--;
							//							}

							if(DateUtils.isSameDay(jcrDate,todaysDate)){
								path.add(pageNode.getPath());
								title.add(n.getProperty("jcr:title").getString());
								nofGuides--;
							}

						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				}

			}

		} catch (RepositoryException e) {
			e.printStackTrace();
		}

		if(title.size()!=0){
			map = new HashMap<String,List<String>>();
			map.put("title", title);
			map.put("path", path);
			return map;
		}
		else
			return map;
	}

	private static List<String> getCustEmails(Node digital) {
		Node customer;
		List<String> recipients = new ArrayList<String>();
		try {
			customer = digital.getNode("customer");

			NodeIterator customerNodes = customer.getNodes();
			while (customerNodes.hasNext()) {
				Node cust = customerNodes.nextNode();
				recipients.add(cust.getProperty("Work Email").getString());
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		}

		return recipients;
	}

}
