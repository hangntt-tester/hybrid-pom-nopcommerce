package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.employee.hrm.DashboardPO;
import pageObject.employee.hrm.LoginPO;
import pageObject.employee.hrm.PageGenerator;
import pageObjects.user.nopCommerce.BlogPageObject;
import pageObjects.user.nopCommerce.OrdersPageObject;
import pageObjects.user.nopCommerce.PageGeneratorManager;
import pageObjects.user.nopCommerce.SearchPageObject;
import pageUIs.admin.nopCommerce.AdminBasePageUI;
import pageUIs.employee.hrm.BasePageUI;
import pageUIs.user.nopCommerce.UserBasePageUI;

/**
 * @author Hi
 *
 */
public class BasePage {
	
	public static BasePage getBasePage() {
		return new BasePage();
	}
	
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public void setAllCookies(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshToPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		alert.accept();
		sleepInSecond(2);
	}
	
	public void cancelAlert(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		alert.dismiss();
	}
	
	public String getTextInAlert(WebDriver driver) {
		alert = waitForAlertPresence(driver);
		return alert.getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String value) {
		alert = waitForAlertPresence(driver);
		alert.sendKeys(value);
	}
	
	public void switchToWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}
	
	public By getByXpath(String locator) {
		return By.xpath(locator);
	}
	
	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}
	
	public WebElement getWebElement(WebDriver driver, String locator, String... params) {
		return driver.findElement(getByXpath(getDynamicLocator(locator, params)));
	}
	
	public List<WebElement> getWebElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
	}
	
	public String getDynamicLocator(String locator, String... params) {
		return String.format(locator, (Object[]) params);
	}
	
	public void clickToElement(WebDriver driver, String locator) {
		waitForElementClickAble(driver, locator).click();
	}
	
	public void clickToElement(WebDriver driver, String locator, String... params) {
		waitForElementClickAble(driver, getDynamicLocator(locator, params)).click();
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		getWebElement(driver, locator).clear();
		getWebElement(driver, locator).sendKeys(value);
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String value, String... params) {
		locator = getDynamicLocator(locator, params);
		getWebElement(driver, locator).clear();
		getWebElement(driver, locator).sendKeys(value);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText) {
		select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(itemText);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText, String... params) {
		locator = getDynamicLocator(locator, params);
		select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(itemText);
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locator, String itemText) {
		select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}
	
	public String getFirstSelectedItemInDefaultDropdown(WebDriver driver, String locator) {
		select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getFirstSelectedItemInDefaultDropdown(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSecond(1);

		explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}
	
	public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}
	
	public String getAttributeValue(WebDriver driver, String locator, String attributeName, String...params) {
		return getWebElement(driver, getDynamicLocator(locator, params)).getAttribute(attributeName);
	}
	
	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText().trim();
	}
	
	public String getElementText(WebDriver driver, String locator, String... params) {
		return getWebElement(driver, getDynamicLocator(locator, params)).getText().trim();
	}
	
	public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}
	
	public int getElementSize(WebDriver driver, String locator) {
		return getWebElements(driver, locator).size();
	}
	
	public int getElementSize(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		return getWebElements(driver, locator).size();
	}
	
	public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
		if (!isElementSelected(driver, locator)) {
			waitForElementClickAble(driver, locator).click();
		}
	}
	
	public void checkToCheckboxOrRadio(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		if (!isElementSelected(driver, locator)) {
			waitForElementClickAble(driver, locator).click();
		}
	}
	
	public void uncheckToCheckbox(WebDriver driver, String locator) {
		if (isElementSelected(driver, locator)) {
			waitForElementClickAble(driver, locator).click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			return getWebElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		System.out.println("Start time = " + new Date().toString());
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = getWebElements(driver, locator);
		overrideGlobalTimeout(driver, longTimeout);
		
		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible on UI");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible on UI");
			return false;
		}
	}
	
	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator, String... params) {
		return getWebElement(driver, getDynamicLocator(locator, params)).isDisplayed();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator, String... params) {
		return getWebElement(driver, getDynamicLocator(locator, params)).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator, String... params) {
		return getWebElement(driver, getDynamicLocator(locator, params)).isSelected();
	}
	
	public void switchToFrame(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}
	
	public void switchToDefaultPage(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(getWebElement(driver, locator)).perform();
	}
	
	public void hoverToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}
	
	public void hoverToElement(WebDriver driver, String locator, String... params) {
		locator = getDynamicLocator(locator, params);
		action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}
	
	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(getWebElement(driver, locator)).perform();
	}
	
	public void dragAndDropToElement(WebDriver driver, String sourceLocator, String targetLocator) {
		action = new Actions(driver);
		action.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
	}
	
	public void sendkeyBoardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locator), key).perform();
	}
	
	public void sendkeyBoardToElement(WebDriver driver, String locator, Keys key, String... params) {
		action = new Actions(driver);
		locator = getDynamicLocator(locator, params);
		action.sendKeys(getWebElement(driver, locator), key).perform();
	}
	
	public String covertRgbaToHex(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public void uploadToElement(WebDriver driver, String locator, String filePath) {
		getWebElement(driver, locator).sendKeys(filePath);
	}
	
	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
	}
	
	public boolean isJQueryAJAXCallsHaveCompleted(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
					return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
				}
		};
		return explicitWait.until(jQueryLoad);
	}


	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public WebElement waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}

	public WebElement waitForElementVisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));
	}
	
	public WebElement waitForElementClickAble(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}
	
	public WebElement waitForElementClickAble(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(locator, params))));
	}
	
	public boolean waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}
	
	public boolean waitForElementInvisible(WebDriver driver, String locator, String... params) {
		explicitWait = new WebDriverWait(driver, shortTimeout);
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(locator, params))));
	}
	
	
	// User - nopcommerce
	public SearchPageObject openSearchPage(WebDriver driver) {
		waitForElementClickAble(driver, UserBasePageUI.SEARCH_PAGE_FOOTER);
		clickToElement(driver, UserBasePageUI.SEARCH_PAGE_FOOTER);
		return PageGeneratorManager.getSearchPage(driver);
	}
	
	public OrdersPageObject openOrdersPage(WebDriver driver) {
		waitForElementClickAble(driver, UserBasePageUI.ORDERS_PAGE_FOOTER);
		clickToElement(driver, UserBasePageUI.ORDERS_PAGE_FOOTER);
		return PageGeneratorManager.getOrdersPage(driver);
	}
	
	public BlogPageObject openBlogPage(WebDriver driver) {
		waitForElementClickAble(driver, UserBasePageUI.BLOG_PAGE_FOOTER);
		clickToElement(driver, UserBasePageUI.BLOG_PAGE_FOOTER);
		return PageGeneratorManager.getBlogPage(driver);
	}
	
	// 1 hàm cho 20 page
	// Case 1 - Page < 10
	public BasePage getFooterPageByName(WebDriver driver, String pageName) {
		waitForElementClickAble(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
		
		if(pageName.equals("Search")) {
			return PageGeneratorManager.getSearchPage(driver);
		} else if(pageName.equals("Blog")) {
			return PageGeneratorManager.getBlogPage(driver);
		} else {
			return PageGeneratorManager.getOrdersPage(driver);
		} 
	}
	
	// Case 2 - Multiple Page
	public void openFooterPageByName(WebDriver driver, String pageName) {
		waitForElementClickAble(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_FOOTER, pageName);
	}
	
	
	// Admin - nopcommerce
	public void openSubMenuPageByName(WebDriver driver, String menuPageName, String submenuPageName) {
		waitForElementClickAble(driver, AdminBasePageUI.MENU_LINK_BY_NAME, menuPageName);
		clickToElement(driver, AdminBasePageUI.MENU_LINK_BY_NAME, menuPageName);
		
		waitForElementClickAble(driver, AdminBasePageUI.SUB_MENU_LINK_BY_NAME, submenuPageName);
		clickToElement(driver, AdminBasePageUI.SUB_MENU_LINK_BY_NAME, submenuPageName);
	}

	public void uploadFileAtCardName(WebDriver driver, String cardName, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FOLDER_PATH;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, AdminBasePageUI.UPLOAD_FILE_BY_CARD_NAME, cardName).sendKeys(fullFileName);
	}
	
	public boolean isMessageDisplayedInEmptyTable(WebDriver driver, String tableName) {
		waitForElementVisible(driver, AdminBasePageUI.NO_DATA_MESSAGE_BY_TABLE_NAME, tableName);
		return isElementDisplayed(driver, AdminBasePageUI.NO_DATA_MESSAGE_BY_TABLE_NAME, tableName);
	}
	
	// Pattern Object - nopcommerce
	public void enterToTextboxByID_nop(WebDriver driver, String textboxID, String value) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textboxID);
	}
	
	public void openHeaderPageByName(WebDriver driver, String pageName) {
		waitForElementClickAble(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER, pageName);
	}
	
	public void clickToRadioButtonByID(WebDriver driver, String radioButtonID) {
		waitForElementClickAble(driver, UserBasePageUI.DYNAMIC_RADIO_BY_ID, radioButtonID);
		clickToElement(driver, UserBasePageUI.DYNAMIC_RADIO_BY_ID, radioButtonID);
	}
	
	public void selectDropdownByName(WebDriver driver, String dropDownName, String itemText) {
		selectItemInDefaultDropdown(driver, UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, itemText, dropDownName);
	}
	
	public void clickToButtonByText(WebDriver driver, String ButtonText) {
		waitForElementClickAble(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, ButtonText);
		clickToElement(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, ButtonText);
	}
	
	
	// HRM - Menu / SubMenu / ChildSubMenu
	// Menu
	public void openMenuPage(WebDriver driver, String menuPageName) {
		waitForElementClickAble(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		
		isJQueryAJAXCallsHaveCompleted(driver);
	}
	
	// SubMenu
	public void openSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName) {
		waitForElementClickAble(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		
		waitForElementClickAble(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		
		isJQueryAJAXCallsHaveCompleted(driver);
	}
	
	// ChildSubMenu
	public void openChildSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName, String childSubMenuPageName) {
		waitForElementClickAble(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		
		waitForElementVisible(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		hoverToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		
		waitForElementClickAble(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubMenuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubMenuPageName);
		
		isJQueryAJAXCallsHaveCompleted(driver);
	}
	
	// click button
	public void clickToButtonByID(WebDriver driver, String buttonIDName) {
		waitForElementClickAble(driver, BasePageUI.BUTTON_BY_ID, buttonIDName);
		clickToElement(driver, BasePageUI.BUTTON_BY_ID, buttonIDName);
	}
	 
	/** 
	 * enter to textbox by ID
	 * @param driver
	 * @param textboxIDName
	 * @param value
	 */
	public void enterToTextboxByID(WebDriver driver, String textboxIDName, String value) {
		waitForElementVisible(driver, BasePageUI.TEXBOX_BY_ID, textboxIDName);
		sendkeyToElement(driver, BasePageUI.TEXBOX_BY_ID, value, textboxIDName);
	}
	
	public void enterToDatePickerByID(WebDriver driver, String textboxIDName, String value) {
		waitForElementVisible(driver, BasePageUI.TEXBOX_BY_ID, textboxIDName);
		sendkeyToElement(driver, BasePageUI.TEXBOX_BY_ID, value, textboxIDName);
		clickToElement(driver, BasePageUI.IMG_PICKER, textboxIDName);
		sleepInSecond(3);
	}
	
	
	/**
	 * get textbox value by ID
	 * @param driver
	 * @param textboxIDName
	 * @return attribute value
	 */
	public String getTextboxValueByID(WebDriver driver, String textboxIDName) {
		waitForElementVisible(driver, BasePageUI.TEXBOX_BY_ID, textboxIDName);
		return getAttributeValue(driver, BasePageUI.TEXBOX_BY_ID, "value", textboxIDName);
	}
	
	// select dropdown by ID
	public void selectToDropdownByID(WebDriver driver, String dropdownIDName, String valueItem) {
		waitForElementClickAble(driver, BasePageUI.DROPDOWN_BY_ID, dropdownIDName);
		selectItemInDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_ID, valueItem, dropdownIDName);
	}
	
	/**
	 * @param driver
	 * @param dropdownIDName
	 * @return Selected text in dropdown
	 */
	public String getSelectedValueInDropdownByID(WebDriver driver, String dropdownIDName) {
		waitForElementVisible(driver, BasePageUI.DROPDOWN_BY_ID, dropdownIDName);
		return getFirstSelectedItemInDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_ID, dropdownIDName);
	}
	
	// CHECKBOX
	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementClickAble(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
		checkToCheckboxOrRadio(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
	}
	
	public boolean isCheckboxSelectedByLabel(WebDriver driver, String checkboxLabelName) {
		waitForElementVisible(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
		return isElementSelected(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxLabelName);
	}
	 
	
	// RADIO BUTTON
	public void clickToRadioButtonByLabel(WebDriver driver, String radioButtonLabelName) {
		waitForElementClickAble(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
		checkToCheckboxOrRadio(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
	}
	
	public boolean isRadioButtonSelectedByLabel(WebDriver driver, String radioButtonLabelName) {
		waitForElementVisible(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
		return isElementSelected(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, radioButtonLabelName);
	}
	 
	// Table
	public String getValueInTableIDAtColumnNameAndRowIndex(WebDriver driver, String tableID, String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_BY_ID_AND_NAME, tableID, headerName) + 1;
		waitForElementVisible(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
		return getElementText(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
	}
	
	public LoginPO logoutToSystem(WebDriver driver) {
		waitForElementClickAble(driver, BasePageUI.WELCOME_USER);
		clickToElement(driver, BasePageUI.WELCOME_USER);
		waitForElementClickAble(driver, BasePageUI.LOGOUT_LINK);
		clickToElement(driver, BasePageUI.LOGOUT_LINK);
		return PageGenerator.getLoginPage(driver);
	}
	
	public DashboardPO loginToSystem(WebDriver driver, String userName, String password) {
		waitForElementVisible(driver, BasePageUI.USER_LOGIN_TEXTBOX);
		sendkeyToElement(driver, BasePageUI.USER_LOGIN_TEXTBOX, userName);
		sendkeyToElement(driver, BasePageUI.PASSWORD_LOGIN_TEXTBOX, password);
		clickToElement(driver, BasePageUI.LOGIN_BUTTON);
		return PageGenerator.getDashboardPage(driver);
	}
	
	public void uploadImage(WebDriver driver, String filePath) {
		getWebElement(driver, BasePageUI.UPLOAD_FILE).sendKeys(filePath);
	}
	
	public boolean isSuccessMessageDisplayed(WebDriver driver, String successMessage) {
		waitForElementVisible(driver, BasePageUI.SUCCESS_MESSAGE_NAME, successMessage);
		return isElementDisplayed(driver, BasePageUI.SUCCESS_MESSAGE_NAME, successMessage);
	}
	
	public boolean isFieldEnableByName(WebDriver driver, String fieldID) {
		waitForElementVisible(driver, BasePageUI.ANY_FIELD_BY_ID, fieldID);
		return isElementEnabled(driver, BasePageUI.ANY_FIELD_BY_ID, fieldID);
	}
	
	private Alert alert;
	private Select select;
	private Actions action;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private WebDriverWait explicitWait;
	private JavascriptExecutor jsExecutor;
}
