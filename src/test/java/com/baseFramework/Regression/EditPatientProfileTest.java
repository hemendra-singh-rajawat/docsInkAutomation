package com.baseFramework.Regression;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baseFramework.Lib.AppLibrary;
import com.baseFramework.Lib.TestBase;

import com.baseFramework.PageObject.EditPatientProfile;
import com.baseFramework.PageObject.LoginPage;


	public class EditPatientProfileTest extends TestBase {

		@BeforeClass
		public void setUp() {
			appLibrary = new AppLibrary("FileUploadTest");
		}

		@Test
		public void fileUploadTest() throws Exception {

			appLibrary.getDriverInstance();
			appLibrary.launchApp();// launching the application in browser
			LoginPage lp = new LoginPage(appLibrary);
			EditPatientProfile ful = new  EditPatientProfile(appLibrary);
			lp.fillLoginDetails("harryrider@mailinator.com", "Test@123");
			Thread.sleep(2000);
			ful.clickProfile();
			ful.profile();
		}
			

}
