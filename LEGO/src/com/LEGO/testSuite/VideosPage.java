package com.LEGO.testSuite;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.LEGO.pages.Videos_Page;

public class VideosPage {
	/** Checking Videos Page on Site Only */

	@BeforeTest
	public void Initialise() throws Exception {
		Base_Class BC = new Base_Class();
		BC.Set_up();
	}

	@Test
	public void Verify_VideosPage() {
		System.out.println("Callin VP");
		Videos_Page VP = new Videos_Page();
		VP.fetch_url();
		AssertJUnit.assertTrue(VP.Result);
	}
}
