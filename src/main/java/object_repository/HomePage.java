package object_repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_repository_utility.BasePage;

public class HomePage extends BasePage {

	private By homeTab = By.xpath("//a[text()='Home']");

	public HomePage(WebDriver driver) {
		super(driver);
	}

	// Calling generic methods from BasePage class to get WebElement of given
	// locators
	public WebElement getHomeTab() {
		return getElementWithWait_elementToBeClickable(homeTab);
	}

	// Clicks on home tab
	public void clickOnHomeTab() {
		getHomeTab().click();
	}
}
