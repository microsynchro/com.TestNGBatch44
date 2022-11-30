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

import java.time.Duration;

public class C01_Allerts {

//    https://the-internet.herokuapp.com/javascript_alerts adresine gidin

    WebDriver driver;

    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test

//    bir method olusturun: acceptAlert
//    1. butona tiklayin, uyaridaki OK butonuna tiklayin ve result mesajinin
//    "You successfully clicked an alert" oldugunu test edin.
    public void acceptAlert(){
        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
        driver.switchTo().alert().accept();
        WebElement resultTextElement = driver.findElement(By.xpath("//p[@id='result']"));
        String alertResulText = resultTextElement.getText();
        Assert.assertEquals(alertResulText,"You successfully clicked an alert");
    }

    @Test
//      Bir method olusturun: dismissAlert
//      2. butona tiklayin, uyaridaki Cancel butonuna tiklayin ve result mesajinin
//      "successfully" icermedigini test edin.
    public void dismissedAlert(){
        driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
        driver.switchTo().alert().dismiss();
        WebElement resultTextElement = driver.findElement(By.xpath("//p[@id='result']"));
        String confirmText = resultTextElement.getText();
        Assert.assertFalse(confirmText.contains("successfully"));
    }


    @Test
//        bir method olusturun: sendKeysAlert
//    3. butona tiklayin, uyaridaki metin kutusuna isminizi yazin, OK butonuna tiklayin ve
//    result mesajinda isminizin goruntulendigini dogrulayin.
    public void sendKeysAlert(){
        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
        driver.switchTo().alert().sendKeys("CA");
        driver.switchTo().alert().accept();
        WebElement resultTextElement = driver.findElement(By.xpath("//p[@id='result']"));
        String sendKeysText = resultTextElement.getText();
        Assert.assertTrue(sendKeysText.contains("CA"));
    }
    @AfterClass
    public void teardown(){
        driver.close();
    }

}
