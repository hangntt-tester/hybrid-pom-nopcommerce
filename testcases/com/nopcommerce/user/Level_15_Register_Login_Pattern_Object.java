package com.nopcommerce.user;

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

public class Level_15_Register_Login_Pattern_Object extends BaseTest{
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
		log.info("Pre-condition: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
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
	public void User_01_Register( ) {
		log.info("User_01_Register - Step 01: Click to Register link");
		homePage.openHeaderPageByName(driver, "Register");
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		
		log.info("User_01_Register - Step 02: Click to Male radio button");
		registerPage.clickToRadioButtonByID(driver, "gender-male");
		
		log.info("User_01_Register - Step 03: Enter to Firstname textbox");
		registerPage.enterToTextboxByID(driver, "FirstName", firstName);
		
		log.info("User_01_Register - Step 04: Enter to Lastname textbox");
		registerPage.enterToTextboxByID(driver, "LastName", lastName);
		
		log.info("User_01_Register - Step 05: Select Day dropdown");
		registerPage.selectDropdownByName(driver, "DateOfBirthDay", day);
		
		log.info("User_01_Register - Step 06: Select Month dropdown");
		registerPage.selectDropdownByName(driver, "DateOfBirthMonth", month);
		
		log.info("User_01_Register - Step07: Select Year dropdown");
		registerPage.selectDropdownByName(driver, "DateOfBirthYear", year);
		
		log.info("User_01_Register - Step 08: Enter to Email textbox with value: " + emailAddress);
		registerPage.enterToTextboxByID(driver, "Email", emailAddress);
		
		log.info("User_01_Register - Step 09: Enter to Company textbox with value: " + companyName);
		registerPage.enterToTextboxByID(driver, "Company", companyName);
		
		log.info("User_01_Register - Step 10: Enter to Password textbox with value: " + password);
		registerPage.enterToTextboxByID(driver, "Password", password);
		
		log.info("User_01_Register - Step 11: Enter to Confirm Password textbox with value: " + password);
		registerPage.enterToTextboxByID(driver, "ConfirmPassword", password);
		
		log.info("User_01_Register - Step 12: Click to Register button");
		registerPage.clickToButtonByText(driver, "Register");
		
		log.info("User_01_Register - Step 13: Verify success Message is displayed");
		verifyTrue(registerPage.isRegisterSuccessMessageDisplayed()); 
		
		log.info("User_01_Register - Step 14: Click to Logout link");
		registerPage.openHeaderPageByName(driver, "Log out");
		homePage = PageGeneratorManager.getHomePage(driver);
		}
	
	@Test
	public void User_02_Login( ) {
		log.info("User_02_Login - Step 01: Click to Login link");
		homePage.openHeaderPageByName(driver, "Log in");
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		log.info("User_02_Login - Step 02: Enter to Email textbox with value: " + emailAddress);
		loginPage.enterToTextboxByID(driver, "Email", emailAddress);
		
		log.info("User_02_Login - Step 03: Enter to Password textbox with value: " + password);
		loginPage.enterToTextboxByID(driver, "Password", password);
		
		log.info("User_02_Login - Step 04: Click to Login button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getHomePage(driver);
	}
	
	@Parameters({"browser"})
	@AfterClass(alwaysRun = true)
	public void cleanClass(String browserName) {
		log.info("Post-Condition: Close browser'" + browserName + "'");
		cleanDriverInstance();
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
