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

public class Downloads
{

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			Downloads a =new Downloads();
			try {
				a.getLinks();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

		}
		
		public void getLinks()throws Exception
	    {	
			int statusCode;
			HttpURLConnection http;
			String src;
			WebDriver driver = new FirefoxDriver();
			driver.get("https://www.webqa.lego.com/en-us/juniors/activities");
			//driver.findElement(By.className("modal-footer")).click();
		    ArrayList<String> links = new ArrayList<String>();
		    //List<WebElement> list1;
		    ArrayList<String> links1 = new ArrayList<String>();
		    
		        try {
		    	List<WebElement> list1=driver.findElements(By.xpath("//section/img"));
		    	for(int j=0;j<list1.size();j++)
	    	    {  System.out.println(list1.size());
		    		links1.add(list1.get(j).getAttribute("lego-lazy-load"));
		    		links1.get(j);
		    		System.out.println(links1.get(j));
	    		  	//src= driver.findElement(By.xpath("//section/img")).getAttribute("src");
		    		if(links1.get(j)!=null)
		    		{
		    		if(links1.get(j).startsWith("https")&&links1.get(j)!=null)
		    		{
			    	System.out.println(links1.get(j));
			    	URL url1=new URL(links1.get(j));
			    	http = (HttpURLConnection)url1.openConnection();
			    	statusCode = http.getResponseCode();
			    	//links1.get(j);
	    		  System.out.println(statusCode);
		    		}
		    		else{
		    			System.out.println("Not valid url"+links1.get(j));
		    		}
	    		}
		    		else
		    		{
		    			System.out.println("Null url");
		    		}
	    	    }
		    	System.out.println("Finished first loop");
		    	 List<WebElement> list = driver.findElements(By.xpath("//a[contains(@href,'www.webqa.lego.com/en-us/juniors/activities')]"));
		    	System.out.println("Going in Next loop");
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
		         http = (HttpURLConnection)url.openConnection();
		         statusCode = http.getResponseCode();
		        System.out.println(statusCode);
		        //JavascriptExecutor js = (JavascriptExecutor) driver;
		         
		        list = driver.findElements(By.xpath("//a[contains(@href,'www.webqa.lego.com/en-us/juniors/activities')]"));
		    	for(int k=0;k<list.size();k++){  
		    		  links.add(list.get(k).getAttribute("href"));
		    		  Set<String> hs = new HashSet<>();
		    		  hs.addAll(links);
		    		  links.clear();
		    		  links.addAll(hs);
		    		}
		    	
		    Boolean isPresent = driver.findElements(By.xpath("//a[contains(@class,'btn-primary')]")).size() > 0;
		    	System.out.println(isPresent);
		    	if(isPresent)
		    	{
		    		 src= driver.findElement(By.xpath("//a[contains(@class,'btn-primary')]")).getAttribute("href");
			    	System.out.println(src);
			    	//driver.findElement(By.cssSelector(".primary-content.aspect-wrapper")).click();
			    	URL url1=new URL(src);
			    	http = (HttpURLConnection)url1.openConnection();
			    	statusCode = http.getResponseCode();
			        if(statusCode==200)
			        {
			    	System.out.println(statusCode+" = Download is working");
			        }
			        else
			        {
			        	System.out.println("Download is not working= "+links.get(j));
			        }
		    	}  
		    	else
		        {
		        	System.out.println("Download is not working= "+links.get(j));
		        }
		    	Thread.sleep(2000);
		        System.out.println("name" + "=" + links.get(j));
		        
		         
		            }
		        System.out.println("Crawl Completed");
		        System.out.println(links.size()); 
		        driver.close();
		            }     
		    catch (Exception e)
		        {
		        e.printStackTrace();
		        }
		  }

	}



