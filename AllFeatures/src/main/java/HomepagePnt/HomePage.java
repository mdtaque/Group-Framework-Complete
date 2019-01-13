package HomepagePnt;

import base.CommonAPI;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends CommonAPI {

    @FindBy(xpath = "/html/body/div[2]/div[14]/div/div[1]/div/div[1]/div/a/img")
    public static WebElement software;
    @FindBy(xpath = "//*[@id=\"service\"]/div/div/div[1]/div[1]/div/a/h4")
    public static WebElement mobileApplication;
    @FindBy(xpath = "/html/body/div[2]/div[5]/div/div[2]/div[1]/div/div[2]/a")
    public static WebElement button;
    @FindBy(xpath = "//div[@class='modal-body']")
    public static WebElement pntPopUpImg;
    @FindBy(xpath = "//button[@class='close']")
    public static WebElement closePopUp;

//    public void buttonClick() throws InterruptedException {
//        button.click();
//        Thread.sleep(3000);
//    }

//    public void clickOnSoftware() throws InterruptedException {
//        software.click();
//        String parent = driver.getWindowHandle();
//        System.out.println("Parent window id is " + parent);
//
//    }
//}

//        Set<String> allWindows = driver.getWindowHandles();
//        int count = allWindows.size();
//        System.out.println("Total window " + count);
//        for(String child : allWindows){
//            if(!parent.equalsIgnoreCase(child)){
//                driver.switchTo().window(child);
//                System.out.println("Child window title is " + driver.getTitle());
//                //mobileApplication.click();
//                Thread.sleep(3000);
//                driver.close();
//            }
//        driver.switchTo().window(parent);
//        System.out.println("Parent window title is " + driver.getTitle());

}


