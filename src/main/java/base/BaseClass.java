package base;

import com.aventstack.chaintest.service.ChainPluginService;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utilities.PropertyFileReader;
import utilities.extentReport.ExtentReportUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {

    private static final Logger logger = LogManager.getLogger(BaseClass.class);

//    public static WebDriver driver;

    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver(){
        if (webDriverThreadLocal.get() == null){
            System.out.println("WebDriver is not initialized");
            throw new IllegalStateException("WebDriver is not initialized");
        }
        return webDriverThreadLocal.get();

    }


    @BeforeSuite(groups = {"smoke","regression"})
    public void beforeSuit() {

        ExtentReportUtility.startReporter();

//        ChainPluginService.getInstance().addSystemInfo("Tester",System.getProperty("user.name"));
//        ChainPluginService.getInstance().addSystemInfo("Browser","chrome");

        try {
            String screenshotFolderPath = System.getProperty("user.dir") + "/test-output/chaintest/resources";
            FileUtils.cleanDirectory(new File(screenshotFolderPath));
            logger.info("Screenshot Folder cleaned : test-output/chaintest");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            String logFilePath = "logs/automation.log";
            File logFile = new File(logFilePath);
            if (logFile.exists()) {
                // Clear the contents of the file (Truncate)
                FileUtils.write(logFile, "", false);
                logger.info("Log file cleaned: " + logFilePath);
            } else {
                // Create the log file if it doesn't exist
                FileUtils.touch(logFile);
                logger.info("Log file created hence not exist: " + logFilePath);
            }
        } catch (IOException e) {
            logger.error("Error cleaning or creating the log file: " + e.getMessage());
        }
    }

    @BeforeMethod(groups = {"smoke","regression"})
    public void openPage() throws IOException {


//        PropertyFileReader propertyFileReader = new PropertyFileReader();

        String browser = PropertyFileReader.getInstance().getProperty("config", "browser");
        String appURL = PropertyFileReader.getInstance().getProperty("config", "App_url");
        long impl_Wait = Long.parseLong(PropertyFileReader.getInstance().getProperty("config", "implicit_wait"));

//        String browser = propertyFileReader.getProperty("config", "browser");
//        String appURL = propertyFileReader.getProperty("config", "App_url");
//        long impl_Wait = Long.parseLong(propertyFileReader.getProperty("config", "implicit_wait"));


        switch (browser.toLowerCase()) {
            case "chrome":
//                driver = new ChromeDriver();
                webDriverThreadLocal.set(new ChromeDriver()); break;

            case "edge":
//                driver = new EdgeDriver();
                webDriverThreadLocal.set(new EdgeDriver()); break;

            case "firefox":
//                driver = new FirefoxDriver();
                webDriverThreadLocal.set(new FirefoxDriver()); break;

            default:
                System.out.println("Browser not supported : " + browser);
                return;
        }

        logger.info("Test case automating with : " + browser);

//        if (browser.equalsIgnoreCase("chrome")) {
//            driver = new ChromeDriver();
//        } else if (browser.equalsIgnoreCase("edge")) {
//            driver = new EdgeDriver();
//        } else if (browser.equalsIgnoreCase("firefox")) {
//            driver = new FirefoxDriver();
//        } else {
//            System.out.println("Browser not supported : " + browser);
//            return;
//        }

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(impl_Wait));
        getDriver().get(appURL);
    }

    @AfterMethod(groups = {"smoke","regression"})
    public void closeBrowser() {
        getDriver().quit();
    }

    @AfterSuite(groups = {"smoke","regression"})
    public void afterSuit() {
        ExtentReportUtility.endReport();
    }

//    public byte[] takeScreenShot() {
//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
//        return screenshot;
//        // Return ((TakesScreenshot) (driver).getScreenshotAs(OutputType.BYTES);
//    }
}
