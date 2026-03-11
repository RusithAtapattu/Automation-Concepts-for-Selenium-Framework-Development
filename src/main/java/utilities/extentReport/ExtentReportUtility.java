package utilities.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportUtility {

    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    public static void startReporter() {

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+
                "//test-output//extentReports//ExtentReport.html");
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("DaveyJones - Extent Report");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Environment","QA");
        extent.setSystemInfo("ProjectName","Learn_Automation");
        extent.setSystemInfo("Browser","Chrome");
        extent.setSystemInfo("Operating System",System.getProperty("os.name"));
        extent.setSystemInfo("Java Version",System.getProperty("java.version"));
        extent.setSystemInfo("Tester",System.getProperty("user.name"));
    }

    public static void createTest(String testName){
        test = extent.createTest(testName);
    }

    public static void endReport(){
        extent.flush();
    }

    public static void captureScreenshotAsBase64_ReportOnly_StepInfo(WebDriver driver,String message){
        String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        test.info(message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
    }

    public static void captureScreenshotAsBase64_ReportOnly_FailCase(WebDriver driver,String message){
        String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        test.fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
    }

    public static void captureScreenshotAsBase64_ReportOnly_SkipCase(WebDriver driver,String message){
        String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        test.skip(message, MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
    }

    public static void extentReportScreenshotFile_FileAndReport(WebDriver driver, String message){
        String timestamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(new Date());
        File sourceFile = ((TakesScreenshot) (driver)).getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir") + "\\ScreenShot\\" + message + "_" + timestamp + ".png";
        File destinationFile = new File(targetFilePath);
        try {
            FileHandler.copy(sourceFile,destinationFile);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        if (destinationFile.exists()){
            test.addScreenCaptureFromPath(targetFilePath,message);
        } else {
            System.out.println("Screenshot file not found");
        }
    }
}
