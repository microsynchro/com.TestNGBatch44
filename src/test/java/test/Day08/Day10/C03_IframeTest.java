package test.Day08.Day10;

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

public class C03_IframeTest {
//● Bir class olusturun: IframeTest
//● https://the-internet.herokuapp.com/iframe adresine gidin.
// ● Bir metod olusturun: iframeTest
//○ “An IFrame containing....” textinin erisilebilir oldugunu test edin ve konsolda
// ○ Text Box’a “Merhaba Dunya!” yazin.
//yazdirin.
//○ TextBox’in altinda bulunan “Elemental Selenium” linkini textinin gorunur oldugunu dogrulayin ve konsolda yazdirin.
    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void iframeTest(){
        driver.get("https://the-internet.herokuapp.com/iframe");
        WebElement textElement = driver.findElement(By.tagName("h3"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(textElement.isEnabled(),"textElement is not displayed");
        System.out.println(textElement.getText());

//        Burada istenilen elementi locate edebilmek icin "iFrame" icerisine girmek zorundayiz.
//        driver.switchTo().frame(driver.findElement(By.tagName("iframe"))); // ya da
        WebElement iFrame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iFrame);

        WebElement textBox = driver.findElement(By.tagName("p"));
        textBox.clear();
        textBox.sendKeys("Merhaba Dunya!");

//        Bizden istenilen "Elemental Selenium" yazisina tiklayabilmek icin yeniden bir uste (parent'a) ya da
//        defultContent() 'e gitmemiz gerekir.
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//a[text()='Elemental Selenium']")).click();

//        testimiz icin sponsored yazisini locate ettik. fakat yazi yeni sayfada oldugundan ve driver
//        eski sayfada kaldigindan yaziyi locate edemedik.
        String windowHandle = driver.getWindowHandle();
        System.out.println(windowHandle);
        driver.switchTo().window(windowHandle);

        WebElement sponsoredTextElement = driver.findElement(By.xpath("//p[text()='Sponsored by ']"));
        softAssert.assertTrue(sponsoredTextElement.isDisplayed()," Sponsored by text not displayed.");






        softAssert.assertAll();
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }

}
