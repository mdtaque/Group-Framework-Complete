package HomePage;

import base.CommonAPI;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import reporting.TestLogger;
import java.util.ArrayList;
import java.util.List;

public class HomePageClick extends CommonAPI {

    @FindBy(xpath = "//*[@id=\"wrapper\"]/header/div[2]/div/div[2]/div[2]/a")
    public static WebElement searchicon;
    @FindBy(xpath = "//*[@id=\"wrapper\"]/header/div[4]/div[1]/div[1]/div/form/fieldset/input[1]")
    public static WebElement searchinputbox;
    @FindBy(className = "resp_site_submit")
    public static WebElement searchbuttonEnter;
    @FindBy(className = "logo")
    public static WebElement clickonlogo;
    @FindBy(tagName = "a")
    public List<WebElement> anchorTag = new ArrayList<>();
    @FindBy(linkText = "World")
    public static WebElement verifyLinkText;
    @FindBy(partialLinkText = "Rosenstein discussed")
    public static WebElement verifyPartialLinkText;
    @FindBy(css = "#main-nav > ul > li.menu-more")
    public static WebElement clickOnMore;
    @FindBy(xpath = "//input[@class='ng-valid ng-dirty']")
    WebElement nextSearchBox;

    public void searchBoxClick() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        searchicon.click();
    }
    //reusable method
    public void searchBoxInput(String input) {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        searchicon.click();
        searchinputbox.sendKeys(input);
    }
    public void searchButtonEnter() throws InterruptedException {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        searchicon.click();
        for(String s: listOfString()){
            searchinputbox.sendKeys(s, Keys.ENTER);
            //Thread.sleep(3000);
            searchinputbox.clear();
            navigateBack();
        }
    }
    public void clicklogo() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        clickonlogo.click();
    }
    public void getLinks() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        for (WebElement links : anchorTag) {
            System.out.println(links.getAttribute("href"));
        }
    }
    public void linkText() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        verifyLinkText.click();
    }
    public void partialLinkText() {
        TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));
        verifyPartialLinkText.click();
    }
    public static List<String> listOfString() {
        List<String> list = new ArrayList<String>();
        list.add(0, "Sprots");
        list.add(1, "Politics");
        return list;
    }
}
