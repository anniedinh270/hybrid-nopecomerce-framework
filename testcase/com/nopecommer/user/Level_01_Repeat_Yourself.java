package com.nopecommer.user;

import comons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_01_Repeat_Yourself {
    private WebDriver driver;
    private String firstName, lastName, companyName, email, password;


    @BeforeClass
    public void beforeClass() throws InterruptedException {
//        driver = new EdgeDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(options);
        ((JavascriptExecutor) driver).executeScript(
                "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
        );

        driver.get("https://demo.nopcommerce.com/");
        Thread.sleep(5000);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        firstName = "Annie";
        lastName = "Dinh";
        companyName = "Spree";
        email = "annie" + randomEmail() + "@gmail.com";
        password = "12345678";
    }


    @Test
    public void TC_01_Register() {
//        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#gender-female")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);


        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);

        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");


    }

    @Test
    public void TC_02_Login() {
        driver.findElement(By.cssSelector("a.ico-login")).click();

        driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);

        driver.findElement(By.cssSelector("button.login-button")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("a.ico-account")).isDisplayed());

    }


    @Test
    public void TC_03_MyAccount() {
        driver.findElement(By.cssSelector("a.ico-account")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute(
                "value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute(
                "value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute(
                "value"), email);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute(
                "value"), companyName);

    }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }

    private int randomEmail() {
        return new Random().nextInt(9999);
    }

}
