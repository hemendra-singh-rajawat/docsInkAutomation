package com.baseFramework.PageObject;

import com.baseFramework.Lib.AppLibrary;

public class PatientPortalDashBoard {
	AppLibrary appLibrary;
	public String logo = "xpath:-://img[@id='main-logo']";
	
	public PatientPortalDashBoard(AppLibrary appLibrary)
	{
		this.appLibrary = appLibrary;
	}
	public void verifyLogo() throws Exception {
		appLibrary.verifyElement(logo, true, 2);
	}

}
