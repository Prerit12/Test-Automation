package com.LEGO.testSuite;

import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.*;
import com.LEGO.pages.Header_Footer;
import com.LEGO.pages.StickyFooter;

public class CheckGlobalHeader_Footer {
	public static String example;
	public static String XPath;
	public static WebDriver driver;
	public Logger logger = Logger.getLogger("My Log");

	@BeforeTest
	public void Initialise() throws Exception {

		Base_Class BC = new Base_Class();
		BC.Set_up();
	}

	/** Calling Sticky Footer test Case for all URL's */
	@Test(priority = 1)
	public void Verify_StickyFooter() {
		System.out.println("=======Callin StickyFooter Test Case =======");
		StickyFooter SF = new StickyFooter();
		SF.stickyFooter();
		AssertJUnit.assertTrue(SF.Result);
		driver.close();
	}

	/** Calling Global Features test Case for all URL's */
	@Test(priority = 2, dependsOnMethods = "Verify_StickyFooter")
	public void Verify_Header() {
		System.out.println("=======Callin Header Footer Test Case =======");
		Header_Footer HF = new Header_Footer();
		HF.fetch_url();
		AssertJUnit.assertTrue(HF.Result);
		driver.close();
	}
}