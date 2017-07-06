package com.LEGO.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class NotInLanguage extends Base_Page {
	public boolean Result;
	Select market;

	public void fetch_url() {

		try {
			Result = false;
			List<WebElement> list1 = driver.findElements(By.xpath("//option"));

			for (int i = 1; i < list1.size() - 1; i++) {
				market = new Select(driver.findElement(By.className("market-selector")));
				market.selectByIndex(i);
				Boolean isPresent = driver.findElements(By.cssSelector(".not-in-language")).size() > 0;
				if (isPresent) {
					Reporter.log("<font color='green'>Passed: </font>" + "Not in Language message is present on = "
							+ driver.getCurrentUrl() + "<br");
				} else {
					Reporter.log("<font color='green'>Passed: </font>" + "Page is available in this Locale = "
							+ driver.getCurrentUrl() + "<br>");
				}
				Thread.sleep(1000);
			}
			Reporter.log("Crawl completed<br>");
			Result = true;

		} catch (Exception e) {
			Result = false;
			e.printStackTrace();
		}
	}

}
