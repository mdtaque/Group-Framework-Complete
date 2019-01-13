package LogInPage;

import DataProviderUtilityWithXlsReader.XlsDataReaderUtil;
import HomepageEbay.HomePage;
import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import java.util.ArrayList;
import java.util.Iterator;

public class SignIn extends CommonAPI {
    @FindBy(css = "#userid")
    WebElement emailOrUsername;
    @FindBy(css = "#pass")
    WebElement password;
    @FindBy(xpath = "//button[@id='sgnBt']")
    WebElement signInButton;
    @FindBy(css = "#errf")
    WebElement invalidMsg;
    @FindBy(css = "#signinContainer")
    WebElement signInFrame;
    @FindBy(xpath = "//div[@id='wrapper']")
    WebElement iframe;

    HomePage objOfHomePage = new HomePage();

    public void switchToSignInForm(){
        driver.switchTo().frame(signInFrame);
    }
    public void logInManual(){
        emailOrUsername.sendKeys("gvhgv@gmail.com");
        password.sendKeys("5646");
        signInButton.click();
        String actual =  invalidMsg.getText();
        System.out.println("SignIn Error Message: " + actual);
    }
    public String logInByExcelData(String email, String passCode) throws InterruptedException {
        objOfHomePage.goToLoginPage();
        emailOrUsername.sendKeys(email);
        password.sendKeys(passCode);
        signInButton.click();
        String actual =  invalidMsg.getText();
        System.out.println("SignIn Error Message: " + actual);
        return actual;
    }
    @DataProvider
    public Iterator<Object[]> supplyData() {
        ArrayList<Object[]> testData =
                XlsDataReaderUtil.getDataFromExcel();
        return testData.iterator();
    }
    public void switchToiframe(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(iframe));
        iframeHandle(iframe);
    }
}
