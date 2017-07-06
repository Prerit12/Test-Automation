package com.LEGO.testSuite;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.LEGO.pages.NotInLanguage;

public class Not_In_Language {
	@BeforeTest
	public void Initialise() throws Exception {

		Base_Class BC = new Base_Class();
		BC.Set_up();
	}

	@Test
	public void Verify_NotInlanguage() {
		System.out.println("Callin NotInlanguage");
		NotInLanguage NL = new NotInLanguage();
		NL.fetch_url();
		AssertJUnit.assertTrue(NL.Result);

	}

}
