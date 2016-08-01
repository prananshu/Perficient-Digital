package com.perficient.adobe.digital.core.sightly;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.day.cq.wcm.api.Page;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ DigitalPageHelper.class })
public class DigitalPageHelperTest {

	/** The page properties. */
	private ValueMap pageProperties;

	/** The current page. */
	@Mock
	private Page currentPage;

	@Spy
	private DigitalPageHelper digitalPageHelper = new DigitalPageHelper();
	
	/**
	   * Setup.
	   */
	  @Before
	  public void setup() {
		  doReturn(currentPage).when(digitalPageHelper).getCurrentPage();
		  
		  Map<String, Object> propertiesMap = new HashMap<String, Object>();
		  propertiesMap.put("jcr:description","This is a test description");
		  propertiesMap.put("jcr:trainer","Test Trainer");
		  propertiesMap.put("jcr:author","Test Author");
		  propertiesMap.put("jcr:date","07/29/16");
		  
		  pageProperties = new ValueMapDecorator(propertiesMap);
		  doReturn(pageProperties).when(digitalPageHelper).getPageProperties();
	  }

	@Test
	public void testPageName() throws Exception 
	{
		digitalPageHelper.activate();
		when(currentPage.getName()).thenReturn("digital");
	    assertEquals("digital", digitalPageHelper.getPageTitle());
	}
	
	@Test
	public void testdescription() throws Exception 
	{
		digitalPageHelper.activate();
	    assertEquals("This is a test description", digitalPageHelper.getDescription());
	}
	
	@Test
	public void testTrainer() throws Exception 
	{
		digitalPageHelper.activate();
	    assertEquals("Test Trainer", digitalPageHelper.getTrainer());
	}
	
	@Test
	public void testAuthor() throws Exception 
	{
		digitalPageHelper.activate();
	    assertEquals("Test Author", digitalPageHelper.getAuthor());
	}
	
	@Test
	public void testDate() throws Exception 
	{
		digitalPageHelper.activate();
	    assertEquals("07/29/16", digitalPageHelper.getTrainingDate());
	}
}
