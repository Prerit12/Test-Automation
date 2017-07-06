package com.LEGO.testSuite;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.LEGO.pages.Galleries_comments;

public class Galleries_Comments {

	/** Checking Galleries_Comments Page on Site Only */
	@BeforeTest
	public void Initialise() throws Exception {
		Base_Class BC = new Base_Class();
		BC.Set_up();
	}

	@Test
	public void Verify_UploadPage() {
		System.out.println("Callin GC");
		Galleries_comments GC = new Galleries_comments();
		GC.fetch_url();
		AssertJUnit.assertTrue(GC.Result);
	}
}
