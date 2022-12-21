package com.baseFramework.Regression;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baseFramework.Lib.AppLibrary;
import com.baseFramework.Lib.TestBase;
import com.baseFramework.PageObject.PatientPortalDashBoard;
import com.baseFramework.PageObject.PatientPortalLoginPage;

public class PatientPortalLoginFuctionalityTest extends TestBase {
	
	@BeforeClass
	public void setUp() {
		appLibrary = new AppLibrary("PatientPortalLoginFuctionalityTest");
	}

	@Test
	public void loginFunctionalityTest() throws Exception { 
		
		appLibrary.getDriverInstance();
		appLibrary.launchApp("https://devpatient.docsink.com");// launching the application in browser
		PatientPortalLoginPage lp = new PatientPortalLoginPage(appLibrary);
		lp.fillLoginDetails("harryrider@mailinator.com", "Test@123").verifyLogo();
	}

}
