package com.baseFramework.PageObject;

import org.testng.Assert;

import com.baseFramework.Lib.AppLibrary;

public class EditPatientProfileVerification {


	public String preferredName = "xpath:-://input[@id='preferred_name']";
	
	AppLibrary appLibrary;
	
	public EditPatientProfileVerification(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	public void editProfileVerify() throws Exception {

		String expectedPreferredName = "Potter";
		String actualPreferredName = appLibrary.findElement(preferredName).getAttribute("value").toString();
		Assert.assertEquals(actualPreferredName, expectedPreferredName);
	}
}
