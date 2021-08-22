package pageObjects.sort.saucelab;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.sort.saucelab.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUsernameTextbox(String userName) {
		waitForElementVisible(driver, LoginPageUI.USER_NAME_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USER_NAME_TEXTBOX, userName);
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASS_WORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASS_WORD_TEXTBOX, password);
	}

	public InventoryPageObject clickToLoginButton() {
		waitForElementClickAble(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getInventoryPage(driver);
	}
}
