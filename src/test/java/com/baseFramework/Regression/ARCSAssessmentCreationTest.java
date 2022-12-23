package com.baseFramework.Regression;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baseFramework.Lib.AppLibrary;
import com.baseFramework.Lib.TestBase;
import com.baseFramework.PageObject.ProviderPortalLoginPage;

public class ARCSAssessmentCreationTest extends TestBase {

	@BeforeClass
	public void setUp() {
		appLibrary = new AppLibrary("ARCSAssessmentCreationTest");
	}

	@Test
	public void ARCSAssessmentCreation() throws Throwable {
		String assessmentName = "TestArcsAuto" + appLibrary.getFormattedDate();

		appLibrary.getDriverInstance();
		appLibrary.launchApp();// launching the application in browser
		new ProviderPortalLoginPage(appLibrary).fillLoginDetails(getProviderUserID(), getProviderPass()).clickCube()
				.clickpatientMessangerApp().clickARCS().clickCreateARCS().createARCSAssessment(assessmentName)
				.verifyCreateMyAssessment(assessmentName);

	}

}
