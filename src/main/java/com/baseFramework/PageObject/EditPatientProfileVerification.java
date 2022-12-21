package com.baseFramework.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.baseFramework.Lib.AppLibrary;

public class EditPatientProfileVerification {

	AppLibrary appLibrary;

	public String preferredName = "xpath:-://input[@id='preferred_name']";
	
	public EditPatientProfileVerification(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	public void editProfileVerify() throws Exception {

		String expectedPreferredName = "Potter";
		String actualPreferredName = appLibrary.findElement(preferredName).getAttribute("value").toString();
		Assert.assertEquals(actualPreferredName, expectedPreferredName);
	}
}
