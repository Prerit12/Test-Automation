package com.LEGO.pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class Locale_Redirect extends Base_Page {
	int x = 0;

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
				if (statusCode == 200) {
					driver.get(links.get(j));
					String Current_url = driver.getCurrentUrl();
					if (Current_url.contains(locale)) {
						Reporter.log(
								"<font color='green'>Passed: </font>" + "Moving in same Locale = " + locale + "<br>");
					} else {
						x = 1;
						Reporter.log("<font color='red'>Failed: </font>" + links.get(j) + " = Locale changed<br>");
					}
					Thread.sleep(2000);

				} else {
					x = 1;
					Reporter.log("<font color='red'>Failed: </font>" + "Page not found= " + links.get(j) + "<br>");
				}
			}
			Reporter.log("Crawl Completed<br>");
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
