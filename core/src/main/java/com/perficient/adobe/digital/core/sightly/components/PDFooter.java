package com.perficient.adobe.digital.core.sightly.components;

import java.util.Calendar;

import org.apache.sling.api.resource.ValueMap;

import com.adobe.cq.sightly.WCMUse;

public class PDFooter extends WCMUse{
	
	private String privacyPolicyText;
	private String slogan;
	private String logoPath;
	private String logoAltText;
	private String logoURL;
	private String privacyPolicyUrl;
	
	//Default values
	public static String DEFAULT_SLOGAN = "Default Slogan123";
	public static String DEFAULT_LOGOPATH = "../../../../../etc/designs/digital/clientlib/img/perficient-logo-grey.png";
	public static String DEFAULT_LOGOURL = "#";
	public static String DEFAULT_PRIVACY_POLICY_TEXT = "Default Privacy Policy Text";
	public static String DEFAULT_PRIVACY_POLICY_URL = "#";
	public static String DEFAULT_LOGO_ALT_TEXT = "Logo Alt Text";
	

	public String getSlogan() {
		return slogan;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getLogoAltText() {
		return logoAltText;
	}

	public void setLogoAltText(String logoAltText) {
		this.logoAltText = logoAltText;
	}

	public String getLogoURL() {
		return logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}

	public String getPrivacyPolicyUrl() {
		return privacyPolicyUrl;
	}

	public void setPrivacyPolicyUrl(String privacyPolicyUrl) {
		this.privacyPolicyUrl = privacyPolicyUrl;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}

	public String getPrivacyPolicyText() {
		return privacyPolicyText;
	}

	public void setPrivacyPolicyText(String privacyPolicyText) {
		this.privacyPolicyText = privacyPolicyText;
	}

@Override
  public void activate() throws Exception {
	  
	ValueMap properties = getProperties();
	setLogoPath(properties.get("logoPath",DEFAULT_LOGOPATH));
	setLogoAltText(properties.get("logoAltText",DEFAULT_LOGO_ALT_TEXT));
	setLogoURL(properties.get("logoImageURL",DEFAULT_LOGOURL));
	setPrivacyPolicyUrl(properties.get("privacyPolicyUrl",DEFAULT_PRIVACY_POLICY_URL));
	setPrivacyPolicyText(properties.get("privacyPolicyText",DEFAULT_PRIVACY_POLICY_TEXT));
	setSlogan(properties.get("slogan",DEFAULT_SLOGAN));
  }

	public int getCurrentYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
}