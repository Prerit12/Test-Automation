package com.LEGO.testSuite;

import java.net.HttpURLConnection;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.AssertJUnit;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.LEGO.pages.Broken_link_Checker;
import com.LEGO.pages.Characters_Page;
import com.LEGO.pages.Downloads_Page;
import com.LEGO.pages.Galleries_SeeMore;
import com.LEGO.pages.Galleries_comments;
import com.LEGO.pages.Galleries_like;
import com.LEGO.pages.Galleries_upload;
import com.LEGO.pages.Header_Footer;
import com.LEGO.pages.Locale_Redirect;
import com.LEGO.pages.Logo_Redirect;
import com.LEGO.pages.NotInLanguage;
import com.LEGO.pages.Page_title;
import com.LEGO.pages.Products_page;
import com.LEGO.pages.SiteBranding_Logo;
import com.LEGO.pages.StickyFooter;
import com.LEGO.pages.Videos_Page;

/** Running all Test Cases on Different Sites using Data Provider */
public class Running_AlltestCase {
	public static String example;
	public static String locale;
	public static String example1;
	public static String XPath;
	public static WebDriver driver;
	

	/** Enter Site URL's Here */
	@DataProvider
	public Object[][] url() {
		Object[][] url1 = new Object[2][1];
		url1[0][0] = "https://www.webqa.lego.com/en-us/architecture";
		url1[1][0] = "https://www.webqa.lego.com/en-us/angrybirdsmovie";
		return url1;
	}

	/** Calling Sticky Footer test Case for all URL's */
	@Test(priority = 1, dataProvider = "url")
	public void Verify_StickyFooter(String Site_url) {
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		XPath = "//a[contains(@href,'" + example + "')]";
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		driver.get(Site_url);
		Reporter.log("=======Calling StickyFooter Test Case= " + Site_url + " ======= <br>");
		StickyFooter SF = new StickyFooter();
		SF.stickyFooter();
		AssertJUnit.assertTrue(SF.Result);
		driver.close();
	}

	/** Calling Global Features test Case for all URL's */
	@Test(priority = 2, dependsOnMethods = "Verify_StickyFooter", dataProvider = "url")
	public void Verify_Header(String Site_url) {
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		XPath = "//a[contains(@href,'" + example + "')]";
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		driver.get(Site_url);
		Reporter.log("=======Calling Header Footer Test Case= " + Site_url + " ======= <br>");
		Header_Footer HF = new Header_Footer();
		HF.fetch_url();
		AssertJUnit.assertTrue(HF.Result);
		driver.close();
	}

	/** Calling Broken Links test Case for all URL's */
	@Test(priority = 3, dataProvider = "url")
	public void Verify_BrokenLinks(String Site_url) {
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		XPath = "//a[contains(@href,'" + example + "')]";
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		driver.get(Site_url);
		Reporter.log("=======Calling Broken Links Test Case= " + Site_url + " ======= <br>");
		Broken_link_Checker BL = new Broken_link_Checker();
		BL.fetch_url();
		AssertJUnit.assertTrue(BL.Result);
		driver.close();
	}

	/** Calling Logo Redirect test Case for all URL's */
	@Test(priority = 4, dataProvider = "url")
	public void Verify_LogoRedirect(String Site_url) {
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		XPath = "//a[contains(@href,'" + example + "')]";
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		driver.get(Site_url);
		Reporter.log("=======Calling Logo Redirect Test Case= " + Site_url + " ======= <br>");
		Logo_Redirect LR = new Logo_Redirect();
		LR.fetch_url();
		AssertJUnit.assertTrue(LR.Result);
		driver.close();
	}

	/** Calling Site Branding test Case for all URL's */
	@Test(priority = 5, dataProvider = "url")
	public void Verify_SiteBranding(String Site_url) {
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		XPath = "//a[contains(@href,'" + example + "')]";
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		driver.get(Site_url);
		Reporter.log("=======Calling Site Branding Test Case= " + Site_url + " ======= <br>");
		SiteBranding_Logo SB = new SiteBranding_Logo();
		SB.fetch_url();
		AssertJUnit.assertTrue(SB.Result);
		driver.close();
	}

