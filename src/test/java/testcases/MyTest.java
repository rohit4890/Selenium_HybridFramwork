package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import listener_factory.MyListener;
import log_factory.MyLog;
import object_repository.ContactPage;
import object_repository.HomePage;
import testcases_utility.BaseTest;

import static project_constants.Constants.*;

public class MyTest extends BaseTest{

	@Test
	public void validateErrorMessageOnContactPage() {
		
		// Verify if Home Page is open or not
		HomePage homePage = parentPage.getInstanceOf(HomePage.class);
		homePage.clickOnHomeTab();
		String homePageUrl = parentPage.getCurrentURL();
		if(homePageUrl.contains("home")) {
			MyListener.logger().log(Status.PASS, "Home Page is opened");
		}else {
			MyListener.logger().log(Status.FAIL, "Home Page is not opened");
			MyLog.info("Expected URL: https://jupiter.cloud.planittesting.com/#/home");
			MyLog.info("Actual URL: "+homePageUrl);
		}
		Assert.assertEquals(homePageUrl, "https://jupiter.cloud.planittesting.com/#/home");
		
		
		// Click on Contact tab and verify if it is open
		homePage.clickOnContactTab();
		ContactPage contactPage = parentPage.getInstanceOf(ContactPage.class);
		String contactPageHeaderMessage = contactPage.getHeaderMessage();
		if(contactPageHeaderMessage.contains("welcome")) {
			MyListener.logger().log(Status.PASS, "Contact Page is opened");
		}else {
			MyListener.logger().log(Status.FAIL, "Contact Page is not opened");
			MyLog.info("Actual header message: "+contactPageHeaderMessage);
			MyLog.info("Expected welcome message: "+"We welcome your feedback");
		}
		Assert.assertEquals(contactPageHeaderMessage, "We welcome your feedback");
		
		
		// Click on submit button without populating fields
		contactPage.clickOnSubmitButton();
		MyListener.logger().log(Status.INFO, "Submit button is clicked");
		
		
		// Is Error Message Displayed on Forename Field
		String errorOnForenameameField = contactPage.getForenameErrorMessage();
		if((errorOnForenameameField != null) && (errorOnForenameameField.equalsIgnoreCase(FORENAME_FIELD_ERROR_MESSAGE))) {
			MyListener.logger().log(Status.PASS, "Error Message is displyed on forename field: "+FORENAME_FIELD_ERROR_MESSAGE);
		}else {
			MyListener.logger().log(Status.FAIL, "Error Message is not displyed on forename field or error message is incorrect. It should be: "+FORENAME_FIELD_ERROR_MESSAGE);
			MyLog.error("Error message on forename field: "+errorOnForenameameField);
		}
		softAssert.assertEquals(errorOnForenameameField, FORENAME_FIELD_ERROR_MESSAGE);
	
		
		// Is Error Message Displayed on Email Field
		String errorOnEmailField = contactPage.getEmailErrorMessage();
		if((errorOnEmailField != null) && (errorOnEmailField.equalsIgnoreCase(EMAIL_FIELD_ERROR_MESSAGE ))) {
			MyListener.logger().log(Status.PASS, "Error Message is displyed on email field: "+EMAIL_FIELD_ERROR_MESSAGE);
		}else {
			MyListener.logger().log(Status.FAIL, "Error Message is not displyed on email field or error message is incorrect. It should be: "+EMAIL_FIELD_ERROR_MESSAGE);
			MyLog.error("Error message on email field: "+errorOnEmailField);
		}
		softAssert.assertEquals(errorOnEmailField , EMAIL_FIELD_ERROR_MESSAGE);
		
		
		// Is Error Message Displayed on Message Field
		String errorOnMessageField = contactPage.getForenameErrorMessage();
		if((errorOnMessageField != null) && (errorOnMessageField.equalsIgnoreCase(MESSAGE_FIELD_ERROR_MESSAGE ))) {
			MyListener.logger().log(Status.PASS, "Error Message is displyed on message field: "+MESSAGE_FIELD_ERROR_MESSAGE);
		}else {
			MyListener.logger().log(Status.FAIL, "Error Message is not displyed on message field or error message is incorrect. It should be: "+MESSAGE_FIELD_ERROR_MESSAGE);
			MyLog.error("Error message on message field: "+errorOnMessageField);
		}
		softAssert.assertEquals(errorOnMessageField , MESSAGE_FIELD_ERROR_MESSAGE);
		
		
		
		// Populate mandatory fields
		contactPage.enterForename(propReader.getDataFromPropertiesFile("forename"));
		MyListener.logger().log(Status.INFO, "Forename field is populated with: "+propReader.getDataFromPropertiesFile("forename"));
		
		contactPage.enterEmail(propReader.getDataFromPropertiesFile("email"));
		MyListener.logger().log(Status.INFO, "Email field is populated with: "+propReader.getDataFromPropertiesFile("email"));
		
		contactPage.enterMessage(propReader.getDataFromPropertiesFile("message"));
		MyListener.logger().log(Status.INFO, "Message field is populated with: "+propReader.getDataFromPropertiesFile("message"));
		
		
		// Verify if error message still there
		boolean isErrorOnForenameame = contactPage.isErrorMessageDisplayedOnfirstNameField();
		if(isErrorOnForenameame) {
			MyListener.logger().log(Status.FAIL, "Error message should not be there");
		}else {
			MyListener.logger().log(Status.PASS, "Error message is not displayed after populating forename field");
		}
		softAssert.assertEquals(isErrorOnForenameame, false);
		
		boolean isErrorOnEmail = contactPage.isErrorMessageDisplayedOnEmailField();
		if(isErrorOnEmail) {
			MyListener.logger().log(Status.FAIL, "Error message should not be there");
		}else {
			MyListener.logger().log(Status.PASS, "Error message is not displayed after populating email field");
		}
		softAssert.assertEquals(isErrorOnEmail, false);
		
		boolean isErrorOnMessage = contactPage.isErrorMessageDisplayedOnMessageField();
		if(isErrorOnMessage) {
			MyListener.logger().log(Status.FAIL, "Error message should not be there");
		}else {
			MyListener.logger().log(Status.PASS, "Error message is not displayed after populating message field");
		}
		softAssert.assertEquals(isErrorOnMessage, false);
		
		softAssert.assertAll();
	}

}
