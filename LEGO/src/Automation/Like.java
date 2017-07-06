package Automation;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Like {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Like a = new Like();
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
			
			Boolean popup=driver.findElements(By.id("IPEinvL124035")).size()>0;
			if(popup){
				driver.findElement(By.id("ipeL124035")).click();
				System.out.println("Element found");
			}
			
			String url1 = driver.getCurrentUrl();
			driver.findElement(By.xpath("//div[contains(@class,'links')]/a[1]")).click();
			Thread.sleep(2000);
			Boolean isPresent2 = driver.findElements(By.className("transparency")).size() > 0; //
			//System.out.println(isPresent2);
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

				Boolean isPresent = driver.findElements(By.xpath("//div[contains(@class,'album-social')]/button"))
						.size() > 0;
				if (isPresent) {
					boolean button = driver.findElements(By.xpath("//button[contains(@class,'has-not-liked')]"))
							.size() > 0;
					if (button) {
						WebElement like = driver.findElement(By.xpath("//button[contains(@class,'has-not-liked')]"));
						String s1 = like.getText();
						System.out.println(s1);

						like.click();
						Thread.sleep(3000);

						String s2 = like.getText();
						System.out.println(s2);

						if (s1.equals(s2)) {
							System.out.println("Like is not working");
						} else {
							System.out.println("Like is working");
						}
					} else {
						System.out.println("All creations liked by this user");
					}
				} else {
					System.out.println("There is no creation in the album ");
				}

			} else {
						
				System.out.println("You are on overview page");
				List<WebElement> list = driver
						.findElements(By.xpath("//a[contains(@href,'www.webqa.lego.com/en-us/city/galleries/')]"));
				for (int j = 0; j < list.size(); j++) {
					links.add(list.get(j).getAttribute("href"));
					//System.out.println(links.get(j));
				}
				
				for (int j = 0; j < list.size(); j++) {
					links.add(list.get(j).getAttribute("href"));
				}
				for (int j = 0; j < links.size(); j++) {
					driver.get(links.get(j));
					Thread.sleep(5000);

					Boolean isPresent = driver.findElements(By.xpath("//div[contains(@class,'album-social')]/button"))
							.size() > 0;
					if (isPresent) {
						boolean button = driver.findElements(By.xpath("//button[contains(@class,'has-not-liked')]"))
								.size() > 0;
						if (button) {
							WebElement like = driver
									.findElement(By.xpath("//button[contains(@class,'has-not-liked')]"));
							String s1 = like.getText();
							System.out.println(s1);

							like.click();
							Thread.sleep(3000);

							String s2 = like.getText();
							System.out.println(s2);

							if (s1.equals(s2)) {
								System.out.println("Like is not working");
							} else {
								System.out.println("Like is working");
							}
						} else {
							System.out.println("All creations liked by this user");
						}
					} else {
						System.out.println("There is no creation in the album ");
					}

				}
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

}
