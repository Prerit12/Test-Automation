package com.LEGO.pages;

import org.openqa.selenium.By;
import org.testng.Reporter;

public class StickyFooter extends Base_Page {
	int x = 0;
	public boolean Result;
	public String Sticky_Xpath;
	public String text;

	/** Checking Sticky Footer on Home Page */
	public void stickyFooter() {

		try {
			Result = false;
			setORprop();
			Sticky_Xpath = prop_OR.getProperty("Sticky_Xpath");
			text = driver.findElement(By.xpath(Sticky_Xpath)).getText();
			Reporter.log(text + "<br>");

			if (text != null) {
				Reporter.log("<font color='green'>Passed: </font>"
						+ "Sticky Footer is visited first time or already visited<br>");
			} else {
				x = 1;
				Reporter.log("<font color='red'>Failed: </font>" + "Sticky Footer is not Present<br>");
				Result = false;
			}
			if (x == 0) {
				Result = true;
			} else {
				Result = false;
			}
		} catch (Exception e) {
			Result = false;
			e.printStackTrace();
		}
	}
}