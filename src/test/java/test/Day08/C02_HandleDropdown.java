package test.Day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class C02_HandleDropdown {
    WebDriver driver;

    @BeforeClass
    public void setUP(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
            public void dropdownTesti() throws InterruptedException {
        //dropdownda var olan seceneklerden birini secmek icin
        // 1. adim: Dropdown webelementini locate edip bir degiskene atiyoruz.

        driver.get("http://www.amazon.com");
        WebElement searchDropdownbutton = driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));

        // 2. adim: Select class'indan bir obje olusturalim ve parametre
        // olarak locate ettigimiz Webelenti yazalim.
        Select select = new Select(searchDropdownbutton);

        // 3. adim: select objesini kullanarak, Select class'inda var olan 3 secim method'undan
        // istedigimizi kullanarak dropdown'da var olan option'lardan birini secebiliriz.
        select.selectByVisibleText("Deals");
        Thread.sleep(3000);

        select.selectByIndex(3);
        Thread.sleep(3000);

        select.selectByValue("search-alias=computers-intl-ship");
        Thread.sleep(3000);

    }
    @AfterClass
    public void teardown(){
        driver.close();
    }



}
