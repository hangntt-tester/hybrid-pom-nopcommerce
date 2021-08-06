package com.nopcommerce.user;

import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.nopCommerce.BlogPageObject;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.MyAccountPageObject;
import pageObjects.user.nopCommerce.OrdersPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;
import pageObjects.user.nopCommerce.SearchPageObject;

public class Level_13_Register_Login_Log_Extent_Report_V2 extends BaseTest{
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	MyAccountPageObject myAccountPage;
	RegisterPageObject registerPage;
	SearchPageObject searchPage;
	OrdersPageObject ordersPage;
	BlogPageObject blogPage;
	
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, day, month, year, emailAddress, companyName, password;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		
		homePage = PageGeneratorManager.getHomePage(driver);
		
		firstName = "Automation"; 
		lastName= "FC"; 
		day= "10"; 
		month= "May"; 
		year= "1999"; 
		emailAddress= "automationfc" + getRandomNumber() +"@mailinator.com"; 
		companyName= "Automation FC"; 
		password= "123456";
	}
	
	@Test
	public void User_01_Register(Method method) {
//		ExtentTestManager.startTest(method.getName(), "User_01_Register");
//
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 01: Click to Register link");
//		registerPage = homePage.clickToRegisterLink();
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 02: Click to Male radio button");
//		registerPage.clickToGenderMaleRadio();
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 03: Enter to Firstname textbox");
//		registerPage.enterToFirstNameTextbox(firstName);
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 04: Enter to Lastname textbox");
//		registerPage.enterToLastNameTextbox(lastName);
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 05: Select Day dropdown");
//		registerPage.selectDayDropdown(day);
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 06: Select Month dropdown");
//		registerPage.selectMonthDropdown(month);
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step07: Select Year dropdown");
//		registerPage.selectYearDropdown(year);
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 08: Enter to Email textbox with value: " + emailAddress);
//		registerPage.enterToEmailTextbox(emailAddress);
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 09: Enter to Company textbox with value: " + companyName);
//		registerPage.enterToCompanyTextbox(companyName);
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 10: Enter to Password textbox with value: " + password);
//		registerPage.enterToPasswordTextbox(password);
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 11: Enter to Confirm Password textbox with value: " + password);
//		registerPage.enterToConfirmasswordTextbox(password);
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 12: Click to Register button");
//		registerPage.clickToRegisterButton();
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 13: Verify success Message is displayed");
//		verifyFalse(registerPage.isRegisterSuccessMessageDisplayed()); // cố tình cho nó false
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_01_Register - Step 14: Click to Logout link");
//		homePage = registerPage.clickToLogoutLink();
//		
//		ExtentTestManager.endTest();
//		}
//	
//	@Test
//	public void User_02_Login(Method method) {
//		ExtentTestManager.startTest(method.getName(), "User_02_Login");
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Login - Step 01: Click to Login link");
//		loginPage = homePage.clickToLoginLink();
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Login - Step 02: Enter to Email textbox with value: " + emailAddress);
//		loginPage.enterToEmailTextbox(emailAddress);
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Login - Step 03: Enter to Password textbox with value: " + password);
//		loginPage.enterToPasswordTextbox(password);
//		
//		ExtentTestManager.getTest().log(LogStatus.INFO, "User_02_Login - Step 04: Click to Login button");
//		homePage = loginPage.clickToLoginButton();
//		
//		ExtentTestManager.endTest();
	}
	
	@AfterClass
	public void cleanClass() {
		driver.quit();
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
