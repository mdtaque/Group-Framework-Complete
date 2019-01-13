package TestLogIn;

import HomepageEbay.HomePage;
import LogInPage.SignIn;
import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reporting.TestLogger;

public class SignInUsingDataProvider extends SignIn {

    SignIn objOfSignIn;
    HomePage objOfHomePage;

    @BeforeMethod
    public void init(){
        objOfSignIn = PageFactory.initElements(driver, SignIn.class);
        objOfHomePage = PageFactory.initElements(driver, HomePage.class);
    }
    @Test(enabled = false)
    public void testiFrame() throws InterruptedException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objOfHomePage.goToLoginPage();
        objOfSignIn.switchToiframe();
    }
    @Test(enabled = false)
    public void testLogInManual() throws InterruptedException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objOfHomePage.goToLoginPage();
        objOfSignIn.logInManual();
    }
    @Test ( dataProvider = "supplyData")
    public void testLogInDataProvider(String email, String passCode, String message) throws InterruptedException {
        //TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        String actual= objOfSignIn.logInByExcelData(email,passCode);
        Assert.assertEquals(actual,message);
    }
}
