package com.baseFramework.PageObject;

import com.baseFramework.Lib.AppLibrary;

public class PatientPortalDashBoard {
	AppLibrary appLibrary;
	public String logo = "xpath:-://img[@id='main-logo']";
	public String setting = "xpath:-://span[text()='Settings']";
	
	public PatientPortalDashBoard(AppLibrary appLibrary)
	{
		this.appLibrary = appLibrary;
	}
	
	public EditPatientProfile clickSetting() throws Exception {
		appLibrary.clickElement(setting);
		return new EditPatientProfile(appLibrary);
	}
	
	public void verifyLogo() throws Exception {
		appLibrary.verifyElement(logo, true, 2);
	}
	
	

}
