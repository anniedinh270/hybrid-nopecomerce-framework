package com.nopecommer.user;

import comons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_III_Inhertitance extends BasePage{
    private WebDriver driver;
    private String firstName, lastName, companyName, email, password;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        firstName = "Annie";
        lastName = "Dinh";
        companyName = "Spree";
        email = "annie" + randomEmail() + "@gmail.com";
        password = "12345678";
    }


    @Test
    public void TC_01_Register() {
        waitForElementClickable(driver, "//a[@class='ico-register']");
        clickToElement(driver, "//input[@id='gender-female']");
        sendKeyToElement(driver,"//input[@id='FirstName']", firstName);
        sendKeyToElement(driver,"//input[@id='LastName']", lastName);
        sendKeyToElement(driver,"//input[@id='Email']", email);
        sendKeyToElement(driver,"//input[@id='Company']", companyName);
        sendKeyToElement(driver,"//input[@id='Password']", password);
        sendKeyToElement(driver,"//input[@id='ConfirmPassword']", password);

       waitForElementClickable(driver,"//button[@id='register-button']");

        Assert.assertEquals(getElementText(driver, "//div[@class='result']"),
                "Your registration completed");

    }

    @Test
    public void TC_02_Login() {
       waitForElementClickable(driver,"//a[@class='ico-login']");

        sendKeyToElement(driver,"//input[@id='Email']", email);
        sendKeyToElement(driver,"//input[@id='Password']", password);

        waitForElementClickable(driver,"//button[@class='button-1 login-button']");
        waitForElementInvisible(driver, "//a[@class='ico-account']");
    }


    @Test
    public void TC_03_MyAccount() {
        waitForElementClickable(driver,"//a[@class='ico-account']");
        Assert.assertEquals(getElementAttribute(driver, "//input[@class='FirstName']",
                "value"), firstName);
        Assert.assertEquals(getElementAttribute(driver, "//input[@class='LastName']",
                "value"), lastName);
        Assert.assertEquals(getElementAttribute(driver, "//input[@class='Email']",
                "value"), email);
        Assert.assertEquals(getElementAttribute(driver, "//input[@class='Company']",
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
