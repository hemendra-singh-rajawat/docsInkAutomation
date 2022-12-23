package com.baseFramework.PageObject;

import com.baseFramework.Lib.AppLibrary;

public class PatientMessengerPage {

	AppLibrary appLibrary;
	public String RPM = "xpath:-://span[text()='Remote Patient Monitoring Program']";
	public String ARCS = "xpath:-://span[text()='ARCS']";

	public PatientMessengerPage(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	public ARCSAssessmentCreationPage clickARCS() throws Exception {
		appLibrary.clickElement(ARCS);
		return new ARCSAssessmentCreationPage(appLibrary);
	}
	
	public RpmProgramCreationPage clickRPM() throws Exception {
		appLibrary.clickElement(RPM);
		return new RpmProgramCreationPage(appLibrary);
	}

	

}
