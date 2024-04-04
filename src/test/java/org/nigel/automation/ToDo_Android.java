package org.nigel.automation;

import org.nigel.automation.pageobjects.CreateTaskPage;
import org.nigel.automation.pageobjects.TasksListPage;
import org.nigel.automation.utils.JsonReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;

public class ToDo_Android extends TestBase {

    CreateTaskPage createTaskPage ;
    TasksListPage tasksListPage;

    @DataProvider(name = "tasks data")
    public Object[][] passData() {
        return JsonReader.getJSONdata
                (System.getProperty("user.dir") + File.pathSeparator + "data" + File.pathSeparator + "TasksData.json"
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
