package com.amazon.stepdefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParallelMethods {
	@Test
	public static void launch() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		System.out.println("Launched");
		
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(("Redmi"),Keys.ENTER);
		WebElement mobName=driver.findElement(By.xpath("(//span[contains(@class,'a-size-medium a-color')])[1]"));
		String mob1=mobName.getText();
		System.out.println(mob1);
		mobName.click();
		driver.quit();
		System.out.println("End");
	}
	@Test
	public void m1() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		System.out.println("Launched");
		driver.quit();
		System.out.println("End");
	}
}
