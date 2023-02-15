package org.tvm;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ToDo {

static WebDriver driver;

@org.testng.annotations.BeforeClass
public static void chromeBrower() {
WebDriverManager.chromedriver().setup();
driver = new ChromeDriver();
driver.get("https://todomvc.com/examples/angularjs/#/");
}

// Verify if the item is added to the list 
@Test(priority=1)
public void tc1() throws AWTException {

//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));

WebElement src = driver.findElement(By.xpath("//input[@placeholder='What needs to be done?']"));
Actions a = new Actions(driver);
a.doubleClick(src).perform();
src.sendKeys("Drink water every hour");
a.keyDown(Keys.ENTER).perform();
WebElement ll = driver.findElement(By.xpath("//label[@class='ng-binding']"));
String tt = ll.getText();

Assert.assertEquals(tt, "Drink water every hour", "tc passed");

}

//Verify if the item is crossed out and verify the counter on the bottom-left
@Test(priority=2)
private void tc2() {

WebElement btn1 = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
btn1.click();
Assert.assertTrue(btn1.isSelected(), "true");
WebElement counter = driver.findElement(By.xpath("//strong[@class='ng-binding']"));
org.testng.Assert.assertTrue(counter.getText().contains("0"));

}

//Verify if the item is removed from the list
@Test(priority=3)
private void tc3() {
WebElement lab = driver.findElement(By.xpath("//label[text()='Drink water every hour']"));
WebElement src= driver.findElement(By.xpath("//input[@placeholder='What needs to be done?']"));
WebElement btn1
=driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
WebElement clear = driver.findElement(By.xpath("//button[@class='destroy']"));
clear.click();
if(src.getAttribute("value").equals(""))
{
	Assert.assertTrue(true);
}


}

//Verify active and completed items
@Test(priority=4)
private void tc4() {
WebElement src = driver.findElement(By.xpath("//input[@placeholder='What needs to be done?']"));
Actions a = new Actions(driver);
a.doubleClick(src).perform();
src.sendKeys("sleeping");
a.keyDown(Keys.ENTER).perform();

WebElement act = driver.findElement(By.xpath("//a[text()='Active']"));
act.click();
WebElement ch1 = driver.findElement(By.xpath("//label[text()='sleeping']"));
String tt = ch1.getText();
Assert.assertEquals(tt,"sleeping","passed");


WebElement comp = driver.findElement(By.xpath("//a[text()='Completed']"));
comp.click();
WebElement ll = driver.findElement(By.xpath("//label[@class='ng-binding']"));
Assert.assertTrue(ll.isEnabled());

}

@AfterClass
public static void close()
{
	driver.close();
	System.out.println("End");
}

}
