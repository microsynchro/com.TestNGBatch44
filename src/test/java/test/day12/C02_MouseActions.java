package test.day12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class C02_MouseActions extends TestBase {


@Test
    public void mouseActionsTest(){
    // amazon.com sayfasina gidin.
    driver.get("https://www.amazon.com");

    // sag ustte hello,signIn elementinin uzerinde mouse'u bekletin

    Actions actions = new Actions(driver);
    WebElement signInElement = driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']"));
    actions.moveToElement(signInElement).perform();
    // acilan menude "create a list" linkine tiklayin
    WebElement createAListElement = driver.findElement(By.xpath("//span[text()='Create a List']"));
    actions.moveToElement(createAListElement).perform();
    actions.click(createAListElement).perform();
    // ve new list sayfasinin acildigini test edin.
    String actualTitle = driver.getTitle();
    String expectedTitle = "Your List";
    Assert.assertTrue(actualTitle.contains(expectedTitle), "Title is not as expected.");

}


}
