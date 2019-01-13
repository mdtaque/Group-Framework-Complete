package DataDriven;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class XlsReaderMain extends CommonAPI {
    @FindBy(xpath = "//*[@id=\"gh-Alerts-i\"]")
    WebElement bellbutton;
    @FindBy(xpath = "//*[@id=\"ghn-err\"]/span/a")
    WebElement signin;
    @FindBy(xpath = "//*[@id=\"CreateAnAccount\"]")
    WebElement createaccount;
    @FindBy(css = "#ghn-err > span > a")
    WebElement firstname;
    @FindBy(xpath = "//input[@id='lastname']")
    WebElement lastname;
    @FindBy(xpath = "//input[@id='email']")
    WebElement emailfield;
    @FindBy(xpath = "//input[@id='PASSWORD']")
    WebElement password;

    public void goToCreateAccountPage() {
        bellbutton.click();
        signin.click();
        createaccount.click();
    }
    public void creatAccountOnEbay(String first, String last, String email, String pass) throws InterruptedException {
            goToCreateAccountPage();
            firstname.sendKeys(first);
            Thread.sleep(900);
            lastname.sendKeys(last);
            Thread.sleep(900);
            emailfield.sendKeys(email);
            Thread.sleep(900);
            password.sendKeys(pass);
            Thread.sleep(900);
    }
}
