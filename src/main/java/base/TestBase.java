package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

/**
 * The Base class contains all the method related to setting the Capabilities
 * and starting/stopping the server and driver.
 * 
 * @author Aarti
 *
 */
public class TestBase {

	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;
	public static final Logger logger = Logger.getLogger(TestBase.class.getName());
	public static Properties prop;

	public TestBase() throws IOException {
		prop = new Properties();
		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\global.properties");
		prop.load(fs);
	}

	/**
	 * The StartServer method is use for starting the server.
	 * 
	 * @return : will return the sever
	 */
	public static void startServer() {
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.withLogFile(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\logs\\logResult.txt"))
				.withArgument(GeneralServerFlag.LOCAL_TIMEZONE));
		service.start();
	}

	/**
	 * The Capabilities method is use for setting the device Capabilities and
	 * connecting server with the device .
	 * 
	 * @param appName : will define string value
	 * @return : will return the driver
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void capabilities(String appName) throws InterruptedException, IOException {
		File app = new File(System.getProperty("user.dir") + "\\app\\" + prop.get(appName));
		DesiredCapabilities capabilities = new DesiredCapabilities();
		String device = (String) prop.get("device");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.amazon.mShop.android.shopping");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.amazon.mShop.home.HomeActivity");
		driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
	}

	/**
	 * The method is use for starting the server, driver and initializing the object
	 * for all classes
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void initialization() throws InterruptedException, IOException {
		logger.info("Starting server");
		startServer();
		logger.info("Connecting with device");
		capabilities("amazonApplication");
	}

	/**
	 * The method is use for stopping the server and closing the driver.
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void endSession() throws InterruptedException, IOException {
		logger.info("closing driver");
		driver.quit();
		logger.info("stopping server");
		service.stop();
	}

	/**
	 * The method is use for stopping the server and closing the driver.
	 * 
	 * @param sc : will define string value
	 * @throws IOException
	 */
	public static void GetScreenShot(String sc) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\screenShots\\" + sc + ".png"));
	}
}
