package com.baseFramework.PageObject;

import com.baseFramework.Lib.AppLibrary;

public class PatientPortalSignUpPage {

	AppLibrary appLibrary;
	
	public String searchBox = "xpath:-://input[@id='search']";
	public String mailinatorMail = "xpath:-://td[@class='ng-binding']";
	public String goButton = "xpath:-://button[text()='GO']";
	public String clickHereLink = "xpath:-://a[text()='Click Here']";
	public String password = "xpath:-://input[@placeholder='Enter your new password']";
	public String confirmPassword = "xpath:-://input[@placeholder='Enter your password again']";
	public String checkBox = "xpath:-://input[@id='remember']";
	public String registerButton = "xpath:-://button[@class='submit-btn green-bg-btn']";
	public String setting = "xpath:-://span[text()='Settings']";
	public String emailVerification = "xpath:-://input[@id='email']";
	
	
	
	public PatientPortalSignUpPage(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	
	
	public void verifyPatientPortalSignUpPageUi() throws Exception {
		appLibrary.verifyElement(searchBox, true, 0);
		appLibrary.verifyElement(mailinatorMail, true, 0);
		appLibrary.verifyElement(goButton , true, 0);
		appLibrary.verifyElement(clickHereLink, true, 0);
	    appLibrary.verifyElement(password, true, 0);
	    appLibrary.verifyElement(confirmPassword, true, 0);
	    appLibrary.verifyElement(checkBox, true, 0);
	    appLibrary.verifyElement(registerButton, true, 0);
	    appLibrary.verifyElement(setting, true, 0);
		appLibrary.verifyElement(emailVerification, true, 0);
	}
	
public PatientPortalSignUpPage emailVerification(String email) throws Exception {
	appLibrary.launchApp("https://www.mailinator.com");
	appLibrary.enterText(searchBox, email);
	appLibrary.clickElement(goButton);
	appLibrary.clickElement(mailinatorMail);
	appLibrary.switchToFrame("html_msg_body");
	appLibrary.scrollIframe();
    appLibrary.clickElement(clickHereLink);
    appLibrary.SwitchWindow();
    appLibrary.enterText(password, "Test@123");
    appLibrary.enterText(confirmPassword, "Test@123");
    appLibrary.clickElement(checkBox);
    appLibrary.clickElement(registerButton);
    return new PatientPortalSignUpPage(appLibrary);
}
	
	public void patientCreationVerification() throws Exception {
		appLibrary.waitTillElementLoaded(setting);
		appLibrary.clickElementWithJs(setting);
		appLibrary.verifyElement(emailVerification, true, 200);
	}





	}

