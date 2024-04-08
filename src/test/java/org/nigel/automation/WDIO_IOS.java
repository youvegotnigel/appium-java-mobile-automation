package org.nigel.automation;

import org.nigel.automation.pageobjects.WDIOHomePage;
import org.nigel.automation.pageobjects.WDIOLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class WDIO_IOS extends TestBase{

    WDIOHomePage wdioHomePage;
    WDIOLoginPage wdioLoginPage;

    @Test
    public void test_login_function() throws MalformedURLException, InterruptedException {

        iOS_setUp();
        wdioHomePage = new WDIOHomePage(driver);
        wdioLoginPage = new WDIOLoginPage(driver);

        wdioHomePage.clickLoginIcon();
        wdioLoginPage.enterEmail("test@wdio.com");
        wdioLoginPage.enterPassword("superStrongPa$$w0rd");
        wdioLoginPage.clickLoginBtn();
        Thread.sleep(2000);
        Assert.assertTrue(wdioLoginPage.isSuccessMessagePopupDisplayed());
        wdioLoginPage.clickOkBtn();
        Thread.sleep(1000);
        Assert.assertFalse(wdioLoginPage.isSuccessMessagePopupDisplayed());
        tearDown();
    }


}
