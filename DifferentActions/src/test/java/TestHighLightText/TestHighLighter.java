package TestHighLightText;

import HighLightText.HighLighter;
import base.BaseUtil;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestHighLighter extends BaseUtil {

    HighLighter highLighter;

    @BeforeMethod
    public void initialization(){
        highLighter = PageFactory.initElements(driver, HighLighter.class);
    }

    @Test
    public void testHighLightText(){
        driver.get("https://www.instagram.com/");
        highLighter.clickOnLogin();
    }
}
