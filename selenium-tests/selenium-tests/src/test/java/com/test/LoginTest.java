package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("http://localhost:3000");
    }

    @Test
    public void testLoginSuccess() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-testid='username']")).sendKeys("test");
        driver.findElement(By.cssSelector("[data-testid='password']")).sendKeys("123");
        driver.findElement(By.cssSelector("[data-testid='login-btn']")).click();

        Thread.sleep(1000);

        WebElement message = driver.findElement(By.cssSelector("[data-testid='message']"));
        Assert.assertEquals(message.getText(), "Login Successful");
    }

    @Test
    public void testLoginFailure() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-testid='username']")).sendKeys("wrong");
        driver.findElement(By.cssSelector("[data-testid='password']")).sendKeys("wrong");
        driver.findElement(By.cssSelector("[data-testid='login-btn']")).click();

        Thread.sleep(1000);

        WebElement message = driver.findElement(By.cssSelector("[data-testid='message']"));
        Assert.assertEquals(message.getText(), "Invalid credentials");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}