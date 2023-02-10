package testcases_utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import driver_factory.DriverFactory;
import object_repository_utility.BasePage;
import object_repository_utility.ParentPage;
import utility.PropertiesReader;

import static project_constants.Constants.*;


public class BaseTest {
	
	public PropertiesReader propReader;
	public WebDriver driver;
	public ParentPage parentPage;
	public SoftAssert softAssert = new SoftAssert();
	
	@BeforeMethod
	public void setUp() {
		
		propReader = new PropertiesReader();
		DriverFactory.initializeWebDriver(propReader.getDataFromPropertiesFile(BROWSER_NAME));
		driver = DriverFactory.getDriver();
		driver.get(propReader.getDataFromPropertiesFile(APP_URL));
		parentPage = new BasePage(driver);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		
		driver.quit();
		DriverFactory.removeDriverFromThreadLocal();
	}

}
