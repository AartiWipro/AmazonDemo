package testCase;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import assertions.Compare;
import base.Base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import pageObjects.HomePage;
import pageObjects.SearchPanel;
import pageObjects.WelcomePage;
import utilities.Utilities;

public class VerifyCountryRegion extends Base {

	@Test
	public void verifyingCountry() throws InterruptedException, IOException {
		service = StartServer();
		AndroidDriver<AndroidElement> driver = Capabilities("AmazonApplication");
		WelcomePage wp = new WelcomePage(driver);
		HomePage hp = new HomePage(driver);
		SearchPanel sp = new SearchPanel(driver);
		Utilities ut = new Utilities(driver);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (wp.signIn.isDisplayed()) {
			wp.signIn.click();
		} else
			System.out.print("In Home screen");
		Thread.sleep(3000);
		Utilities.Click(hp.searchPlate);
		Utilities.Click(sp.setting);
		Utilities.Click(sp.countryLanguage);
		Utilities.Click(sp.countryRegion);		

		try {
			if (!sp.ausRedioBtn.isSelected()) {
				sp.ausRedioBtn.click();
				Thread.sleep(3000);
			} else {
				WebElement ele = ut.ScrollToText("Australia");
				System.out.print(ele.getText());
				if (!sp.ausRedioBtn.isSelected())
					sp.ausRedioBtn.click();
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Country Verification
		String Country = sp.GetCountryNameText(driver);
		boolean flag = Compare.assertEquals(Country, "Australia");	
		if (flag)
			System.out.println("Verified Country...");
		else
			Compare.assertFail();
		closeDriver();
		service.stop();
	}
}