package com.perficient.adobe.digital.core.sightly.components;

import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.ValueMap;

import com.adobe.acs.commons.widgets.MultiFieldPanelFunctions;
import com.adobe.cq.sightly.WCMUse;

public class PDHeader extends WCMUse {
	
	public static String DEFAULT_LOGO_IMAGE = "../../../../../etc/designs/digital/clientlib/img/perficient-logo.png";
    public static String DEFAULT_ALT_TEXT = "Perficient Logo";
    public static String DEFAULT_LOGO_URL = "http://www.perficient.com/services/perficient-digital";
	
	private String logoUrl;
	private String logoPath;
	private String logoAltText;
	
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
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
	
	private String section1Label;
	private String section1URL;
	private List<Map<String, String>> nav1Items;
	
	//Navigation1 Tab
	public static String NAV_1_LABEL_PROP = "section1Label";
	public static String NAV_1_URL_PROP = "section1URL";
	public static String NAV_1_ITEMS_PROP = "section1Items";
	
	//Component Property Defaults
	public static String DEFAULT_NAV1_LABEL = "Services!!!";
	public static String DEFAULT_NAV1_URL = "#";
	
	public String getSection1Label() {
		return section1Label;
	}
	public void setSection1Label(String section1Label) {
		this.section1Label = section1Label;
	}
	public String getSection1URL() {
		return section1URL;
	}
	public void setSection1URL(String section1url) {
		section1URL = section1url;
	}
	public List<Map<String, String>> getNav1Items() {
		return nav1Items;
	}
	public void setNav1Items(List<Map<String, String>> list) {
		this.nav1Items = list;
	}
	
	
	@Override
	public void activate() throws Exception {
		
		ValueMap properties = getProperties();
		setLogoUrl(properties.get("logoUrl",DEFAULT_LOGO_URL));
		setLogoAltText(properties.get("logoAltText",DEFAULT_ALT_TEXT));
		setLogoPath(properties.get("logoPath",DEFAULT_LOGO_IMAGE));
		
		setSection1Label(properties.get(NAV_1_LABEL_PROP,DEFAULT_NAV1_LABEL));
		setSection1URL(properties.get(NAV_1_URL_PROP,DEFAULT_NAV1_URL));
		setNav1Items(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(),NAV_1_ITEMS_PROP));
		
		/*setLogoPath(properties.get("logoImage",String.class));
		setLogoUrl(properties.get("logoImageURL",String.class));
		setLogoAltText(properties.get("logoAltText",String.class));*/
	}

}
