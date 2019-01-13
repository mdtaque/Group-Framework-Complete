package Database;

import base.BaseUtil;
import database2.ConnectToSqlDB;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class Databaseconnection extends BaseUtil {

    @FindBy(xpath = "//input[@id='gh-ac']")
    WebElement searchinputbox;

    public List<String> menulist() throws InterruptedException {
        Select dropDown = new Select(driver.findElement(By.cssSelector(".gh-sb.gh-sprRetina")));
        List<WebElement> dropDownList = dropDown.getOptions();
        List<String> dropDownListText = new ArrayList<>();
        for(WebElement it: dropDownList){
           dropDownListText.add(it.getText());
        }
        return  dropDownListText;
    }
    //mySql
    public List<String> listOfItemsToSearch(){
        List<String> list = new ArrayList<>();
        list.add("sunglass");
        list.add("backpack");
        list.add("sneakers");
        return list;
    }
    public List<String> searchItemsFromDBSql() throws Exception {
        ConnectToSqlDB.insertDataFromArrayListToMySql(listOfItemsToSearch(), "testsearch", "items");
        List<String> itemsToSearch = ConnectToSqlDB.readDataBase("testsearch", "items");
        for (String st : itemsToSearch) {
            searchinputbox.sendKeys(st, Keys.ENTER);
            Thread.sleep(800);
            driver.navigate().back();
        }
        return itemsToSearch;
    }
}