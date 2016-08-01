package com.perficient.adobe.digital.core.sightly;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.perficient.adobe.digital.core.sightly.components.ContactUsCo;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ ContactUsCo.class })
public class ContactUsCoTest {
private ValueMap properties;
	
	@Mock
	private ContactUsCo contact;
	
	@Before
	  public void setup() {
		properties = new ValueMapDecorator(new HashMap<String, Object>());
		when(contact.getEmailRecepients()).thenReturn("p.palandurkar@perficient.com");
		when(contact.getContactTitle()).thenReturn("Contact US");
		when(contact.getReceiveMailText()).thenReturn("Test Receive Mail Text");
	  }
	
	@Test
    public void testSlogan() throws Exception {
		String expectedRecipients = "p.palandurkar@perficient.com";
		properties.put("emailRecipents", expectedRecipients);
		contact.activate();
        String testRecipients = contact.getEmailRecepients();
        assertEquals(expectedRecipients, testRecipients);
    }
	
	@Test
    public void testContactUsTitle() throws Exception {
		String expectedContactUsTitle = "Contact US";
		properties.put("contactusTitle", expectedContactUsTitle);
		contact.activate();
        String testContactUsTitle = contact.getContactTitle();
        assertEquals(expectedContactUsTitle, testContactUsTitle);
    }
	
	@Test
    public void testReceiveMailText() throws Exception {
		String expectedReceiveMailText = "Test Receive Mail Text";
		properties.put("receiveMailText", expectedReceiveMailText);
		contact.activate();
        String testReceiveMailText = contact.getReceiveMailText();
        assertEquals(expectedReceiveMailText, testReceiveMailText);
    }
}
