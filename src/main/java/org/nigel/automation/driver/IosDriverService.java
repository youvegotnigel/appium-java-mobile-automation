package org.nigel.automation.driver;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class IosDriverService {

    private IOSDriver iosDriver;


    public void spinDriver() {

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


        try {
            iosDriver = new IOSDriver(new URL("http://localhost:4723"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        iosDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    public void closeDriver() {
        iosDriver.close();
    }
}
