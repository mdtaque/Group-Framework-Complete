package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseUtil {

    public static void addScreenCapture(WebDriver driver, String screenshotName) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File("./Screenshots/" + screenshotName + ".png"));
        System.out.println("Screenshot taken");
    }

    public static WebDriver driver = null;
    @BeforeMethod
    public void SetUpDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/mdtaque/eclipse-workspace/GroupFramework/Generic/Browser-driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    @AfterMethod
    public void cleanup(ITestResult result) throws IOException {
        if(ITestResult.FAILURE==result.getStatus()){
            addScreenCapture(driver, result.getName());
        }
        driver.close();
    }

    public void switchWindow(WebDriver driver) {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
    }

    //handling Alert
    public void okAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void cancelAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }
}
