package Automation;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.log4testng.Logger;
import java.util.logging.Logger;
import com.LEGO.pages.Base_Page;

public class PageTitle extends Base_Page {
	public Logger logger= Logger.getLogger("My Log");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PageTitle a =new PageTitle();
		try {
			a.getLinks();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

	}
	
	public void getLinks()throws Exception
    {
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.webqa.lego.com/en-us/architecture");
		String Site_url= "https://www.webqa.lego.com/en-us/architecture";
	    ArrayList<String> links = new ArrayList<String>();
	    
	    
	        try {
	    	
	    	 List<WebElement> list = driver.findElements(By.xpath("//a[contains(@href,'www.webqa.lego.com/en-us/architecture')]"));
	    	for(int j=0;j<list.size();j++)
	    	    {  
	    		  links.add(list.get(j).getAttribute("href"));
	    		  Set<String> hs = new HashSet<>();
	    		  hs.addAll(links);
	    		  links.clear();
	    		  links.addAll(hs);
	    		}
	    	list.clear();
	    	
	        for(int j=0;j<links.size();j++)
	            {
	        	URL url = new URL(links.get(j));
		        HttpURLConnection http = (HttpURLConnection)url.openConnection();
		        int statusCode = http.getResponseCode();
	        	if (statusCode==200){
	        driver.get(links.get(j));
	       
	        example = Site_url.substring(Site_url.indexOf("com/") + 4);
	        String locale= example.substring(0,5);
	        
	        System.out.println(locale);
	        
	        // if (statusCode==200){
	        /*list = driver.findElements(By.xpath("//a[contains(@href,'www.webqa.lego.com/en-us/architecture')]"));
	    	for(int k=0;k<list.size();k++){  
	    		  links.add(list.get(k).getAttribute("href"));
	    		  Set<String> hs = new HashSet<>();
	    		  hs.addAll(links);
	    		  links.clear();
	    		  links.addAll(hs);
	    		}*/
	    	
//	    	String Title=driver.getTitle();
	        String Current_url=driver.getCurrentUrl();
	    	/*if(Title.startsWith("$")|| Title.startsWith("-"))
	    	{
	    		logger.info("Wrong Page Title= "+links.get(j)+" = "+Title);
	    	}  
	    	else
	        {
	        	System.out.println("Name= "+links.get(j)+" = "+Title);
	        }*/
	    	
	    	if(Current_url.contains("en-us"))
	    	{
	    		logger.info("Moving in same Locale");
	    	}  
	    	else
	        {
	        	System.out.println("Locale changed");
	        }
	    	Thread.sleep(2000);
	        
	        
	            }
	         else{
		        	System.out.println("Page not found= "+links.get(j));
		        }
	        
	            }  
	        System.out.println("Crawl Completed");
	        System.out.println(links.size()); 
	        
	        }
	        
	    catch (Exception e)
	        {
	        e.printStackTrace();
	        }
	  }

}
