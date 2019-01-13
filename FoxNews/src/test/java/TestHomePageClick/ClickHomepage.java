package TestHomePageClick;

import HomePage.HomePageClick;
import base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reporting.TestLogger;

public class ClickHomepage extends HomePageClick {
    HomePageClick objofHomePage;

    @BeforeMethod
    public void initialization() {
        objofHomePage = PageFactory.initElements(driver, HomePageClick.class);
    }
    @Test
    public void click() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        driver.findElement(By.xpath("//*[@id=\"main-nav\"]/ul/li[1]/a")).click();
    }
    @Test
    public void click2() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        driver.findElement(By.cssSelector("#main-nav > ul > li:nth-child(2) > a")).click();
    }
    @Test
    public void main() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        commonMethodWithCssForClick("#main-nav > ul > li.menu-opinion > a");
    }
    @Test
    public void main2() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        commonMethodWithXpathForClick("//*[@id=\"main-nav\"]/ul/li[5]/a");
    }
    @Test
    public void main3() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        commonMethodWithXpathForClick("//li[contains(@class,'menu-more')]");
    }
    @Test
    public void main4() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        commonMethodWithXpathForClick("//*[@id=\"main-nav\"]/ul/li[7]/a");
    }
    @Test
    public void main5() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        commonMethodWithClassForClick("subnav-title");
    }
    @Test
    public void clickSearchBox() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objofHomePage.searchBoxClick();
    }
    @Test
    public void SearchBoxInput() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objofHomePage.searchBoxInput("Sports");
    }
    @Test
    public void searchboxiconEnter() throws InterruptedException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objofHomePage.searchButtonEnter();
    }
    @Test
    public void logo() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objofHomePage.clicklogo();
    }
    @Test
    public void links() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objofHomePage.getLinks();
    }
    @Test
    public void linkTextverify() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objofHomePage.linkText();
    }
    @Test
    public void partialLinkTextverify() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objofHomePage.partialLinkText();
    }
}