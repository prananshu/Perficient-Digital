<%@page session="false"%>

<%@ page import="com.day.cq.commons.DiffService,
    com.day.cq.commons.Doctype,
    com.day.cq.wcm.api.WCMMode,
    com.day.cq.wcm.api.components.DropTarget,
    com.day.cq.wcm.foundation.Image, com.day.cq.wcm.foundation.Placeholder" %><%
%><%@include file="/libs/foundation/global.jsp"%><%
    boolean isAuthoringUIModeTouch = Placeholder.isAuthoringUIModeTouch(slingRequest);
%>

<div class="clear"></div>

<div class="container guides-block">
<div class="row">
	<div class="col-md-6">
		<div class="col-md-12 cms-tags">
			<cq:include script="keywords.html"/>
            <br/>
            <cq:include script="title.jsp"/>
            <cq:include script="text.jsp"/>
		</div>
	</div>
	<div class="col-md-6">
        <div class="clear"></div>
       <% 
        Image image1 = new Image(resource, "image1");

    // don't draw the placeholder in case UI mode touch it will be handled afterwards
    if (isAuthoringUIModeTouch) {
        image1.setNoPlaceholder(true);
    }

    //drop target css class = dd prefix + name of the drop target in the edit config
    String ddClassName1 = DropTarget.CSS_CLASS_PREFIX + "image1";

    // if diff is being taken, then also the image or default image has to be drawn
    String vLabel1 = request.getParameter(DiffService.REQUEST_PARAM_DIFF_TO);

    if (image1.hasContent() || WCMMode.fromRequest(request) == WCMMode.EDIT || vLabel1 != null) {
        image1.loadStyleData(currentStyle);
        // add design information if not default (i.e. for reference paras)
        if (!currentDesign.equals(resourceDesign)) {
            image1.setSuffix(currentDesign.getId());
        }
        image1.addCssClass(ddClassName1);
        image1.setSelector(".img");
        image1.setDoctype(Doctype.fromRequest(request));

        String divId1 = "cq-image-jsp-" + resource.getPath();
        String imageHeight1 = image1.get(image1.getItemName(Image.PN_HEIGHT));
        String placeholder1 = (isAuthoringUIModeTouch && !image1.hasContent())
               ? Placeholder.getDefaultPlaceholder(slingRequest, component, "", ddClassName1)
               : "";
        // div around image for additional formatting
        %><div class="image" id="<%= xssAPI.encodeForHTMLAttr(divId1) %>"><%
        %><cq:text escapeXml="true" placeholder="<%= placeholder1 %>"/>
		<% image1.draw(out); %><br><%

        %></div><%
    }
    %> 
	</div>
</div>
