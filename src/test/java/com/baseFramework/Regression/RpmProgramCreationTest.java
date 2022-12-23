package com.baseFramework.Regression;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baseFramework.Lib.AppLibrary;
import com.baseFramework.Lib.TestBase;
import com.baseFramework.PageObject.ProviderPortalLoginPage;

public class RpmProgramCreationTest extends TestBase {

	@BeforeClass
	public void setUp() {
		appLibrary = new AppLibrary("RpmProgramCreationTest");
	}

	@Test
	public void RpmAssessmentCreationTest() throws Exception {
		String rpmName = "TestRPM1";

		appLibrary.getDriverInstance();
		appLibrary.launchApp();// launching the application in browser
		new ProviderPortalLoginPage(appLibrary).fillLoginDetails(getProviderUserID(), getProviderPass()).clickCube()
				.clickpatientMessangerApp().clickRPM().clickCreateRPM().createRpmProgram(rpmName)
				.verifyRpmProgram(rpmName);

	}

}
