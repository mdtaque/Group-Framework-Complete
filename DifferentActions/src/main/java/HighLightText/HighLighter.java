package HighLightText;

import base.BaseUtil;
import base.CommonAPI;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HighLighter extends BaseUtil {

    @FindBy(xpath = "//input[@name='username']")
    WebElement username;
    @FindBy(xpath = "//input[@name='password']")
    WebElement password;
    @FindBy(xpath = "//*[@id=\"react-root\"]/section/main/article/div[2]/div[1]/div/form/div[3]")
    WebElement loginBtn;

    public void clickOnLogin(){
        highLightElement(driver, username);
        username.sendKeys("fghhcghv");
        highLightElement(driver, password);
        password.sendKeys("vghchv");
        loginBtn.click();
    }

    public static void highLightElement(WebDriver driver, WebElement element){

        JavascriptExecutor js=(JavascriptExecutor)driver;

        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

        try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {

            System.out.println(e.getMessage());
        }

        js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);

    }
}