	/** Calling Video Page Test Case */
	@Test(priority = 6, dataProvider = "url")
	public void Verify_VideosPage(String Site_url) {
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		XPath = "//a[contains(@href,'" + example + "/videos')]";
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		driver.get(Site_url + "/videos");
		Reporter.log("=======Calling Videos Page Test Case= " + Site_url + " ======= <br>");
		Videos_Page VP = new Videos_Page();
		VP.fetch_url();
		AssertJUnit.assertTrue(VP.Result);
		driver.close();
	}

	/** Calling Page Title Test Case */
	@Test(priority = 7, dataProvider = "url")
	public void Verify_Page_Title(String Site_url) {
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		XPath = "//a[contains(@href,'" + example + "')]";
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		driver.get(Site_url);
		Reporter.log("=======Calling Page Title Test Case= " + Site_url + " ======= <br>");
		Page_title PT = new Page_title();
		PT.fetch_url();
		AssertJUnit.assertTrue(PT.Result);
		driver.close();
	}

	/** Calling Locale Redirect Test Case */
	@Test(priority = 8, dataProvider = "url")
	public void Verify_LocaleRedirect(String Site_url) {
		example1 = Site_url.substring(Site_url.indexOf("com/") + 4);
		locale = example1.substring(0, 5);
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		XPath = "//a[contains(@href,'" + example + "')]";
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		driver.get(Site_url);
		Reporter.log("=======Calling Locale Redirect Test Case= " + Site_url + " ======= <br>");
		Locale_Redirect LR = new Locale_Redirect();
		LR.fetch_url();
		AssertJUnit.assertTrue(LR.Result);
		driver.close();
	}

	/** Calling Download Page Test Case */
	@Test(priority = 9, dataProvider = "url")
	public void Verify_DownloadsPage(String Site_url) {
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		XPath = "//a[contains(@href,'" + example + "/activities')]";
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		driver.get(Site_url + "/activities");
		Reporter.log("=======Calling Downloads Page Test Case= " + Site_url + " ======= <br>");
		Downloads_Page DP = new Downloads_Page();
		DP.fetch_url();
		AssertJUnit.assertTrue(DP.Result);
		driver.close();
	}

	/** Calling Not In Language Test Case */
	@Test(priority = 10, dataProvider = "url")
	public void Verify_Not_in_language(String Site_url) {
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		driver.get(Site_url);
		Reporter.log("=======Calling Not in Language Test Case= " + Site_url + " ======= <br>");
		NotInLanguage NL = new NotInLanguage();
		NL.fetch_url();
		AssertJUnit.assertTrue(NL.Result);
		driver.close();
	}

	/** Calling Characters Page Test Case */
	@Test(priority = 11, dataProvider = "url")
	public void Verify_CharactersPage(String Site_url) throws Exception {
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		XPath = "//a[contains(@href,'" + example + "/characters')]";
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		URL url1 = new URL(Site_url + "/characters");
		HttpURLConnection http = (HttpURLConnection) url1.openConnection();
		int statusCode = http.getResponseCode();
		if (statusCode == 200) {
			driver.get(Site_url + "/characters");
			Reporter.log("=======Calling Characters Page Test Case= " + Site_url + " ======= <br>");
			Characters_Page CP = new Characters_Page();
			CP.fetch_url();
			AssertJUnit.assertTrue(CP.Result);
			driver.close();
		} else {
			Reporter.log("Characters page is not present on site" + Site_url);
			driver.close();
		}
	}

	/** Calling Products Page Test Case */
	@Test(priority = 12, dataProvider = "url")
	public void Verify_ProductsPage(String Site_url) {
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		XPath = "//a[contains(@href,'" + example + "/products')]";
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		driver.get(Site_url + "/products");
		Reporter.log("=======Calling Products Page Test Case= " + Site_url + " ======= <br>");
		Products_page PP = new Products_page();
		PP.fetch_url();
		AssertJUnit.assertTrue(PP.Result);
		driver.close();
	}

