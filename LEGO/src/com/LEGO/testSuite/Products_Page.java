package com.LEGO.testSuite;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.LEGO.pages.Products_page;

public class Products_Page {

	/** Checking Products Page on Site Only */
	@BeforeTest
	public void Initialise() throws Exception {
		Base_Class BC = new Base_Class();
		BC.Set_up();
	}

	@Test
	public void Verify_ProductsPage() {
		System.out.println("Callin PP");
		Products_page PP = new Products_page();
		PP.fetch_url();
		AssertJUnit.assertTrue(PP.Result);
	}
}
