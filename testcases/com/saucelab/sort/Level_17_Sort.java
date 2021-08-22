package com.saucelab.sort;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.sort.saucelab.InventoryPageObject;
import pageObjects.sort.saucelab.LoginPageObject;
import pageObjects.sort.saucelab.PageGeneratorManager;

public class Level_17_Sort extends BaseTest{
	WebDriver driver;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		loginPage.enterToUsernameTextbox("standard_user");
		loginPage.enterToPasswordTextbox("secret_sauce");
		inventoryPage = loginPage.clickToLoginButton();
	}
	
	@Test
	public void Sort_01_Name() {
		inventoryPage.selectItemInSortDropdown("Name (A to Z)");
		verifyTrue(inventoryPage.isProductNameSortAscending());
		inventoryPage.sleepInSecond(3);
		
		inventoryPage.selectItemInSortDropdown("Name (Z to A)");
		verifyTrue(inventoryPage.isProductNameSortDescending());
		inventoryPage.sleepInSecond(3);
	}
	
	@Test
	public void Sort_02_Name() {
		inventoryPage.selectItemInSortDropdown("Name (A to Z)");
		verifyTrue(inventoryPage.isProductNameSortDescending()); // Cố tình cho fail
		inventoryPage.sleepInSecond(3);
	}
	
	@Test
	public void Sort_03_Price() {
		inventoryPage.selectItemInSortDropdown("Price (low to high)");
		verifyTrue(inventoryPage.isProductPriceSortAscending());
		inventoryPage.sleepInSecond(3);
		
		inventoryPage.selectItemInSortDropdown("Price (high to low)");
		verifyTrue(inventoryPage.isProductPriceSortDescending());
		inventoryPage.sleepInSecond(3);
		
	}
	
	@Parameters({"browser"})
	@AfterClass(alwaysRun = true)
	public void cleanClass(String browserName) {
		log.info("Post-Condition: Close browser'" + browserName + "'");
		cleanDriverInstance();
	}
	
	
	LoginPageObject loginPage;
	InventoryPageObject inventoryPage;
}
