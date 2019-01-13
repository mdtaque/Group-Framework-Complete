package RetryTests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MyTests {

    @Test
    public void test1(){
        Assert.assertEquals(true, false);
    }

    @Test
    public void test2(){
        Assert.assertEquals(true, false);
    }
}
