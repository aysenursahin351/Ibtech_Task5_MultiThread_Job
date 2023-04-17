package net.ibtech.hibernate.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import net.ibtech.hibernate.dao.CustomerDao;
import net.ibtech.hibernate.model.Customer;
import net.ibtech.hibernate.xbag.XBag;
import net.ibtech.hibernate.xbag.XBagKey;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XML_Parser {

	public String parseCommandName(String xmlType) {
	    String commandName = "";
	    try {
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document document = dBuilder.parse(new File(xmlType + ".xml"));
	        document.getDocumentElement().normalize();
	        commandName = document.getElementsByTagName("commandName").item(0).getTextContent();
	    } catch (ParserConfigurationException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return commandName;
	}

	public XBag parseCustomer(String xmlType) {
	    XBag xbag = null;
	    try {
	        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document document = dBuilder.parse(new File(xmlType + ".xml"));
	        document.getDocumentElement().normalize();
	        Node customerNode = document.getElementsByTagName("Customer").item(0);
	        if (customerNode.getNodeType() == Node.ELEMENT_NODE) {
	            Element customerElement = (Element) customerNode;
	            int id = Integer.parseInt(customerElement.getAttribute("id"));
	            String firstName = customerElement.getAttribute("firstName");
	            String lastName = customerElement.getAttribute("lastName");
	            String email = customerElement.getAttribute("email");
	            xbag = new XBag();
	            xbag.add(XBagKey.FIRSTNAME, firstName);
	            xbag.add(XBagKey.LASTNAME, lastName);
	            xbag.add(XBagKey.EMAIL, email);
	            xbag.add(XBagKey.CUSTOMER_ID, id);

	        }
	    } catch (ParserConfigurationException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return xbag;
	}

    
}
