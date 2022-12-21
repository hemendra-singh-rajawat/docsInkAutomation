package com.baseFramework.Regression;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baseFramework.Lib.AppLibrary;
import com.baseFramework.Lib.TestBase;

import com.baseFramework.PageObject.EditPatientProfile;
import com.baseFramework.PageObject.PatientPortalLoginPage;


	public class EditPatientProfileTest extends TestBase {

		@BeforeClass
		public void setUp() {
			appLibrary = new AppLibrary("FileUploadTest");
			
		}

		@Test
		public void editPatientProfile() throws Exception {

			appLibrary.getDriverInstance();
			appLibrary.launchApp("https://devpatient.docsink.com");// launching the application in browser
			PatientPortalLoginPage lp = new PatientPortalLoginPage(appLibrary);
			EditPatientProfile ful = new  EditPatientProfile(appLibrary);
			lp.fillLoginDetails("harryrider@mailinator.com", "Test@123");
			Thread.sleep(2000);
			ful.clickProfile();
			ful.profile();
		}
			

}
