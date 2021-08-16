package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class Level_02_Register_Login_BasePage_Part_II {
	WebDriver driver;
	BasePage basePage;
	Select select;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, day, month, year, emailAddress, companyName, password;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		// Tính che dấu việc khởi tạo của đối tượng (Đóng gói)
		basePage = BasePage.getBasePage();
		
		basePage.openPageUrl(driver, "https://demo.nopcommerce.com/");
		
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
		basePage.clickToElement(driver, "//a[@class='ico-register']");
		
		basePage.checkToCheckboxOrRadio(driver, "//input[@id='gender-male']");
		
		basePage.sendkeyToElement(driver, "//input[@id='FirstName']", firstName);
		basePage.sendkeyToElement(driver, "//input[@id='LastName']", lastName);
		
		basePage.selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']", day);
		basePage.selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']", month);
		basePage.selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']", year);
		
		basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendkeyToElement(driver, "//input[@id='Company']", companyName);
		
		basePage.checkToCheckboxOrRadio(driver, "//input[@id='Newsletter']");
		
		basePage.sendkeyToElement(driver, "//input[@id='Password']", password);
		basePage.sendkeyToElement(driver, "//input[@id='ConfirmPassword']", password);
		
		basePage.clickToElement(driver, "//button[@id='register-button']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"), "Your registration completed");
		
		basePage.clickToElement(driver, "//a[@class='ico-logout']");
	}
	
	@Test
	public void TC_02_Login( ) {
		basePage.clickToElement(driver, "//a[@class='ico-login']");
		
		basePage.sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
		basePage.sendkeyToElement(driver, "//input[@id='Password']", password);
		basePage.clickToElement(driver, "//button[@class='button-1 login-button']");
		
		Assert.assertTrue(basePage.isElementDisplayed(driver, "//a[@class='ico-account']"));
		Assert.assertTrue(basePage.isElementDisplayed(driver, "//a[@class='ico-logout']"));
	}
	
	@Test
	public void TC_03_My_Account( ) {
		basePage.clickToElement(driver, "//a[@class='ico-account']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//h1"), "My account - Customer info");
		Assert.assertTrue(basePage.isElementSelected(driver, "//input[@id='gender-male']"));
		
		Assert.assertEquals(basePage.getAttributeValue(driver, "//input[@id='FirstName']", "value"), firstName);
		Assert.assertEquals(basePage.getAttributeValue(driver, "//input[@id='LastName']", "value"), lastName);
		Assert.assertEquals(basePage.getAttributeValue(driver, "//input[@id='Email']", "value"), emailAddress);
		Assert.assertEquals(basePage.getAttributeValue(driver, "//input[@id='Company']", "value"), companyName);
		
		Assert.assertEquals(basePage.getFirstSelectedItemInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']"), day);
		Assert.assertEquals(basePage.getFirstSelectedItemInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']"), month);
		Assert.assertEquals(basePage.getFirstSelectedItemInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']"), year);
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
