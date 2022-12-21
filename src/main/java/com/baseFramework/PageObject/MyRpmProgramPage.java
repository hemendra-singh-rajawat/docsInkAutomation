package com.baseFramework.PageObject;

import com.baseFramework.Lib.AppLibrary;

public class MyRpmProgramPage {

	AppLibrary appLibrary;

	
	String rpmLocator = "xpath:-://td[text()='replace']";

	public MyRpmProgramPage(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	public void verifyRpmProgram(String rpmName) throws Exception {

		appLibrary.verifyElement(rpmLocator.replace("replace", rpmName), true, 0);

	}
}
