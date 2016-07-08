package com.perficient.adobe.digital.core.sightly.components;

import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.ValueMap;

import com.adobe.acs.commons.widgets.MultiFieldPanelFunctions;
import com.adobe.cq.sightly.WCMUse;

public class ContactUsCo extends WCMUse{

	private String emailRecepients;
	private String caption;
	private List<Map<String, String>> formItems;
	public static String ADD_FORM_FIELDS = "nav1Items";
	
	@Override
	public void activate() throws Exception {
		ValueMap properties = getProperties();
		setEmailRecepients(properties.get("emailRecipents",String.class));
		setCaption(properties.get("text2",String.class));
		setFormItems(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(),ADD_FORM_FIELDS));
	}

	public List<Map<String, String>> getFormItems() {
		return formItems;
	}

	public void setFormItems(List<Map<String, String>> formItems) {
		this.formItems = formItems;
	}

	public String getEmailRecepients() {
		return emailRecepients;
	}

	public void setEmailRecepients(String emailRecepients) {
		this.emailRecepients = emailRecepients;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	
}
