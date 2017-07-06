package Automation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class sitesurl 

{public static WebDriver driver;
	// TODO Auto-generated constructor stub
public static void main (String args[]) throws Exception{
	sitesurl s=new sitesurl();
	
s.fetchUrl();
}	


public NodeList GetUrlList(String tagname) throws Exception {
	//File fXmlFile = new File("/LEGO/sites.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse("sites.xml");
	doc.getDocumentElement().normalize();
	NodeList urlList = doc.getElementsByTagName(tagname);
	return urlList;
}

public void fetchUrl() throws Exception{
	NodeList urlList = GetUrlList("url");
	for (int urlCount = 0; urlCount < urlList.getLength(); urlCount++) {
		try {

			String url = urlList.item(urlCount).getTextContent();
			System.out.println(url);
			driver = new FirefoxDriver();
			driver.get(url);
		}
		catch (Exception e){
			e.printStackTrace();
		}
}
}
}