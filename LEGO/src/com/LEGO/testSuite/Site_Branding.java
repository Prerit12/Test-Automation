package com.LEGO.testSuite;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.*;
import com.LEGO.pages.SiteBranding_Logo;

/** Calling Site Branding test Case only */
public class Site_Branding {

	@BeforeTest
	public void Initialise() throws Exception {
		Base_Class BC = new Base_Class();
		BC.Set_up();
	}

	@Test
	public void Verify_Site_Branding() throws Exception {
		System.out.println("Callin SB");
		SiteBranding_Logo SB = new SiteBranding_Logo();
		SB.fetch_url();
		AssertJUnit.assertTrue(SB.Result);

	}
}
