package com.LEGO.testSuite;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.LEGO.pages.Characters_Page;

public class Characters_page {
	/** Checking Characters Page on Site Only */

	@BeforeTest
	public void Initialise() throws Exception {
		Base_Class BC = new Base_Class();
		BC.Set_up();
	}

	@Test
	public void Verify_CharactersPage() {
		System.out.println("Callin DP");
		Characters_Page CP = new Characters_Page();
		CP.fetch_url();
		AssertJUnit.assertTrue(CP.Result);
	}
}
