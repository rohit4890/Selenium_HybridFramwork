package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import object_repository.HomePage;
import testcases_utility.BaseTest;

public class MyTest extends BaseTest{

	@Test
	public void test_01() {
		
		HomePage homePage = parentPage.getInstanceOf(HomePage.class);
		homePage.clickOnHomeTab();
		
		System.out.println("Test One Started");
		Assert.assertTrue(false);
	}
}
