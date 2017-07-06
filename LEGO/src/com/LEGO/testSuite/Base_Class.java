package com.LEGO.testSuite;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base_Class 
{
public static WebDriver driver;
public static List<WebElement> list;
public Logger logger = Logger.getLogger("My Log");
public Scanner sc = new Scanner(System.in);
public static String example;
public static String XPath;
public String url;

public void Set_up() throws Exception 
{
	/**Getting URLs from GetUrlList Method*/
	NodeList urlList = GetUrlList("url");
	for (int urlCount = 0; urlCount < urlList.getLength(); urlCount++) 
	{
		try
		{
			url = urlList.item(urlCount).getTextContent();
			System.out.println(url);
			example = url.substring(url.indexOf("https://") + 8);
			XPath = "//a[contains(@href,'" + example + "')]";
			logger.info("Calling Driver");
			driver = new FirefoxDriver();
			driver.get(url);
			
			/**Calling different test cases in Base Class to run on all URLs from XML file*/
			/*CheckGlobalHeader_Footer cghf=new CheckGlobalHeader_Footer();
			cghf.Verify_StickyFooter();
			cghf.Verify_Header();
			
			Broken_Links bl=new Broken_Links();
			bl.Verify_BrokenLinks();
		
			Site_Branding sb = new Site_Branding();
			sb.Verify_Site_Branding();
			
			Logo_redirect lr=new Logo_redirect();
			lr.Verify_Redirection();*/
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	// System.out.print("Enter the URL: ");
	// String s= sc.next();
	// driver = new FirefoxDriver();
	// driver.get(s);
}

/**Getting URL from XML file*/
public NodeList GetUrlList(String tagname) throws Exception
{
	// File fXmlFile = new File("/LEGO/sites.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse("sites.xml");
	doc.getDocumentElement().normalize();
	NodeList urlList = doc.getElementsByTagName(tagname);
	return urlList;
}
}