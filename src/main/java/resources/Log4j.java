package resources;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * The Log4J class implements the information about methods/test cases
 * @author Aarti
 *
 */
public class Log4j {

	public static final Logger logger = Logger.getLogger(Log4j.class.getName());

	public static void loadLog4J() {
		String log4jConfPath = System.getProperty("user.dir") + "log4J.properties";
		PropertyConfigurator.configure(log4jConfPath);
	}			
}
