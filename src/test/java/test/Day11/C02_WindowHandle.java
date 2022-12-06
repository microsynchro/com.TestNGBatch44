package test.Day11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Set;

public class C02_WindowHandle {


    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


    }

    @Test
    public void windowHandleTest() throws InterruptedException {
//            ● Tests package’inda yeni bir class olusturun: WindowHandle2
//            ● https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");

//            ● Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
        WebElement textInPage = driver.findElement(By.tagName("h3"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(textInPage.getText(), "Opening a new window", "You are not on correct page.");

//            ● Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
        softAssert.assertEquals(driver.getTitle(), "The Internet", "Title is not same.");

//            ● Click Here butonuna basın.
        // soruda windowhandle degerini bilmedigim bir window aciliyor
        // acilan sayfanin windowhandle degerini bulmak icin gectigim sayfalardaki
        // window handle degerini kaydetmeliyim.
        String windowHandle1 = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='Click Here']")).click();


//            ● Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.
        // once acilan yeni sayfanin handle degerini elde etmeliyim.
        // tum handle degerlerini alip bir set'e koyalim
        Set<String> windowHandleSet = driver.getWindowHandles();
        System.out.println(windowHandleSet);

        String windowHandle2 = "";

        for (String each : windowHandleSet
        ) {
            if (!each.equals(windowHandle1)) {
                windowHandle2 = each;
            }
        }

        // artik yeni sayfaya gecis yapabilirim.
        driver.switchTo().window(windowHandle2);
        System.out.println(driver.getTitle());
        softAssert.assertEquals(driver.getTitle(), "New Window", "new window title is not same.");


        //  Sayfadaki textin “New Window” olduğunu doğrulayın.
        WebElement secondPageTextElement = driver.findElement(By.tagName("h3"));


        //  Bir önceki pencereye geri döndükten sonra sayfa başlığının “The Internet” olduğunu doğrulayın.

        driver.switchTo().window(windowHandle1);
        softAssert.assertEquals(driver.getTitle(),"The Internet","Title is different!");

        softAssert.assertAll();

    }

    @AfterClass
    public void teardown(){

    driver.quit();
    }


}
