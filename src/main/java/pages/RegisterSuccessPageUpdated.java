package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterSuccessPageUpdated {

    WebDriver driver;

    public RegisterSuccessPageUpdated(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void visitRegisterSuccessPage(){
        driver.get("https://demo.guru99.com/test/newtours/register_sucess.php");
    }

    @FindBy(xpath = "//b[contains(text(),'Dear')]")
    WebElement successText_ele;

    @FindBy(partialLinkText = "sign-in")
    WebElement signIn_btn;

    public String registerSuccessText(){
        String successText = successText_ele.getText();
        return successText;
    }

    public void clickSignInBtn() {
        signIn_btn.click();
    }
}
