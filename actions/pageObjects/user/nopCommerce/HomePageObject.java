package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.user.nopCommerce.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;
	
	public HomePageObject(WebDriver _driver) {
		this.driver = _driver;
	}
	
	@Step("Click to Register link")
	public RegisterPageObject clickToRegisterLink() {
		waitForElementClickAble(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		if(driver.toString().contains("chrome")) {
			sleepInSecond(3);
		}
		return PageGeneratorManager.getRegisterPage(driver);
	}
	
	@Step("Click to Login link")
	public LoginPageObject clickToLoginLink() {
		waitForElementClickAble(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		if(driver.toString().contains("chrome")) {
			sleepInSecond(3);
		}
		return PageGeneratorManager.getLoginPage(driver);
	}
	
	@Step("Click to My Account link")
	public MyAccountPageObject clickToMyAccountLink() {
		waitForElementClickAble(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getMyAccountPage(driver);
	}

}
