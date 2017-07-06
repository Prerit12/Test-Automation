package com.LEGO.pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class Products_page extends Base_Page {
	int x = 0;
	int statusCode;
	public String Close;
	public String Shoplinks;
	public String iFrame;
	HttpURLConnection http;
	ArrayList<String> links = new ArrayList<String>();
	ArrayList<String> links1 = new ArrayList<String>();
	ArrayList<String> links2 = new ArrayList<String>();
	ArrayList<String> links3 = new ArrayList<String>();

	public void fetch_url() {

		try {
			Result = false;
			setORprop();
			Close = prop_OR.getProperty("Close");
			Shoplinks = prop_OR.getProperty("Shoplinks");
			iFrame = prop_OR.getProperty("iFrame");

			List<WebElement> list3 = driver.findElements(By.xpath("//section/img"));
			for (int j = 0; j < list3.size(); j++) {
				// System.out.println("Inside image loop");
				links3.add(list3.get(j).getAttribute("lego-lazy-load"));
				links3.get(j);

				/** Checking thumbnail images on overview page */
				if (links3.get(j) != null) {
					if (links3.get(j).startsWith("https")) {
						// System.out.println(links3.get(j));
						URL url1 = new URL(links3.get(j));
						http = (HttpURLConnection) url1.openConnection();
						statusCode = http.getResponseCode();
						if (statusCode == 200) {
							links3.get(j);
							// System.out.println(statusCode+" =
							// "+links3.get(j));
						} else {
							Reporter.log(statusCode + " = " + links3.get(j) + "<br>");
						}
					} else {
						Reporter.log("Not valid url= " + links3.get(j) + "<br>");
					}
				} else {
					// System.out.println("Null url");
				}
			}

			List<WebElement> list = driver.findElements(By.xpath(XPath));
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
				Reporter.log("name" + "=" + links.get(j) + "<br>");

				URL url = new URL(links.get(j));
				http = (HttpURLConnection) url.openConnection();
				statusCode = http.getResponseCode();
				// System.out.println(statusCode);
				list = driver.findElements(By.xpath(XPath));
				for (int k = 0; k < list.size(); k++) {
					links.add(list.get(k).getAttribute("href"));
					Set<String> hs = new HashSet<>();
					hs.addAll(links);
					links.clear();
					links.addAll(hs);
				}

				/** Checking slider images on products detail page */
				Boolean isPresent = driver.findElements(By.className("lego-slider")).size() > 0;
				if (isPresent) {
					List<WebElement> list1 = driver
							.findElements(By.xpath("//div[contains(@class,'primary-content')]/img"));

					for (int i = 0; i < list1.size(); i++) {
						links1.add(list1.get(i).getAttribute("src"));
						if (links1.get(i) != null) {

							if (links1.get(i).startsWith("https")) {
								URL url1 = new URL(links1.get(i));
								http = (HttpURLConnection) url1.openConnection();
								statusCode = http.getResponseCode();

								if (statusCode == 200) {
									Reporter.log("<font color='green'>Passed: </font>" + "Image is present in slider = "
											+ links1.get(i) + "<br>");

								} else {
									x = 1;
									Reporter.log("<font color='red'>Failed: </font>" + "Image is missing = "
											+ links1.get(i) + "<br>");
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
					Reporter.log("<font color='red'>Failed: </font>" + "Lego slider is not Present" + "<br>");
				}
				Thread.sleep(1000);

				/** Checking Shoplinks Button */
				Boolean isPresent1 = driver.findElements(By.xpath(Shoplinks)).size() > 0;
				// System.out.println(isPresent1);
				if (isPresent1) {
					// System.out.println("Inside if loop");

					driver.findElement(By.className("icon-shopping-cart-filled")).click();
					Boolean Modal = driver.findElements(By.className("lego-modal-body")).size() > 0;
					if (Modal) {
						driver.findElement(By.xpath(Close)).click();
						Reporter.log("<font color='green'>Passed: </font>" + "Popup is coming = Shoplinks<br> ");
					} else {
						x = 1;
						Reporter.log("<font color='red'>Failed: </font>" + "Popup is not present = Shoplinks<br>");
					}
					Thread.sleep(1000);

					/** Checking Wishlist Button */
					driver.findElement(By.className("icon-wish-list")).click();
					Boolean Modal1 = driver.findElements(By.className("lego-modal-dialog")).size() > 0;
					// System.out.println(Modal1);
					if (Modal1) {
						driver.findElement(By.xpath(Close)).click();
						Reporter.log("<font color='green'>Passed: </font>" + "Popup is coming = Wishlist<br> ");
					} else {
						x = 1;
						Reporter.log("<font color='red'>Failed: </font>" + "Popup is not present = Wishlist<br>");
					}

					/** Checking Building instruction link */
					List<WebElement> list1 = driver
							.findElements(By.xpath("//ul[contains(@class,'list-inline')]/li[3]/a"));
					links2.add(list1.get(0).getAttribute("href"));
					for (int l = 0; l < links2.size(); l++) {
						url = new URL(links2.get(l));
						http = (HttpURLConnection) url.openConnection();
						statusCode = http.getResponseCode();
						Reporter.log(
								"<font color='green'>Passed: </font>" + statusCode + " = Building Instruction<br>");
						links2.clear();
					}
				} else {
					Reporter.log("Null url<br>");
				}

				/** Checking Video is present on product */
				Boolean isPresent2 = driver.findElements(By.cssSelector(".primary-content.aspect-wrapper")).size() > 0;

				if (isPresent2) {
					driver.findElement(By.cssSelector(".primary-content.aspect-wrapper")).click();
					String src = driver.findElement(By.xpath(iFrame)).getAttribute("src");
					URL url1 = new URL(src);
					http = (HttpURLConnection) url1.openConnection();
					statusCode = http.getResponseCode();
					if (statusCode == 200) {
						Reporter.log(
								"<font color='green'>Passed: </font>" + "Video is Present= " + links.get(j) + "<br>");
					}

					else {
						x = 1;
						Reporter.log(
								"<font color='red'>Failed: </font>" + "Video is not Present= " + links.get(j) + "<br>");
					}
				} else {
					x = 1;
					Reporter.log(
							"<font color='red'>Failed: </font>" + "Video div is not present= " + links.get(j) + "<br>");
				}

				Thread.sleep(1000);

			}
			Reporter.log("Crawl Completed<br>");
			// System.out.println(links.size());
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