	/** Calling Galleries Upload Test Case */
	@Test(priority = 13, dataProvider = "url")
	public void Verify_UploadPage(String Site_url) throws Exception {
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		// XPath = "//a[contains(@href,'" + example + "/galleries/upload')]";
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		URL url1 = new URL(Site_url + "/galleries/upload");
		HttpURLConnection http = (HttpURLConnection) url1.openConnection();
		int statusCode = http.getResponseCode();
		if (statusCode == 200) {
			driver.get(Site_url + "/galleries/upload");
			Reporter.log("=======Calling Upload Page Test Case= " + Site_url + " ======= <br>");
			Galleries_upload GU = new Galleries_upload();
			GU.fetch_url();
			AssertJUnit.assertTrue(GU.Result);
			driver.close();
		} else {
			Reporter.log("Galleries page is not present on site" + Site_url+"<br>");
			driver.close();
		}
	}
	
	/** Calling Galleries Like Test Case */
	@Test(priority = 14, dataProvider = "url")
	public void Verify_LikePage(String Site_url) throws Exception {
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		 XPath = "//a[contains(@href,'" + example + "/galleries/')]";
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		URL url1 = new URL(Site_url + "/galleries");
		HttpURLConnection http = (HttpURLConnection) url1.openConnection();
		int statusCode = http.getResponseCode();
		if (statusCode == 200) {
			driver.get(Site_url + "/galleries");
			Reporter.log("=======Calling Like Page Test Case= " + Site_url + " ======= <br>");
			Galleries_like GL = new Galleries_like();
			GL.fetch_url();
			AssertJUnit.assertTrue(GL.Result);
			driver.close();
		} else {
			Reporter.log("Galleries page is not present on site" + Site_url+"<br>");
			driver.close();
		}
	}
	
	/** Calling Galleries SeeMore Test Case */
	@Test(priority = 15, dataProvider = "url")
	public void Verify_SeeMore(String Site_url) throws Exception {
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		 XPath = "//a[contains(@href,'" + example + "/galleries/')]";
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		URL url1 = new URL(Site_url + "/galleries");
		HttpURLConnection http = (HttpURLConnection) url1.openConnection();
		int statusCode = http.getResponseCode();
		if (statusCode == 200) {
			driver.get(Site_url + "/galleries");
			Reporter.log("=======Calling SeeMore Test Case= " + Site_url + " ======= <br>");
			Galleries_SeeMore GS = new Galleries_SeeMore();
			GS.fetch_url();
			AssertJUnit.assertTrue(GS.Result);
			driver.close();
		} else {
			Reporter.log("Galleries page is not present on site" + Site_url+"<br>");
			driver.close();
		}
	}
	
	/** Calling Galleries Comments Test Case */
	@Test(priority = 16, dataProvider = "url")
	public void Verify_Coments(String Site_url) throws Exception {
		example = Site_url.substring(Site_url.indexOf("https://") + 8);
		 XPath = "//a[contains(@href,'" + example + "/galleries/')]";
		//Reporter.log("Calling Driver");
		driver = new FirefoxDriver();
		URL url1 = new URL(Site_url + "/galleries");
		HttpURLConnection http = (HttpURLConnection) url1.openConnection();
		int statusCode = http.getResponseCode();
		if (statusCode == 200) {
			driver.get(Site_url + "/galleries");
			Reporter.log("=======Calling Galleries Comments Test Case= " + Site_url + " ======= <br>");
			Galleries_comments GC = new Galleries_comments();
			GC.fetch_url();
			AssertJUnit.assertTrue(GC.Result);
			driver.close();
		} else {
			Reporter.log("Galleries page is not present on site" + Site_url+"<br>");
			driver.close();
		}
	}
	
}
