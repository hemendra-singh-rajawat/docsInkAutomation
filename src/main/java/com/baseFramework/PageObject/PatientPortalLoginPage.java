package com.baseFramework.PageObject;

import org.testng.Assert;

import com.baseFramework.Lib.AppLibrary;

public class PatientPortalLoginPage {

	AppLibrary appLibrary;
	public String emailInput = "xpath:-://input[@placeholder='Enter your email']";
	public String passwordInput = "xpath:-://input[@placeholder='Enter your password']";
	public String hello = "xpath:-://h3[text()='Hey, good to see you again!']";
	public String signIn = "xpath:-://h3[text()='Sign in']";
	public String email = "xpath:-://label[text()='Email']";
	public String password = "xpath:-://label[text()='Password']";
	public String rememberMeLabel = "xpath:-://span[text()='Remember me']";
	public String logInButton = "xpath:-://button[contains(@class,'submit-btn-disabled')]";
	public String rememberMeCheckBox = "xpath:-://span[@class='ant-checkbox-inner']";
	public String logInButtonGreen = "xpath:-://button[contains(@class,'submit-btn')]";
	public String invalidCred = "xpath:-://span[@class='text-danger fs-5']";
	public String greet = "xpath:-://h3[text()='Hey, good to see you again!']";

	public PatientPortalLoginPage(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	public void verifyLoginPageUi() throws Exception {
		appLibrary.verifyElement(emailInput, true, 0);
		appLibrary.verifyElement(passwordInput, true, 0);
		appLibrary.verifyElement(hello, true, 0);
		appLibrary.verifyElement(signIn, true, 0);
		appLibrary.verifyElement(email, true, 0);
		appLibrary.verifyElement(password, true, 0);
		appLibrary.verifyElement(rememberMeLabel, true, 0);
		appLibrary.verifyElement(logInButton, true, 0);
		appLibrary.verifyElement(rememberMeCheckBox, true, 0);

	}

	public PatientPortalDashBoard fillLoginDetails(String email, String pass) throws Exception {
		appLibrary.enterText(emailInput, email);
		appLibrary.enterText(passwordInput, pass);
		appLibrary.clickElement(logInButtonGreen);
		return new PatientPortalDashBoard(appLibrary);

	}

}
