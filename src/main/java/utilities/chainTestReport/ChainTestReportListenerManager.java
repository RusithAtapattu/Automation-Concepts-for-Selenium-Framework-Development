package utilities.chainTestReport;

import base.BaseClass;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;
import utilities.RetryAnalyzer;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ChainTestReportListenerManager extends BaseClass implements ITestListener, IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }


    public void onTestStart(ITestResult result) {
        ChainTestListener.log("Started Test Execution : " + result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        ChainTestListener.log(result.getName() + " : Test case Passed");
    }

    public void onTestFailure(ITestResult result) {
        ChainTestListener.log(result.getName() + " - Test Case Failed");
        ChainTestScreenshotUtility.chainTestScreenshotBase64(getDriver());

//        ChainTestListener.embed(takeScreenShot(), "image/png");
        //ChainTestScreenshotsUtility.chainTestScreenshotByte(getDriver());
    }

    public void onTestSkipped(ITestResult result) {
        ChainTestListener.log(result.getName() + " : Test case got skipped");
    }
}

