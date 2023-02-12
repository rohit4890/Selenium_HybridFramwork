package listener_factory;


import java.util.Arrays;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import log_factory.MyLog;
import report_factory.ExtentManager;
import utility.TakeScreenShot;

public class MyListener implements ITestListener {

	private static ExtentReports extent = ExtentManager.createInstance();
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		ExtentTest test = extent
				.createTest(result.getTestClass().getName() + ": " + result.getMethod().getMethodName());
		extentTest.set(test);
		MyLog.startTestCase(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		String message = "<b>Test Method:  " + result.getMethod().getMethodName() + " successful.</b>";
		Markup m = MarkupHelper.createLabel(message, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
		MyLog.endTestCase(result.getTestClass().getName());
	}

	public void onTestFailure(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());

		extentTest.get().fail("<details><summary><b><font color=red>" + "Exception Occured, click to see details:"
				+ "</font></b></summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details> \n");

		String path = TakeScreenShot.takeScreenShoot(methodName);
	       
		try {
			extentTest.get().fail("<b><font color=red>"+"screenshot of failure"+"</font></b>", 
			MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}catch(Exception e) {
			extentTest.get().fail("Test failed, cannot attach screenshot");
		}
		
		String message = "<b>Test Method::  " + methodName + " failed.</b>";
		Markup m = MarkupHelper.createLabel(message, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);
		MyLog.endTestCase(result.getTestClass().getName());
	}

	public void onTestSkipped(ITestResult result) {
		String message = "<b>Test Method::  " + result.getMethod().getMethodName() + " skipped</b>";
		Markup m = MarkupHelper.createLabel(message, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, m);
		MyLog.endTestCase(result.getTestClass().getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	public void onFinish(ITestContext context) {
		if (extent != null) {
			extent.flush();
		}
	}
	
	// For report logging
	public static ExtentTest logger() {
		return extentTest.get();
	}
}
