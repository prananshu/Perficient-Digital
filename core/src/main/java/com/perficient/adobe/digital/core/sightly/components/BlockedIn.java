package com.perficient.adobe.digital.core.sightly.components;

import java.util.Calendar;

import org.apache.sling.api.resource.ValueMap;

import com.adobe.cq.sightly.WCMUse;

public class BlockedIn extends WCMUse{
	
	private String heroBlockText;
	private String heroTabText;
	
	//Default values
	public static String DEFAULT_BLOCK_TEXT = "This is Hero Block Text";
	public static String DEFAULT_TAB_TEXT = "This is Hero Tab Text";

	public String getHeroBlockText() {
		return heroBlockText;
	}

	public void setHeroBlockText(String heroBlockText) {
		this.heroBlockText = heroBlockText;
	}

	
public String getHeroTabText() {
		return heroTabText;
	}

	public void setHeroTabText(String heroTabText) {
		this.heroTabText = heroTabText;
	}

@Override
  public void activate() throws Exception {
	  
	ValueMap properties = getProperties();
	setHeroBlockText(properties.get("textBlk",DEFAULT_BLOCK_TEXT)); //textBlk is jcr property of heroBlock node under page..
	setHeroTabText(properties.get("textBlk",DEFAULT_TAB_TEXT)); //textBlk is jcr property of heroTab node under page..
	
  }

}