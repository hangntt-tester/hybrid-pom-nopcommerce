package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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

public class Level_08_Register_Login_Page_Dynamic_Locator extends BaseTest{
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
	public void TC_01_Register( ) {
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
		
		homePage = registerPage.clickToLogoutLink();
		}
	
	@Test
	public void TC_02_Login( ) {
		loginPage = homePage.clickToLoginLink();
		
		loginPage.enterToEmailTextbox(emailAddress);
		loginPage.enterToPasswordTextbox(password);
		
		homePage = loginPage.clickToLoginButton();
	}
	
	@Test
	public void TC_03_My_Account( ) {
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
	
	@Test
	public void TC_04_Open_Page_At_Footer( ) {
		searchPage = (SearchPageObject) myAccountPage.getFooterPageByName(driver, "Search");
		
		ordersPage = (OrdersPageObject) searchPage.getFooterPageByName(driver, "Orders");
		
		blogPage = (BlogPageObject) ordersPage.getFooterPageByName(driver, "Blog");
		
		searchPage = (SearchPageObject) blogPage.getFooterPageByName(driver, "Search");
		
		blogPage = (BlogPageObject) searchPage.getFooterPageByName(driver, "Blog");
		
		ordersPage = (OrdersPageObject) blogPage.getFooterPageByName(driver, "Orders");
	}
	
	@Test
	public void TC_05_Open_Page_At_Footer( ) {
		ordersPage.openFooterPageByName(driver, "Blog");
		blogPage = PageGeneratorManager.getBlogPage(driver);
		
		blogPage.openFooterPageByName(driver, "Search");
		searchPage = PageGeneratorManager.getSearchPage(driver);
		
		searchPage.openFooterPageByName(driver, "Blog");
		blogPage = PageGeneratorManager.getBlogPage(driver);
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
