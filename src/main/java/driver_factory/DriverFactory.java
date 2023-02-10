package driver_factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import static project_constants.Constants.*;

public class DriverFactory {

	/*
	 * ThreadLocal is a Java class that provides support for Multithreading.
	 */
	private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<WebDriver>();

	
	/*
	 * This method is used to initialize the thread local driver on the basis of given browser
	 * @param: browserName
	 * @return This will return thread safe driver
	 */
	public static WebDriver initializeWebDriver(String browserName) {
		
		switch (browserName.toLowerCase()) {
		
			case CHROME_BROWSWER:
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				threadLocalDriver.set(new ChromeDriver(chromeOptions));
				threadLocalDriver.set(new ChromeDriver());
				break;
			
			case SAFARI_BROWSWER:
				WebDriverManager.safaridriver().setup();
				threadLocalDriver.set(new SafariDriver());
				break;
		
			case EDGE_BROWSWER:
				WebDriverManager.edgedriver().setup();
				threadLocalDriver.set(new EdgeDriver());
				break;

			case FIREFOX_BROWSWER:
				WebDriverManager.firefoxdriver().setup();
				threadLocalDriver.set(new FirefoxDriver());
				break;
			
			default:
				System.out.println("You have provided wrong browser name: "+browserName+"  which is incorrect. Please provide valid browser name.");
				break;
		}
		threadLocalDriver.get().manage().deleteAllCookies();
		threadLocalDriver.get().manage().window().maximize();
		threadLocalDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_VALUE));
		
		return threadLocalDriver.get();
	}
	
	public static WebDriver getDriver() {
		return threadLocalDriver.get();
	}
	
	/*
	 * This method will remove driver instance from ThreadLocal Class.
	 * */
	public static void removeDriverFromThreadLocal() {
		threadLocalDriver.remove();
	}
	
	
}
