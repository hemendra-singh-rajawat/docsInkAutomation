package com.baseFramework.PageObject;

import org.testng.Assert;

import com.baseFramework.Lib.AppLibrary;

public class PatientCreationFillDetailsPage {

	AppLibrary appLibrary;
	
	public String firstName = "xpath:-://input[@id='fname']";
	public String lastName = "xpath:-://input[@id='lname']";
	public String dob = "xpath:-://input[@id='patient-dob']";
	public String email = "xpath:-://input[@id='email']";
	public String radioButton = "xpath:-://label[@id='send-invite-label']";
	public String selectDropDown = "xpath:-://select[@id='patient-arc-communication-preference']";
	public String selectEmail = "xpath:-://option[text()='Email']";
	public String saveButton = "xpath:-://button[text()='Save']";
	public String okButton = "xpath:-://button[text()='OK']";
	
	
	public PatientCreationFillDetailsPage(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	public void verifyPatientCreationFillDetailsPageUi() throws Exception {
		
	    appLibrary.verifyElement(firstName, true, 0);
	    appLibrary.verifyElement(lastName, true, 0);
	    appLibrary.verifyElement(dob, true, 0);
	    appLibrary.verifyElement(email, true, 0);
	    appLibrary.verifyElement(selectDropDown, true, 0);
		appLibrary.verifyElement(selectEmail, true, 0);
	    appLibrary.verifyElement(saveButton, true, 0);
	    appLibrary.verifyElement(okButton, true, 0);
	}
	
public PatientPortalSignUpPage fillDetails(String fname,String lname,String emailId ) throws Exception {
 
	appLibrary.enterText(firstName, fname);
	appLibrary.enterText(lastName, lname);
	appLibrary.enterText(dob, "01/09/1999");
	appLibrary.enterText(email, emailId);
	appLibrary.clickElement(radioButton);
	appLibrary.clickElement(selectDropDown);
	appLibrary.clickElement(selectEmail);
	appLibrary.clickElement(saveButton);
	appLibrary.clickElement(okButton);
	return new PatientPortalSignUpPage(appLibrary);
}
	}

