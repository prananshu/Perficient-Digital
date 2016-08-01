package com.perficient.adobe.services;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.jcr.JsonItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/*
 * Generate json response on hitting "http://localhost:4502/bin/digital"
 */
@SlingServlet(paths={"/bin/digital"})
public class DemoJSONServlet extends SlingSafeMethodsServlet {
	/** The logger */
    private static final Logger logger = LoggerFactory.getLogger(DemoJSONServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        final PrintWriter out = response.getWriter();
        final ResourceResolver resolver = request.getResourceResolver();
        final Resource resource = resolver.getResource("/content/digital");
        final Node node = resource.adaptTo(Node.class);

        /* Node properties to exclude from the JSON object. */
        final Set<String> propertiesToIgnore = new HashSet<String>() {{
            add("jcr:created");
            add("jcr:createdBy");
            add("jcr:versionHistory");
            add("jcr:predecessors");
            add("jcr:baseVersion");
            add("jcr:uuid");
        }};

        JsonItemWriter jsonWriter = new JsonItemWriter(propertiesToIgnore);

        try {
        	/* Get JSON with no limit to recursion depth. */
            jsonWriter.dump(node, out, -1, true);
            response.setStatus(SlingHttpServletResponse.SC_OK);
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
