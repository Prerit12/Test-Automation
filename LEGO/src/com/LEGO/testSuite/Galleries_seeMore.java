package com.LEGO.testSuite;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.LEGO.pages.Galleries_SeeMore;

public class Galleries_seeMore {

	/** Checking Galleries_Seemore Page on Site Only */
	@BeforeTest
	public void Initialise() throws Exception {
		Base_Class BC = new Base_Class();
		BC.Set_up();
	}

	@Test
	public void Verify_SeemorePage() {
		System.out.println("Callin GS");
		Galleries_SeeMore GS = new Galleries_SeeMore();
		GS.fetch_url();
		AssertJUnit.assertTrue(GS.Result);
	}
}
