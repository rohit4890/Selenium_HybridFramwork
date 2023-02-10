package report_factory;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import static project_constants.Constants.*;

public class ExtentManager {

	private static ExtentReports extent;

	/*
	 * This method return the instance of extent report. 
	 */
	public static ExtentReports createInstance() {

		String fileName = getReportName();
		String directory = PROJECT_DIRECTORY + REPORT_LOCATION;
		new File(directory).mkdirs();
		String path = directory + fileName;

		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);
		htmlReporter = new ExtentSparkReporter(PROJECT_DIRECTORY + REPORT_LOCATION + REPORT_NAME);
		htmlReporter.config().setEncoding(EXTENT_REPORT_ENCODING);
		htmlReporter.config().setDocumentTitle(EXTENT_REPORT_TITLE);
		htmlReporter.config().setReportName(EXTENT_REPORT_NAME);
		htmlReporter.config().setTimeStampFormat(EXTENT_REPORT_TIME_FORMAT);
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.setSystemInfo("Name", "Rohit Report");
		extent.setSystemInfo("Organization", "Rohit Framework");
		extent.setSystemInfo("Browser", "Chrome");
		extent.attachReporter(htmlReporter);

		return extent;
	}

	/*
	 * This method return the unique file name. 
	 */
	public static String getReportName() {
		Date d = new Date();
		String fileName = "Automation Report_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";
		return fileName;
	}
}
