package com.baseFramework.PageObject;

import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;

import com.baseFramework.Lib.AppLibrary;

public class ProviderPortalTeamBuilderPage {

	AppLibrary appLibrary;
	
	public String teamBuilder = "xpath:-://span[text()='Team Builder']";
	public String cube = "xpath:-://i[@class='fal fa-cube']";
	public String patientMessangerApp = "xpath:-://span[text()='Patient Messenger']";
	
	public ProviderPortalTeamBuilderPage(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	public void verifyLoginPageUi() throws Exception {
		
		appLibrary.verifyElement(teamBuilder, true, 0);
		appLibrary.verifyElement(cube, true, 0);
		appLibrary.verifyElement(patientMessangerApp, true, 0);
	
	}

	public void clickCube() throws Exception {
		appLibrary.clickElement(cube);
	}

	public void clickpatientMessangerApp() throws Exception {
		appLibrary.clickElement(patientMessangerApp);
	}
}
