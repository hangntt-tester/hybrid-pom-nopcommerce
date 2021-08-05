package takeScreenShot;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class takeScreenShotTestFailed {
	WebDriver driver;
	Select select;
	String projectPath = System.getProperty("user.dir") + "\\screenshotImg\\";
	String firstName, lastName, day, month, year, emailAddress, companyName, password;

@BeforeClass
public void beforeClass() {
	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\browserDrivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	driver.get("https://demo.nopcommerce.com/");
	
	firstName = "Automation"; 
	lastName= "FC"; 
	day= "10"; 
	month= "May"; 
	year= "1999"; 
	emailAddress= "automationfc" + getRandomNumber() +"@mailinator.com"; 
	companyName= "Automation FC"; 
	password= "123456";
}

@Test
public void TC_01_Register( ) {
	driver.findElement(By.className("ico-register")).click();
	
	driver.findElement(By.id("gender-male")).click();
	driver.findElement(By.id("FirstName")).sendKeys(firstName);
	driver.findElement(By.id("LastName")).sendKeys(lastName);
	
	select = new Select(driver.findElement(By.name("DateOfBirthDay")));
	select.selectByVisibleText(day);
	
	select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
	select.selectByVisibleText(month);
	
	select = new Select(driver.findElement(By.name("DateOfBirthYear")));
	select.selectByVisibleText(year);
	
	driver.findElement(By.id("Email")).sendKeys(emailAddress);
	driver.findElement(By.id("Company")).sendKeys(companyName);
	
	// Check
	if (!driver.findElement(By.id("Newsletter")).isSelected()) {
		driver.findElement(By.id("Newsletter")).click();
	}
	
	driver.findElement(By.id("Password")).sendKeys(password);
	driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
	
	driver.findElement(By.id("register-button")).click();
	
	Assert.assertEquals(driver.findElement(By.className("result")).getText(), "Your registration completed.");
	
	driver.findElement(By.className("ico-logout")).click();
	
}

@Test
public void TC_02_Login( ) {
	driver.findElement(By.className("ico-login")).click();
	
	driver.findElement(By.id("Email")).sendKeys(emailAddress);
	driver.findElement(By.id("Password")).sendKeys(password);
	driver.findElement(By.cssSelector(".login-button")).click();
	
	Assert.assertTrue(driver.findElement(By.className("ico-account")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.className("ico-logout")).isDisplayed());
	
}

@Test
public void TC_03_My_Account( ) {
	driver.findElement(By.className("ico-account")).click();
	
	Assert.assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "My account - Customer info");
	Assert.assertTrue(driver.findElement(By.id("gender-male")).isSelected());
	
	Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
	Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
	Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
	Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
	
	select = new Select(driver.findElement(By.name("DateOfBirthDay")));
	Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
	
	select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
	Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
	
	select = new Select(driver.findElement(By.name("DateOfBirthYear")));
	Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
}

public WebDriver getWebDriver() {
	return this.driver;
}

@AfterClass
public void afterClass() {
	driver.quit();
}

public int getRandomNumber() {
	Random rand = new Random();
	return rand.nextInt(9999);
}

}
