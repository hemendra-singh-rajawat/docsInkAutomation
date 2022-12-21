package com.baseFramework.Regression;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baseFramework.Lib.AppLibrary;
import com.baseFramework.Lib.TestBase;
import com.baseFramework.PageObject.PatientCreation;
import com.baseFramework.PageObject.PatientCreationFillDetailsPage;
import com.baseFramework.PageObject.PatientPortalSignUpPage;
import com.baseFramework.PageObject.ProviderPortalDashBoardPage;
import com.baseFramework.PageObject.ProviderPortalLoginPage;

public class PatientCreationTest extends TestBase {

	private String emailId;

	@BeforeMethod
	@BeforeClass
	public void setUp() {
		appLibrary = new AppLibrary("PatientCreationTest");
	}

	@Test
	public void PatientCreationTest() throws Exception {
		appLibrary.getDriverInstance();
		appLibrary.launchApp();// launching the application in browser

		String fname = "Harry" + appLibrary.getFormattedDate();
		String lname = "joa" + appLibrary.getFormattedDate();

		ProviderPortalLoginPage lp = new ProviderPortalLoginPage(appLibrary);
		lp.fillLoginDetails("dseals+admin@docsink.com", "Beer2nite!").
		clickChat()
		.clickPatients().dismissAlert().clickAddNewPatients().fillDetails(fname, lname, fname + "@mailinator.com").emailVerification(fname + "@mailinator.com").patientCreationVerification();;
		
		
		
//		
//		ProviderPortalDashBoardPage ppdp = new ProviderPortalDashBoardPage(appLibrary);
//		ppdp.clickChat();
//		ppdp.clickPatients();
//		ppdp.dismissAlert();
//		ppdp.clickAddNewPatients();
//		PatientCreationFillDetailsPage pcfd = new PatientCreationFillDetailsPage(appLibrary);
//		pcfd.fillDetails(fname, lname, fname + "@mailinator.com");
//		
//		
//		PatientPortalSignUpPage ppsp = new PatientPortalSignUpPage(appLibrary);
//		ppsp.emailVerification(fname + "@mailinator.com");
//		ppsp.patientCreationVerification();

	}

}
