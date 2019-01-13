package DataDrivenTest;

import DataDriven.XlsReaderMain;
import DataReader.XlsReader;
import base.CommonAPI;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TestXlsReader extends XlsReaderMain {

    XlsReaderMain objOfXlsReaderMain;

    @BeforeMethod
    public void init(){
        objOfXlsReaderMain = PageFactory.initElements(driver, XlsReaderMain.class);
    }
    @Test
    public void signInWithHover() throws InterruptedException {
        objOfXlsReaderMain.goToCreateAccountPage();
    }
    @DataProvider
    public Object[][] getExcelTestData(){
        Object data[][] = XlsReader.getTestData(0);
        return data;
    }
    @Test(dataProvider = "getExcelTestData")
    public void testCreatingAccount(String FirstName, String LastName, String Email, String PassWord) throws InterruptedException {
        objOfXlsReaderMain.creatAccountOnEbay(FirstName, LastName, Email, PassWord);
    }
}
