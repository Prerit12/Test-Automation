package Automation;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeeMore {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SeeMore a = new SeeMore();
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
			driver.get("https://www.webqa.lego.com/en-us/angrybirdsmovie/galleries");
			String s = driver.getCurrentUrl();
			
			Boolean popup = driver.findElements(By.id("IPEbgCover124035")).size() > 0;
			System.out.println(popup);
			if (popup) {
				System.out.println("inside popup");
				driver.get(s);
			}
			
			if (s.contains("galleries/")) {
				System.out.println("you are on detail page");
				int size_before = driver.findElements(By.xpath("//ul/li[contains(@class,'container')]")).size();
				System.out.println(size_before);
				Thread.sleep(5000);
				Boolean Sticky=driver.findElements(By.xpath("//div[contains(@class,'first-time-visit opened')]")).size()>0;
				if(Sticky){
					driver.findElement(By.xpath("//div[contains(@class,'first-time-visit opened')]/span[1]")).click();
				}
					Boolean isPresent = driver.findElements(By.xpath("//button[contains(@class,'lego-async')]")).size() > 0;
					if (isPresent) {
						WebElement element = driver.findElement(By.xpath("//button[contains(@class,'lego-async')]"));
						element.click();
						Thread.sleep(5000);
						int size_after = driver.findElements(By.xpath("//ul/li[contains(@class,'container')]")).size();
						System.out.println(size_after);
						if (size_before != size_after) {
							System.out.println("See More is working");
						} else {
							System.out.println("See more is not working");
						}
					} else {
						System.out.println("See more is not Present");
					}

				/*else {
					System.out.println("There is no Creations");
				}*/
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
					int size_before = driver.findElements(By.xpath("//ul/li[contains(@class,'container')]")).size();
					System.out.println(size_before);
					Thread.sleep(5000);
					if (size_before != 0) {
						Boolean isPresent = driver.findElements(By.cssSelector(".lego-async-controls.ng-hide"))
								.size() > 0;
						if (!isPresent) {
							driver.findElement(By.className("lego-async-button")).click();
							Thread.sleep(5000);
							int size_after = driver.findElements(By.xpath("//ul/li[contains(@class,'container')]"))
									.size();
							System.out.println(size_after);
							if (size_before != size_after) {
								System.out.println("See More is working");
							} else {
								System.out.println("See more is not working");
							}
						} else {
							System.out.println("See more button is not present");
						}
					} else {
						System.out.println("There is no creations");
					}
				}
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

}
