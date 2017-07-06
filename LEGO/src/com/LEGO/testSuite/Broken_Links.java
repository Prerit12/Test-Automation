package com.LEGO.testSuite;

import org.testng.AssertJUnit;
import org.testng.annotations.*;

import com.LEGO.pages.Broken_link_Checker;

/**Checking Broken Links on Site Only*/
public class Broken_Links
{	
	@BeforeTest
	public void Initialise() throws Exception
	{
		Base_Class BC = new Base_Class();
		BC.Set_up();
	}
	 
	@Test 
	public void Verify_BrokenLinks()
	{
		System.out.println("Callin BL");
		Broken_link_Checker BL = new Broken_link_Checker();
		BL.fetch_url();
		AssertJUnit.assertTrue(BL.Result);	
	}
}
