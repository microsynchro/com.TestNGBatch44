package test.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

import java.util.Set;

public class C3_MouseActions1 extends TestBase {

//      1- Yeni bir class olusturalim: MouseActions1

    @Test
    public void mauseAction1Test(){
        //      2- https://the-internet.herokuapp.com/context_menu sitesine gidelim
        driver.get("https://the-internet.herokuapp.com/context_menu");
        String windowHandle1 = driver.getWindowHandle();

        //      3- Cizili alan uzerinde sag click yapalim
        Actions actions = new Actions(driver);
        WebElement hotSpotElement = driver.findElement(By.id("hot-spot"));
        actions.moveToElement(hotSpotElement).perform();
        actions.contextClick(hotSpotElement).perform();

        //      4- Alert’te cikan yazinin “You selected a context menu” oldugunu
        driver.switchTo().alert();
        String actualAlertText = driver.switchTo().alert().getText();
        System.out.println(actualAlertText);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualAlertText, "You selected a context menu","Alert message as not as expected.");

        //      5- Tamam diyerek alert’i kapatalim
        driver.switchTo().alert().accept();

        //      6- Elemental Selenium linkine tiklayalim
        driver.findElement(By.xpath("//a[text()='Elemental Selenium']")).click();
        Set<String> windowsHandles = driver.getWindowHandles();
        String windowHandle2 = "";

        for (String each : windowsHandles
             ) {
            if (!each.equals(windowHandle1)){
                windowHandle2 = each;
            }
        }

        //      7- Acilan sayfada h1 taginda “Elemental Selenium” yazdigini test edelim.
        driver.switchTo().window(windowHandle2);
        WebElement h1Element = driver.findElement(By.xpath("//*[text()='Elemental Selenium']"));
        String actualH1ElementText = h1Element.getText();
        String expectedH1ElementText = "Elemental Selenium";

        softAssert.assertEquals(actualH1ElementText,expectedH1ElementText,"h1 text not same.");





    }






}
