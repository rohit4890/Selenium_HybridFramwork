package log_factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLog {

	private static Logger logger = LogManager.getLogger(MyLog.class.getName());
	

	public static void startTestCase(String testCaseName) {
		logger.info("<<=================================="+testCaseName+" method started. ===================================>>");
	}
	
	public static void endTestCase(String testCaseName) {
		logger.info("<<==================================="+testCaseName+" method ended. ===================================>>");
	}
	
	public static void info(String message) {
		logger.info(message);
	}
	
	public static void warn(String message) {
		logger.warn(message);
	}
	
	public static void error(String message) {
		logger.error(message);
	}
	
	public static void fatal(String message) {
		logger.fatal(message);
	}
}
