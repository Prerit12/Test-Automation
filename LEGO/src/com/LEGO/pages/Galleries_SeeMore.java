package com.LEGO.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class Galleries_SeeMore extends Base_Page {

	public void fetch_url() {

		ArrayList<String> links = new ArrayList<String>();
		try {
			int x = 0;
			Result = false;
			String s = driver.getCurrentUrl();
			Boolean popup = driver.findElements(By.id("IPEbgCover124035")).size() > 0;
			if (popup) {
				driver.get(s);
			}
			Reporter.log(s + "<br>");
			if (s.contains("galleries/")) {
				Reporter.log("you are on detail page<br>");
				int size_before = driver.findElements(By.xpath("//ul/li[contains(@class,'container')]")).size();
				Reporter.log(size_before + "<br>");
				Thread.sleep(5000);
				Boolean Sticky = driver.findElements(By.xpath("//div[contains(@class,'first-time-visit opened')]"))
						.size() > 0;
				if (Sticky) {
					driver.findElement(By.xpath("//div[contains(@class,'first-time-visit opened')]/span[1]")).click();
				}
				if (size_before != 0) {
					Boolean isPresent = driver.findElements(By.cssSelector(".lego-async-controls.ng-hide")).size() > 0;
					if (!isPresent) {
						driver.findElement(By.className("lego-async-button")).click();
						Thread.sleep(5000);
						int size_after = driver.findElements(By.xpath("//ul/li[contains(@class,'container')]")).size();
						Reporter.log(size_after + "<br>");
						if (size_before != size_after) {
							Reporter.log("<font color='green'>Passed: </font>" + "See More is working<br>");
						} else {
							x = 1;
							Reporter.log("<font color='red'>Failed: </font>" + "See more is not working<br>");
						}
					} else {
						Reporter.log("See more button is not present<br>");
					}
				} else {
					Reporter.log("There is no creations<br>");
				}
			} else {

				Reporter.log("You are on overview page<br>");
				List<WebElement> list = driver.findElements(By.xpath(XPath));
				for (int j = 0; j < list.size(); j++) {
					links.add(list.get(j).getAttribute("href"));
				}

				for (int j = 0; j < list.size(); j++) {
					links.add(list.get(j).getAttribute("href"));
				}
				for (int j = 0; j < links.size(); j++) {
					driver.get(links.get(j));
					Reporter.log(j + "<br>");
					int size_before = driver.findElements(By.xpath("//ul/li[contains(@class,'container')]")).size();
					Reporter.log(size_before + "<br>");
					Thread.sleep(5000);
					if (size_before != 0) {
						Boolean isPresent = driver.findElements(By.cssSelector(".lego-async-controls.ng-hide"))
								.size() > 0;
						if (!isPresent) {
							driver.findElement(By.className("lego-async-button")).click();
							Thread.sleep(5000);
							int size_after = driver.findElements(By.xpath("//ul/li[contains(@class,'container')]"))
									.size();
							Reporter.log(size_after + "<br>");
							if (size_before != size_after) {
								Reporter.log("<font color='green'>Passed: </font>" + "See More is working<br>");
							} else {
								x = 1;
								Reporter.log("<font color='red'>Failed: </font>" + "See more is not working<br>");
							}
						} else {
							Reporter.log("See more button is not present<br>");
						}
					} else {
						Reporter.log("There is no creations<br>");
					}
				}
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
