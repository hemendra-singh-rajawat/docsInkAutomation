package com.baseFramework.Regression;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baseFramework.Lib.AppLibrary;
import com.baseFramework.Lib.TestBase;

import com.baseFramework.PageObject.LoginPage;
import com.baseFramework.PageObject.ProviderPortalLoginPage;
import com.baseFramework.PageObject.ProviderPortalTeamBuilderPage;
import com.baseFramework.PageObject.RpmProgramCreation;
import com.baseFramework.PageObject.RpmProgramCreationPage;

public class RpmProgramCreationTest extends TestBase {

	@BeforeClass
	public void setUp() {
		appLibrary = new AppLibrary("RpmAssessmentCreationTest");
	}

	@Test
	public void RpmAssessmentCreationTest() throws Exception {
		String rpmName = "TestRPM1";
		String rpmLocator = "xpath:-://td[text()='"+rpmName+"']";
		appLibrary.getDriverInstance();
		appLibrary.launchApp();// launching the application in browser
		ProviderPortalLoginPage pplp = new ProviderPortalLoginPage(appLibrary);
		pplp.fillLoginDetails("dseals+admin@docsink.com", "Beer2nite!");
		appLibrary.sleep(5000);
		ProviderPortalTeamBuilderPage pptp = new ProviderPortalTeamBuilderPage(appLibrary);
		pptp.clickCube();
		pptp.clickpatientMessangerApp();
		RpmProgramCreationPage rpcp = new RpmProgramCreationPage(appLibrary);
		rpcp.clickRPM();
		rpcp.clickCreateRPM();
		rpcp.createRpmProgram(rpmName);
		appLibrary.verification(rpmLocator, rpmName);

	}

}
