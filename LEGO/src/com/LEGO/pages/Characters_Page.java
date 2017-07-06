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

public class Characters_Page extends Base_Page {
	public boolean Result;
	public String Character;

	public void fetch_url() {
		int x = 0;
		int statusCode;
		HttpURLConnection http;
		List<WebElement> list2;
		ArrayList<String> links1 = new ArrayList<String>();
		ArrayList<String> links2 = new ArrayList<String>();

		try {
			Result = false;
			setORprop();
			Character = prop_OR.getProperty("Character");
			List<WebElement> list1 = driver.findElements(By.xpath(Character));

			/** Checking images on Overview Page */
			for (int j = 0; j < list1.size(); j++) {
				links1.add(list1.get(j).getAttribute("lego-lazy-load"));
				links1.get(j);
				if (links1.get(j) != null) {
					if (links1.get(j).startsWith("https")) {
						URL url1 = new URL(links1.get(j));
						http = (HttpURLConnection) url1.openConnection();
						statusCode = http.getResponseCode();
						Reporter.log(
								"<font color='blue'>Status: </font>" + statusCode + " = " + links1.get(j) + "<br>");
					} else {
						Reporter.log("Not valid url" + links1.get(j) + "<br>");
					}
				} else {
					Reporter.log("Null url<br>");
				}
			}

			/** Moving to internal Pages and check for images on detail pages */
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
				http = (HttpURLConnection) url.openConnection();
				statusCode = http.getResponseCode();
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

					/** Checking images on Detail pages */
					Boolean isPresent = driver.findElements(By.xpath(Character)).size() > 0;

					if (isPresent) {

						list2 = driver.findElements(By.xpath(Character));

						for (int k = 0; k < list2.size(); k++) {
							links2.add(list2.get(k).getAttribute("lego-lazy-load"));
							links2.get(k);

							if (links2.get(k) != null) {

								if (links2.get(k).startsWith("https")) {
									URL url1 = new URL(links2.get(k));
									http = (HttpURLConnection) url1.openConnection();
									statusCode = http.getResponseCode();
									if (statusCode == 200) {
										// Reporter.log(statusCode+" =
										// "+links2.get(k));
									} else {
										x = 1;
										Reporter.log("<font color='red'>Failed: </font>" + statusCode + " = "
												+ links2.get(k));
									}
								} else {
									// Reporter.log("Not valid
									// url"+links1.get(k));
								}
							} else {
								// Reporter.log("Null url");
							}
						}

						Thread.sleep(1000);

					} else {
						x = 1;
						Reporter.log("<font color='red'>Failed: </font>" + "Character detais is missing = "
								+ links1.get(j) + "<br>");
					}
				} else {
					x = 1;
					Reporter.log("<font color='red'>Failed: </font>" + "Page not found= " + links.get(j) + "<br>");
				}
			}
			Reporter.log("Crawl Completed<br>");
			System.out.println(links.size());

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