package com.alada.login;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.alada.HomePageObject;
import pageObjects.alada.LoginPageObject;

public class Level_04_Login_Multiple_Browser extends BaseTest {
	WebDriver driver;
	LoginPageObject loginPage;
	HomePageObject homePage;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = new LoginPageObject(driver);
	}
	
	@Test
	public void TC_01_Login_With_Empty_Email_Password( ) {
		loginPage.enterToEmailTextbox("");
		loginPage.enterToPasswordTextbox("");
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Vui lòng nhập email");
		Assert.assertEquals(loginPage.getErrorMessageAtPasswordTextbox(), "Vui lòng nhập mật khẩu");
	}
	
	@Test
	public void TC_02_Login_With_Invalid_Email( ) {
		// Invalid Email
		loginPage.enterToEmailTextbox("automationfc@#$");
		loginPage.enterToPasswordTextbox("123456");
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Vui lòng nhập email hợp lệ");
		
		// Invalid Email
		loginPage.enterToEmailTextbox("12345678");
		loginPage.enterToPasswordTextbox("123456");
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Vui lòng nhập email hợp lệ");
	}
	
	@Test
	public void TC_03_Login_With_Email_Not_Registered ( ) {
		loginPage.enterToEmailTextbox("automationfc" + getRandomNumber() + "@hotmail.com");
		loginPage.enterToPasswordTextbox("123");
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtLoginForm(), "Email này chưa được đăng ký.");
	}
	
	@Test
	public void TC_04_Login_With_Invalid_Password ( ) {
		// Invalid Password
		loginPage.enterToEmailTextbox("automationfc123@hotmail.com");
		loginPage.enterToPasswordTextbox("123");
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtLoginForm(), "Mật khẩu sai.");
		
		// Incorrect Password
		loginPage.enterToEmailTextbox("automationfc123@hotmail.com");
		loginPage.enterToPasswordTextbox("123456789");
		loginPage.clickLoginButton();
		
		Assert.assertEquals(loginPage.getErrorMessageAtLoginForm(), "Mật khẩu sai.");
	}
	
	@Test
	public void TC_05_Login_With_Valid_Email_Password ( ) {
		loginPage.enterToEmailTextbox("automationfc123@hotmail.com");
		loginPage.enterToPasswordTextbox("123456");
		loginPage.clickLoginButton();
		homePage = new HomePageObject(driver);
		
		Assert.assertTrue(homePage.isMyCourseDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
