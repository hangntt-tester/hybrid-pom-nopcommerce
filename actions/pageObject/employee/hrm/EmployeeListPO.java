package pageObject.employee.hrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.employee.hrm.EmployeeListPageUI;

public class EmployeeListPO extends BasePage {
	private WebDriver driver;
	
	public EmployeeListPO(WebDriver driver) {
		this.driver = driver;
	}

	public void ClickToEmployeeID() {
		waitForElementVisible(driver, EmployeeListPageUI.EMPLOYEE_ID_AT_RESULT_TABLE);
		clickToElement(driver, EmployeeListPageUI.EMPLOYEE_ID_AT_RESULT_TABLE);
	}	
	
}
