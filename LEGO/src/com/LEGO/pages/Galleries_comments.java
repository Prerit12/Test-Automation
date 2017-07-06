package com.LEGO.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class Galleries_comments extends Base_Page {

	public void fetch_url() {
		int x = 0;
		ArrayList<String> links = new ArrayList<String>();

		try {
			Result = false;
			String url = driver.getCurrentUrl();
			Boolean popup = driver.findElements(By.id("IPEbgCover124035")).size() > 0;
			if (popup) {
				driver.get(url);
			}

			String url1 = driver.getCurrentUrl();
			driver.findElement(By.xpath("//div[contains(@class,'links')]/a[1]")).click();
			Thread.sleep(2000);
			Boolean isPresent2 = driver.findElements(By.className("transparency")).size() > 0; //
			// Reporter.log(isPresent2);
			driver.switchTo().frame("legoid-iframe");
			if (isPresent2) {
				driver.findElement(By.id("fieldUsername")).sendKeys("newqauser1");
				driver.findElement(By.id("fieldPassword")).sendKeys("lego@123");
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
				Reporter.log("You are on detail page<br>");

				Thread.sleep(5000);
				int size_before = driver.findElements(By.xpath("//ul/li[contains(@class,'container')]")).size();
				if (size_before > 1) {
					driver.findElement(By.xpath("//ul/li[2][contains(@class,'container')]")).click();
					Boolean Comments = driver
							.findElements(By.xpath("//div[contains(@class,'comments-text-area-container')]/textarea"))
							.size() > 0;
					if (Comments) {
						driver.findElement(By.xpath("//div[contains(@class,'comments-text-area-container')]/textarea"))
								.sendKeys("Test Comment");
						driver.findElement(By.xpath("//button[contains(@class,'comments-signin')]")).click();
						Thread.sleep(3000);
						String comments_message = driver
								.findElement(
										By.xpath("//div[contains(@class,'comments-signin-button-container')]/span"))
								.getText();
						Reporter.log(comments_message + "<br>");
						if (comments_message != null) {
							Reporter.log("<font color='green'>Passed: </font>" + "Comments is working<br>");
						} else {
							x = 1;
							Reporter.log("<font color='red'>Failed: </font>" + "Comments is not working<br>");
						}
					}
				} else {
					Reporter.log("There is no Creations<br>");
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
					int size_before = driver.findElements(By.xpath("//ul/li[contains(@class,'container')]")).size();
					if (size_before > 1) {
						driver.findElement(By.xpath("//ul/li[2][contains(@class,'container')]")).click();
						Boolean Comments = driver
								.findElements(
										By.xpath("//div[contains(@class,'comments-text-area-container')]/textarea"))
								.size() > 0;
						if (Comments) {
							driver.findElement(
									By.xpath("//div[contains(@class,'comments-text-area-container')]/textarea"))
									.sendKeys("Test Comment");
							driver.findElement(By.xpath("//button[contains(@class,'comments-signin')]")).click();
							Thread.sleep(3000);
							String comments_message = driver
									.findElement(
											By.xpath("//div[contains(@class,'comments-signin-button-container')]/span"))
									.getText();
							Reporter.log(comments_message + "<br>");
							if (comments_message != null) {
								Reporter.log("<font color='green'>Passed: </font>" + "Comments is working<br>");
							} else {
								x = 1;
								Reporter.log("<font color='red'>Failed: </font>" + "Comments is not working<br>");
							}
						}
					} else {
						Reporter.log("There is no Creations<br>");
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
