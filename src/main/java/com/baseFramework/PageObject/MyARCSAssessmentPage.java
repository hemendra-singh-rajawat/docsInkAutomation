package com.baseFramework.PageObject;

import com.baseFramework.Lib.AppLibrary;

public class MyARCSAssessmentPage {

	AppLibrary appLibrary;

	String assessmentlocator = "xpath:-://td[text()='replace']";

	public MyARCSAssessmentPage(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	public void verifyCreateMyAssessment(String assessmentName) throws Exception {

		appLibrary.verifyElement(assessmentlocator.replace("replace", assessmentName), true, 0);

	}
}
