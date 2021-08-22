package pageObjects.sort.saucelab;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	private static LoginPageObject loginPage;
	private static InventoryPageObject inventoryPage;
	
	private PageGeneratorManager() {
		
	}
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		loginPage = new LoginPageObject(driver);
		return loginPage;
	}
	
	public static InventoryPageObject getInventoryPage(WebDriver driver) {
		inventoryPage = new InventoryPageObject(driver);
		return inventoryPage;
	}
	
}
