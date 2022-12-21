package com.baseFramework.Regression;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baseFramework.Lib.AppLibrary;
import com.baseFramework.Lib.TestBase;
import com.baseFramework.PageObject.PatientPortalLoginPage;


public class LoginUiTest extends TestBase {


	@BeforeClass
	public void setUp() {
		appLibrary = new AppLibrary("SignUpValidationTest");
	}

	@Test
	public void testSearchOnBuyPage() throws Exception {

		appLibrary.getDriverInstance();
		appLibrary.launchApp();// launching the application in browser
		PatientPortalLoginPage lp = new PatientPortalLoginPage(appLibrary);
		lp.verifyLoginPageUi();
	}

}
