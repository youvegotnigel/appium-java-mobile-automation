package org.nigel.automation;

import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class WDIO_IOS extends TestBase{


    @Test
    public void add_item_to_cart() throws MalformedURLException {

        iOS_setUp();

        tearDown();
    }


}
