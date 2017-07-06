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

public class FirstTestCase
{
	public static void main(String args[]) {
		FirstTestCase a= new FirstTestCase();
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
			driver.findElement(By.className("modal-footer")).click();
		    ArrayList<String> links = new ArrayList<String>();
		    
		        try {
		    	
		    	 List<WebElement> list = driver.findElements(By.xpath("//a[contains(@href,'www.webqa.lego.com/en-us/architecture/')]"));
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
		        driver.get(links.get(j));
		       
		        URL url = new URL(links.get(j));
		        HttpURLConnection http = (HttpURLConnection)url.openConnection();
		        int statusCode = http.getResponseCode();
		        System.out.println(statusCode);
		        
		         list = driver.findElements(By.xpath("//a[contains(@href,'www.webqa.lego.com/en-us/architecture/')]"));
		    	for(int k=0;k<list.size();k++){  
		    		  links.add(list.get(k).getAttribute("href"));
		    		  Set<String> hs = new HashSet<>();
		    		  hs.addAll(links);
		    		  links.clear();
		    		  links.addAll(hs);
		    		}
		        Thread.sleep(5000);
		        System.out.println("name" + "=" + links.get(j));
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
