package com.LEGO.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class Galleries_like extends Base_Page {

	public void fetch_url() {
		int x = 0;
		ArrayList<String> links = new ArrayList<String>();
		String UserName;
		String Passwd;

		try {
			Result = false;
			String url = driver.getCurrentUrl();
			Boolean popup = driver.findElements(By.id("IPEbgCover124035")).size() > 0;
			if (popup) {
				driver.get(url);
			}
			setORprop();
			UserName = prop_OR.getProperty("UserName");
			Passwd = prop_OR.getProperty("Passwd");
			String url1 = driver.getCurrentUrl();
			driver.findElement(By.xpath("//div[contains(@class,'links')]/a[1]")).click();
			Thread.sleep(5000);
			Boolean isPresent2 = driver.findElements(By.className("transparency")).size() > 0; //
			// Reporter.log(isPresent2);
			driver.switchTo().frame("legoid-iframe");
			if (isPresent2) {
				driver.findElement(By.id("fieldUsername")).sendKeys(UserName);
				driver.findElement(By.id("fieldPassword")).sendKeys(Passwd);
				driver.findElement(By.id("buttonSubmitLogin")).click();
				Thread.sleep(3000);
				Reporter.log("Logged in<br>");
			} else {
				Reporter.log("Already Logged In<br>");
			}
			driver.get(url1);
			String s = driver.getCurrentUrl();
			Reporter.log(s + "<br>");
			if (s.contains("galleries/")) {
				Reporter.log("you are on detail page<br>");

				Thread.sleep(5000);

				Boolean isPresent = driver.findElements(By.xpath("//div[contains(@class,'album-social')]/button"))
						.size() > 0;
				if (isPresent) {
					boolean button = driver.findElements(By.xpath("//button[contains(@class,'has-not-liked')]"))
							.size() > 0;
					if (button) {
						WebElement like = driver.findElement(By.xpath("//button[contains(@class,'has-not-liked')]"));
						String s1 = like.getText();
						Reporter.log(s1 + "<br>");

						like.click();
						Thread.sleep(3000);

						String s2 = like.getText();
						Reporter.log(s2 + "<br>");

						if (s1.equals(s2)) {
							x = 1;
							Reporter.log("<font color='red'>Failed: </font>" + "Like is not working<br>");
						} else {
							Reporter.log("<font color='green'>Passed: </font>" + "Like is working<br>");
						}
					} else {
						Reporter.log("All creations liked by this user<br>");
					}
				} else {
					Reporter.log("There is no creation in the album <br>");
				}

			} else {

				Reporter.log("You are on overview page<br>");
				List<WebElement> list = driver.findElements(By.xpath(XPath));
				for (int j = 0; j < list.size(); j++) {
					links.add(list.get(j).getAttribute("href"));
					// Reporter.log(links.get(j));
				}

				for (int j = 0; j < list.size(); j++) {
					links.add(list.get(j).getAttribute("href"));
				}
				for (int j = 0; j < links.size(); j++) {
					driver.get(links.get(j));
					Thread.sleep(5000);
					Reporter.log(j + "<br>");
					Boolean isPresent = driver.findElements(By.xpath("//div[contains(@class,'album-social')]/button"))
							.size() > 0;
					if (isPresent) {
						boolean button = driver.findElements(By.xpath("//button[contains(@class,'has-not-liked')]"))
								.size() > 0;
						if (button) {
							WebElement like = driver
									.findElement(By.xpath("//button[contains(@class,'has-not-liked')]"));
							String s1 = like.getText();
							Reporter.log(s1 + "<br>");

							like.click();
							Thread.sleep(3000);

							String s2 = like.getText();
							Reporter.log(s2 + "<br>");

							if (s1.equals(s2)) {
								x = 1;
								Reporter.log("<font color='red'>Failed: </font>" + "Like is not working<br>");
							} else {
								Reporter.log("<font color='red'>Failed: </font>" + "Like is working<br>");
							}
						} else {
							Reporter.log("All creations liked by this user<br>");
						}
					} else {
						Reporter.log("There is no creation in the album<br> ");
					}

				}
			}
			if (x == 0) {
				Result = true;
			} else {
				Result = false;
			}

		} catch (

		Exception e) {
			Result = false;
			e.printStackTrace();
		}
	}
}
