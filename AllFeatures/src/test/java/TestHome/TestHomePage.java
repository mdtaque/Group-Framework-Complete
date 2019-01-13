package TestHome;

import HomepagePnt.HomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;

public class TestHomePage extends HomePage {

    HomePage objOfHomePage;

    @BeforeMethod
    public void init(){
        objOfHomePage = PageFactory.initElements(driver, HomePage.class);
    }

//    @Test
//    public void testpopup() throws InterruptedException {
//        objOfHomePage.clickOnSoftware();
//    }

//    @Test
//    public void buttonClickOn() throws InterruptedException {
//        buttonClick();
//    }
}
