package BaseClass;

import Elements.Elements;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static WebDriver driver;
    public static Properties prop;


    public BaseClass() {
        Elements elements = new Elements();
        PageFactory.initElements(driver, elements);
    }


    public void driverInitialization() {
        if (driver == null) {
            try {
                prop = new Properties();
                String confPath = "src\\main\\java\\Properties\\config.properties";
                FileInputStream ip = new FileInputStream(confPath);
                prop.load(ip);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (prop.getProperty("browser").equalsIgnoreCase("ch")) {
                WebDriverManager.chromedriver().setup();
                System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
                driver = new ChromeDriver();
                manageDriver();
            } else {
                if (prop.getProperty("browser").equalsIgnoreCase("ff")) {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    manageDriver();
                } else {
                    if (prop.getProperty("browser").equalsIgnoreCase("ed")) {
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver();
                        manageDriver();
                    }
                }
            }
        }
    }


    private void manageDriver() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    public static void killWindowsProcessAfterTestFinished() throws IOException {
        Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
    }

    public static void screenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("C:\\temp\\" + testResult.getName() + "-"
                    + Arrays.toString(testResult.getParameters()) + ".jpg"));
        }
    }

    public WebElement waitForElementIsPresent(WebElement element) {
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, 10)
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}




