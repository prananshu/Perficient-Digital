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
	public static String NAV_1_LABEL_PROP = "section1label";
	public static String NAV_1_URL_PROP = "section1URL";
	public static String NAV_1_ITEMS_PROP = "nav1Items";
	
	private String industriesLabel;
	private String industriesURL;
	private List<Map<String, String>> industriesItems;
	
	//Navigation2 Tab
	public static String NAV_2_LABEL_PROP = "industriesLabel";
	public static String NAV_2_URL_PROP = "industriesURL";
	public static String NAV_2_ITEMS_PROP = "industriesItems";
	
	private String partnersLabel;
	private String partnersURL;
	private List<Map<String, String>> partnersItems;
	
	//Navigation3 Tab
	public static String NAV_3_LABEL_PROP = "partnersLabel";
	public static String NAV_3_URL_PROP = "partnersURL";
	public static String NAV_3_ITEMS_PROP = "partnersItems";
	
	private String workLabel;
	private String workURL;
	private List<Map<String, String>> workItems;
	
	//Navigation4 Tab
	public static String NAV_4_LABEL_PROP = "workLabel";
	public static String NAV_4_URL_PROP = "workURL";
	public static String NAV_4_ITEMS_PROP = "workItems";
	
	private String insightLabel;
	private String insightURL;
	private List<Map<String, String>> insightItems;
	
	//Navigation5 Tab
	public static String NAV_5_LABEL_PROP = "insightLabel";
	public static String NAV_5_URL_PROP = "insightURL";
	public static String NAV_5_ITEMS_PROP = "insightItems";
	
	
	//Utility Navigation Items
	private List<Map<String, String>> utilNavItems;
	public static String NAV_UTIL_ITEMS_PROP = "utilNavItems";
	
	
	
	public List<Map<String, String>> getUtilNavItems() {
		return utilNavItems;
	}
	public void setUtilNavItems(List<Map<String, String>> utilNavItems) {
		this.utilNavItems = utilNavItems;
	}
	public String getIndustriesLabel() {
		return industriesLabel;
	}
	public void setIndustriesLabel(String industriesLabel) {
		this.industriesLabel = industriesLabel;
	}
	public String getIndustriesURL() {
		return industriesURL;
	}
	public void setIndustriesURL(String industriesURL) {
		this.industriesURL = industriesURL;
	}
	public List<Map<String, String>> getIndustriesItems() {
		return industriesItems;
	}
	public void setIndustriesItems(List<Map<String, String>> industriesItems) {
		this.industriesItems = industriesItems;
	}
	public String getPartnersLabel() {
		return partnersLabel;
	}
	public void setPartnersLabel(String partnersLabel) {
		this.partnersLabel = partnersLabel;
	}
	public String getPartnersURL() {
		return partnersURL;
	}
	public void setPartnersURL(String partnersURL) {
		this.partnersURL = partnersURL;
	}
	public List<Map<String, String>> getPartnersItems() {
		return partnersItems;
	}
	public void setPartnersItems(List<Map<String, String>> partnersItems) {
		this.partnersItems = partnersItems;
	}
	public String getWorkLabel() {
		return workLabel;
	}
	public void setWorkLabel(String workLabel) {
		this.workLabel = workLabel;
	}
	public String getWorkURL() {
		return workURL;
	}
	public void setWorkURL(String workURL) {
		this.workURL = workURL;
	}
	public List<Map<String, String>> getWorkItems() {
		return workItems;
	}
	public void setWorkItems(List<Map<String, String>> workItems) {
		this.workItems = workItems;
	}
	public String getInsightLabel() {
		return insightLabel;
	}
	public void setInsightLabel(String insightLabel) {
		this.insightLabel = insightLabel;
	}
	public String getInsightURL() {
		return insightURL;
	}
	public void setInsightURL(String insightURL) {
		this.insightURL = insightURL;
	}
	public List<Map<String, String>> getInsightItems() {
		return insightItems;
	}
	public void setInsightItems(List<Map<String, String>> insightItems) {
		this.insightItems = insightItems;
	}

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
		setLogoUrl(properties.get("logoImageURL",DEFAULT_LOGO_URL));
		setLogoAltText(properties.get("logoAltText",DEFAULT_ALT_TEXT));
		setLogoPath(properties.get("logoImage",DEFAULT_LOGO_IMAGE));
		
		setSection1Label(properties.get(NAV_1_LABEL_PROP,DEFAULT_NAV1_LABEL));
		setSection1URL(properties.get(NAV_1_URL_PROP,DEFAULT_NAV1_URL));
		setNav1Items(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(),NAV_1_ITEMS_PROP));
		
		setIndustriesLabel(properties.get(NAV_2_LABEL_PROP,DEFAULT_NAV1_LABEL));
		setIndustriesURL(properties.get(NAV_2_URL_PROP,DEFAULT_NAV1_URL));
		setIndustriesItems(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(),NAV_2_ITEMS_PROP));
		
		setPartnersLabel(properties.get(NAV_3_LABEL_PROP,DEFAULT_NAV1_LABEL));
		setPartnersURL(properties.get(NAV_3_URL_PROP,DEFAULT_NAV1_URL));
		setPartnersItems(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(),NAV_3_ITEMS_PROP));
		
		setWorkLabel(properties.get(NAV_4_LABEL_PROP,DEFAULT_NAV1_LABEL));
		setWorkURL(properties.get(NAV_4_URL_PROP,DEFAULT_NAV1_URL));
		setWorkItems(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(),NAV_4_ITEMS_PROP));
		
		setInsightLabel(properties.get(NAV_5_LABEL_PROP,DEFAULT_NAV1_LABEL));
		setInsightURL(properties.get(NAV_5_URL_PROP,DEFAULT_NAV1_URL));
		setInsightItems(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(),NAV_5_ITEMS_PROP));
		
		setUtilNavItems(MultiFieldPanelFunctions.getMultiFieldPanelValues(getResource(),NAV_UTIL_ITEMS_PROP));
		
		/*setLogoPath(properties.get("logoImage",String.class));
		setLogoUrl(properties.get("logoImageURL",String.class));
		setLogoAltText(properties.get("logoAltText",String.class));*/
	}

}
