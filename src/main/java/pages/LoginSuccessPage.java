package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSuccessPage {

    WebDriver driver;

    public LoginSuccessPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//b[normalize-space()='Thank you for Loggin.']")
    WebElement successMessage;

    public String getLoginSuccessText(){
        return successMessage.getText();
    }
}
