package pageObjects.sort.saucelab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sort.saucelab.InventoryPageUI;

public class InventoryPageObject extends BasePage {
	private WebDriver driver;

	public InventoryPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInSortDropdown(String itemText) {
		waitForElementClickAble(driver, InventoryPageUI.SORT_DROPDOWN);
		selectItemInDefaultDropdown(driver, InventoryPageUI.SORT_DROPDOWN, itemText);
		
	}

	public boolean isProductNameSortAscending() {
		List<WebElement> productNameElements = getWebElements(driver, InventoryPageUI.PRODUCT_NAME_TEXT);
		
		List<String> productNameText = new ArrayList<String>();
		
		for (WebElement productName: productNameElements) {
			productNameText.add(productName.getText());
		}
		
		List<String> ProductNameTextClone = new ArrayList<String>();
		for (String product: productNameText) {
			ProductNameTextClone.add(product);
		}
		
		Collections.sort(ProductNameTextClone);
		
		return productNameText.equals(ProductNameTextClone);
	}

	public boolean isProductNameSortDescending() {
		List<WebElement> productNameElements = getWebElements(driver, InventoryPageUI.PRODUCT_NAME_TEXT);
		
		List<String> productNameText = new ArrayList<String>();
		
		for (WebElement productName: productNameElements) {
			productNameText.add(productName.getText());
		}
		
		List<String> ProductNameTextClone = new ArrayList<String>();
		for (String product: productNameText) {
			ProductNameTextClone.add(product);
		}
		
		Collections.sort(ProductNameTextClone);
		Collections.reverse(ProductNameTextClone);
		
		return productNameText.equals(ProductNameTextClone);
	}

	public boolean isProductPriceSortAscending() {
		List<WebElement> productPriceElements = getWebElements(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);
		
		List<Float> productPriceValue = new ArrayList<Float>();
		
		for (WebElement productPrice: productPriceElements) {
			productPriceValue.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}
		
		List<Float> productPriceClone = new ArrayList<Float>();
		for (Float product: productPriceValue) {
			productPriceClone.add(product);
		}
		
		Collections.sort(productPriceClone);
		
		return productPriceValue.equals(productPriceClone);
	}

	public boolean isProductPriceSortDescending() {
		List<WebElement> productPriceElements = getWebElements(driver, InventoryPageUI.PRODUCT_PRICE_TEXT);
		
		List<Float> productPriceValue = new ArrayList<Float>();
		
		for (WebElement productPrice: productPriceElements) {
			productPriceValue.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}
		
		List<Float> productPriceClone = new ArrayList<Float>();
		for (Float product: productPriceValue) {
			productPriceClone.add(product);
		}
		
		Collections.sort(productPriceClone);
		Collections.reverse(productPriceClone);
		
		return productPriceValue.equals(productPriceClone);
	}
	
	
}
