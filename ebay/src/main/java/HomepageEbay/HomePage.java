package HomepageEbay;

import base.CommonAPI;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import reporting.TestLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomePage extends CommonAPI {

    @FindBy(xpath = "//*[@id=\"mainContent\"]/div[1]/ul/li[4]/a")
    public static WebElement fashiontab;
    @FindBy(xpath = "//*[@id=\"mainContent\"]/div[1]/ul/li[4]/div[2]/div[1]/div[1]/ul/li[3]/a")
    public static WebElement mensclothing;
    @FindBy(xpath = "//input[@id='gh-ac']")
    WebElement searchinputbox;
    @FindBy(xpath = "//*[@id=\"gh-eb-Alerts\"]/div/button")
    public static WebElement bellButton;
    @FindBy(css = "#ghn-err")
    public static WebElement clickOnSignIn;
    @FindBy(xpath = "//select[@id='gh-cat']")
    WebElement allCategories;
    @FindBy(tagName = "a")
    public List<WebElement> anchorTag = new ArrayList<>();
    @FindBy(tagName = "a")
    public List<WebElement> numberOfLinks;
    @FindBy(tagName = "iframe")
    List<WebElement> numberOfiFrame;
    @FindBy(linkText = "Featured Sales & Events")
    WebElement featuredSalesEvents;
    @FindBy(xpath = "//button[@id='gh-shop-a']")
    WebElement shopByCategory;

    public void InputSearchBox(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        searchinputbox.sendKeys("perfume", Keys.ENTER);
    }
    public List<String> listOfElements() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        List<String> list = new ArrayList<>();
        list.add("lotion");
        list.add("watch");
        list.add("sunglass");
        list.add("phone");

        return list;
    }
    public List<String> listOfText(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        List<WebElement> elements = new ArrayList<>();
        List<String> text = new ArrayList<>();
        for(WebElement web : elements){
            text.add(web.getText());
        }
        return text;
    }
    public void getList(){
        for(String st : listOfElements()){
            searchinputbox.sendKeys(st,Keys.ENTER);
            searchinputbox.clear();
        }
    }
    public void hoverOver() throws InterruptedException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        Actions actions=new Actions(driver);
        actions.moveToElement(fashiontab).perform();
        Thread.sleep(2000);
        actions.moveToElement(mensclothing).click().perform();
    }
    public void goToLoginPage() throws InterruptedException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        bellButton.click();
        clickOnSignIn.click();
        Thread.sleep(3000);
    }
    public void screenShot() throws IOException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        takeScreenShot("https://www.ebay.com/");
    }
    public void clickOnAllCategories(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        allCategories.click();
    }
    public List<String> dropDrownList(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        Select myDropDown = new Select(allCategories);
        List<WebElement> list = myDropDown.getOptions();
        List<String> text = new ArrayList<>();
        for(WebElement web : list){
            text.add(web.getText());
            System.out.println(web.getText());
        }
        return text;
    }
    public void dropDownSingleElement(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        dropDown(allCategories, 5);
    }
    public void findNumberOfLinksInHomePage() throws IOException{
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        List<String> actualLinkList = findNumberOfLink(anchorTag);
        List<String> expectedLinkList = getAssertData("../ebay/data/expectedLinksOnEbay.xls", 2);
        assertData(actualLinkList, expectedLinkList);
    }
    public void linkstext(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        //List<WebElement> list = new ArrayList<>(anchorTag);
        List<String> txt = new ArrayList<>();
        for(WebElement web : anchorTag){
            txt.add(web.getText());
            System.out.println(web.getText());
        }
    }
    public void noOfLinksOnEbay(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        int totalLinks = numberOfLinks.size();
        System.out.println("total no of links: " + totalLinks);
    }
    public void totalNoOfiFrames(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        int totaliFrame = numberOfiFrame.size();
        System.out.println("total no of iframe: " + totaliFrame);
    }
    public String currentLinkOfLoginPage() throws InterruptedException{
        bellButton.click();
        clickOnSignIn.click();
        sleepFor(3);
        String currentUrl = driver.getCurrentUrl();
        return currentUrl;
    }
    public boolean shopByCategoryIsDisplayed() throws InterruptedException {
        boolean actual = shopByCategory.isEnabled();
        System.out.println(actual);
        return actual;
    }
    //scrolling untill element is visible on iframe
    public void scrollToAboutEbay(){
        JavascriptExecutor je = (JavascriptExecutor) driver; //downcasting webdriver object to javascript executor
        je.executeScript("arguments[0].scrollIntoView(true);", featuredSalesEvents);
        featuredSalesEvents.click();
    }
}