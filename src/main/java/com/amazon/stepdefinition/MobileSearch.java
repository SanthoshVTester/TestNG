package com.amazon.stepdefinition;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MobileSearch {
	@DataProvider(name="mobName")
	public Object[][] getMobName(){
		return new Object[][] {{"Redmi"}};
	}
	
	static WebDriver driver;
	@BeforeClass(groups="default")
	public static void launch() {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		System.out.println("Launched");
		
		
	}
@AfterClass(groups="default")
public static void close() {
	driver.quit();
	System.out.println("End");
}
static long start;
@BeforeMethod(groups="default")
public void startTime() {
	start=System.currentTimeMillis();
}
@AfterMethod(groups="default")
public void endTime() {
	long end=System.currentTimeMillis();
	System.out.println("Total time taken:"+(end-start));
}

static String mob1;

@Test(priority=-2,groups="smoke",dataProvider="mobName")
public void moblieSearch(String m1) {
	driver.findElement(By.xpath("//input[@type='text']")).sendKeys((m1),Keys.ENTER);
	WebElement mobName=driver.findElement(By.xpath("(//span[contains(@class,'a-size-medium a-color')])[1]"));
	mob1=mobName.getText();
	System.out.println(mob1);
	mobName.click();
}
@Test(priority=-1,groups="smoke")
public void WindowHandle() throws Exception {
	String CWin=driver.getWindowHandle();
	
	Set<String> allWin=driver.getWindowHandles();
	for(String x:allWin) {
		if(!x.equals(CWin)) {
			driver.switchTo().window(x);
			System.out.println("Window Switched");
		}
	}
	
}

@Test(priority=1,groups="buyNow")
public void buyNow() throws Exception {
	WebElement mobName=driver.findElement(By.xpath("(//span[contains(text(),'Redmi')])[1]"));
	String mob2=mobName.getText();
    if(mob2.equals(mob1)) {
    	System.out.println("Same Mobile");
    	}
    else {
    	System.out.println("Name Changed");
    }
    WebElement buyNow =driver.findElement(By.xpath("//input[@id='buy-now-button']"));
    boolean b=buyNow.isDisplayed();
    System.out.println(b);
    buyNow.click();
   
}
	
}
