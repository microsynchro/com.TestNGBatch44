package test.Day11;

import org.testng.annotations.Test;
import utilities.TestBase;

public class C03_TestBaseFirstTest extends TestBase {

    @Test
    public void test01(){
        driver.get("http://www.amazon.com");
    }
}
