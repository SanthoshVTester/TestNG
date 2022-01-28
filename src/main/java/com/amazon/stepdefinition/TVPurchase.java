package com.amazon.stepdefinition;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TVPurchase {

	 static WebDriver driver;	
	 @BeforeClass(groups="default")
	   public static void launch() {
	 	WebDriverManager.chromedriver().setup();
	 	driver = new ChromeDriver();
	 	driver.get("https://www.flipkart.com/");
	 	driver.manage().window().maximize();
	 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	 }
	 @AfterClass(groups="default")
	 public static void close() {
	 	driver.quit();
	 }

	 static long startTime;
	 @BeforeMethod(groups="default")
	 public void startTime() {
	 	startTime = System.currentTimeMillis();
	 	
	 }
	 @AfterMethod(groups="default")
	 public void endTime() {
	 	long endTime =System.currentTimeMillis();
	 	System.out.println("Total time taken by:"+(endTime-startTime));
	 }
	 @Parameters({"username","password"})
	 @Test(groups="smoke")
	 public void m1(String m1,String m2) {
	 	try {
	 		WebElement close=driver.findElement(By.xpath("//button[text()='✕']"));
	 		close.isDisplayed();
	 		close.click();
	 	}
	 	catch(Exception e) {
	 		System.out.println("Login not Required");
	 	}
	 	System.out.println(m1);
	    System.out.println(m2);	
	 }
	 static String fn;
	 @Test(groups="smoke")
	 public void m2() throws Exception {
	 	 driver.findElement(By.xpath("//input[@name='q']")).sendKeys("TV",Keys.ENTER);
	 	 WebElement tv=driver.findElement(By.xpath("(//div[contains(text(),'TV')])[2]"));
	 	 tv.click();
	 	 fn=tv.getText();
	 	 System.out.println("First TV name is:"+fn);
	 	 }
	 @Test(groups="smoke")
	 public void m3() {
	 	String cWin =driver.getWindowHandle();
	 	
	 	Set<String> allWin=driver.getWindowHandles();
	 	for(String x:allWin) {
	 		if(!x.equals(cWin)) {
	 			driver.switchTo().window(x);
	 			System.out.println("Window Switched");
	 		}
	 		
	 	}
	 	
	 }

	 @Test(groups="buyNow")
	 public void m4() { 
	 	WebElement tv=driver.findElement(By.xpath("(//span[contains(text(),'TV')])[2]"));
	 	String ln=tv.getText();
	 	System.out.println("Second TV name is:"+ln);
	 	boolean compare=fn.equals(ln);
	 	System.out.println(compare);
	 	
	 	Assert.assertEquals(fn,ln);
	 	
	 	WebElement buyNow=driver.findElement(By.xpath("//button[text()='BUY NOW']"));
	 	Assert.assertTrue(buyNow.isDisplayed());
	 	System.out.println("End");
	 }

}
