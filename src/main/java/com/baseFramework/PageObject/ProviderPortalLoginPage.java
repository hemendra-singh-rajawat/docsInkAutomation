package com.baseFramework.PageObject;

import com.baseFramework.Lib.AppLibrary;

public class ProviderPortalLoginPage {

	AppLibrary appLibrary;
	
	public String emailInput = "xpath:-://input[@id='email']";
	public String passwordInput = "xpath:-://input[@id='password']";
	public String signInButton = "xpath:-://button[text()='Sign in']";
	
	
	
	public ProviderPortalLoginPage(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	
	
	public void verifyLoginPageUi() throws Exception {
		appLibrary.verifyElement(emailInput, true, 0);
		appLibrary.verifyElement(passwordInput, true, 0);
		appLibrary.verifyElement(signInButton , true, 0);
		

	}
	
	public ProviderPortalTeamBuilderPage fillLoginDetails(String email, String pass) throws Exception {
		appLibrary.enterText(emailInput, email);
		appLibrary.enterText(passwordInput, pass);
		appLibrary.clickElement(signInButton);
		return new ProviderPortalTeamBuilderPage(appLibrary);

	}
	
	





	}

