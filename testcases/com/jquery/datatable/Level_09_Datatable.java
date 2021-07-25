package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.jQuery.HomePageObject;
import pageObject.jQuery.PageGeneratorManager;

public class Level_09_Datatable extends BaseTest{
	WebDriver driver;
	HomePageObject homePage;
	
	String projectPath = System.getProperty("user.dir");
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		
		homePage = PageGeneratorManager.getHomePage(driver);
	}
	
	//@Test
	public void Table_01_Paging( ) {
		
		homePage.openPageByNumber("15");
		Assert.assertTrue(homePage.isPageActivedByNumber("15"));
		
		homePage.openPageByNumber("4");
		Assert.assertTrue(homePage.isPageActivedByNumber("4"));
		
		homePage.openPageByNumber("20");
		Assert.assertTrue(homePage.isPageActivedByNumber("20"));
	}
	
	//@Test
	public void Table_02_Input_Header_Textbox( ) {
		// Input to Textbox
		homePage.inputToHeaderTextboxByName("Females", "434000");
		homePage.sleepInSecond(3);
		homePage.refreshToPage(driver);
		
		homePage.inputToHeaderTextboxByName("Males", "45100");
		homePage.sleepInSecond(3);
		homePage.refreshToPage(driver);
		
		homePage.inputToHeaderTextboxByName("Country", "America");
		homePage.sleepInSecond(3);
		homePage.refreshToPage(driver);
	}
	
	//@Test
	public void Table_03_Click_To_Icon( ) {
		// Click to icon
		homePage.clickToIconByCountryName("Angola", "remove");
		homePage.sleepInSecond(3);
		
		homePage.clickToIconByCountryName("Argentina", "remove");
		homePage.sleepInSecond(3);
		
		homePage.clickToIconByCountryName("AFRICA", "edit");
		homePage.sleepInSecond(3);
	}
	
	//@Test
	public void Table_04_Verify_Row_Values( ) {
		homePage.inputToHeaderTextboxByName("Country", "Afghanistan");
		Assert.assertTrue(homePage.isRowValueDisplayed("384187", "Afghanistan", "407124", "791312"));
		homePage.sleepInSecond(3);
		
		homePage.inputToHeaderTextboxByName("Country", "Antigua and Barbuda");
		Assert.assertTrue(homePage.isRowValueDisplayed("777", "Antigua and Barbuda", "803", "1580"));
		homePage.sleepInSecond(3);
	}
	
	@Test
	public void Table_05_Input_To_Row_Textbox() {
		homePage.inputToTextboxByRowNumber("Contact Person", "3", "John Kenedy");
		homePage.sleepInSecond(3);
		
		homePage.inputToTextboxByRowNumber("Company", "2", "Apple");
		homePage.sleepInSecond(3);
		
		homePage.inputToTextboxByRowNumber("Order Placed", "1", "5");
		homePage.sleepInSecond(3);
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
