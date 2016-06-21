package com.perficient.adobe.digital.core.sightly;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;

import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ PageHelper.class })
public class PageHelperTest {
	
	/** The page properties. */
	  private ValueMap pageProperties;
	
	/** The current page. */
	  @Mock
	  private Page currentPage;

	  @Spy
	  private PageHelper pageHelper = new PageHelper();
	  
	  
	  /**
	   * Setup.
	   */
	  @Before
	  public void setup() {
		  doReturn(currentPage).when(pageHelper).getCurrentPage();
		  when(currentPage.getName()).thenReturn("digital");
		  
		  Map<String, Object> propertiesMap = new HashMap<String, Object>();
		  propertiesMap.put("jcr:description",
		              "Our full-service offering is designed to tackle your ever-changing, ever-increasing business challenges by making sure the executional means never fail the strategic vision.");
		  pageProperties = new ValueMapDecorator(propertiesMap);
		  doReturn(pageProperties).when(pageHelper).getPageProperties();
		  
		  Tag tag = mock(Tag.class);
		  Tag[] tagArray = { tag, tag, tag, tag, tag };  
		  when(currentPage.getTags()).thenReturn(tagArray);
		  when(tag.getTitle()).thenReturn("digital", "marketing", "advertising", "agency", "creative");
	  }

  
	  /**
	  * Test retrieving the title when the "Page Title" property has been set.
	  *
	  * @throws Exception
	  *             the exception
	  */
	  @Test
	  public void testTitleWithPageTitle() throws Exception {       
	    pageHelper.activate();
	    when(currentPage.getTitle()).thenReturn("Digital");
	    when(currentPage.getPageTitle()).thenReturn("Perficient Digital");
	    assertEquals("Perficient Digital", pageHelper.getPageTitle());
	  }

	  /**
	  * Test retrieving the title when the "Title" property has been set and the "Page Title" property has not been set.
	  *
	  * @throws Exception
	  *             the exception
	  */
	  @Test
	  public void testTitleWithTitle() throws Exception {
	    pageHelper.activate();
	        when(currentPage.getTitle()).thenReturn("Digital");
	        assertEquals("Digital", pageHelper.getPageTitle());
	  }

	  /**
	  * Test retrieving the title when only the page name is present.
	  *
	  * @throws Exception
	  *             the exception
	  */
	  @Test
	  public void testTitleWithName() throws Exception {
	    pageHelper.activate();
	    assertEquals("digital", pageHelper.getPageTitle());
	  }
	  
	  /**
	  * Test description.
	  *
	  * @throws Exception the exception
	  */
	  @Test
	  public void testDescription() throws Exception {
	    pageHelper.activate();
	    assertEquals(
	        "Our full-service offering is designed to tackle your ever-changing, ever-increasing business challenges by making sure the executional means never fail the strategic vision.",
	        pageHelper.getDescription());
	  }
	  
	  /**
	   * Test keywords.
	   */
	  @Test
	  public void testKeywords() throws Exception {
	      pageHelper.activate();
	      assertEquals("digital, marketing, advertising, agency, creative", pageHelper.getKeywords());

	  }
	  
	  /**
	  * Test page language.
	  *
	  * @throws Exception the exception
	  */
	  @Test
	  public void testPageLanguage() throws Exception {
	    pageHelper.activate();
	    Locale locale = new Locale("fr");
	    when(currentPage.getLanguage(true)).thenReturn(locale);
	    assertEquals("fr", pageHelper.getPageLanguage());
	  }

	  /**
	  * Test page language when no locale is set.
	  *
	  * @throws Exception the exception
	  */
	  @Test
	  public void testPageLanguageWhenNoLocaleIsSet() throws Exception {
	    pageHelper.activate();
	    assertEquals("en", pageHelper.getPageLanguage());
	  }

}