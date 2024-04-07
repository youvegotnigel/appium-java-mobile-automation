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
    public void test_login_function() throws MalformedURLException {

        iOS_setUp();
        wdioHomePage = new WDIOHomePage(driver);
        wdioLoginPage = new WDIOLoginPage(driver);

        wdioHomePage.clickLoginIcon();
        wdioLoginPage.enterEmail("test@wdio.com");
        wdioLoginPage.enterPassword("superStrongPa$$w0rd");
        wdioLoginPage.clickLoginBtn();
        Assert.assertTrue(wdioLoginPage.isSuccessMessagePopupDispalyed());
        wdioLoginPage.clickOkBtn();
        Assert.assertFalse(wdioLoginPage.isSuccessMessagePopupDispalyed());
        tearDown();
    }


}
