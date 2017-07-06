package com.LEGO.testSuite;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.LEGO.pages.Downloads_Page;

public class Downloads_page {
	/** Checking Download Page on Site Only */
	@BeforeTest
	public void Initialise() throws Exception {
		Base_Class BC = new Base_Class();
		BC.Set_up();
	}

	@Test
	public void Verify_DownloadsPage() {
		System.out.println("Callin DP");
		Downloads_Page DP = new Downloads_Page();
		DP.fetch_url();
		AssertJUnit.assertTrue(DP.Result);
	}
}
