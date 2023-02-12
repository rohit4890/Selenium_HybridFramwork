package testcases_utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

import driver_factory.DriverFactory;
import log_factory.MyLog;
import object_repository_utility.BasePage;
import object_repository_utility.ParentPage;
import utility.PropertiesReader;


public class BaseTest {
	
	public PropertiesReader propReader;
	public WebDriver driver;
	public ParentPage parentPage;
	public SoftAssert softAssert = new SoftAssert();
	
	@BeforeMethod
	public void setUp() {
		
		propReader = new PropertiesReader();
		DriverFactory.initializeWebDriver(propReader.getDataFromPropertiesFile("browserName"));
		driver = DriverFactory.getDriver();
		driver.get(propReader.getDataFromPropertiesFile("appUrl"));
		MyLog.info("Launching Browser");
		parentPage = new BasePage(driver);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		MyLog.info("Closing Browser");
		driver.quit();
		DriverFactory.removeDriverFromThreadLocal();
	}
}
