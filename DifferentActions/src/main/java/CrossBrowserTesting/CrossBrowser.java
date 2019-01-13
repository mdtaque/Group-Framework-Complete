package CrossBrowserTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CrossBrowser {

    public static WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browserName){
        if(browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", "/Users/mdtaque/eclipse-workspace/GroupFramework/DifferentActions/drivers/geckodriver");
            driver = new FirefoxDriver();
        }
        else if(browserName.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "/Users/mdtaque/eclipse-workspace/GroupFramework/DifferentActions/drivers/chromedriver");
            driver = new ChromeDriver();
        }

        driver.get("https://www.facebook.com/");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void getTitle(){
        String title = driver.getTitle();
        System.out.println(title);
        driver.quit();
    }

}
