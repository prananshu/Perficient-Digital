package com.perficient.adobe.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.commons.json.jcr.JsonItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(metatype = true)
@Service(Servlet.class)
@Properties({
	@Property(name = "sling.servlet.resourceTypes", value = "sling/servlet/default"),
	@Property(name = "sling.servlet.selectors", value = "myjson"),
	@Property(name = "sling.servlet.extensions", value = "json"),
	@Property(name = "sling.servlet.methods", value = "GET")
})
public class OverrideDefaultServlet extends SlingSafeMethodsServlet{

	/** The logger */
	private static final Logger logger = LoggerFactory.getLogger(OverrideDefaultServlet.class);

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
		try {
			/*

		Resource resource = request.getResource();

	    PageManager pageManager= resource.getResourceResolver().adaptTo(PageManager.class);

	    Page currentPage = pageManager.getContainingPage(resource);

	    response.getWriter().write(currentPage.getPath()); // /content/digital/pageName
			 */	
			
			response.setCharacterEncoding("UTF-8");
	        response.setContentType("application/json");
	        
	        final PrintWriter out = response.getWriter();

			Resource resource = request.getResource();
			logger.info("Resource>>>",resource);

			if (resource != null) {
				Node node = resource.adaptTo(Node.class);
				logger.info("Node>>>"+node.getName());
				logger.info("Node Path>>>"+node.getPath());
				StringWriter stringWriter = new StringWriter();
				JsonItemWriter jsonWriter = new JsonItemWriter(null);

				jsonWriter.dump(node, out, -1, true);
	            response.setStatus(SlingHttpServletResponse.SC_OK);
			}
		} catch (RepositoryException e) {
			logger.error("Repository Exception", e);
			response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		catch (JSONException e) {
			logger.error("Could not get JSON", e);
			response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}

