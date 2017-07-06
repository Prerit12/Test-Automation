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

public class Header_Footer extends Base_Page {
	int x = 0;
	Global_header_Footer GHF = new Global_header_Footer();
	StickyFooter SF = new StickyFooter();
	public boolean Result;

	/** fetching all URLS of Site */
	public void fetch_url() {

		try {
			Result = false;
			// Reporter.log("inside fetch url<br>");
			List<WebElement> list = new ArrayList<WebElement>();
			list = driver.findElements(By.xpath(XPath));

			for (int j = 0; j < list.size(); j++) {
				links.add(list.get(j).getAttribute("href"));
				Set<String> hs = new HashSet<>();
				hs.addAll(links);
				links.clear();
				links.addAll(hs);
			}

			for (int j = 0; j < links.size(); j++) {
				URL url = new URL(links.get(j));
				HttpURLConnection http = (HttpURLConnection) url.openConnection();
				int statusCode = http.getResponseCode();
				// Reporter.log(statusCode);

				if (statusCode == 200) {
					driver.get(links.get(j));
					list = driver.findElements(By.xpath(XPath));
					// Reporter.log("Inside if Block");

					for (int k = 0; k < list.size(); k++) {
						links.add(list.get(k).getAttribute("href"));
						Set<String> hs = new HashSet<>();
						hs.addAll(links);
						links.clear();
						links.addAll(hs);
					}

					/** Calling Global Header and Footer method */
					boolean res = GHF.header_footer();
					if (res) {
						/** Calling Market Selector method */
						boolean marsel = GHF.MarketSelector();

						if (marsel) {

							// Reporter.log("name" + "=" + links.get(j)+" -
							// Market Selector found");
						} else {
							x = 1;
							Reporter.log("<font color='red'>Failed: </font>" + "name" + " = " + links.get(j)
									+ " - Market Selector not found<br>");
						}

						/** Calling Global Navigation method */
						boolean globalnav = GHF.GlobalNav();

						if (globalnav) {
							// Reporter.log("name" + "=" + links.get(j)+" -
							// Global Navigation found");
						} else {
							x = 1;
							Reporter.log("<font color='red'>Failed: </font>" + "name" + " = " + links.get(j)
									+ " - Global Navigation not found<br>");
						}

						/** Calling Privacy Policy method */
						boolean privacy = GHF.Privacy();
						if (privacy) {
							// Reporter.log("name" + "=" + links.get(j)+" -
							// Privacy Policy found");
						} else {
							x = 1;
							Reporter.log("<font color='red'>Failed: </font>" + "name" + " = " + links.get(j)
									+ " -Privacy Policy not found<br>");
						}

						Reporter.log("<font color='green'>Passed: </font>" + "name" + " = " + links.get(j)
								+ " -Privacy Policy, Global Navigation, Market Selector, Gloabal Header and Footer found<br>");
					}

					else {
						x = 1;
						Reporter.log("<font color='red'>Failed: </font>" + "name" + " = " + links.get(j)
								+ " - Gloabal Header and Footer not found<br>");

					}

					Thread.sleep(2000);
				} else {
					Reporter.log("name" + " = " + links.get(j) + "=" + statusCode + "<br>");
				}

			}

			Reporter.log("Crawl Completed<br>");
			// Reporter.log(Integer.toString(links.size()));
			if (x == 0) {
				Result = true;
			} else {
				Result = false;
			}
		}

		catch (Exception e) {
			Result = false;
			e.printStackTrace();
		}
	}
}