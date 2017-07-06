package Automation;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;



public class Global_header {
	public static void main (String args[]){
		
		
		
Global_header a= new Global_header();
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
   	for(int j=0; j<links.size();j++){
   		driver.get(links.get(j));
   		String s= driver.findElement(By.className("lego-global-header-outer-wrap")).toString();
   		String s1= driver.findElement(By.className("lego-global-footer")).toString();
   		System.out.println(s1);
   		if (s.contains("lego-global-header-outer-wrap") && s1.contains("lego-global-footer")){
   			System.out.println("Global Header Found");
   			
   		}
   		else {
   			System.out.println(links.get(j) + "="+ "Global and Footer Header Not found");
   		}
   	}
       }
   	catch (Exception e){
   		e.printStackTrace();
   	}
       }
}
