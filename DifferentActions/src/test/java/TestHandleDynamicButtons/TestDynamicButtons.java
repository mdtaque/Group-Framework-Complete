package TestHandleDynamicButtons;

import DynamicRadioButtonAndCheckBox.HandleDynamicButtons;
import base.BaseUtil;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestDynamicButtons extends BaseUtil {

    HandleDynamicButtons handleDynamicButtons;

    @BeforeMethod
    public void init(){
        handleDynamicButtons = PageFactory.initElements(BaseUtil.driver, HandleDynamicButtons.class);
    }

    @Test
    public void testButtonsClick(){
        driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-automate-radio-button-in.html");
        handleDynamicButtons.clickOnRadioButtons();
    }

    @Test
    public void testAllButtons(){
        driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-automate-radio-button-in.html");
        handleDynamicButtons.allButtons();
    }

    @Test
    public void testAllCheckBoxes(){
        driver.get("http://seleniumpractise.blogspot.com/2016/08/how-to-automate-radio-button-in.html");
        handleDynamicButtons.allCheckBoxes();
    }


}
