package com.baseFramework.PageObject;

import com.baseFramework.Lib.AppLibrary;

public class RpmProgramCreationPage {

	AppLibrary appLibrary;
	public String rpm = "xpath:-://span[text()='Remote Patient Monitoring Program']";
	public String createRPM = "xpath:-://span[normalize-space()='Create RPM Program']";
	public String title = "xpath:-://input[@id='rpm_title']";
	public String provider = "xpath:-: //span[@id='select2-rpm_user-container']";
	public String selectProvider = "xpath:-: //li[text()='Ashley Williams']";
	public String description = "xpath:-://textarea[@placeholder='Description']";
	public String conditions = "xpath:-://input[@placeholder='Search to find an ICD 10 Diagnosis code']";
	public String conditionSelect = "xpath:-://li[text()='N15.1 - Kidney abscess']";
	public String rpmFlag = "xpath:-://select[@id='rpm_flag']";
	public String rpmWorkFlow = "//select[@id='rpm_workflows']";
	public String addCoverImag = "xpath:-: //span[@class='mb-1 fs-xl pointr addCoverImg']//i[@class='fal fa-plus-circle ml-2']";
	public String coverImagSelect = "xpath:-://div[@id='addImages']//span[1]//img[1]";
	public String item = "xpath:-://span[@id='select2-rpm_items-container']";
	public String bp = "xpath:-://li[text()='replace']";
	public String selectItem = "xpath:-://th[normalize-space()='Model']";
	public String highValue = "xpath:-://input[@placeholder='High value']";
	public String lowValue = "xpath:-://input[@placeholder='Low value']";
	public String addDevice = "xpath:-://a[@id='add-device-btn']";
	public String selectAll = "xpath:-://label[@for='ckbCheckAll']";
	public String saveButton = "xpath:-://div[button[text()='Close']]/button[text()='Save']";
	public String rpmProgramPageSaveButton = "xpath:-://div[div[@id='rpm-view']]//button[text()='Save']";
	public String rpmAssessmentName = "xpath:-://table[1]/tbody/tr[1]/td[1]";

	public RpmProgramCreationPage(AppLibrary appLibrary) {
		this.appLibrary = appLibrary;
	}

	public void verifyLoginPageUi() throws Exception {
		appLibrary.verifyElement(rpm, true, 0);
		appLibrary.verifyElement(createRPM, true, 0);
		appLibrary.verifyElement(title, true, 0);
		appLibrary.verifyElement(provider, true, 0);
		appLibrary.verifyElement(selectProvider, true, 0);
		appLibrary.verifyElement(description, true, 0);
	}

	public RpmProgramCreationPage clickRPM() throws Exception {
		appLibrary.clickElement(rpm);
		return new RpmProgramCreationPage(appLibrary);
	}

	public RpmProgramCreationPage clickCreateRPM() throws Exception {
		appLibrary.clickElement(createRPM);
		return new RpmProgramCreationPage(appLibrary);

	}

	public MyRpmProgramPage createRpmProgram(String rpmName) throws Exception {
		appLibrary.enterText(title, rpmName);
		appLibrary.clickElement(provider);
		appLibrary.clickElement(selectProvider);
		appLibrary.enterText(conditions, "Kid");
		appLibrary.clickElement(conditionSelect);
		appLibrary.clickElement(rpmFlag);
		appLibrary.enterText(description, "Kidney is not working as ");
		appLibrary.selectOption(rpmWorkFlow, 1);
		appLibrary.clickElement(addCoverImag);
		appLibrary.clickElement(coverImagSelect);
		appLibrary.clickElement(item);
		appLibrary.clickElement(bp.replace("replace", "Blood Glucose"));
		appLibrary.dropDown();
		appLibrary.enterText(highValue, "160");
		appLibrary.enterText(lowValue, "90");
		appLibrary.clickElement(addDevice);
		appLibrary.clickElement(selectAll);
		appLibrary.waitTillElementClickable(saveButton);
		appLibrary.clickElement(saveButton);
		appLibrary.waitTillElementLoaded(rpmProgramPageSaveButton);
		appLibrary.clickElement(rpmProgramPageSaveButton);
		return new MyRpmProgramPage(appLibrary);
	}

}
