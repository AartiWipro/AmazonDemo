package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
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
 * The Base  class contains all the method related to setting the Capabilities and starting/stopping the server and driver.
 * @author Aarti
 *
 */
public class Base {

	public static AppiumDriverLocalService service;
	public static AndroidDriver<AndroidElement> driver;

	// Need Dependency slf4j :api, simple. commons : lang3, io, validator.
	/**
	 * The StartServer method is use for starting the server.
	 * @return : will return the sever 
	 */
	public AppiumDriverLocalService StartServer() {
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.withLogFile(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\logs\\logResult.txt"))
				.withArgument(GeneralServerFlag.LOCAL_TIMEZONE));
		service.start();
		return service;
	}

	/**
	 * The Capabilities method is use for setting the device Capabilities and connecting server with the device .
	 * @param appName : will define string value
	 * @return : will return the driver
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static AndroidDriver<AndroidElement> Capabilities(String appName) throws InterruptedException, IOException {

		FileInputStream fs = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\global.properties");
		Properties prop = new Properties();
		prop.load(fs);
		File app = new File(System.getProperty("user.dir") + "\\app\\" + prop.get(appName));
		DesiredCapabilities capabilities = new DesiredCapabilities();
		String device = (String) prop.get("device");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.amazon.mShop.android.shopping");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.amazon.mShop.home.HomeActivity");
		driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		return driver;
	}

	/**
	 * The method is use for stopping the server and closing the driver.
	 * @param sc : will define string value
	 * @throws IOException
	 */
	public static void GetScreenShot(String sc) throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\screenShots\\" + sc + ".png"));
	}

	/**
	 * The method is use for closing the driver.
	 */
	public void closeDriver() {
		driver.quit();
	}
}
