package com.LEGO.pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class Page_title extends Base_Page {
	int x = 0;
	public boolean Result;

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
				driver.get(links.get(j));
				URL url = new URL(links.get(j));
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				int statusCode = http.getResponseCode();

				if (statusCode == 200) {
					list = driver.findElements(By.xpath(XPath));

					for (int k = 0; k < list.size(); k++) {
						links.add(list.get(k).getAttribute("href"));
						Set<String> hs = new HashSet<>();
						hs.addAll(links);
						links.clear();
						links.addAll(hs);
					}

					String Title = driver.getTitle();

					/** Checking Title is valid or Not */
					if (Title.startsWith("$") || Title.startsWith("-") || Title.startsWith("https://")) {
						x = 1;
						Reporter.log("<font color='red'>Failed: </font>" + "Wrong Page Title= " + links.get(j) + " = "
								+ Title + "<br>");
					} else {
						Reporter.log("<font color='green'>Passed: </font>" + "Name= " + links.get(j) + " = " + Title
								+ "<br>");
					}
					Thread.sleep(2000);
				} else {
					x = 1;
					Reporter.log("<font color='red'>Failed: </font>" + "Page not found= " + links.get(j) + "<br>");
				}
			}

			System.out.println("Crawl Completed");
			// System.out.println(links.size());
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
