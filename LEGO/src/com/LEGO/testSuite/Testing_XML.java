package com.LEGO.testSuite;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.AssertJUnit;
import com.LEGO.pages.StickyFooter;

public class Testing_XML {
	public static String example;
	public static String XPath;
    public static WebDriver driver;
    public Logger logger = Logger.getLogger("My Log");
	/*
	@BeforeTest
	public void Initialise() throws Exception {
		
		Base_Class BC = new Base_Class();
		BC.Set_up();
	}*/
	
    @DataProvider
    public Object[][] url()
    {
    	Object[][] url1=new Object[2][1];
    	
    	url1[0][0]="https://www.lego.com/en-us/architecture";
    	url1[1][0]="https://www.lego.com/en-us/juniors";
    	return url1;
    }
	
	@Test (dataProvider="url")
	//@Parameters ({"Architecture"})
	public void Verify_StickyFooter(String Site_url){
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		XPath = "//a[contains(@href,'" + example + "')]";
		logger.info("Calling Driver");
		driver = new FirefoxDriver();
		driver.get(Site_url);
		System.out.println("Callin StickyFooter");
		StickyFooter SF = new StickyFooter();
		SF.stickyFooter();
		AssertJUnit.assertTrue(SF.Result);
		
	}
	
	/*@Test (priority=2)
	@Parameters ({"Juniors"})
	public void Verify_StickyFooter1(String Juniors){
		example = Juniors.substring(Juniors.indexOf("https://") + 8);
		XPath = "//a[contains(@href,'" + example + "')]";
		logger.info("Calling Driver");
		driver = new FirefoxDriver();
		driver.get(Juniors);
		System.out.println("Callin StickyFooter1");
		StickyFooter SF = new StickyFooter();
		SF.stickyFooter();
		AssertJUnit.assertTrue(SF.Result);
		
	}*/
	
	
	/*@Test (priority=2,  dependsOnMethods="Verify_StickyFooter")
	public void Verify_Header()  {
		System.out.println("Calling HeaderFooter");
		Header_Footer HF = new Header_Footer();
		HF.fetch_url();
		AssertJUnit.assertTrue(HF.Result);
	}
	
	@Test (priority=3)
	public void Verify_BrokenLinks(){
		System.out.println("Calling BrokenLinks");
		Broken_link_Checker BL=new Broken_link_Checker();
		BL.fetch_url();
		AssertJUnit.assertTrue(BL.Result);
	}
	
	@Test (priority=4)
	public void Verify_LogoRedirect(){
		System.out.println("Calling Logo Redirect");
		Logo_Redirect LR=new Logo_Redirect();
		LR.fetch_url();
		AssertJUnit.assertTrue(LR.Result);
	}
	
	@Test (priority=5)
	public void Verify_SiteBranding(){
		System.out.println("Calling Site Branding");
		SiteBranding_Logo SB=new SiteBranding_Logo();
		SB.fetch_url();
		AssertJUnit.assertTrue(SB.Result);
	}*/
}
