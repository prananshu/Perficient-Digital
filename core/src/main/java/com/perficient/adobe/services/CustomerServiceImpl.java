package com.perficient.adobe.services;

import java.util.Iterator;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.sling.jcr.api.SlingRepository;

@Component(metatype = true, label = "Customer addition Service", description = "Cust add service component")
@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Reference
    private SlingRepository repository;
	
	Session session = null;

	@Override
	public void addCust(String firstName, String lastName, String workEmail) {
		
		try {
			session = repository.login(new SimpleCredentials("admin","admin".toCharArray()));
		
		
		Node node= session.getRootNode();
        Node content = node.getNode("content");
        Node digital = content.getNode("digital");
        
      //Determine if the content/digital/customer node exists
        Node customerRoot = null;
        int custRec = doesCustExist(digital);
                                                   
        //-1 means that content/customer does not exist
        if (custRec == -1)
            //content/customer does not exist -- create it
           customerRoot = digital.addNode("customer","sling:OrderedFolder");
        else
          //content/customer does exist -- retrieve it
          customerRoot = digital.getNode("customer");
                                        
          int custId = custRec+1; //assign a new id to the customer node
                             
          //Store content from the client JSP in the JCR
          Node customer = customerRoot.addNode("customer"+custId); 
             
	        customer.setProperty("First Name", firstName);
	        customer.setProperty("Last Name", lastName);
	        customer.setProperty("Work Email", workEmail);
	        customer.setPrimaryType("nt:unstructured");
	
	        session.save();
	        session.logout();
        
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * Determines if the digital/customer node exists 
	 * This method returns these values:
	 * -1 - if customer does not exist
	 * 0 - if digital/customer node exists; however, contains no children
	 * number - the number of children that the digital/customer node contains
	*/
	private int doesCustExist(Node digital)
	{
	    try
	    {
	        int index = 0 ; 
	        int childRecs = 0 ; 
	         
	    java.lang.Iterable<Node> custNode = JcrUtils.getChildNodes(digital, "customer");
	    Iterator it = custNode.iterator();
	              
	    //only going to be 1 digital/customer node if it exists
	    if (it.hasNext())
	        {
	        //Count the number of child nodes in digital/customer
	        Node customerRoot = digital.getNode("customer");
	        Iterable itCust = JcrUtils.getChildNodes(customerRoot); 
	        Iterator childNodeIt = itCust.iterator();
	             
	        //Count the number of customer child nodes 
	        while (childNodeIt.hasNext())
	        {
	            childRecs++;
	            childNodeIt.next();
	        }
	         return childRecs; 
	       }
	    else
	        return -1; //digital/customer does not exist
	    }
	    catch(Exception e)
	    {
	    e.printStackTrace();
	    }
	    return 0;
	 }
	}


