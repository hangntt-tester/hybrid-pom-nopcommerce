package pageObjects.user.nopCommerce;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	private static HomePageObject homePage;
	private static LoginPageObject loginPage;
	private static RegisterPageObject registerPage;
	private static MyAccountPageObject myAccountPage;
	private static SearchPageObject searchPage;
	private static OrdersPageObject orderPage;
	private static BlogPageObject blogPage;
	
	private PageGeneratorManager() {
		
	}
	
	public static HomePageObject getHomePage(WebDriver driver) {
		//if (homePage == null) {
			homePage = new HomePageObject(driver);
		//}
		return homePage;
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		//if (loginPage == null) {
			loginPage = new LoginPageObject(driver);
		//}
		return loginPage;
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		//if (registerPage == null) {
			registerPage = new RegisterPageObject(driver);
		//}
		return registerPage;
	}
	
	public static MyAccountPageObject getMyAccountPage(WebDriver driver) {
		//if (myAccountPage == null) {
			myAccountPage = new MyAccountPageObject(driver);
		//}
		return myAccountPage;
	}

	public static SearchPageObject getSearchPage(WebDriver driver) {
		//if (searchPage == null) {
			searchPage = new SearchPageObject(driver);
		//}
		return searchPage;
	}
	
	public static OrdersPageObject getOrdersPage(WebDriver driver) {
		//if (orderPage == null) {
			orderPage = new OrdersPageObject(driver);
		//}
		return orderPage;
	}
	public static BlogPageObject getBlogPage(WebDriver driver) {
		//if (blogPage == null) {
			blogPage = new BlogPageObject(driver);
		//}
		return blogPage;
	}
}
