package SoftNHardAssertion;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assertion {

    @Test
    public void test1(){
        SoftAssert softAssert1 = new SoftAssert();

        System.out.println("open the browser");
        Assert.assertEquals(true, true);

        System.out.println("enter username");
        softAssert1.assertEquals(true, false);

        System.out.println("enter password");
        softAssert1.assertEquals(true, false);

        System.out.println("click on login button");
        Assert.assertEquals(true, true);

        System.out.println("validate homepage");
        softAssert1.assertEquals(true, false);

        softAssert1.assertAll();
    }

    @Test
    public void test2(){
        SoftAssert softAssert2 = new SoftAssert();

        System.out.println("close the browser");
        softAssert2.assertEquals(true, false);
        softAssert2.assertAll();
    }
}
