package pageObjects.admin.nopCommerce;

import org.openqa.selenium.WebDriver;


public class PageGeneratorManager {
	private static LoginPageObject loginPage;
	private static DashboardPageObject DashboardPage;
	private static ProductDetailPageObject productDetailPage;
	private static ProductSearchPageObject productSearchPage;
	
	private PageGeneratorManager() {
		
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		if (loginPage == null) {
			loginPage = new LoginPageObject(driver);
		}
		return loginPage;
	}
	
	public static DashboardPageObject getDashboardPage(WebDriver driver) {
		if (DashboardPage == null) {
			DashboardPage = new DashboardPageObject(driver);
		}
		return DashboardPage;
	}
	
	public static ProductDetailPageObject getProductDetailPage(WebDriver driver) {
		if (productDetailPage == null) {
			productDetailPage = new ProductDetailPageObject(driver);
		}
		return productDetailPage;
	}
	
	public static ProductSearchPageObject getProductSearchPage(WebDriver driver) {
		if (productSearchPage == null) {
			productSearchPage = new ProductSearchPageObject(driver);
		}
		return productSearchPage;
	}
}
