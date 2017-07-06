package com.LEGO.testSuite;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.LEGO.pages.Locale_Redirect;

/** Calling Locale Redirect test case only */
public class Locale_redirect {
	@BeforeTest
	public void Initialise() throws Exception {

		Base_Class BC = new Base_Class();
		BC.Set_up();
	}

	@Test
	public void Verify_LocaleRedirection() {
		System.out.println("Callin LocaleRedirect");
		Locale_Redirect locredirect = new Locale_Redirect();
		locredirect.fetch_url();
		AssertJUnit.assertTrue(locredirect.Result);

	}
}
