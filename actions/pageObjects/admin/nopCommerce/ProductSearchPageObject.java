package pageObjects.admin.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.admin.nopCommerce.ProductSearchPageUI;

public class ProductSearchPageObject extends BasePage {
	private WebDriver driver;
	
	public ProductSearchPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToProductNameTextbox(String productName) {
		waitForElementVisible(driver, ProductSearchPageUI.PRODUCT_NAME_TEXTBOX);
		sendkeyToElement(driver, ProductSearchPageUI.PRODUCT_NAME_TEXTBOX, productName);
	}

	public void clickToSearchButton() {
		waitForElementClickAble(driver, ProductSearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, ProductSearchPageUI.SEARCH_BUTTON);
	}

	public ProductDetailPageObject clickToEditButtonByProductName(String productName) {
		waitForElementClickAble(driver, ProductSearchPageUI.EDIT_BUTTON_AT_PRODUCT_NAME, productName);
		clickToElement(driver, ProductSearchPageUI.EDIT_BUTTON_AT_PRODUCT_NAME, productName);
		return PageGeneratorManager.getProductDetailPage(driver);
	}

	public boolean isSuccessMessageDisplayed(String messageName) {
		waitForElementVisible(driver, ProductSearchPageUI.SUCCESS_MESAGE_NAME, messageName);
		return isElementDisplayed(driver, ProductSearchPageUI.SUCCESS_MESAGE_NAME, messageName);
	}

	public boolean isPictureImageUpdated(String productImageName, String productName) {
		productImageName = productImageName.replace(" ", "-").toLowerCase();
		waitForElementVisible(driver, ProductSearchPageUI.PRODUCT_IMAGE_BY_PRODUCT_NAME, productName, productImageName);
		return isElementDisplayed(driver, ProductSearchPageUI.PRODUCT_IMAGE_BY_PRODUCT_NAME, productName, productImageName);
	}

}
