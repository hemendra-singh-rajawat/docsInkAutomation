package com.baseFramework.Regression;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.baseFramework.Lib.AppLibrary;
import com.baseFramework.Lib.TestBase;

import com.baseFramework.PageObject.LoginPage;

public class LoginFuctionalityTest extends TestBase {
	
	
	

	@BeforeClass
	public void setUp() {
		appLibrary = new AppLibrary("LoginFunctionalityTest");
	}

	@Test
	public void loginFunctionalityTest() throws Exception { 
		
		appLibrary.getDriverInstance();
		appLibrary.launchApp();// launching the application in browser
		LoginPage lp = new LoginPage(appLibrary);
		lp.fillLoginDetails("stark@mailinator.com", "Pass@123234");
		Thread.sleep(2000);
		String a = lp.invalidcredential();
		System.out.println(a);
		Assert.assertEquals(a, "You have entered wrong credentials.");
		appLibrary.getScreenshot("docinkloginpage");
		
		//DashBoardPage dbp = new DashBoardPage(appLibrary);
		//dbp.dashBoardPageUi();
//		DashBoardPage2 dbp2 = new DashBoardPage2(appLibrary);
//		dbp2.dashBoardPage2Ui();
				
	}

}
