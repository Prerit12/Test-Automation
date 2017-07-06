package com.LEGO.testSuite;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.LEGO.pages.Page_title;;

public class Page_Title {
	/** Checking Page Title on Site Only */
	@BeforeTest
	public void Initialise() throws Exception {
		Base_Class BC = new Base_Class();
		BC.Set_up();
	}

	@Test
	public void Verify_Page_Title() {
		System.out.println("Callin PT");
		Page_title PT = new Page_title();
		PT.fetch_url();
		AssertJUnit.assertTrue(PT.Result);
	}
}
