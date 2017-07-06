package com.LEGO.pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class Products_shoplinks extends Base_Page {
	int x = 0;
	ArrayList<String> links2 = new ArrayList<String>();

	public void Shoplinks() throws Exception {

		Boolean isPresent1 = driver.findElements(By.xpath("//section[contains(@class,'media-item')]/ul")).size() > 0;
		// System.out.println(isPresent1);
		if (isPresent1) {
			// System.out.println("Inside if loop");

			driver.findElement(By.className("icon-shopping-cart-filled")).click();
			Boolean Modal = driver.findElements(By.className("lego-modal-body")).size() > 0;
			if (Modal) {
				driver.findElement(By.xpath("//button[contains(@class,'lego-modal-close-trigger')]")).click();
				Reporter.log("<font color='green'>Passed: </font>" + "Popup is coming = Shoplinks <br>");
			} else {
				x = 1;
				Reporter.log("<font color='red'>Failed: </font>" + "Popup is not present = Shoplinks<br>");
			}
			Thread.sleep(500);
			driver.findElement(By.className("icon-wish-list")).click();
			Boolean Modal1 = driver.findElements(By.className("lego-modal-dialog")).size() > 0;
			// System.out.println(Modal1);
			if (Modal1) {
				driver.findElement(By.xpath("//button[contains(@class,'lego-modal-close-trigger')]")).click();
				Reporter.log("<font color='green'>Passed: </font>" + "Popup is coming = Wishlist <br>");
			} else {
				x = 1;
				Reporter.log("<font color='red'>Failed: </font>" + "Popup is not present = Wishlist<br>");
			}

			List<WebElement> list1 = driver.findElements(By.xpath("//ul[contains(@class,'list-inline')]/li[3]/a"));
			links2.add(list1.get(0).getAttribute("href"));
			for (int l = 0; l < links2.size(); l++) {
				URL url = new URL(links2.get(l));
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				int statusCode = http.getResponseCode();
				Reporter.log("<font color='green'>Passed: </font>" + statusCode + " = Building Instruction<br>");
				links2.clear();
			}
		} else {
			Reporter.log("Null url<br>");
		}
	}
}
