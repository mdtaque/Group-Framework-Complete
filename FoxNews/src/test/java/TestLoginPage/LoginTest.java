package TestLoginPage;

import LoginPage.LogIn;
import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reporting.TestLogger;

public class LoginTest extends LogIn {

    LogIn objOfLogIn;
    @BeforeMethod
    public void Initialization2(){
        objOfLogIn = PageFactory.initElements(driver, LogIn.class);
    }
    @Test
    public void testLogInButton(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objOfLogIn.goToLoginPage();
    }
    @Test
    public void testCreateAccount() throws InterruptedException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        testLogInButton();
        objOfLogIn.clickCreateAccount();
    }
    @Test
    public void enterEmailOnEmailBox() throws InterruptedException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        testLogInButton();
        objOfLogIn.inputOnEmailBox();
    }
    @Test
    public void enterPass() throws InterruptedException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        enterEmailOnEmailBox();
        commonMethodWithXpathForSendKey("//input[@id='password_login']","75674");
    }
    @Test
    public void logInButtonPress() throws InterruptedException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        enterPass();
        objOfLogIn.pressLogIn();
        Thread.sleep(9000);
    }
}
