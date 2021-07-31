package com.facebook.register;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facabook.PageGeneratorManager;
import pageObjects.facabook.RegisterPageObject;

public class Level_12_Register_Login_Assert_Verify extends BaseTest{
	WebDriver driver;
	String emailAddress, password;
	RegisterPageObject registerPage;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		
		registerPage = PageGeneratorManager.getRegisterPage(driver);
	}
	
	@Test
	public void TC_01_Verify( ) {
		// Fail lần 1
		verifyFalse(registerPage.isEmailTextboxDisplayed());
		
		registerPage.enterToEmailTextbox("dam@gmail.com");
		registerPage.sleepInSecond(3);
		
		// Fail lần 2
		verifyFalse(registerPage.isConfirmEmailTextboxDisplayed());
		
		registerPage.enterToEmailTextbox("");
		registerPage.sleepInSecond(3);
		
		verifyFalse(registerPage.isConfirmEmailTextboxDisplayed());
		
		// Fail lần 3
		verifyFalse(registerPage.isLoginButtonUndisplayed());
	}
	
	@AfterClass
	public void cleanBrowser() {
		driver.quit();
	}
	
}
