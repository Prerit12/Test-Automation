package com.LEGO.testSuite;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.LEGO.pages.Logo_Redirect;

/** Calling Logo Redirect test case only */
public class Logo_redirect {
	@BeforeTest
	public void Initialise() throws Exception {

		Base_Class BC = new Base_Class();
		BC.Set_up();
	}

	@Test
	public void Verify_Redirection() {
		System.out.println("Callin Redirect");
		Logo_Redirect redirect = new Logo_Redirect();
		redirect.fetch_url();
		AssertJUnit.assertTrue(redirect.Result);

	}
}
