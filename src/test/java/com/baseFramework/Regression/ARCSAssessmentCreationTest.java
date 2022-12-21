package com.baseFramework.Regression;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baseFramework.Lib.AppLibrary;
import com.baseFramework.Lib.TestBase;
import com.baseFramework.PageObject.ARCSAssessmentCreation;
import com.baseFramework.PageObject.ARCSAssessmentCreationPage;
import com.baseFramework.PageObject.ProviderPortalLoginPage;
import com.baseFramework.PageObject.ProviderPortalTeamBuilderPage;

public class ARCSAssessmentCreationTest extends TestBase {

	@BeforeClass
	public void setUp() {
		appLibrary = new AppLibrary("ARCSAssessmentCreation");
	}

	@Test
	public void ARCSAssessmentCreation() throws Throwable {
		String assessmentName = "TestArcsAuto7";

		appLibrary.getDriverInstance();
		appLibrary.launchApp("https://dev.docsink.com");// launching the application in browser
		ProviderPortalLoginPage pplp = new ProviderPortalLoginPage(appLibrary);
		pplp.fillLoginDetails("dseals+admin@docsink.com", "Beer2nite!")
		.clickCube()
		.clickpatientMessangerApp()
		.clickARCS()
		.clickCreateARCS()
		.createARCSAssessment(assessmentName)
		.verifyCreateMyAssessment(assessmentName);
		
		
		
		//appLibrary.sleep(5000);
		//ProviderPortalTeamBuilderPage pptp = new ProviderPortalTeamBuilderPage(appLibrary);
//		pptp.clickCube();
		//pptp.clickpatientMessangerApp();
		
//		ARCSAssessmentCreationPage aacp = new ARCSAssessmentCreationPage(appLibrary);
//		aacp.clickARCS();
//		aacp.clickCreateARCS();
//		aacp.createARCSAssessment(assessmentName).verifyCreateMyAssessment(assessmentName);
//		//appLibrary.verification(assessmentlocator,assessmentName);

	}

}
