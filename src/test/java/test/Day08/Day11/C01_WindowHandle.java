package test.Day08.Day11;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class C01_WindowHandle {

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void iframeTest() throws InterruptedException {
        driver.get("http://www.amazon.com");
        String windowHandle1 = driver.getWindowHandle();
        System.out.println("1. sayfanin window handle degeri: " + driver.getWindowHandle());

        driver.switchTo().newWindow(WindowType.WINDOW); // yeni bir pencere acilir.
        driver.get("http://www.bestbuy.com");
        String windowHandle2 = driver.getWindowHandle();
        System.out.println("2. sayfanin window handle degeri: " + driver.getWindowHandle());

        Thread.sleep(3000);
        Set<String> windowHandleSet = driver.getWindowHandles();
        System.out.println(windowHandleSet);

        Thread.sleep(3000);
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("http://www.facebook.com");


        // amazon'un acik oldugu sayfaya gecin ve nutella icin arama yapin.
        Thread.sleep(3000);
        driver.switchTo().window(windowHandle1);
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchBox.sendKeys("nutella" + Keys.ENTER);


        // bestbuy acik olan sayfaya gecin ve title'in "Best Buy" icerdigini test edin
        Thread.sleep(3000);
        driver.switchTo().window(windowHandle2);
        Assert.assertTrue(driver.getTitle().contains("Best Buy"));

        // facebook'un acik oldugu sayfaya gecin ve url'nin https://www.facebook.com oldugunu test edin.
        // eger acik olan pencelerden sadece bir tanesinin window handle degeri bilinmiyorsa
        // once tum handle degerleri bulup bir set'e koyariz.

        windowHandleSet = driver.getWindowHandles();

        // bu soru icin su anda set'te 3 window handle degeri var
        // 1. ve 2. sayfanin window handle degerini biliyoruz.
        // setimizde olup, ilk iki sayfanin handle degeri olmayan deger 3. sayfanin handle degeri olacaktir.

        String windowHandle3="";
        for (String each: windowHandleSet
             ) {
            if (!(each.equals(windowHandle1) || each.equals(windowHandle2)))
                windowHandle3=each;
        }

        System.out.println(windowHandle3);
        System.out.println(windowHandleSet);

        driver.switchTo().window(windowHandle3);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");






        Thread.sleep(3000);


    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }
}
