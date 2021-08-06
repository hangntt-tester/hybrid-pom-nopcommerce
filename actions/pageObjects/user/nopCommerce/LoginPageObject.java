package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.user.nopCommerce.LoginPageUI;

public class LoginPageObject extends BasePage {
	private WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Enter to Email textbox with value {0}")
	public void enterToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}
	
	@Step("Enter to Password textbox with value {0}")
	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}
	
	@Step("Click to Login button")
	public HomePageObject clickToLoginButton() {
		waitForElementClickAble(driver, LoginPageUI.LONGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LONGIN_BUTTON);
		if(driver.toString().contains("chrome")) {
			sleepInSecond(3);
		}
		return PageGeneratorManager.getHomePage(driver);
	}
}
