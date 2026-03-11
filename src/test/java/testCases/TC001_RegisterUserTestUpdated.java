package testCases;

import base.BaseClass;
import com.aventstack.chaintest.plugins.ChainTestListener;
import functions.NewToursCommonFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import utilities.PropertyFileReader;
import utilities.RetryAnalyzer;
import utilities.extentReport.ExtentReportListenerManager;
import utilities.extentReport.ExtentReportUtility;

import java.time.Duration;


public class TC001_RegisterUserTestUpdated extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC001_RegisterUserTestUpdated.class);

//    PropertyFileReader propertyFileReader = new PropertyFileReader();
//    String pass = propertyFileReader.getProperty("testData","pass");




    @Test(groups = "regression")
    public void TC001(){

//        ChainTestListener.log("Started test execution : TC001_RegisterUserTestUpdated");
        String pass = PropertyFileReader.getInstance().getProperty("testData","pass");
        logger.debug("debug TC001_1 Test");
        logger.info("*** Starting TC001_1 Test ***");
        logger.warn("warn TC001_1 Test");
        logger.error("error TC001_1 Test");
        logger.fatal("fatal TC001_1 Test");

//        registerUser();
//        verifyRegistrationSuccess();

        HomePageUpdated homePage = new HomePageUpdated(getDriver());
        homePage.selectRegisterMenu();

        ExtentReportUtility.captureScreenshotAsBase64_ReportOnly_StepInfo(getDriver(),"Verify Register menu");
//        ChainTestListener.embed(takeScreenShot(),"image/png");

        RegisterPageUpdated registerPage = new RegisterPageUpdated(getDriver());
        registerPage.setFirstName("Davey");
        registerPage.setLastName("Jones");
        registerPage.setEmail("test@gmail.com");
        registerPage.setCountrySelect();
        registerPage.setUserName("DJones");
        registerPage.setPassword(pass);
        registerPage.setConfirmPassword(pass);
        registerPage.setSubmitBtn();


        logger.info("Verifying register success text");
        RegisterSuccessPageUpdated registerSuccessPage = new RegisterSuccessPageUpdated(getDriver());
        String actualText = registerSuccessPage.registerSuccessText();
        Assert.assertTrue(actualText.contains("Dear123"),"Registration attempt failed");

        logger.info("*** Finished TC001_1 Test ***");

//        NewToursCommonFunctions newToursCommonFunctions = new NewToursCommonFunctions(driver);
//        String actualText =newToursCommonFunctions.registerUser("Davey","Jones","test@gmail.com","DJones","test123","test123");
//        Assert.assertTrue(actualText.contains("Dear"),"Registration Attempt failed");

//        driver.findElement(homePage.registerBtn_loc).click();

    }
}

//    public void registerUser(){
//        HomePageUpdated homePage = new HomePageUpdated(driver);
//        homePage.registerBtn_ele.click();
//
//        RegisterPageUpdated registerPage = new RegisterPageUpdated(driver);
//        registerPage.setFirstName("Davey");
//        registerPage.setLastName("Jones");
//        registerPage.setEmail("test@gmail.com");
//        registerPage.setCountrySelect();
//        registerPage.setUserName("DJones");
//        registerPage.setPassword("test123");
//        registerPage.setConfirmPassword("test123");
//        registerPage.setSubmitBtn();
//    }
//
//    public void verifyRegistrationSuccess(){
//        RegisterSuccessPageUpdated registerSuccessPage = new RegisterSuccessPageUpdated(driver);
//        String actualText = registerSuccessPage.registerSuccessText();
//        Assert.assertTrue(actualText.contains("Dear"),"Registration attempt failed");
//    }
