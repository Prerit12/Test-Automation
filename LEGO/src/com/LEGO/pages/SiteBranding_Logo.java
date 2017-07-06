package com.LEGO.pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class SiteBranding_Logo extends Base_Page {
	int x = 0;
	public boolean Result;
	public String SiteBranding;
	public String Branding_path;

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
				setORprop();
				Branding_path = prop_OR.getProperty("Branding_path");
				SiteBranding = driver.findElement(By.className(Branding_path)).toString();
				// System.out.println(SiteBranding);
				URL url = new URL(links.get(j));
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				int statusCode = http.getResponseCode();

				if (statusCode == 200) {
					driver.get(links.get(j));

					/** Checking Site Branding Logo is present or Not */
					if (SiteBranding.contains("site-branding")) {
						list = driver.findElements(By.xpath(XPath));

						for (int k = 0; k < list.size(); k++) {
							links.add(list.get(k).getAttribute("href"));
							Set<String> hs = new HashSet<>();
							hs.addAll(links);
							links.clear();
							links.addAll(hs);
						}
						Reporter.log("<font color='green'>Passed: </font>" + "name" + " = " + links.get(j)
								+ " = Site Branding Present<br>");
					} else {
						x = 1;
						Reporter.log("<font color='red'>Failed: </font>" + "name" + " = " + links.get(j)
								+ " = Site Branding not Present<br>");
					}
				}

				else {
					x = 1;
					Reporter.log("<font color='red'>Failed: </font>" + "name" + " = " + links.get(j) + " = "
							+ statusCode + "<br>");
				}
				Thread.sleep(2000);
			}
			Reporter.log("Crawl Completed<br>");
			// System.out.println(links.size());
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