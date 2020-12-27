package DataPickerTest;

import BaseClass.BaseClass;
import DatePickerPage.DatePickerPage;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;


public class DataPickerPageTest extends BaseClass {

    DatePickerPage datePickerPage;


    public DataPickerPageTest() {
        super();
    }

    @BeforeClass
    public void run() {
        driverInitialization();
        datePickerPage = new DatePickerPage();
    }

    @AfterClass
    public void afterMethod() {
        driver.quit();
    }

    @AfterMethod
    public void screenShot(ITestResult testResult) throws IOException {
        screenShotOnFailure(testResult);
    }

    @Test(priority = 1)
    public void findCurrentDate() {
        datePickerPage.clickOnDatePickerButton();
        datePickerPage.findDate(366);
    }
}
