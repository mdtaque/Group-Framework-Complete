package TestHandlePopUp;

import PopUpHandle.HandlePopUp;
import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import reporting.TestLogger;

public class TestPopUpHandle extends HandlePopUp {
    HandlePopUp objOfHandlePopUp;

    @Test
    public void popUpHandle() throws InterruptedException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        objOfHandlePopUp = PageFactory.initElements(driver, HandlePopUp.class);
        objOfHandlePopUp.handlePopUpWindowBeforeLogIn(driver);
    }
}
