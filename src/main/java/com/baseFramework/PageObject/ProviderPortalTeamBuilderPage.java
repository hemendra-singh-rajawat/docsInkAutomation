package com.baseFramework.PageObject;

import com.baseFramework.Lib.AppLibrary;

public class ProviderPortalTeamBuilderPage {

	AppLibrary appLibrary;

	public String teamBuilder = "xpath:-://span[text()='Team Builder']";
	public String cube = "xpath:-://i[@class='fal fa-cube']";
	public String patientMessangerApp = "xpath:-://span[text()='Patient Messenger']";
	public String chat = "xpath:-://i[@class='fal fa-comments']";

	public ProviderPortalTeamBuilderPage(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	public void verifyLoginPageUi() throws Exception {

		appLibrary.verifyElement(teamBuilder, true, 0);
		appLibrary.verifyElement(cube, true, 0);
		appLibrary.verifyElement(patientMessangerApp, true, 0);

	}

	public ProviderPortalTeamBuilderPage clickCube() throws Exception {
		appLibrary.clickElement(cube);
		return new ProviderPortalTeamBuilderPage(appLibrary);

	}

	public PatientMessengerPage clickpatientMessangerApp() throws Exception {
		appLibrary.clickElement(patientMessangerApp);
		return new PatientMessengerPage(appLibrary);
	}

	public ProviderPortalDashBoardPage clickChat() throws Exception {
		appLibrary.clickElement(chat);
		return new ProviderPortalDashBoardPage(appLibrary);
	}
}
