package pageObject.jQuery;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.jQuery.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openPageByNumber(String pageNumber) {
		waitForElementClickAble(driver, HomePageUI.PAGING_BY_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.PAGING_BY_NUMBER, pageNumber);
	}

	public boolean isPageActivedByNumber(String pageNumber) {
		waitForElementVisible(driver, HomePageUI.PAGING_BY_NUMBER_ACTIVED, pageNumber);
		return isElementDisplayed(driver, HomePageUI.PAGING_BY_NUMBER_ACTIVED, pageNumber);
	}

	public void inputToHeaderTextboxByName(String headerName, String value) {
		waitForElementVisible(driver, HomePageUI.HEADER_TEXTBOX_BY_NAME, headerName);
		sendkeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_NAME, value, headerName);
		sendkeyBoardToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_NAME, Keys.ENTER, headerName);
	}

	public void clickToIconByCountryName(String countryName, String iconAction) {
		waitForElementClickAble(driver, HomePageUI.ICON_BY_COUNTRY_NAME, countryName, iconAction);
		clickToElement(driver, HomePageUI.ICON_BY_COUNTRY_NAME, countryName, iconAction);
	}

	public boolean isRowValueDisplayed(String female, String country, String males, String total) {
		waitForElementVisible(driver, HomePageUI.ROW_VALUE_BY_FEMALE_COUNTRY_MALE_TOTAL, female, country, males, total);
		return isElementDisplayed(driver, HomePageUI.ROW_VALUE_BY_FEMALE_COUNTRY_MALE_TOTAL, female, country, males, total);
	}

	public void inputToTextboxByRowNumber(String headerName, String rowIndex, String value) {
		int columnIndex = getElementSize(driver, HomePageUI.HEADER_NAME_INDEX, headerName) + 1;
		
		waitForElementVisible(driver, HomePageUI.TEXTBOX_BY_COLUMN_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
		sendkeyToElement(driver, HomePageUI.TEXTBOX_BY_COLUMN_ROW_INDEX, value, rowIndex, String.valueOf(columnIndex)); 
	}


}
