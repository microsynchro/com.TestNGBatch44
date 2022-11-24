package test.Day08;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class C04_Dropdown {
    WebDriver driver;
    Select select;


    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://www.amazon.com");
    }
    @Test
    public void Test01(){
//        amazon.com adresine gidin
//        arama kutusunun yanindaki kategori menusundeki kategori sayisinin 45 oldugunu test edin.

        WebElement dropdownElement = driver.findElement(By.xpath("//select[@aria-describedby='searchDropdownDescription']"));
        select = new Select(dropdownElement);

        List<WebElement> valueList = select.getOptions();

        Assert.assertEquals(valueList.size(),45);

    }

    @Test
    public void Test02(){
//        1- kategori menusunden 'Books' secenegini secin.
        WebElement dropdownElement = driver.findElement(By.xpath("//select[@aria-describedby='searchDropdownDescription']"));
        select = new Select(dropdownElement);
        select.selectByVisibleText("Books");;
//        2- arama kutusuna Java yazdirin.
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java"+Keys.ENTER);
//        3- bulunan sonuc sayisini yazdirin.
        WebElement resultElement = driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));
        System.out.println(resultElement.getText());
        String resultText = resultElement.getText();
//        4- sonucun Java kelimesini icerdigini test edin.
        Assert.assertTrue(resultText.contains("Java"));
    }

@AfterClass
    public void teardown(){
        driver.close();
}






}
