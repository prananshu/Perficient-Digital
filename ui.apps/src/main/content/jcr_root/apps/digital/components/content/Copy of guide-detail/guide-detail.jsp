<%@page session="false"%><%--
  Copyright 1997-2008 Day Management AG
  Barfuesserplatz 6, 4001 Basel, Switzerland
  All Rights Reserved.

  This software is the confidential and proprietary information of
  Day Management AG, ("Confidential Information"). You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with Day.

  ==============================================================================

  Image-Image component

  Combines 2 image components and 1 text component

--%><%@ page import="com.day.cq.commons.DiffService,
    com.day.cq.commons.Doctype,
    com.day.cq.wcm.api.WCMMode,
    com.day.cq.wcm.api.components.DropTarget,
    com.day.cq.wcm.foundation.Image, com.day.cq.wcm.foundation.Placeholder" %><%
%><%@include file="/libs/foundation/global.jsp"%><%
    boolean isAuthoringUIModeTouch = Placeholder.isAuthoringUIModeTouch(slingRequest);

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
<div class="clear">
		<cq:include script="text.jsp"/>
</div>
<div class="clear">
		<cq:include script="title.jsp"/>
</div>


