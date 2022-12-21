package com.baseFramework.PageObject;

import com.baseFramework.Lib.AppLibrary;

public class MyARCSAssessmentPage {

	AppLibrary appLibrary;

	// public String actualAssessmentName = "xpath:-://table[1]/tbody/tr[1]/td[1]";
	String assessmentlocator = "xpath:-://td[text()='replace']";

	public MyARCSAssessmentPage(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	public void verifyCreateMyAssessment(String assessmentName) throws Exception {

		appLibrary.verifyElement(assessmentlocator.replace("replace", assessmentName), true, 0);

	}
}
