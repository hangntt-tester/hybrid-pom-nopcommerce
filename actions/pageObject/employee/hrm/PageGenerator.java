package pageObject.employee.hrm;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
	private static LoginPO loginPage;
	private static AddEmployeePO addEmployeePage;
	private static DashboardPO dashboardPage;
	private static EmployeeListPO employeeListPage;
	private static EmployeeSearchPO employeeSearchPage;
	private static MyInfoPO myInfoPage;
	
	private PageGenerator() {
		
	}
	
	public static LoginPO getLoginPage(WebDriver driver) {
		loginPage = new LoginPO(driver);
		return loginPage;
	}
	
	public static AddEmployeePO getAddEmployeePage(WebDriver driver) {
		addEmployeePage = new AddEmployeePO(driver);
		return addEmployeePage;
	}
	
	public static DashboardPO getDashboardPage(WebDriver driver) {
		dashboardPage = new DashboardPO(driver);
		return dashboardPage;
	}
	
	public static EmployeeListPO getEmployeeListPage(WebDriver driver) {
		employeeListPage = new EmployeeListPO(driver);
		return employeeListPage;
	}
	
	public static EmployeeSearchPO getEmployeeSearchPage(WebDriver driver) {
		employeeSearchPage = new EmployeeSearchPO(driver);
		return employeeSearchPage;
	}
	
	public static MyInfoPO getMyInforPage(WebDriver driver) {
		myInfoPage = new MyInfoPO(driver);
		return myInfoPage;
	}
	
}
