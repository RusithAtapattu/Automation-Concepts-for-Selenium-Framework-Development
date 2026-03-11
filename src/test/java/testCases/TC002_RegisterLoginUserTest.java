package testCases;

import base.BaseClass;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import utilities.PropertyFileReader;
import utilities.RetryAnalyzer;
import utilities.extentReport.ExtentReportUtility;

public class TC002_RegisterLoginUserTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC002_RegisterLoginUserTest.class);

//    PropertyFileReader propertyFileReader = new PropertyFileReader();

//    String pass = propertyFileReader.getProperty("testData","pass");



    @Test(groups = "smoke")
    public void TC002(){

        String pass = PropertyFileReader.getInstance().getProperty("testData","pass");
        logger.info("*** Starting TC002 Test ***");

//        ChainTestListener.log("Started test execution : TC002_RegisterLoginUserTest");
        HomePageUpdated homePage = new HomePageUpdated(getDriver());
        homePage.selectRegisterMenu();

        RegisterPageUpdated registerPage = new RegisterPageUpdated(getDriver());
        registerPage.setFirstName("Davey");
        registerPage.setLastName("Jones");
        registerPage.setEmail("test@gmail.com");
        registerPage.setCountrySelect();
        registerPage.setUserName("DJones");
        registerPage.setPassword(pass);
        registerPage.setConfirmPassword(pass);
        registerPage.setSubmitBtn();
        ExtentReportUtility.captureScreenshotAsBase64_ReportOnly_StepInfo(getDriver(),"Verify Register success page");
//        ChainTestListener.embed(takeScreenShot(),"image/png");

        RegisterSuccessPageUpdated registerSuccessPage = new RegisterSuccessPageUpdated(getDriver());
        String actualText = registerSuccessPage.registerSuccessText();
        Assert.assertTrue(actualText.contains("Dear"),"Registration attempt failed");
        registerSuccessPage.clickSignInBtn();

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("DJones",pass);

        logger.info("Verifying register success text");

        LoginSuccessPage loginSuccessPage = new LoginSuccessPage(getDriver());
        String actualSuccessText = loginSuccessPage.getLoginSuccessText();
        Assert.assertTrue(actualSuccessText.contains("Thank you"), "Login Attempt failed");
        logger.info("*** Finished TC002 Test ***");

    }
}
