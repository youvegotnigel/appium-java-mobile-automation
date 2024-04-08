package org.nigel.automation.appium2;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DemoTest {


    @Test
    public void demo_android_test() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2); //optional
        options.setDeviceName("nigel-test-device"); // if u have only one device u can put any name here
        options.setCapability("app",
                System.getProperty("user.dir") + File.separator + "apps" + File.separator + "SauceLabs-Demo-App.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();
    /*new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(e->e.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]")));
 */
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]"))
                .click();
        // Thread.sleep(5000);
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("wfwdfg");
        driver.quit();
    }


    @Test
    public void demo_ios_test() {


    }


}
