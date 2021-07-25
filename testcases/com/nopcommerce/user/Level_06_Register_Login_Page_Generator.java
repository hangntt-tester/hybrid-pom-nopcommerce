package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.user.nopCommerce.HomePageObject;
import pageObjects.user.nopCommerce.LoginPageObject;
import pageObjects.user.nopCommerce.MyAccountPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.RegisterPageObject;

public class Level_06_Register_Login_Page_Generator extends BaseTest{
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	MyAccountPageObject myAccountPage;
	RegisterPageObject registerPage;
	
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
	public void TC_01_Register( ) {
		// 2 - Đang từ Home Page (Click vào Register link) -> Navigate tới Register Page
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.clickToGenderMaleRadio();
		registerPage.enterToFirstNameTextbox(firstName);
		registerPage.enterToLastNameTextbox(lastName);
		registerPage.selectDayDropdown(day);
		registerPage.selectMonthDropdown(month);
		registerPage.selectYearDropdown(year);
		registerPage.enterToEmailTextbox(emailAddress);
		registerPage.enterToCompanyTextbox(companyName);
		registerPage.enterToPasswordTextbox(password);
		registerPage.enterToConfirmasswordTextbox(password);
		registerPage.clickToRegisterButton();
		
		Assert.assertTrue(registerPage.isRegisterSuccessMessageDisplayed());
		
		// 3 - Đang từ Register (Click vào Logout Link) -> Navigate tới Home Page
		homePage = registerPage.clickToLogoutLink();
		}
	
	@Test
	public void TC_02_Login( ) {
		// 4 - Đang từ Home Page (Click vào Login Link) -> Navigate tới Login Page
		loginPage = homePage.clickToLoginLink();
		
		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPasswordTextbox(password);
		
		// 5 - Đang từ Login Page (Click vào Login Button) -> Navigate tới Home Page
		homePage = loginPage.clickToLoginButton();
	}
	
	@Test
	public void TC_03_My_Account( ) {
		// 6 - Đang từ Home Page (Click vào MyAccount Link) -> Navigate tới MyAccount Page
		myAccountPage = homePage.clickToMyAccountLink();
		
		Assert.assertTrue(myAccountPage.isGenderMaleRadioSelected());
		Assert.assertEquals(myAccountPage.getFirstNameTextboxValue(), firstName);
		Assert.assertEquals(myAccountPage.getLasttNameTextboxValue(), lastName);
		Assert.assertEquals(myAccountPage.getEmailTextboxValue(), emailAddress);
		Assert.assertEquals(myAccountPage.getCompanyNameTextboxValue(), companyName);
		
		Assert.assertEquals(myAccountPage.getDateDropdownValue(), day);
		Assert.assertEquals(myAccountPage.getMonthDropdownValue(), month);
		Assert.assertEquals(myAccountPage.getYearDropdownValue(), year);
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
