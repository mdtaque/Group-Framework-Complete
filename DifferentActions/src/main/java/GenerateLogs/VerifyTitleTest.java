package GenerateLogs;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class VerifyTitleTest {

    public static WebDriver driver;
    Logger logger = Logger.getLogger(VerifyTitleTest.class);

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/Users/mdtaque/eclipse-workspace/GroupFramework/DifferentActions/drivers/chromedriver");
        driver = new ChromeDriver();
        logger.info("browser is launching");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.get("https://www.facebook.com/");
        logger.info("entering url");


        logger.warn("this is a warning msg!");
        logger.fatal("this is fatal/error msg!");
        logger.debug("this is debug msg");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
        logger.info("browser is closing");
    }

    @Test
    public void titleTest(){
        String title = driver.getTitle();
        logger.info("title is achieved");
        System.out.println(title);
        logger.info("title is printed");
        Assert.assertTrue(false);
    }
}
