package org.nigel.automation.appium1;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {
    public static AppiumDriver driver;

    public static void Android_setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "13.0");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("app",
                System.getProperty("user.dir") + File.separator + "apps" + File.separator + "ToDo.apk");

        caps.setCapability("unicodeKeyboard", true);

        driver = new AndroidDriver(new URL("http://localhost:4723"), caps);
    }

    public static void Android_wdio_setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "13.0");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("appPackage", "com.wdiodemoapp");
        caps.setCapability("appActivity", "com.wdiodemoapp.MainActivity");
        caps.setCapability("app",
                System.getProperty("user.dir") + File.separator + "apps" + File.separator + "wdioNativeDemoApp.apk");

        caps.setCapability("unicodeKeyboard", true);

        driver = new AndroidDriver(new URL("http://localhost:4723"), caps);
    }

    public static void iOS_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformVersion", "15.0");
        capabilities.setCapability("udid", "0A91734C-D156-448B-BBC2-739003C9B4ED");
        capabilities.setCapability("bundleID", "org.wdioNativeDemoApp");
        capabilities.setCapability("app",
                System.getProperty("user.dir") + File.separator + "apps" + File.separator + "wdioNativeDemoApp.app");

        capabilities.setCapability("noReset", "false");
        capabilities.setCapability("fullReset", "false");
        capabilities.setCapability("autoAcceptAlerts", "true");


        driver = new IOSDriver(new URL("http://localhost:4723"), capabilities);
    }

    public static void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }

}
