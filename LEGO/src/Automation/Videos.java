package Automation;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Videos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Videos a = new Videos();
		try {
			a.getLinks();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getLinks() throws Exception {
		int statusCode;
		HttpURLConnection http;
		String src;
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.webqa.lego.com/en-us/architecture");
		// driver.findElement(By.className("modal-footer")).click();
		ArrayList<String> links = new ArrayList<String>();
		ArrayList<String> links1 = new ArrayList<String>();
		// List<WebElement> list1;

		try {

			List<WebElement> list1 = driver.findElements(By.xpath("//div[contains(@class,'primary-content')]/img"));
			for (int j = 0; j < list1.size(); j++) {
				System.out.println("Inside image loop");
				links1.add(list1.get(j).getAttribute("lego-lazy-load"));
				links1.get(j);
				// src=
				// driver.findElement(By.xpath("//section/img")).getAttribute("src");
				if (links1.get(j).startsWith("https")) {
					System.out.println(links1.get(j));
					URL url1 = new URL(links1.get(j));
					http = (HttpURLConnection) url1.openConnection();
					statusCode = http.getResponseCode();
					links1.get(j);
					System.out.println(statusCode);
				} else {
					System.out.println("Not valid url" + links1.get(j));
				}
			}

			List<WebElement> list = driver
					.findElements(By.xpath("//a[contains(@href,'www.webqa.lego.com/en-us/architecture/videos')]"));
			for (int j = 0; j < list.size(); j++) {
				links.add(list.get(j).getAttribute("href"));
				Set<String> hs = new HashSet<>();
				hs.addAll(links);
				links.clear();
				links.addAll(hs);
			}
			list.clear();

			for (int j = 0; j < links.size(); j++) {
				driver.get(links.get(j));

				URL url = new URL(links.get(j));
				http = (HttpURLConnection) url.openConnection();
				statusCode = http.getResponseCode();
				System.out.println(statusCode);
				// JavascriptExecutor js = (JavascriptExecutor) driver;

				list = driver
						.findElements(By.xpath("//a[contains(@href,'www.webqa.lego.com/en-us/architecture/videos')]"));
				for (int k = 0; k < list.size(); k++) {
					links.add(list.get(k).getAttribute("href"));
					Set<String> hs = new HashSet<>();
					hs.addAll(links);
					links.clear();
					links.addAll(hs);
				}

				Boolean isPresent = driver.findElements(By.cssSelector(".primary-content.aspect-wrapper")).size() > 0;

				if (isPresent) {
					src = driver.findElement(By.xpath("//div[contains(@class, 'mediaplayer-component')]/iframe"))
							.getAttribute("src");
					System.out.println(src);
					driver.findElement(By.cssSelector(".primary-content.aspect-wrapper")).click();
					URL url1 = new URL(src);
					http = (HttpURLConnection) url1.openConnection();
					statusCode = http.getResponseCode();
					System.out.println(statusCode);
				} else {
					System.out.println("Video is not present= " + links.get(j));
				}
				Thread.sleep(5000);
				System.out.println("name" + "=" + links.get(j));

			}
			System.out.println("Crawl Completed");
			System.out.println(links.size());
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
