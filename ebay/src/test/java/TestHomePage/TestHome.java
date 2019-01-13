package TestHomePage;

import HomepageEbay.HomePage;
import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reporting.TestLogger;

import java.io.IOException;

public class TestHome extends HomePage {
    HomePage objOfHomePage;

    @BeforeMethod
    public void initialization(){
        objOfHomePage = PageFactory.initElements(CommonAPI.driver, HomePage.class);
    }
    @Test
    public void search(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objOfHomePage.InputSearchBox();
    }
    @Test(enabled = true)
    public void listOfEl(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objOfHomePage.getList();
        objOfHomePage.listOfText();
    }
    @Test(enabled = true)
    public void hover_Test() throws InterruptedException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        hoverOver();
    }
    @Test(enabled = true)
    public void testGoToLoginPage() throws InterruptedException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objOfHomePage.goToLoginPage();
    }
    @Test
    public void testScreenShot() throws IOException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        screenShot();
    }
    @Test
    public void testAllCategory(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objOfHomePage.clickOnAllCategories();
    }
    @Test
    public void dropdowntest(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objOfHomePage.dropDrownList();
    }
    @Test
    public void teeee(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objOfHomePage.linkstext();
    }
    @Test
    public void noOfLinksText() throws IOException{
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objOfHomePage.findNumberOfLinksInHomePage();
    }
    @Test
    public void dropDownSingleElement(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objOfHomePage.dropDownSingleElement();
    }
    @Test
    public void noOfLinks(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objOfHomePage.noOfLinksOnEbay();
    }
    @Test
    public void noOfFrame(){
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objOfHomePage.totalNoOfiFrames();
    }
    @Test
    public void testCurrentUrl() throws InterruptedException {
        String actualLink = objOfHomePage.currentLinkOfLoginPage();
        String expected = "https://signin.ebay.com/ws/eBayISAPI.dll?SignIn&ru=https%3A%2F%2Fwww.ebay.com%2F";
        Assert.assertTrue(actualLink.equals(expected));
    }
    @Test
    public void testDisplayed() throws InterruptedException {
        objOfHomePage.shopByCategoryIsDisplayed();
        boolean actual = shopByCategoryIsDisplayed();
        Assert.assertEquals(actual, true);
    }
    @Test
    public void testScroll(){
        objOfHomePage.scrollToAboutEbay();
    }
}
