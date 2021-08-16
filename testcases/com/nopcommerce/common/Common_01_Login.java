package com.nopcommerce.common;

import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObjects.user.nopCommerce.BlogPageObject;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.MyAccountPageObject;
import pageObjects.user.nopCommerce.OrdersPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;
import pageObjects.user.nopCommerce.SearchPageObject;

public class Common_01_Login extends BaseTest{
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	MyAccountPageObject myAccountPage;
	RegisterPageObject registerPage;
	SearchPageObject searchPage;
	OrdersPageObject ordersPage;
	BlogPageObject blogPage;
	
	public static Set<Cookie> loginPageCookie;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, day, month, year, emailAddress, companyName, password;
	
	@Parameters({"browser", "url"})
	@BeforeTest
	public void beforeTest(String browserName, String appUrl) {
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
	
		log.info("Common_01 - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Common_01 - Step 02: Click to Male radio button");
		registerPage.clickToGenderMaleRadio();
		
		log.info("Common_01 - Step 03: Enter to Firstname textbox");
		registerPage.enterToFirstNameTextbox(firstName);
		
		log.info("Common_01 - Step 04: Enter to Lastname textbox");
		registerPage.enterToLastNameTextbox(lastName);
		
		log.info("Common_01 - Step 05: Select Day dropdown");
		registerPage.selectDayDropdown(day);
		
		log.info("Common_01 - Step 06: Select Month dropdown");
		registerPage.selectMonthDropdown(month);
		
		log.info("Common_01 - Step07: Select Year dropdown");
		registerPage.selectYearDropdown(year);
		
		log.info("Common_01 - Step 08: Enter to Email textbox with value: " + emailAddress);
		registerPage.enterToEmailTextbox(emailAddress);
		
		log.info("Common_01 - Step 09: Enter to Company textbox with value: " + companyName);
		registerPage.enterToCompanyTextbox(companyName);
		
		log.info("Common_01 - Step 10: Enter to Password textbox with value: " + password);
		registerPage.enterToPasswordTextbox(password);
		
		log.info("Common_01 - Step 11: Enter to Confirm Password textbox with value: " + password);
		registerPage.enterToConfirmasswordTextbox(password);
		
		log.info("Common_01 - Step 12: Click to Register button");
		registerPage.clickToRegisterButton();
		
		log.info("Common_01 - Step 13: Verify success Message is displayed");
		verifyTrue(registerPage.isRegisterSuccessMessageDisplayed());
		
		log.info("Common_01 - Step 14: Click to Logout link");
		homePage = registerPage.clickToLogoutLink();
		
		log.info("Common_01 - Step 15: Click to Login link");
		loginPage = homePage.clickToLoginLink();
		
		log.info("Common_01 - Step 16: Enter to Email textbox with value: " + emailAddress);
		loginPage.enterToEmailTextbox(emailAddress);
		
		log.info("Common_01 - Step 17: Enter to Password textbox with value: " + password);
		loginPage.enterToPasswordTextbox(password);
		
		log.info("Common_01 - Step 18: Click to Login button");
		homePage = loginPage.clickToLoginButton();
		
		log.info("Common_01 - Step 19: Get all login page cookies");
		loginPageCookie = homePage.getAllCookies(driver);
		System.out.println(loginPageCookie);
		
		log.info("Post-Condition: Close browser'" + browserName + "'");
		cleanDriverInstance();
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
