package com.LEGO.testSuite;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.LEGO.pages.Galleries_upload;

public class Galleries_Upload {

	/** Checking Galleries_Upload Page on Site Only */
	@BeforeTest
	public void Initialise() throws Exception {
		Base_Class BC = new Base_Class();
		BC.Set_up();
	}

	@Test
	public void Verify_UploadPage() {
		System.out.println("Callin GU");
		Galleries_upload GU = new Galleries_upload();
		GU.fetch_url();
		AssertJUnit.assertTrue(GU.Result);
	}
}
