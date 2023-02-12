package object_repository_utility;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import log_factory.MyLog;

public class BasePage extends ParentPage{

	public BasePage(WebDriver driver) {
		super(driver);
	}

	/*
	 * This method will return current page title
	 */
	@Override
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	/*
	 * This method will return current page URL
	 */
	@Override
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	/*
	 * This method will return true or false for given locator
	 * @return:
	 * 		true -> If element displayed on web page
	 * 		false -> If element not displayed on web page
	 * @Param: locator
	 */
	@Override
	public boolean isElementDisplayedOnPage(By locator) {
		if (driver.findElements(locator).size() != 0) {
			return true;
		}else{
			return false;
		}
	}

	/*
	 * This method will return WebElement without wait of given locator
	 * @return:
	 * 		element -> If element present on web page
	 * 		null -> If element not present on web page
	 * @Param: locator
	 */
	@Override
	public WebElement getElement_withoutWait(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			return element;
		}catch (Exception e) {
			MyLog.error("Locator: "+locator+" is wrong in class: "+getClass().getName());
			e.printStackTrace();
		}
		return element;
	}

	/*
	 * This method will return WebElement with wait (presenseOfElementLocated) of given locator
	 * @return:
	 * 		element -> If element present on web page
	 * 		null -> If element not present on web page
	 * @Param: locator
	 */
	@Override
	public WebElement getElementWithWait_presenseOfElementLocated(By locator) {
		WebElement element = null;
		try {
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return element;
		}catch (Exception e) {
			MyLog.error("Locator: "+locator+" is wrong in class: "+getClass().getName());
			e.printStackTrace();
		}
		return element;
	}

	/*
	 * This method will return WebElement with wait (elementToBeClickable) of given locator
	 * @return:
	 * 		element -> If element present on web page
	 * 		null -> If element not present on web page
	 * @Param: locator
	 */
	@Override
	public WebElement getElementWithWait_elementToBeClickable(By locator) {
		WebElement element = null;
		try {
			element = wait.until(ExpectedConditions.elementToBeClickable(locator));
			return element;
		}catch (Exception e) {
			MyLog.error("Locator: "+locator+" is wrong in class: "+getClass().getName());
			e.printStackTrace();
		}
		return element;
	}

	/*
	 * This method will return list of WebElement with wait (presenseOfElementLocated) of given locator
	 * @return:
	 * 		element -> If element present on web page
	 * 		null -> If element not present on web page
	 * @Param: locator
	 */
	@Override
	public List<WebElement> getElementListWithWait_presenseOfElementLocated(By locator) {
		List<WebElement> element = null;
		try {
			element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			return element;
		}catch (Exception e) {
			MyLog.error("Locator: "+locator+" is wrong in class: "+getClass().getName());
			e.printStackTrace();
		}
		return element;
	}
}


