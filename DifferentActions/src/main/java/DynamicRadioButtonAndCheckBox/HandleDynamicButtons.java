package DynamicRadioButtonAndCheckBox;

import base.BaseUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HandleDynamicButtons extends BaseUtil {

    @FindBy(xpath = "//input[@id='python']")
    WebElement python;
    @FindBy(xpath = "//input[@name='lang' and @type='radio']")
    List<WebElement> radioButtons;
    @FindBy(xpath = "//input[@type='checkbox']")
    List<WebElement> checkBox;

    public void clickOnRadioButtons(){
        //python.click();
    }

    public void allButtons(){
       for(int i=0; i<radioButtons.size(); i++){
           WebElement radio = radioButtons.get(i);
           String value = radio.getAttribute("value");
           System.out.println(value);

           if(value.contains("RUBY")){
               radio.click();
               break; //if you find "RUBY" then click on it & go out of the loop. if we don't put break
                      // it will again check unnecessarily untill the for loop ends.
           }
       }
    }

    public void allCheckBoxes(){
        for(int i=0; i<checkBox.size(); i++){
            WebElement element = checkBox.get(i);
            String id = element.getAttribute("id");
            System.out.println(id);

            if(id.contains("sing")){
                element.click();
                break;
            }
        }
    }
}
