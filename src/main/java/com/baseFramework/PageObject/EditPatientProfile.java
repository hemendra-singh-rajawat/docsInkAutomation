package com.baseFramework.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.baseFramework.Lib.AppLibrary;

public class EditPatientProfile {

	AppLibrary appLibrary;

	public String profile = "xpath:-://span[text()='Settings']";
	public String preferredName = "xpath:-://input[@id='preferred_name']";
	public String phoneNumber = "xpath:-://input[@id='phone_number']";
	public String editIcon = "xpath:-://input[@type='file']";
	public String saveChangesButton = "xpath:-://button[@class='green-bg-btn']//span[text()='SAVE CHANGES']";
	public String address = "xpath:-://input[@id='address']";
	public String verifySuccessMessage = "xpath:-://div[text()='Account Details Updated Successfully']";

	public EditPatientProfile(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	public void clickProfile() throws Exception {
		appLibrary.clickElement(profile);
	}

	public void profile() throws Exception {

		String expectedPreferredName = "Potter";
		appLibrary.sleep(5000);
		appLibrary.sleep(5000);
		appLibrary.findElement(preferredName).sendKeys(Keys.CONTROL, "a");
		appLibrary.findElement(preferredName).sendKeys(Keys.DELETE);
		appLibrary.enterText(preferredName, "Potter");
		appLibrary.enterText(address, "88 street road");
		appLibrary.clickElement(saveChangesButton);
		appLibrary.waitForPageToLoad();
		String actualPreferredName = appLibrary.findElement(preferredName).getAttribute("value").toString();
		Assert.assertEquals(actualPreferredName, expectedPreferredName);
	}
}
