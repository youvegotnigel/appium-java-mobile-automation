package org.nigel.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.nigel.automation.pageobjects.CreateTaskPage;
import org.nigel.automation.pageobjects.TasksListPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

public class ToDo_Android extends TestBase {

    CreateTaskPage createTaskPage ;
    TasksListPage tasksListPage;

    @DataProvider(name = "tasks data")
    public Object[][] passData() throws IOException, ParseException {
        return JsonReader.getJSONdata
                (System.getProperty("user.dir") + "/data/TasksData.json"
                        , "Tasks Data", 2);
    }

    @Test(dataProvider = "tasks data")
    public void test_add_task(String taskName, String TaskDesc) throws MalformedURLException {
        Android_setUp();
        tasksListPage = new TasksListPage(driver);
        createTaskPage = new CreateTaskPage(driver);
        tasksListPage.clickAddTaskBtn();
        createTaskPage.enterTaskName(taskName);
        createTaskPage.enterTaskDesc(TaskDesc);
        //(IOSDriver) driver.hideKeyboard();
        createTaskPage.clickSaveBtn();
        tearDown();
    }

}
