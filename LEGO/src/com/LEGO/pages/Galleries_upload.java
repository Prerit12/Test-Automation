package com.LEGO.pages;

import org.openqa.selenium.By;
import org.testng.Reporter;

public class Galleries_upload extends Base_Page {

	public void fetch_url() {
		int x = 0;
		try {
			Result = false;
			String s = driver.getCurrentUrl();
			Boolean popup = driver.findElements(By.id("IPEinvL124035")).size() > 0;
			if (popup) {
				driver.get(s);
			}
			String url = driver.getCurrentUrl();
			Thread.sleep(2000);

			Boolean isPresent = driver.findElements(By.className("transparency")).size() > 0;
			// Reporter.log(isPresent);
			driver.switchTo().frame("legoid-iframe");
			if (isPresent) {
				driver.findElement(By.id("fieldUsername")).sendKeys("newqauser1");
				driver.findElement(By.id("fieldPassword")).sendKeys("lego@123");
				driver.findElement(By.id("buttonSubmitLogin")).click();
				Thread.sleep(2000);
				Reporter.log("Logged in<br>");
			} else {
				Reporter.log("Already Logged In<br>");
			}

			driver.get(url);
			driver.findElement(By.xpath("//lego-album-upload-button/span/input"))
					.sendKeys("C:\\Users\\a1PreBhu\\Workspace\\LEGO\\MCDP.jpg");
			Thread.sleep(5000);
			driver.findElement(By.id("albumTitle")).sendKeys("TestAlbum");
			driver.findElement(By.id("albumDescription")).sendKeys("Automation Upload");
			driver.findElement(By.className("btn-lg")).click();
			Thread.sleep(5000);

			Boolean isPresent1 = driver.findElements(By.className("album-editor-success-modal")).size() > 0;
			if (isPresent1) {
				Reporter.log("<font color='green'>Passed: </font>" + "Album uploaded successfully<br>");
			} else {
				x = 1;
				Reporter.log("<font color='red'>Failed: </font>" + "Album not uploaded<br>");
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
