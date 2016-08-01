package com.perficient.adobe.digital.core.sightly;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;

import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.mockito.Mockito.when;
import com.perficient.adobe.digital.core.sightly.components.FooterSocial;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ FooterSocial.class })
public class FooterSocialTest {
	
	private ValueMap properties;
	
	@Mock
	private FooterSocial footer;
	
	@Before
	  public void setup() {
		properties = new ValueMapDecorator(new HashMap<String, Object>());
		when(footer.getProperties()).thenReturn(properties);
		when(footer.getSlogan()).thenReturn("Slogan");
		when(footer.getLogoAltText()).thenReturn("Test Logo Alt text");
		when(footer.getLogoURL()).thenReturn("#");
		when(footer.getPrivacyPolicyUrl()).thenReturn("#");
		when(footer.getPrivacyPolicyText()).thenReturn("Test Privacy Policy");
	  }
	
	@Test
    public void testSlogan() throws Exception {
		String expectedSlogan = "Slogan";
		properties.put("slogan", expectedSlogan);
        footer.activate();
        String testSlogan = footer.getSlogan();
        assertEquals(expectedSlogan, testSlogan);
    }
	
	@Test
    public void testLogoAltTxt() throws Exception {
		String expectedAltText = "Test Logo Alt text";
		properties.put("logoAltText", expectedAltText);
        footer.activate();
        String testAltText = footer.getLogoAltText();
        assertEquals(expectedAltText, testAltText);
    }
	
	@Test
    public void testLogoUrl() throws Exception {
		String expectedLogoUrl = "#";
		properties.put("logoImageURL", expectedLogoUrl);
        footer.activate();
        String testLogoUrl = footer.getLogoURL();
        assertEquals(expectedLogoUrl, testLogoUrl);
    }
	
	@Test
    public void testPrivacyPolicyUrl() throws Exception {
		String expectedPolicyUrl = "#";
		properties.put("privacyPolicyUrl", expectedPolicyUrl);
        footer.activate();
        String testPolicyUrl = footer.getPrivacyPolicyUrl();
        assertEquals(expectedPolicyUrl, testPolicyUrl);
    }
	
	@Test
    public void testPrivacyPolicy() throws Exception {
		String expectedPrivacyPolicyText = "Test Privacy Policy";
		properties.put("privacyPolicyText", expectedPrivacyPolicyText);
        footer.activate();
        String testPrivacyPolicyText = footer.getPrivacyPolicyText();
        assertEquals(expectedPrivacyPolicyText, testPrivacyPolicyText);
    }
}
