package com.LEGO.pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class Products_Slider extends Base_Page {
	int x = 0;
	ArrayList<String> links1 = new ArrayList<String>();
	public Boolean isPresent;

	public void Slider() throws Exception {

		isPresent = driver.findElements(By.className("lego-slider")).size() > 0;
		// System.out.println(isPresent);
		if (isPresent) {
			// System.out.println("inside if");
			List<WebElement> list1 = driver.findElements(By.xpath("//div[contains(@class,'primary-content')]/img"));

			for (int i = 0; i < list1.size(); i++) {
				// System.out.println("insideforloop");
				links1.add(list1.get(i).getAttribute("src"));
				// System.out.println("Elements added");
				if (links1.get(i) != null) {

					if (links1.get(i).startsWith("https")) {
						URL url1 = new URL(links1.get(i));
						HttpURLConnection http = (HttpURLConnection) url1.openConnection();
						int statusCode = http.getResponseCode();

						if (statusCode == 200) {
							Reporter.log("<font color='green'>Passed: </font>" + "Image is present in slider<br>");

						} else {
							x = 1;
							Reporter.log("<font color='red'>Failed: </font>" + "Image is missing = " + links1.get(i)
									+ "<br>");
						}
					} else {
						// System.out.println("Invalid URL");
					}
				} else {
					// System.out.println("Null URL");
				}
			}
		} else {
			x = 1;
			Reporter.log("<font color='red'>Failed: </font>" + "Lego slider is not Present<br>");
		}
	}
}
