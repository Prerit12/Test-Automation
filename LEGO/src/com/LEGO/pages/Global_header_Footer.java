package com.LEGO.pages;

import org.openqa.selenium.By;

public class Global_header_Footer extends Base_Page {
	public String GlobalHeader;
	public String GlobalFooter;
	public String Marketselector;
	public String Global_Nav;
	public String Policy;
	public String Sticky_Xpath;
	
	/** Checking Global Header and Footer */
	public boolean header_footer() throws Exception {
		
		/** Setting Or Properties */
		setORprop();
		GlobalHeader = prop_OR.getProperty("GlobalHeader");
		GlobalFooter = prop_OR.getProperty("GlobalFooter");

		String s = driver.findElement(By.className(GlobalHeader)).toString();
		String s1 = driver.findElement(By.className(GlobalFooter)).toString();

		if (s.contains("lego-global-header-outer-wrap") && s1.contains("lego-global-footer")) {
			// Reporter.log("Global Header and Footer Found");
			return true;
		} else {
			// Reporter.log("Global Footer and Header Not found");
			return false;
		}
	}

	/** Checking Market Selector */
	public boolean MarketSelector() throws Exception {
		/** Setting Or Properties */
		setORprop();
		Marketselector = prop_OR.getProperty("Marketselector");

		String ms = driver.findElement(By.className(Marketselector)).toString();
		// Reporter.log(ms);

		if (ms.contains("market-selector")) {
			// Reporter.log("Market Selector Found");
			return true;
		}

		else {
			// Reporter.log("Market Selector Not found");
			return false;
		}
	}

	/** Checking Global Navigation */
	public boolean GlobalNav() throws Exception {
		/** Setting Or Properties */
		setORprop();
		Global_Nav = prop_OR.getProperty("Global_Nav");

		String gn = driver.findElement(By.className(Global_Nav)).toString();
		// Reporter.log(gn);

		if (gn.contains("lego-global-navigation")) {
			// Reporter.log("Global Navigation Found");
			return true;
		}

		else {
			// Reporter.log("Global Navigation Not found");
			return false;
		}
	}

	/** Checking Privacy Policy Cookies */
	public boolean Privacy() throws Exception {
		/** Setting Or Properties */
		setORprop();
		Policy = prop_OR.getProperty("Policy");

		String p = driver.findElement(By.id(Policy)).toString();
		// Reporter.log(p);

		if (p.contains("GFSpplink")) {
			// Reporter.log("Privacy Policy Found");
			return true;
		}

		else {
			// Reporter.log("Privacy Policy Not found");
			return false;
		}
	}
}