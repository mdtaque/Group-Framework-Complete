package TestWindowHandle;

import WindowHandling.HandleWindow;
import base.BaseUtil;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestHandleWindow extends BaseUtil {

    HandleWindow objOfHandleWindow;

    @BeforeMethod
    public void init(){
        objOfHandleWindow = PageFactory.initElements(driver, HandleWindow.class);
        driver.get("http://toolsqa.com/automation-practice-switch-windows/");
    }
    @Test
    public void testWindowHandling() throws InterruptedException {
        objOfHandleWindow.handlingWindow();
    }
    @Test
    public void testWindowHandleByCondition() throws InterruptedException {
        objOfHandleWindow.windowSwitchByCondition();
    }
    @Test
    public void switchAgainnn() throws InterruptedException, IOException {
        objOfHandleWindow.switchWindowAgain();
    }
    @Test
    public void switchMsgWindow() throws InterruptedException {
        objOfHandleWindow.switchToMsgWindow();
    }
    @Test
    public void switchTab() throws InterruptedException {
        objOfHandleWindow.switchToNewTab();
    }
    @Test
    public void handleAlertBox() throws InterruptedException {
        objOfHandleWindow.handleAlertBox();
    }
}
