package com.facebook.register;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facabook.PageGeneratorManager;
import pageObjects.facabook.RegisterPageObject;

public class Level_11_Register_Login_Element_Undisplayed extends BaseTest{
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
	public void TC_01_Element_Displayed( ) {
		Assert.assertTrue(registerPage.isEmailTextboxDisplayed());
		
		registerPage.enterToEmailTextbox("dam@gmail.com");
		registerPage.sleepInSecond(3);
		
		Assert.assertTrue(registerPage.isConfirmEmailTextboxDisplayed());
	}
	
	@Test
	public void TC_02_Element_Undisplayed_In_DOM( ) {
		registerPage.enterToEmailTextbox("");
		registerPage.sleepInSecond(3);
		
		Assert.assertFalse(registerPage.isConfirmEmailTextboxDisplayed());
	}
	
	@Test
	public void TC_03_Element_Undisplayed_Not_In_DOM( ) {
		Assert.assertFalse(registerPage.isLoginButtonDisplayed());
	}
	
	@Test
	public void TC_04_Element_Undisplayed_Not_In_DOM( ) {
		Assert.assertTrue(registerPage.isLoginButtonUndisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
