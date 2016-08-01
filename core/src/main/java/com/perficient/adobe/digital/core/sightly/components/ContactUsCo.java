package com.perficient.adobe.digital.core.sightly.components;

import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.ValueMap;

import com.adobe.acs.commons.widgets.MultiFieldPanelFunctions;
import com.adobe.cq.sightly.WCMUse;

public class ContactUsCo extends WCMUse{

	private String emailRecepients;
	private String caption;
	private String contactTitle;
	private String receiveMailText;
	private List<Map<String, String>> formItems;
	public static String ADD_FORM_FIELDS = "nav1Items";
	public static String DEFAULT_RECEIVE_TEXT = "Receive email text....";
	
	@Override
	public void activate() throws Exception {
		ValueMap properties = getProperties();
		setEmailRecepients(properties.get("emailRecipents",String.class));
		setContactTitle(properties.get("contactusTitle",String.class));
		setCaption(properties.get("text2",String.class));
		setReceiveMailText(properties.get("receiveMailText",DEFAULT_RECEIVE_TEXT));
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

	public String getContactTitle() {
		return contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public String getReceiveMailText() {
		return receiveMailText;
	}

	public void setReceiveMailText(String receiveMailText) {
		this.receiveMailText = receiveMailText;
	}
	
	
}
