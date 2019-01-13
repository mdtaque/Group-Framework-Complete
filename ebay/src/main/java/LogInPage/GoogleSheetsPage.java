package LogInPage;

import base.CommonAPI;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.Sheets;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static GoogleAPIs.GoogleSheetReader.getSheetsService;

public class GoogleSheetsPage extends CommonAPI {
    @FindBy(xpath = "//*[@id=\"gh-eb-Alerts\"]/div/button")
    public static WebElement bellButton;
    @FindBy(xpath = "//div[@id='ghn-err']")
    public static WebElement clickOnSignIn;
    @FindBy(xpath = "//input[@id='userid']")
    WebElement emailOrUsername;
    @FindBy(xpath = "//input[@id='pass']")
    WebElement password;
    @FindBy(xpath = "//button[@id='sgnBt']")
    WebElement signInButton;
    @FindBy(xpath = "//p[@id='errf']")
    WebElement invalidMsg;

    public void signInn(){
        bellButton.click();
        clickOnSignIn.click();
    }
    public List<List<Object>> getSpreadSheetRecords(String spreadsheetId, String range) throws IOException {
        // Build a new authorized API client service.
        Sheets service = getSheetsService();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();
        if (values == null || values.size() == 0) {
            return null;
        } else {
            return values;
        }
    }
    //LogIn by using Google Sheet sheet data
    public List<String> signInByInvalidIdPass(String spreadsheetId, String range) throws IOException, InterruptedException {

        List<List<Object>> col2Value = getSpreadSheetRecords(spreadsheetId, range);
        List<String> actual = new ArrayList<>();
        for (List row : col2Value) {
            sleepFor(1);
            inputValueInTextBoxByWebElement(emailOrUsername, row.get(0).toString());
            inputValueInTextBoxByWebElement(password, row.get(1).toString());
            sleepFor(1);
            //actual.add(getCurrentPageTitle());
            actual.add(getTextByWebElement(invalidMsg));
            System.out.println(getTextByWebElement(invalidMsg));
            clearInputBox(emailOrUsername);
            clearInputBox(password);
            sleepFor(1);
        }
        return actual;
    }
    //Verify log in by taking data from a google sheets file
    public void LogInByInvalidIdPassUsingGoogleSheet() throws IOException, InterruptedException {
        sleepFor(3);
        signInn();
        sleepFor(3);
        int i = 0;
        String spreadsheetId = "1KqovQvwLhzgCGoJv8Ov6ktgrgYkFuyd768Y7Epoka-k";
        String range = "Sheet1!A2:C";
        List<String> actualErrorMessage = signInByInvalidIdPass(spreadsheetId, range);
        List<List<Object>> expectedErrorMessage = getSpreadSheetRecords(spreadsheetId, range);
        for (List row : expectedErrorMessage) {
            Assert.assertTrue(actualErrorMessage.get(i).contains(row.get(2).toString()));
            // System.out.println("expected"+row.get(2).toString());
            System.out.println(expectedErrorMessage.get(i) + ": Search - Passed");
            i++;
        }
        System.out.println("testLogInByInvalidIdPassUsingGoogleSheet Passed");
    }
}