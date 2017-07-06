package com.LEGO.testSuite;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.LEGO.pages.Galleries_like;

public class Galleries_Like {

	/** Checking Galleries_like Page on Site Only */
	@BeforeTest
	public void Initialise() throws Exception {
		Base_Class BC = new Base_Class();
		BC.Set_up();
	}

	@Test
	public void Verify_likePage() {
		System.out.println("Callin GL");
		Galleries_like GL = new Galleries_like();
		GL.fetch_url();
		AssertJUnit.assertTrue(GL.Result);
	}
}
