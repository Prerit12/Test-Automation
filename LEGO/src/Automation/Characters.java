package Automation;

import java.net.HttpURLConnection;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.LEGO.pages.Base_Page;

public class Characters extends Base_Page {
	static {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
	}
	
	Logger logger=Logger.getLogger("Characters");
	

	public static void main(String[] args) {
		Characters a = new Characters();
		try {
			a.fetch_url();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void fetch_url() throws Exception {
		int statusCode;
		HttpURLConnection http;

		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.webqa.lego.com/en-us/angrybirdsmovie/characters");
		ArrayList<String> links = new ArrayList<String>();
		List<WebElement> list2;
		ArrayList<String> links1 = new ArrayList<String>();
		ArrayList<String> links2 = new ArrayList<String>();

		try {

			/*
			 * FileHandler fh; fh = new
			 * FileHandler("C:\\Users\\a1PreBhu\\Workspace\\LEGO\\MyLogFile.log"
			 * ); logger.addHandler(fh); SimpleFormatter formatter = new
			 * SimpleFormatter(); fh.setFormatter(formatter);
			 */

			List<WebElement> list1 = driver.findElements(By.xpath("//div[contains(@class,'content')]/img"));
			for (int j = 0; j < list1.size(); j++) {
				links1.add(list1.get(j).getAttribute("lego-lazy-load"));
				links1.get(j);
				if (links1.get(j) != null) {
					if (links1.get(j).startsWith("https")) {
						URL url1 = new URL(links1.get(j));
						http = (HttpURLConnection) url1.openConnection();
						statusCode = http.getResponseCode();
						logger.info(statusCode + " = " + links1.get(j));
					} else {
						logger.info("Not valid url" + links1.get(j));
					}
				} else {
					System.out.println("Null url");
				}
			}

			List<WebElement> list = driver.findElements(
					By.xpath("//a[contains(@href,'www.webqa.lego.com/en-us/angrybirdsmovie/characters')]"));
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
				list = driver.findElements(
						By.xpath("//a[contains(@href,'www.webqa.lego.com/en-us/angrybirdsmovie/characters')]"));

				for (int k = 0; k < list.size(); k++) {
					links.add(list.get(k).getAttribute("href"));
					Set<String> hs = new HashSet<>();
					hs.addAll(links);
					links.clear();
					links.addAll(hs);
				}

				Boolean isPresent = driver.findElements(By.xpath("//div[contains(@class,'primary-content')]/img"))
						.size() > 0;

				if (isPresent) {

					list2 = driver.findElements(By.xpath("//div[contains(@class,'primary-content')]/img"));

					for (int k = 0; k < list2.size(); k++) {
						links2.add(list2.get(k).getAttribute("lego-lazy-load"));
						links2.get(k);

						if (links2.get(k) != null) {

							if (links2.get(k).startsWith("https")) {
								URL url1 = new URL(links2.get(k));
								http = (HttpURLConnection) url1.openConnection();
								statusCode = http.getResponseCode();
								logger.info(statusCode + " = " + links2.get(k));

							} else {
								System.out.println("Not valid url" + links1.get(k));
							}
						} else {
							System.out.println("Null url");
						}
					}

					Thread.sleep(2000);
					logger.info("name" + "=" + links.get(j));

				} else {
					System.out.println("Character detais is missing = " + links1.get(j));
				}
			}
			logger.info("Crawl Completed");
			System.out.println(links.size());
			driver.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
