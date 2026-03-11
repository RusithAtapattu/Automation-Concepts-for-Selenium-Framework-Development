package functions;

import org.openqa.selenium.WebDriver;
import pages.HomePageUpdated;
import pages.RegisterPageUpdated;
import pages.RegisterSuccessPageUpdated;

public class NewToursCommonFunctions {

    WebDriver driver;
    public NewToursCommonFunctions(WebDriver driver){
        this.driver = driver;
    }

    public String registerUser(String fName, String lName, String email, String uName, String pass, String confirmPass){
        HomePageUpdated homePage = new HomePageUpdated(driver);
        homePage.registerBtn_ele.click();

        RegisterPageUpdated registerPage = new RegisterPageUpdated(driver);
        registerPage.setFirstName(fName);
        registerPage.setLastName(lName);
        registerPage.setEmail(email);
        registerPage.setCountrySelect();
        registerPage.setUserName(uName);
        registerPage.setPassword(pass);
        registerPage.setConfirmPassword(confirmPass);
        registerPage.setSubmitBtn();

        RegisterSuccessPageUpdated registerSuccessPage =new RegisterSuccessPageUpdated(driver);
        String successText = registerSuccessPage.registerSuccessText();
        return successText;
    }
}
