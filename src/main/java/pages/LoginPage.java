package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//input[@name='userName']")
    public WebElement userName;

    @FindBy(xpath="//input[@name='password']")
    public WebElement password;

    @FindBy(xpath="//input[@name='submit']")
    public WebElement submit;

    public void login(String uName, String pass) {
        userName.sendKeys(uName);
        password.sendKeys(pass);
        submit.click();
    }
}

