package Automation;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Comments {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Comments a = new Comments();
		try {
			a.fetch_url();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fetch_url() {
		ArrayList<String> links = new ArrayList<String>();

		try {
			WebDriver driver = new FirefoxDriver();
			driver.get("https://www.webqa.lego.com/en-us/city/galleries");

			Boolean popup = driver.findElements(By.id("IPEinvL124035")).size() > 0;
			if (popup) {
				driver.findElement(By.id("IPEbgCover124035")).click();
				System.out.println("Element found");
			}

			String url1 = driver.getCurrentUrl();
			driver.findElement(By.xpath("//div[contains(@class,'links')]/a[1]")).click();
			Thread.sleep(2000);
			Boolean isPresent2 = driver.findElements(By.className("transparency")).size() > 0; //
			// System.out.println(isPresent2);
			driver.switchTo().frame("legoid-iframe");
			if (isPresent2) {
				driver.findElement(By.id("fieldUsername")).sendKeys("newqauser1");
				driver.findElement(By.id("fieldPassword")).sendKeys("lego@123");
				driver.findElement(By.id("buttonSubmitLogin")).click();
				Thread.sleep(3000);
				System.out.println("Logged in");
			} else {
				System.out.println("Already Logged In");
			}
			driver.get(url1);
			String s = driver.getCurrentUrl();
			if (s.contains("galleries/")) {
				System.out.println("you are on detail page");

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
						System.out.println(comments_message);
						if (comments_message != null) {
							System.out.println("Comments is working");
						} else {
							System.out.println("Comments is not working");
						}
					}
				} else {
					System.out.println("There is no Creations");
				}
			} else {

				System.out.println("You are on overview page");
				List<WebElement> list = driver
						.findElements(By.xpath("//a[contains(@href,'www.webqa.lego.com/en-us/city/galleries/')]"));
				for (int j = 0; j < list.size(); j++) {
					links.add(list.get(j).getAttribute("href"));
					// System.out.println(links.get(j));
				}

				for (int j = 0; j < list.size(); j++) {
					links.add(list.get(j).getAttribute("href"));
				}
				for (int j = 0; j < links.size(); j++) {
					driver.get(links.get(j));
					Thread.sleep(5000);
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
							System.out.println(comments_message);
							if (comments_message != null) {
								System.out.println("Comments is working");
							} else {
								System.out.println("Comments is not working");
							}
						}
					} else {
						System.out.println("There is no Creations");
					}
				}
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

}
