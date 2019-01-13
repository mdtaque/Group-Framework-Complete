package WindowHandling;

import Utility.CaptureScreenShot;
import base.BaseUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class HandleWindow extends BaseUtil {

    @FindBy(xpath = "//button[@id='button1']")
    public static WebElement newWindowOpen;
    @FindBy(xpath = "/html//div[@id='content']/div[1]/div/div/div[@class='wpb_wrapper']//a[@href='http://toolsqa.com/selenium-tutorial/']")
    WebElement clickOnSeleniumOnChildWindow;
    @FindBy(css = "#content > p:nth-child(5) > button")
    WebElement newMsgWindow;
    @FindBy(css = "#content > p:nth-child(6) > button")
    WebElement newTab;
    @FindBy(xpath = "//button[@id='alert']")
    WebElement alertWindow;

    public void handlingWindow() throws InterruptedException {
        String parentWindow = driver.getWindowHandle();
        System.out.println(parentWindow);
        newWindowOpen.click();
        for(String subWindow : driver.getWindowHandles()){
            driver.switchTo().window(subWindow);
        }
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOf(clickOnSeleniumOnChildWindow));
        clickOnSeleniumOnChildWindow.click();
        Thread.sleep(2000);
        driver.close();
        Thread.sleep(2000);
        driver.switchTo().window(parentWindow);
    }
    public void windowSwitchByCondition() throws InterruptedException {
        String mainWindow = driver.getWindowHandle();
        newWindowOpen.click();
        Thread.sleep(2000);
        Set<String> allWindows = driver.getWindowHandles();
        int numberOfWindows = allWindows.size();
        System.out.println(numberOfWindows);
        Iterator it = allWindows.iterator();
        String currentWindowId;
        while (it.hasNext()){
            currentWindowId = it.next().toString();
            System.out.println(currentWindowId);

            if(!currentWindowId.equals(mainWindow)){
                driver.switchTo().window(currentWindowId);
                Thread.sleep(2000);
                driver.close();
                driver.switchTo().window(mainWindow);
            }
        }
    }
    public void switchWindowAgain() throws InterruptedException, IOException {
        newWindowOpen.click();
        switchWindow(driver);
        System.out.println("the title of child window is: " + driver.getTitle());
        driver.close();
        switchWindow(driver);
        System.out.println("the title of parent window is: " + driver.getTitle());
    }
    public void switchToMsgWindow() throws InterruptedException {
        newMsgWindow.click();
        Thread.sleep(2000);
        switchWindow(driver);
        System.out.println("the title of child window is: " + driver.getTitle());
        driver.close();
        switchWindow(driver);
        System.out.println("the title of parent window is: " + driver.getTitle());
    }
    public void switchToNewTab() throws InterruptedException {
        newTab.click();
        Thread.sleep(2000);
        switchWindow(driver);
        System.out.println("the title of child window is: " + driver.getTitle());
        driver.close();
        switchWindow(driver);
        System.out.println("the title of parent window is: " + driver.getTitle());
    }
    public void handleAlertBox() throws InterruptedException {
        alertWindow.click();
        Thread.sleep(2000);
        //driver.switchTo().alert().accept();
        String text = driver.switchTo().alert().getText();
        System.out.println("the alert msg is: " + text);
        okAlert();
    }
}