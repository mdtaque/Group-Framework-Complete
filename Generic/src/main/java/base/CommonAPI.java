package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;
import org.testng.asserts.SoftAssert;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import reporting.TestLogger;
import utility.DataReader;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CommonAPI {

    public static WebDriver driver;
    public String browserstack_username = "mdrifattaque1";
    public String browserstack_accesskey = "wb76kksaAYVDv6yivyzb";
    public String saucelabs_username = "";
    public String saucelabs_accesskey = "";

    @Parameters({"useCloudEnv", "cloudEnvName", "os", "os_version", "browserName", "browserVersion", "url"})
    @BeforeMethod
    public void setUp(@Optional("false") boolean useCloudEnv, @Optional("false") String cloudEnvName,
                      @Optional("OS X") String os, @Optional("10") String os_version, @Optional("chrome-options") String browserName, @Optional("34")
                              String browserVersion, @Optional("https://www.ebay.com/") String url) throws IOException {
        System.setProperty("webdriver.chrome.driver", "/SeleniumProject/MavenProject/TestAutomation/Generic/brwoser-driver/chromedriver");
        if (useCloudEnv == true) {
            if (cloudEnvName.equalsIgnoreCase("browserstack")) {
                getCloudDriver(cloudEnvName, browserstack_username, browserstack_accesskey, os, os_version, browserName, browserVersion);
            } else if (cloudEnvName.equalsIgnoreCase("saucelabs")) {
                getCloudDriver(cloudEnvName, saucelabs_username, saucelabs_accesskey, os, os_version, browserName, browserVersion);
            }
        } else {
            getLocalDriver(os, browserName);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
    }

    public WebDriver getLocalDriver(@Optional("mac") String OS, String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            if (OS.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.chrome.driver", "/Users/mdtaque/eclipse-workspace/GroupFramework/Generic/Browser-driver/chromedriver");
            } else if (OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.chrome.driver", "../Generic/browser-driver/chromedriver.exe");
            }
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("chrome-options")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            if (OS.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.chrome.driver", "/Users/mdtaque/eclipse-workspace/GroupFramework/Generic/Browser-driver/chromedriver");
            } else if (OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.chrome.driver", "../Generic/browser-driver/chromedriver.exe");
            }
            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            if (OS.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.gecko.driver", "/Users/mdtaque/eclipse-workspace/GroupFramework/Generic/Browser-driver/geckodriver2");
            } else if (OS.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.gecko.driver", "../Generic/browser-driver/geckodriver.exe");
            }
            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver", "../Generic/browser-driver/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        return driver;
    }

    public WebDriver getCloudDriver(String envName, String envUsername, String envAccessKey, String os, String os_version, String browserName,
                                    String browserVersion) throws IOException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browser", browserName);
        cap.setCapability("browser_version", browserVersion);
        cap.setCapability("os", os);
        cap.setCapability("os_version", os_version);
        if (envName.equalsIgnoreCase("Saucelabs")) {
            //resolution for Saucelabs
            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey +
                    "@ondemand.saucelabs.com:80/wd/hub"), cap);
        } else if (envName.equalsIgnoreCase("Browserstack")) {
            cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey +
                    "@hub-cloud.browserstack.com/wd/hub"), cap);
        }
        return driver;
    }

    //ExtentReport
    public static ExtentReports extent;

    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentManager.setOutputDirectory(context);
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName().toLowerCase();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }

    protected String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }

    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));

        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot(driver, result.getName());
        }
        driver.quit();
    }

    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    @AfterMethod
    public void cleanUp() {
         driver.quit();
    }

    public void clickOnCss(String locator) {
        driver.findElement(By.cssSelector(locator)).click();
    }

    public void clickOnElement(String locator) {
        try {
            driver.findElement(By.cssSelector(locator)).click();
        } catch (Exception ex1) {
            try {
                driver.findElement(By.xpath(locator)).click();
            } catch (Exception ex2) {
                driver.findElement(By.id(locator)).click();
            }
        }
    }
    public void typeOnCss(String locator, String value) {
        driver.findElement(By.cssSelector(locator)).sendKeys(value);
    }

    public void typeOnInputField(String locator, String value) {
        try {
            driver.findElement(By.cssSelector(locator)).sendKeys(value);
        } catch (Exception ex) {
            driver.findElement(By.id(locator)).sendKeys(value);
        }

    }
    public void clickByXpath(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

    public void typeByCss(String locator, String value) {
        driver.findElement(By.cssSelector(locator)).sendKeys(value);
    }

    public void typeByCssNEnter(String locator, String value) {
        driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
    }

    public void typeByXpath(String locator, String value) {
        driver.findElement(By.xpath(locator)).sendKeys(value);
    }

    public void takeEnterKeys(String locator) {
        driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
    }

    public void clearInputField(String locator) {
        driver.findElement(By.cssSelector(locator)).clear();
    }

    public List<WebElement> getListOfWebElementsById(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.id(locator));
        return list;
    }

    public List<String> getTextFromWebElements(String locator) {
        List<WebElement> element = new ArrayList<WebElement>();
        List<String> text = new ArrayList<String>();
        element = driver.findElements(By.cssSelector(locator));
        for (WebElement web : element) {
            String st = web.getText();
            text.add(st);
        }

        return text;
    }

    public List<WebElement> getListOfWebElementsByCss(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.cssSelector(locator));
        return list;
    }

    public List<WebElement> getListOfWebElementsByXpath(String locator) {
        List<WebElement> list = new ArrayList<WebElement>();
        list = driver.findElements(By.xpath(locator));
        return list;
    }

    public String getCurrentPageUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public String getTextByCss(String locator) {
        String st = driver.findElement(By.cssSelector(locator)).getText();
        return st;
    }

    public String getTextByXpath(String locator) {
        String st = driver.findElement(By.xpath(locator)).getText();
        return st;
    }

    public String getTextById(String locator) {
        return driver.findElement(By.id(locator)).getText();
    }

    public String getTextByName(String locator) {
        String st = driver.findElement(By.name(locator)).getText();
        return st;
    }

    public List<String> getListOfString(List<WebElement> list) {
        List<String> items = new ArrayList<String>();
        for (WebElement element : list) {
            items.add(element.getText());
        }
        return items;
    }

    public void selectOptionByVisibleText(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    public static void sleepFor(int sec) throws InterruptedException {
        Thread.sleep(sec * 1000);
    }

    public void mouseHoverByCSS(String locator) {
        try {
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            Actions hover = action.moveToElement(element);
        } catch (Exception ex) {
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
        }
    }

    public void mouseHoverByXpath(String locator) {
        try {
            WebElement element = driver.findElement(By.xpath(locator));
            Actions action = new Actions(driver);
            Actions hover = action.moveToElement(element);
        } catch (Exception ex) {
            System.out.println("First attempt has been done, This is second try");
            WebElement element = driver.findElement(By.cssSelector(locator));
            Actions action = new Actions(driver);
            action.moveToElement(element).perform();
        }
    }

    public void switchWindow(WebDriver driver) {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
    }

    public static void implicitWait(WebDriver driver, int sec) {
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    //Verify all Links in one page
    // Return list of links available in the HomePage
    public List<String> findNumberOfLink(List<WebElement> anchorTag) {
        System.out.println(anchorTag.size());
        List<String> actualLinks = new ArrayList<>();
        for (int i = 0; i < anchorTag.size(); i = i + 1) {
            if (anchorTag.get(i).getText() != null && anchorTag.get(i).getText().length() > 0) {
                actualLinks.add(anchorTag.get(i).getText());
            }
        }
        for (String link : actualLinks) {
            System.out.println(link);
        }
        return actualLinks;
    }

    //Get Assert Data
    public List<String> getAssertData(String DataFilePath, int ColumnNo) throws IOException {
        DataReader dtr = new DataReader();
        List<String> output = Arrays.asList(dtr.colReader(DataFilePath, ColumnNo));
        return output;
    }

    // Assert Data
    public void assertData(List<String> actualList, List<String> expectedList) {
        for (int i = 0; i < actualList.size(); i++) {
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(actualList.get(i).contains(expectedList.get(i)));
            System.out.println("LinkVerified " + expectedList.get(i));
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

    public String converToString(String st) {
        String splitString;
        splitString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(st), ' ');
        return splitString;
    }

    //iFrame Handle
    public void iframeHandle(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void goBackToHomeWindow() {
        driver.switchTo().defaultContent();
    }

    public void dropDown(WebElement web, int x) {
        Select dropdown = new Select(web);
        dropdown.selectByIndex(x);
    }

    public void inputValueInTextBoxByWebElement(WebElement webElement, String value) {
        webElement.sendKeys(value + Keys.ENTER);
    }

    public void clearInputBox(WebElement webElement) {
        webElement.clear();
    }

    public String getTextByWebElement(WebElement webElement) {
        String text = webElement.getText();
        return text;
    }

    //get Links
    public void getLinks(String locator) {
        driver.findElement(By.linkText(locator)).findElement(By.tagName("a")).getText();
    }

    public void SendKeys(WebElement element, String keys, String elementName) {
        TestLogger.log("Sending Keys to " + elementName);
        element.sendKeys(keys);
        TestLogger.log("Keys Sent Successfully to " + elementName);
    }

    //Synchronization
    public void waitUntilClickAble(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitUntilVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilSelectable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));
    }

    public void upLoadFile(String locator, String path) {
        driver.findElement(By.cssSelector(locator)).sendKeys(path);
        /* path example to upload a file/image
           path= "C:\\Users\\rrt\\Pictures\\ds1.png";
         */
    }

    public void clearInput(String locator) {
        driver.findElement(By.cssSelector(locator)).clear();
    }

    public void keysInput(String locator) {
        driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
    }

    public static String convertToString(String st) {
        String splitString;
        splitString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(st), ' ');
        return splitString;
    }

    //Handling New Tabs
    public static WebDriver handleNewTab(WebDriver driver1) {
        String oldTab = driver1.getWindowHandle();
        List<String> newTabs = new ArrayList<String>(driver1.getWindowHandles());
        newTabs.remove(oldTab);
        driver1.switchTo().window(newTabs.get(0));
        return driver1;
    }

       public void commonMethodWithCssForClick(String locator) {
        driver.findElement(By.cssSelector(locator)).click();
    }

    public void commonMethodWithXpathForClick(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

    public void commonMethodWithClassForClick(String locator) {
        driver.findElement(By.className(locator)).click();
    }

    public void clickOnElementTryCatch(String locator){
        try{
            driver.findElement(By.cssSelector(locator)).click();
        }catch(Exception ex1){
            try {
                driver.findElement(By.xpath(locator)).click();
            }catch (Exception ex2){
                try {
                    driver.findElement(By.id(locator)).click();
                }catch (Exception ex3){
                    try {
                        driver.findElement(By.className(locator)).click();
                    }catch (Exception ex4){
                        System.out.println("All locators failed to locate element!");
                    }
                }
            }

        }
    }

    public void sendKeyOnInputTryCatch(String locator, String value){
        try{
            driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
        }catch(Exception ex1){
            try {
                driver.findElement(By.xpath(locator)).sendKeys(value, Keys.ENTER);
            }catch (Exception ex2){
                try {
                    driver.findElement(By.id(locator)).sendKeys(value, Keys.ENTER);
                }catch (Exception ex3){
                    try {
                        driver.findElement(By.className(locator)).sendKeys(value, Keys.ENTER);
                    }catch (Exception ex4){
                        System.out.println("All locators failed to locate element!");
                    }
                }
            }

        }
    }
    public void commonMethodWithXpathForSendKey(String locator, String value){
        driver.findElement(By.xpath(locator)).sendKeys(value, Keys.ENTER);
    }

    public void commonMethodWithClassForSendKey(String locator, String value){
        driver.findElement(By.className(locator)).sendKeys(value, Keys.ENTER);
    }

    public void commonMethodWithCssForSendKey(String locator, String value){
        driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
    }

    public static boolean isPopUpWindowDisplayed(WebDriver driver1, String locator) {
        boolean value = driver1.findElement(By.cssSelector(locator)).isDisplayed();
        return value;
    }

    public void typeOnInputBox(String locator, String value) {
        try {
            driver.findElement(By.id(locator)).sendKeys(value, Keys.ENTER);
        } catch (Exception ex1) {
            System.out.println("ID locator didn't work");
        }
        try {
            driver.findElement(By.name(locator)).sendKeys(value, Keys.ENTER);
        } catch (Exception ex2) {
            System.out.println("Name locator didn't work");
        }
        try {
            driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
        } catch (Exception ex3) {
            System.out.println("CSS locator didn't work");
        }
    }

       public static void captureScreenshot(WebDriver driver, String screenshotName){

        DateFormat df = new SimpleDateFormat("(MM.dd.yyyy-HH:mma)");
        Date date = new Date();
        df.format(date);

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(System.getProperty("user.dir")+ "/screenshots/"+screenshotName+" "+df.format(date)+".png"));
            System.out.println("Screenshot captured");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot "+e.getMessage());;
        }

    }

    //Taking Screen shots
    public void takeScreenShot(String url)throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file,new File("ScreenshotManual.png"));
    }
}




