package com.nopcommerce.admin;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.admin.nopCommerce.DashboardPageObject;
import pageObjects.admin.nopCommerce.LoginPageObject;
import pageObjects.admin.nopCommerce.PageGeneratorManager;
import pageObjects.admin.nopCommerce.ProductDetailPageObject;
import pageObjects.admin.nopCommerce.ProductSearchPageObject;

public class Level_10_Admin_Upload_File extends BaseTest{
	WebDriver driver;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	ProductSearchPageObject productSearchPage;
	ProductDetailPageObject productDetailPage;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
		loginPage.enterToEmailTextbox("admin@yourstore.com");
		loginPage.enterToPasswordTextbox("admin");
		dashboardPage = loginPage.clickToLoginButton();
		
		productSearchPage = dashboardPage.openSubMenuPageByName("Catalog", "Products");
		
		productSearchPage.enterToProductNameTextbox("Adobe Photoshop CS4");
		
		productSearchPage.clickToSearchButton();
		
		productDetailPage = productSearchPage.clickToEditButtonByProductName("Adobe Photoshop CS4");
	}
	
	@Test
	public void Admin_01_Upload_File() {
		productDetailPage.clickToExpanPanalByName("Pictures");
		
		productDetailPage.uploadPictureByFileName("");
		
		Assert.assertTrue(productDetailPage.isPictureUploadedSuccessByFileName());
		
		productDetailPage.enterToAltTextbox("");
		productDetailPage.enterToTitleTextbox("");
		productDetailPage.enterToDisplayedOrderTextbox("");
		
		productDetailPage.clickToAddProductPictureButton();
		
		Assert.assertTrue(productDetailPage.isPictureImageDisplayed("", "", "", ""));
		
		productSearchPage = productDetailPage.clickToSaveButton();
		
		Assert.assertTrue(productSearchPage.isSuccesMessageDisplayed("The product has been updated successfully."));
		
		productSearchPage.enterToProductNameTextbox("Adobe Photoshop CS4");
		
		productSearchPage.clickToSearchButton();
		
		Assert.assertTrue(productSearchPage.isPictureImageUpdated("adobe-photoshop-cs4", "Adobe Photoshop CS4"));
		
		productDetailPage = productSearchPage.clickToEditButtonByProductName("Adobe Photoshop CS4");
		
		productDetailPage.clickToExpanPanalByName("Pictures"); 
		
		productDetailPage.clickToDeleteButtonAtPictureName(); // Accept Alert
		
		Assert.assertTrue(productDetailPage.isMessageDisplayedInTable("No data available in table"));
		
		productSearchPage = productDetailPage.clickToSaveButton();
		
		productSearchPage.enterToProductNameTextbox("Adobe Photoshop CS4");
		
		productSearchPage.clickToSearchButton();
		
		Assert.assertTrue(productSearchPage.isPictureImageUpdated("default-image", "Adobe Photoshop CS4"));
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}
