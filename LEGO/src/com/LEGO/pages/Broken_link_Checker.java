package com.LEGO.pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class Broken_link_Checker extends Base_Page {
	int x = 0;
	public boolean Result;
	static {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
	}
	// public Logger logger = Logger.getLogger("Broken_link_Checker");

	/** fetching all urls of Site */
	public void fetch_url() {
		// Logger logger = Logger.getLogger("Broken_link_Checker");
		try {
			// Reporter.log("Inside fettch URL");
			Result = false;
			List<WebElement> list = driver.findElements(By.xpath(XPath));
			// Reporter.log(XPath);
			for (int j = 0; j < list.size(); j++) {
				links.add(list.get(j).getAttribute("href"));
				Set<String> hs = new HashSet<>();
				hs.addAll(links);
				links.clear();
				links.addAll(hs);
			}
			list.clear();

			/** Checking Response Code for Page */
			for (int j = 0; j < links.size(); j++) {
				// Reporter.log("Inside response code");
				URL url = new URL(links.get(j));
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				int statusCode = http.getResponseCode();
				// Reporter.log(statusCode);

				if (statusCode == 200) {
					driver.get(links.get(j));
					list = driver.findElements(By.xpath(XPath));

					for (int k = 0; k < list.size(); k++) {
						links.add(list.get(k).getAttribute("href"));
						Set<String> hs = new HashSet<>();
						hs.addAll(links);
						links.clear();
						links.addAll(hs);
					}
					Reporter.log("<font color='green'>Passed: </font>name" + " = " + links.get(j) + " = " + statusCode
							+ "<br>");
				}

				else {
					x = 1;
					Reporter.log("<font color='red'>Failed: </font>name" + " = " + links.get(j) + " = " + statusCode
							+ "<br>");
					Result = false;
				}
				Thread.sleep(2000);
			}

			Reporter.log("Crawl Completed<br>");
			// Reporter.log(links.size());
			if (x == 0) {
				Result = true;
			} else {
				Result = false;
			}
		}

		catch (Exception e) {
			Result = false;
			e.printStackTrace();
		}
	}
}
