package test.Day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class C03_Dropdown {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://the-internet.herokuapp.com/dropdown");
    }

    @Test
    public void dropdownIndexTtest(){
//        https://the-internet.herokuapp.com/dropdown adresine gidin.
//        index kullanarak (option 1) secin ve yazdirin.

        WebElement dropdown = driver.findElement(By.id("dropdown"));
        select = new Select(dropdown);
        select.selectByIndex(1);
        System.out.println(select.getFirstSelectedOption().getText());

    }
    @Test
    public void dropdownValueTest(){
//        value kullanarak (option 2) secin ve yazdirin.
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        select = new Select(dropdown);

        select.selectByValue("2");
        System.out.println(select.getFirstSelectedOption().getText());
    }
    @Test
    public void visibleTextTest(){
//        visible text kullanarak (option 1'i) secin ve yazdirin

        WebElement dropdown = driver.findElement(By.id("dropdown"));
        select = new Select(dropdown);

        select.selectByVisibleText("Option 1");
        System.out.println(select.getFirstSelectedOption().getText());
    }
    @Test
    public void allDropdownValues(){
//        tum dropdown degerlerini (value) yazdirin
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        select = new Select(dropdown);

        List<WebElement> valuesList = select.getOptions();
        for (WebElement each: valuesList
             ) {
            System.out.println(each.getText());
        }


    }
    @Test
    public void dropdownSizeTest(){
        //        dropdown'un boyutunu bulun, dropdown'da 4 oge varsa konsolda True, degilse False yazdirin.
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        select = new Select(dropdown);

        List<WebElement> valuesList = select.getOptions();
        System.out.println(valuesList.size());

        int expectedSize=4;
        Assert.assertEquals(valuesList.size(),expectedSize);

        }
    @AfterClass
    public void teardown(){
//        driver'i kapatin.
        driver.close();
    }

}
