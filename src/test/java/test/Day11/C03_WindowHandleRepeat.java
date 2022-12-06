package test.Day11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Set;

public class C03_WindowHandleRepeat {

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }

    @Test
    public void windowHandleTest(){

// https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");

// Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
        WebElement pageText = driver.findElement(By.tagName("h3"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(pageText.getText(),"Opening a new window", "Page text is not as expected.");

// Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
        softAssert.assertEquals(driver.getTitle(), "The Internet" , "Page title is not same.");
        String windowHandle1 = driver.getWindowHandle();

// Click Here butonuna basın.
        driver.findElement(By.xpath("//a[text()='Click Here']")).click();
        Set<String> windowHandleSet = driver.getWindowHandles();
        System.out.println(windowHandleSet);
        String windowHandle2 = "";

        for (String each: windowHandleSet
             ) {
            if (!each.equals(windowHandle1)){
                windowHandle2=each;
            }
            System.out.println(windowHandle2);
        }

        driver.switchTo().window(windowHandle2);

// Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.
        softAssert.assertEquals(driver.getTitle(),"New Window","Page title is not 'New Window'.");

// Sayfadaki textin “New Window” olduğunu doğrulayın.
        softAssert.assertEquals(driver.findElement(By.tagName("h3")).getText(),"New Window", "Page Text is not same.");

// Bir önceki pencereye geri döndükten sonra sayfa başlığının “The Internet” olduğunu doğrulayın.
        driver.switchTo().window(windowHandle1);
        softAssert.assertEquals(driver.getTitle(),"The Internet","Page title is not 'The Internet'.");

        softAssert.assertAll();
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }
}
