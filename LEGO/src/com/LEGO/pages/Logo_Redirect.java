package com.LEGO.pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class Logo_Redirect extends Base_Page {
	int x = 0;
	public boolean Result;
	public String red;
	public String Redirect;

	/** fetching URL of Site */
	public void fetch_url() {

		try {
			Result = false;
			List<WebElement> list = driver.findElements(By.xpath(XPath));
			for (int j = 0; j < list.size(); j++) {
				links.add(list.get(j).getAttribute("href"));
				Set<String> hs = new HashSet<>();
				hs.addAll(links);
				links.clear();
				links.addAll(hs);
			}
			list.clear();

			for (int j = 0; j < links.size(); j++) {
				URL url = new URL(links.get(j));
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				int statusCode = http.getResponseCode();

				/**
				 * Checking Redirect on URL that are present on home page only
				 */
				if (statusCode == 200) {
					driver.get(links.get(j));
					setORprop();
					Redirect = prop_OR.getProperty("Redirect");
					red = driver.findElement(By.xpath(Redirect)).getAttribute("href");
					Thread.sleep(2000);
					// Reporter.log(red);
					driver.get(red);
				}

				else {
					x = 1;
					Reporter.log("<font color='red'>Failed: </font>" + "name = " + links.get(j) + "page not found");
				}
				if (x == 0) {
					Result = true;
				} else {
					Result = false;
				}
			}
		}

		catch (Exception e) {
			Result = false;
			e.printStackTrace();
		}
	}
}