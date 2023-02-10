package object_repository_utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static project_constants.Constants.*;

public abstract class ParentPage {

	public WebDriver driver;
	public WebDriverWait wait;
	
	/*
	 * Constructor of ParentPage
	 * @param: WebDriver instance
	 */
	public ParentPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(WEBDRIVER_WAIT_VALUE));
	}
	
	/*
	 * Abstract generic methods 
	 */
	public abstract String getPageTitle();
	public abstract String getCurrentURL();
	public abstract boolean isElementDisplayedOnPage(By locator);
	public abstract WebElement getElement_withoutWait(By locator);
	public abstract WebElement getElementWithWait_presenseOfElementLocated(By locator);
	public abstract WebElement getElementWithWait_elementToBeClickable(By locator);
	public abstract List<WebElement> getElementListWithWait_presenseOfElementLocated(By locator);
	
	/*
	 * Generic method that gives object of any provided page class
	 * @param: anyClass name with ".". E.g: HomePage.class
	 * */
	public <Tpage extends BasePage>Tpage getInstanceOf(Class<Tpage> pageClass) {
		try {
			return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
		}catch (Exception e) {
			System.out.println("Please provide correct class name.");
			e.printStackTrace();
			return null;
		}
	}
}
