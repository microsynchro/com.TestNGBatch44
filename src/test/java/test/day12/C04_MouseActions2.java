package test.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C04_MouseActions2 extends TestBase {

    @Test
    public void mauseActionsTest2() throws InterruptedException {

//        Yeni bir class olusturalim: MouseActions2
//        1- https://demoqa.com/droppable adresine gidelim
        driver.get("https://demoqa.com/droppable");
//        2- “Drag me” butonunu tutup “Drop here” kutusunun ustune birakalim 3- “Drop here” yazisi yerine “Dropped!” oldugunu test edin
        WebElement dragMeElement = driver.findElement(By.xpath("//div[text()='Drag me']"));
        WebElement dropHereElement = driver.findElement(By.xpath("(//div[@id='droppable'])[1]"));

        Actions actions = new Actions(driver);
        actions.moveToElement(dragMeElement).perform();
        actions.dragAndDrop(dragMeElement,dropHereElement).perform();

        Thread.sleep(3000);

        WebElement dragAndDropBoxTextElement = driver.findElement(By.xpath("//*[text()='Dropped!']"));
        String actualdragAndDropBoxText = dragAndDropBoxTextElement.getText();
        String expecteddragAndDropBoxText = "Dropped!";

        Assert.assertEquals(actualdragAndDropBoxText,expecteddragAndDropBoxText,"Text is not same");





    }



}
