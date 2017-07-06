package Automation;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class NotInLanguage 
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		NotInLanguage a =new NotInLanguage();
		try 
		{
			a.fetch_url();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public void fetch_url()throws Exception
    {	
		Select market;
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.webqa.lego.com/en-us/juniors");
		try
		{
			List<WebElement> list1= driver.findElements(By.xpath("//option"));
						
			for(int i=1;i<list1.size()-1;i++)
			{
				market = new Select(driver.findElement(By.className("market-selector")));
				market.selectByIndex(i);
				Boolean isPresent=driver.findElements(By.cssSelector(".not-in-language")).size()>0;
				if(isPresent)
				{
					System.out.println("Not in Language message is present on = "+ driver.getCurrentUrl());					
				}
				else
				{
					System.out.println("Page is available in this Locale = "+ driver.getCurrentUrl());
				}
				Thread.sleep(1000);
			}
			System.out.println("Crawl completed");
        }     
	    catch (Exception e)
        {
	    	e.printStackTrace();
        }
 	}
}
