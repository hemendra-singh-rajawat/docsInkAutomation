package com.baseFramework.PageObject;

import com.baseFramework.Lib.AppLibrary;

public class ARCSAssessmentCreationPage {

	AppLibrary appLibrary;
	
	public String createARCS = "xpath:-://span[@data-i18n='nav.deviceless_rpm']";
	public String title = "xpath:-://input[@placeholder='Title']";
	public String surveyFlag = "xpath:-://select[@id='survey_flag']";
	public String surveyFlagSelect = "xpath:-://select[@id='survey_flag']//option[text()='ARCS Kidney']";
	public String description = "xpath:-://textarea[@placeholder='Description']";
	public String conditions = "xpath:-://input[@placeholder='Search to find an ICD 10 Diagnosis code']";
	public String conditionSelect = "xpath:-://li[text()='N15.1 - Kidney abscess']";
	public String okButton = "xpath:-://button[text()='OK']";
	public String nextbutton = "xpath:-://button[text()='Next']";
	public String addquestions = "xpath:-://button[normalize-space()='Add Question']";
	public String medicalFollowUp = "xpath:-://button[@id='medication_follow_up']";
	public String saveButton = "xpath:-://form[@id='quesForm_deviceless_rpm1']//button[contains(@type,'button')][normalize-space()='Save']";
	public String switchMonday = "xpath:-:xpath:-://label[normalize-space()='Monday']//span[contains(@class,'checkmark')]";
	public String mondayAllDay = "xpath:-://label[contains(@for,'mon_all_day')]";
	public String browserTest = "xpath:-://label[normalize-space()='accept']";
	public String smsTest = "xpath:-://form[@id='surveyType']//label[contains(@class,'form-label')][normalize-space()='SMS']//span[@class='checkmark']";
	public String voiceTest = "xpath:-://div[@role='form']//div//div//form//div//div//label[contains(text(),'Voice')]//span";
	public String finishButton = "xpath:-://button[text()='Finish']";
	public String inactive = "xpath:-://div[@data-id='NjAzNA==']//div[text()='Inactive']";
	public String myAssessmentLocator = "xpath:-://table[1]/tbody/tr[1]/td[1]";

	public ARCSAssessmentCreationPage(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	public void verifyARCSAssessmentCreationPageUi() throws Exception {
		
		appLibrary.verifyElement(createARCS, true, 0);
		appLibrary.verifyElement(title, true, 0);
		appLibrary.verifyElement(surveyFlag, true, 0);
		appLibrary.verifyElement(surveyFlagSelect, true, 0);
		appLibrary.verifyElement(description, true, 0);

	}


	public ARCSAssessmentCreationPage clickCreateARCS() throws Exception {
		appLibrary.clickElement(createARCS);
		return new ARCSAssessmentCreationPage(appLibrary);

	}
	public MyARCSAssessmentPage createARCSAssessment(String AssessmentName) throws Exception {

		appLibrary.enterText(title, AssessmentName);
		appLibrary.clickElement(surveyFlag);
		appLibrary.clickElement(surveyFlagSelect);
		appLibrary.enterText(description, "Kidney is not working as ");
		appLibrary.enterText(conditions, "Kid");
		appLibrary.clickElement(conditionSelect);
		appLibrary.clickElement(nextbutton);
		appLibrary.clickElement(okButton);
		appLibrary.clickElement(smsTest);
		appLibrary.clickElement(voiceTest);
		appLibrary.clickElement(nextbutton);
		appLibrary.clickElement(okButton);
		appLibrary.clickElement(nextbutton);
		appLibrary.clickElement(addquestions);
		appLibrary.clickElement(medicalFollowUp);
		appLibrary.clickElement(saveButton);
		appLibrary.clickElement(okButton);
		appLibrary.clickElement(nextbutton);
		appLibrary.clickElement(nextbutton);
		appLibrary.waitTillElementLoaded(okButton);
		appLibrary.clickElement(okButton);
		appLibrary.clickElement(browserTest);
		appLibrary.clickElement(finishButton);
		return new MyARCSAssessmentPage(appLibrary);

	}

}
