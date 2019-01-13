package TestLogIn;

import LogInPage.LoginWithKeyword;
import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reporting.TestLogger;

import java.io.IOException;

public class TestKeywordDriven extends LoginWithKeyword {
    LoginWithKeyword loginWithKeyword;

    @BeforeMethod
    public void init(){
        loginWithKeyword = PageFactory.initElements(driver, LoginWithKeyword.class);
    }
    @Test
    public void testKeyword() throws IOException, InterruptedException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        loginWithKeyword.loginByKeyword();
    }
}
