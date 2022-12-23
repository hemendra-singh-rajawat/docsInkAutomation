package com.baseFramework.PageObject;

import com.baseFramework.Lib.AppLibrary;

public class ProviderPortalDashBoardPage {

	AppLibrary appLibrary;
	public String patients = "xpath:-://i[@class='fal fa-id-card']";
	public String addNewPatients = "xpath:-://a[@id='add-new-patient']";
	public String appAlert = "xpath:-://i[@class='fa fa-times']";
	
	
	
	public ProviderPortalDashBoardPage(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	
public ProviderPortalDashBoardPage clickPatients() throws Exception {
	appLibrary.clickElement(patients);
	return new ProviderPortalDashBoardPage(appLibrary);
}
public ProviderPortalDashBoardPage dismissAlert() throws Exception {
	appLibrary.clickElement(appAlert);
	return new ProviderPortalDashBoardPage(appLibrary);
}
	
public PatientCreationFillDetailsPage clickAddNewPatients() throws Exception {
	appLibrary.waitTillElementLoaded(addNewPatients);
	appLibrary.clickElement(addNewPatients);
	return new PatientCreationFillDetailsPage(appLibrary);
}





	}

