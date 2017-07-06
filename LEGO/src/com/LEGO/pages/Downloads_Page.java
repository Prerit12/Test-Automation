package com.LEGO.pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class Downloads_Page extends Base_Page {
	int x = 0;
	ArrayList<String> links1 = new ArrayList<String>();
	public boolean Result;
	public String Download;

	public void fetch_url() {

		try {
			Result = false;
			List<WebElement> list1 = driver.findElements(By.xpath("//section/img"));
			for (int j = 0; j < list1.size(); j++) {
				// Reporter.log("Inside image loop");
				links1.add(list1.get(j).getAttribute("lego-lazy-load"));
				links1.get(j);

				/** Checking thumbnail images on overview page */
				if (links1.get(j) != null) {
					if (links1.get(j).startsWith("https")) {
						// Reporter.log(links1.get(j));
						URL url1 = new URL(links1.get(j));
						HttpURLConnection http = (HttpURLConnection) url1.openConnection();
						int statusCode = http.getResponseCode();
						if (statusCode == 200) {
							links1.get(j);
							// Reporter.log(statusCode+" =
							// "+links1.get(j));
						} else {
							x = 1;
							Reporter.log(
									"<font color='red'>Failed: </font>" + statusCode + " = " + links1.get(j) + "<br>");
						}
					} else {
						Reporter.log("Not valid url= " + links1.get(j) + "<br>");
					}
				} else {
					// Reporter.log("Null url");
				}
			}

			/** Checking Downloads on internal Pages */
			setORprop();
			Download = prop_OR.getProperty("Download");

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
				driver.get(links.get(j));
				URL url = new URL(links.get(j));
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				int statusCode = http.getResponseCode();
				Reporter.log("name" + "=" + links.get(j) + "<br>");
				if (statusCode == 200) {
					list = driver.findElements(By.xpath(XPath));
					for (int k = 0; k < list.size(); k++) {
						links.add(list.get(k).getAttribute("href"));
						Set<String> hs = new HashSet<>();
						hs.addAll(links);
						links.clear();
						links.addAll(hs);
					}
					/** Checking Download is Present or not */
					Boolean isPresent = driver.findElements(By.xpath(Download)).size() > 0;

					if (isPresent) {
						String src = driver.findElement(By.xpath(Download)).getAttribute("href");
						URL url1 = new URL(src);
						http = (HttpURLConnection) url1.openConnection();
						statusCode = http.getResponseCode();
						if (statusCode == 200) {
							Reporter.log("<font color='green'>Passed: </font>" + links.get(j)
									+ " = Download is working<br>");
						} else {
							x = 1;
							Reporter.log("<font color='red'>Failed: </font>" + links.get(j)
									+ " = Download is not working<br>");
						}
					} else {
						Reporter.log("<font color='red'>Failed: </font>" + "Download button is not present= "
								+ links.get(j) + "<br>");
					}
					Thread.sleep(2000);
					// Reporter.log("name" + "=" + links.get(j));
				} else {
					x = 1;
					Reporter.log("<font color='red'>Failed: </font>" + "Page not found= " + links.get(j) + "<br>");
				}
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