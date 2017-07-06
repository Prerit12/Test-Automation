package com.LEGO.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import org.testng.Reporter;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.io.*;
//import com.LEGO.testSuite.Base_Class;
//import com.LEGO.testSuite.CheckGlobalHeader_Footer;
import com.LEGO.testSuite.Running_AlltestCase;
//import com.LEGO.testSuite.Testing_XML;

public class Base_Page extends Running_AlltestCase {
	public Properties prop_OR;
	public String propFileName;
	public InputStream input;
	public String Get_url;
	public Boolean Result;
	ArrayList<String> links = new ArrayList<String>();
	static {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		System.setProperty("current.date.time", dateFormat.format(new Date()));
	}
	// public Logger logger = Logger.getLogger("Base_Page");

	/** Setting OR properties file code */
	public void setORprop() throws Exception {
		//Logger logger = Logger.getLogger("Base_Page");
		try {
			Result = false;
			prop_OR = new Properties();
			propFileName = "OR.properties";

			input = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (input != null) {
				prop_OR.load(input);

			} else {
				throw new FileNotFoundException("Property File" + propFileName + "not found in the classpath");
			}
			Result = true;
		}

		catch (Exception e) {
			Reporter.log("Exception" + e);
		}
	}
}