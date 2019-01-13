package LoginPage;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogIn extends CommonAPI {

    @FindBy(xpath = "//*[@id=\"account\"]/div/a")
    WebElement clickonLoginButton;
    @FindBy(xpath = "//button[@id='btn-create-account']")
    WebElement clickonCreateAccount;
    @FindBy(xpath = "//input[@id='email_login']")
    WebElement inputEmailBox;
    @FindBy(xpath = "//input[@id='password_login']")
    WebElement inputPasswordBox;
    @FindBy(css = "#btn-login")
    WebElement pressLogInButton;

    public void goToLoginPage(){
        clickonLoginButton.click();
    }
    public void clickCreateAccount(){
        clickonCreateAccount.click();
    }
    public void inputOnEmailBox(){
        inputEmailBox.click();
        inputEmailBox.sendKeys("vghv@yahoo.com");
    }
    public void inputOnPasswordBox(){
        inputPasswordBox.click();
        inputPasswordBox.sendKeys("2564665");
    }
    public void pressLogIn(){
        pressLogInButton.click();
    }
}
