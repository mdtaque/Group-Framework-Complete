package LogInPage;

import HomepageEbay.HomePage;
import base.CommonAPI;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.support.PageFactory;
import utility.DataReader;
import java.io.IOException;

public class LoginWithKeyword extends CommonAPI {

    HomePage homePage = PageFactory.initElements(driver,HomePage.class);
    SignIn objOfSignIn = PageFactory.initElements(driver, SignIn.class);

    public void navigateToLoginPage() throws InterruptedException {
        homePage.goToLoginPage();
    }
    public void submitLoginButton(){
        objOfSignIn.logInManual();
    }
    public void selectAction(String featureName) throws InterruptedException {
        switch (featureName){
            case "goToLOGINPage":
                navigateToLoginPage();
                break;
            case "Login":
                submitLoginButton();
                break;
            default:
                throw new InvalidArgumentException("Invalid feature choice");
        }
    }
    DataReader reader = new DataReader();
    public String[] getDataFromSignInKeyword(String filename) throws IOException {
        String path = "../ebay/data/" + filename;
        String[] output = reader.colReader(path, 2);
        return output;
    }
    public void loginByKeyword() throws IOException, InterruptedException {
        String[] keyword = getDataFromSignInKeyword("ExelKeywordDriven.xls");
        for(int i=0; i<keyword.length; i++){
            selectAction(keyword[i]);
        }
    }
}