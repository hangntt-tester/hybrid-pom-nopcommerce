package pageObject.employee.hrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.employee.hrm.MyInfoPageUI;

public class MyInfoPO extends BasePage {
	private WebDriver driver;
	
	public MyInfoPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToChangePhotoImage() {
		waitForElementClickAble(driver, MyInfoPageUI.AVATAR_IMAGE);
		clickToElement(driver, MyInfoPageUI.AVATAR_IMAGE);
	}

	public boolean isNewAvatarImageDisplayed() {
		waitForElementVisible(driver, MyInfoPageUI.AVATAR_IMAGE);
		int imageWidth = Integer.parseInt(getAttributeValue(driver, MyInfoPageUI.AVATAR_IMAGE, "width"));
		int imageHeight = Integer.parseInt(getAttributeValue(driver, MyInfoPageUI.AVATAR_IMAGE, "height"));
		return (imageWidth != 200) || (imageHeight != 200);
	}

	public void openTabAtSideBarByName(String tabName) {
		waitForElementClickAble(driver, MyInfoPageUI.TAB_AT_SIDE_BAR_BY_NAME, tabName);
		clickToElement(driver, MyInfoPageUI.TAB_AT_SIDE_BAR_BY_NAME, tabName);
	}

	public boolean isMessageRequiredDisplayed(String fieldID) {
		waitForElementVisible(driver, MyInfoPageUI.MESSAGE_REQUIRED_BY_ID, fieldID);
		return isElementDisplayed(driver, MyInfoPageUI.MESSAGE_REQUIRED_BY_ID, fieldID);
	}

	public boolean isMessagePhoneDisplayed(String phoneID) {
		waitForElementVisible(driver, MyInfoPageUI.MESSAGE_PHONE_BY_ID, phoneID);
		return isElementDisplayed(driver, MyInfoPageUI.MESSAGE_PHONE_BY_ID, phoneID);
	}

}
