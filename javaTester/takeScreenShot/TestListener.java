package takeScreenShot;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	String projectPath = System.getProperty("user.dir") + "\\screenshotImg\\";
	WebDriver driver;
	@Override
	public void onFinish(ITestContext arg0) {
	}

	@Override
	public void onStart(ITestContext arg0) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Object testClass = result.getInstance();
		driver = ((takeScreenShotTestFailed) testClass).getWebDriver();
		TakesScreenshot scrShot =(TakesScreenshot) driver;
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(projectPath + result.getName() + ".png");
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
	}

	@Override
	public void onTestStart(ITestResult arg0) {
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
	}

}
