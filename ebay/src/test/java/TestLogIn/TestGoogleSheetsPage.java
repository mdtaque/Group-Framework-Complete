package TestLogIn;

import LogInPage.GoogleSheetsPage;
import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reporting.TestLogger;
import java.io.IOException;

public class TestGoogleSheetsPage extends GoogleSheetsPage {
    GoogleSheetsPage objGoogleSheetsPage;

    @BeforeMethod
    public void initialization(){
        objGoogleSheetsPage = PageFactory.initElements(driver, GoogleSheetsPage.class);
    }
    @Test
    public void testLogInByInvalidIdPassUsingGoogleSheet() throws IOException, InterruptedException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objGoogleSheetsPage.LogInByInvalidIdPassUsingGoogleSheet();
    }
}

