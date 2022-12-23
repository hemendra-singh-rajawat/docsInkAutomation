package com.baseFramework.PageObject;

import org.openqa.selenium.Keys;

import com.baseFramework.Lib.AppLibrary;

public class EditPatientProfile {

	AppLibrary appLibrary;

	public String preferredName = "xpath:-://input[@id='preferred_name']";
	public String phoneNumber = "xpath:-://input[@id='phone_number']";
	public String editIcon = "xpath:-://input[@type='file']";
	public String saveChangesButton = "xpath:-://button[@class='green-bg-btn']//span[text()='SAVE CHANGES']";
	public String address = "xpath:-://input[@id='address']";
	public String verifySuccessMessage = "xpath:-://div[text()='Account Details Updated Successfully']";

	public EditPatientProfile(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	public EditPatientProfileVerification accountDetails() throws Exception {

		appLibrary.findElement(preferredName).sendKeys(Keys.CONTROL, "a");
		appLibrary.findElement(preferredName).sendKeys(Keys.DELETE);
		appLibrary.enterText(preferredName, "Potter");
		appLibrary.enterText(address, "88 street road");
		appLibrary.clickElement(saveChangesButton);
		appLibrary.waitForPageToLoad();
		
		return new EditPatientProfileVerification(appLibrary);
	}
}
