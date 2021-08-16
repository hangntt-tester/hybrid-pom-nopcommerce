package pageUIs.user.nopCommerce;

public class UserBasePageUI {
	public static final String SEARCH_PAGE_FOOTER = "//div[@class='footer']//a[text()='Search']";
	public static final String ORDERS_PAGE_FOOTER = "//div[@class='footer']//a[text()='Orders']";
	public static final String BLOG_PAGE_FOOTER = "//div[@class='footer']//a[text()='Blog']";
	
	// tối ưu thành 1 locator (dynamic)
	public static final String DYNAMIC_PAGE_FOOTER = "//div[@class='footer']//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_PAGE_HEADER = "//div[@class='header']//a[text()='%s']";
	public static final String DYNAMIC_RADIO_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "//select[@name='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[text()='%s']";
}
