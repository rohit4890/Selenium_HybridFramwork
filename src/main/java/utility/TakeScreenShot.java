package utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import driver_factory.DriverFactory;

import static project_constants.Constants.*;

public class TakeScreenShot {

	/*
	 * This method take screenshot 
	 * @Param: fileName
	 */
	public static String takeScreenShoot(String screenShotName) {
		
		String fileName = getScreenShotName(screenShotName);
		String directory = PROJECT_DIRECTORY + SCREEN_SHOT_LOCATION;
		new File(directory).mkdirs();
		String path = directory + fileName;
	
		try {
			File screenshot = ((TakesScreenshot)DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
			
	public static String getScreenShotName(String screenShotName) {
		String fileName = java.time.LocalDate.now()+"-"+java.time.LocalTime.now()+"-"+screenShotName+".jpg";		
		return fileName;
	}
}