//    //ExtentReport
//    public static ExtentReports extent;
//    @BeforeSuite
//    public void extentSetup(ITestContext context) {
//        ExtentManager.setOutputDirectory(context);
//        extent = ExtentManager.getInstance();
//    }
//    @BeforeMethod
//    public void startExtent(Method method) {
//        String className = method.getDeclaringClass().getSimpleName();
//        String methodName = method.getName().toLowerCase();
//        ExtentTestManager.startTest(method.getName());
//        ExtentTestManager.getTest().assignCategory(className);
//    }
//    protected String getStackTrace(Throwable t) {
//        StringWriter sw = new StringWriter();
//        PrintWriter pw = new PrintWriter(sw);
//        t.printStackTrace(pw);
//        return sw.toString();
//    }
//    @AfterMethod
//    public void afterEachTestMethod(ITestResult result) {
//        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
//        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));
//
//        for (String group : result.getMethod().getGroups()) {
//            ExtentTestManager.getTest().assignCategory(group);
//        }
//
//        if (result.getStatus() == 1) {
//            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
//        } else if (result.getStatus() == 2) {
//            ExtentTestManager.getTest().log(LogStatus.FAIL, getStackTrace(result.getThrowable()));
//        } else if (result.getStatus() == 3) {
//            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
//        }
//        ExtentTestManager.endTest();
//        extent.flush();
//        if (result.getStatus() == ITestResult.FAILURE) {
//            captureScreenshot(driver, result.getName());
//        }
//        driver.quit();
//    }
//    @AfterSuite
//    public void generateReport() {
//        extent.close();
//    }
//    private Date getTime(long millis) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(millis);
//        return calendar.getTime();
//    }
//
//    public WebDriver driver = null;
//    private String saucelabs_username = "";
//    private String browserstack_username = "mdrifattaque1";
//    private String saucelabs_accesskey = "";
//    private String browserstack_accesskey = "wb76kksaAYVDv6yivyzb";
//
//    @Parameters({"useCloudEnv", "cloudEnvName", "os", "os_version", "browserName", "browserVersion", "url"})
//    @BeforeMethod
//    public void setUp(@Optional("false") boolean useCloudEnv, @Optional("false") String cloudEnvName,
//                      @Optional("OS X") String os, @Optional("10") String os_version, @Optional("chrome-options") String browserName, @Optional("34")
//                                  String browserVersion, @Optional("https://www.piit.us/") String url) throws IOException {
//        System.setProperty("webdriver.chrome.driver", "/Users/mdtaque/eclipse-workspace/GroupFramework/Generic/Browser-driver/chromedriver");
//        if (useCloudEnv == true) {
//            if (cloudEnvName.equalsIgnoreCase("browserstack")) {
//                getCloudDriver(cloudEnvName, browserstack_username, browserstack_accesskey, os, os_version, browserName, browserVersion);
//            } else if (cloudEnvName.equalsIgnoreCase("saucelabs")) {
//                getCloudDriver(cloudEnvName, saucelabs_username, saucelabs_accesskey, os, os_version, browserName, browserVersion);
//            }
//        } else {
//            getLocalDriver(browserName, os);
//        }
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //20
//        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); //35
//        driver.get(url);
//        driver.manage().window().maximize();
//    }
//
//    public WebDriver getLocalDriver(String browserName, String os) {
//        if (browserName.equalsIgnoreCase("chrome")) {
//            if (os.equalsIgnoreCase("windows")) {
//                System.setProperty("webdriver.chrome.driver", "/Users/mdtaque/eclipse-workspace/GroupFramework/Generic/Browser-driver/chromedriver");
//                driver = new ChromeDriver();
//            } else if (os.equalsIgnoreCase("mac")) {
//                System.setProperty("webdriver.chrome.driver", "/Users/mdtaque/eclipse-workspace/GroupFramework/Generic/Browser-driver/chromedriver");
//                driver = new ChromeDriver();
//            }
//        } else if (browserName.equalsIgnoreCase("firefox")) {
//            if (os.equalsIgnoreCase("windows")) {
//                System.setProperty("webdriver.gecko.driver", "/Users/mdtaque/eclipse-workspace/GroupFramework/Generic/Browser-driver/geckodriver2");
//                driver = new FirefoxDriver();
//            } else if (os.equalsIgnoreCase("mac")) {
//                System.setProperty("webdriver.gecko.driver", "/Users/mdtaque/eclipse-workspace/GroupFramework/Generic/Browser-driver/geckodriver2");
//                driver = new FirefoxDriver();
//            }
//        }
//        return driver;
//    }
//
//    public WebDriver getCloudDriver(String envName, String envUsername, String envAccessKey, String os, String os_version, String browserName,
//                                    String browserVersion) throws IOException {
//
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("browser", "Chrome");
//        caps.setCapability("browser_version", "62.0");
//        caps.setCapability("os", "mac");
//        caps.setCapability("os_version", "10");
//
//        if (envName.equalsIgnoreCase("Saucelabs")) {
//            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey +
//                    "@ondemand.saucelabs.com:80/wd/hub"), caps);
//        } else if (envName.equalsIgnoreCase("Browserstack")) {
//            caps.setCapability("resolution", "1024x768");
//            driver = new RemoteWebDriver(new URL("http://" + envUsername + ":" + envAccessKey +
//                    "@hub-cloud.browserstack.com/wd/hub"), caps);
//        }
//        return driver;
//    }
//
//    @AfterMethod
//    public void cleanUp() {
//        driver.quit();
//    }
//
//    //get title of the current page
//    public static String showTitle(WebDriver driver){
//        String title = driver.getTitle();
//        return title;
//    }
//
//    public void commonMethodWithCssForClick(String locator) {
//        driver.findElement(By.cssSelector(locator)).click();
//    }
//
//    public void commonMethodWithXpathForClick(String locator) {
//        driver.findElement(By.xpath(locator)).click();
//    }
//
//    public void commonMethodWithClassForClick(String locator) {
//        driver.findElement(By.className(locator)).click();
//    }
//
//    public void clickOnElementTryCatch(String locator){
//        try{
//            driver.findElement(By.cssSelector(locator)).click();
//        }catch(Exception ex1){
//            try {
//                driver.findElement(By.xpath(locator)).click();
//            }catch (Exception ex2){
//                try {
//                    driver.findElement(By.id(locator)).click();
//                }catch (Exception ex3){
//                    try {
//                        driver.findElement(By.className(locator)).click();
//                    }catch (Exception ex4){
//                        System.out.println("All locators failed to locate element!");
//                    }
//                }
//            }
//
//        }
//    }
//
//    public void sendKeyOnInputTryCatch(String locator, String value){
//        try{
//            driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
//        }catch(Exception ex1){
//            try {
//                driver.findElement(By.xpath(locator)).sendKeys(value, Keys.ENTER);
//            }catch (Exception ex2){
//                try {
//                    driver.findElement(By.id(locator)).sendKeys(value, Keys.ENTER);
//                }catch (Exception ex3){
//                    try {
//                        driver.findElement(By.className(locator)).sendKeys(value, Keys.ENTER);
//                    }catch (Exception ex4){
//                        System.out.println("All locators failed to locate element!");
//                    }
//                }
//            }
//
//        }
//    }
//
//
//
//    public void commonMethodWithXpathForSendKey(String locator, String value){
//        driver.findElement(By.xpath(locator)).sendKeys(value, Keys.ENTER);
//    }
//
//    public void commonMethodWithClassForSendKey(String locator, String value){
//        driver.findElement(By.className(locator)).sendKeys(value, Keys.ENTER);
//    }
//
//    public void commonMethodWithCssForSendKey(String locator, String value){
//        driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
//    }
//
//    public void clearInputFieldByXpath(String locator){
//        driver.findElement(By.xpath(locator)).clear();
//    }
//    public void clearInputByCss(String locator){
//        driver.findElement(By.cssSelector(locator)).clear();
//    }
//    public void clearInputFieldByClass(String locator){
//        driver.findElement(By.className(locator)).clear();
//    }
//    public void clearInputFieldById(String locator){
//        driver.findElement(By.id(locator)).clear();
//    }
//
//    public List<WebElement> getListOfWebElementByXpath(String locator){
//        List<WebElement> list = new ArrayList<>();
//        list = driver.findElements(By.xpath(locator));
//        return list;
//    }
//
//    public List<String> getTextFromElements(String locator){
//        List<WebElement> elements = new ArrayList<>();
//        List<String> text = new ArrayList<>();
//        elements = driver.findElements(By.cssSelector(locator));
//        for(WebElement web : elements){
//            String st = web.getText();
//            text.add(st);
//        }
//        return text;
//    }
//    public List<WebElement> getListOfWebElementByCss(String locator){
//        List<WebElement> list = new ArrayList<>();
//        list = driver.findElements(By.cssSelector(locator));
//        return list;
//    }
//
//    public String getCurrentPageUrl(){
//        String url = driver.getCurrentUrl();
//        return url;
//    }
//
//    public void navigateBack(){
//        driver.navigate().back();
//    }
//
//    public void navigateForward(){
//        driver.navigate().forward();
//    }
//
//    public String getTextByCss(String locator){
//        String text = driver.findElement(By.cssSelector(locator)).getText();
//        return text;
//    }
//
//    public String getTextByXpath(String locator){
//        String text = driver.findElement(By.cssSelector(locator)).getText();
//        return text;
//    }
//
//    public String getTextById(String locator){
//        String text = driver.findElement(By.id(locator)).getText();
//        return text;
//    }
//
//    public void mouseHoverByCss(String locator){
//        WebElement element = driver.findElement(By.cssSelector(locator));
//        Actions action = new Actions(driver);
//        action.moveToElement(element).perform();
//    }
//
//    public static void captureScreenshot(WebDriver driver, String screenshotName){
//
//        DateFormat df = new SimpleDateFormat("(MM.dd.yyyy-HH:mma)");
//        Date date = new Date();
//        df.format(date);
//
//        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        try {
//            FileUtils.copyFile(file, new File(System.getProperty("user.dir")+ "/screenshots/"+screenshotName+" "+df.format(date)+".png"));
//            System.out.println("Screenshot captured");
//        } catch (Exception e) {
//            System.out.println("Exception while taking screenshot "+e.getMessage());;
//        }
//
//    }
//
//    //Taking Screen shots
//    public void takeScreenShot(String url)throws IOException {
//        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(file,new File("ScreenshotManual.png"));
//    }
//
//    //Handling New Tabs
//    public static WebDriver handleNewTab(WebDriver driver1){
//        String oldTab = driver1.getWindowHandle();
//        List<String> newTabs = new ArrayList<String>(driver1.getWindowHandles());
//        newTabs.remove(oldTab);
//        driver1.switchTo().window(newTabs.get(0));
//        return driver1;
//    }
//    public static boolean isPopUpWindowDisplayed(WebDriver driver1, String locator){
//        boolean value = driver1.findElement(By.cssSelector(locator)).isDisplayed();
//        return value;
//    }
//
//    public void selectOptionByVisibleText(WebElement element, String value) {
//        Select select = new Select(element);
//        select.selectByVisibleText(value);
//    }
//    public static void sleepFor(int sec)throws InterruptedException{
//        Thread.sleep(sec * 1000);
//    }
//    public void mouseHoverByCSS(String locator){
//        try {
//            WebElement element = driver.findElement(By.cssSelector(locator));
//            Actions action = new Actions(driver);
//            Actions hover = action.moveToElement(element);
//        }catch(Exception ex){
//            System.out.println("First attempt has been done, This is second try");
//            WebElement element = driver.findElement(By.cssSelector(locator));
//            Actions action = new Actions(driver);
//            action.moveToElement(element).perform();
//
//        }
//    }
//    public void clearInput(String locator){
//        driver.findElement(By.cssSelector(locator)).clear();
//    }
//    public void keysInput(String locator){
//        driver.findElement(By.cssSelector(locator)).sendKeys(Keys.ENTER);
//    }
//    public static String convertToString(String st){
//        String splitString ;
//        splitString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(st), ' ');
//        return splitString;
//    }
//
//    public void switchWindow(WebDriver driver){
//        for (String handle: driver.getWindowHandles()){
//            driver.switchTo().window(handle);
//        }
//    }
//    public void dropDown(WebElement web, int x){
//        Select dropdown = new Select(web);
//        dropdown.selectByIndex(x);
//    }
//    public void inputValueInTextBoxByWebElement(WebElement webElement, String value){
//        webElement.sendKeys(value + Keys.ENTER);
//    }
//    public void clearInputBox(WebElement webElement){
//        webElement.clear();
//    }
//    public String getTextByWebElement(WebElement webElement){
//        String text = webElement.getText();
//        return text;
//    }
//}
