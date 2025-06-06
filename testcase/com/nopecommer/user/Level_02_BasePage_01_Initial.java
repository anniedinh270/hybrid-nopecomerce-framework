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

public class Level_02_BasePage_01_Initial {
    private WebDriver driver;
    private String firstName, lastName, companyName, email, password;
    BasePage basePage;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        basePage = new BasePage();

        firstName = "Annie";
        lastName = "Dinh";
        companyName = "Spree";
        email = "annie" + randomEmail() + "@gmail.com";
        password = "12345678";
    }


    @Test
    public void TC_01_Register() {
        basePage.waitForElementClickable(driver, "//a[@class='ico-register']");
        basePage.clickToElement(driver, "//input[@id='gender-female']");
        basePage.sendKeyToElement(driver,"//input[@id='FirstName']", firstName);
        basePage.sendKeyToElement(driver,"//input[@id='LastName']", lastName);
        basePage.sendKeyToElement(driver,"//input[@id='Email']", email);
        basePage.sendKeyToElement(driver,"//input[@id='Company']", companyName);
        basePage.sendKeyToElement(driver,"//input[@id='Password']", password);
        basePage.sendKeyToElement(driver,"//input[@id='ConfirmPassword']", password);

       basePage.waitForElementClickable(driver,"//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//div[@class='result']"),
                "Your registration completed");

    }

    @Test
    public void TC_02_Login() {
       basePage.waitForElementClickable(driver,"//a[@class='ico-login']");

        basePage.sendKeyToElement(driver,"//input[@id='Email']", email);
        basePage.sendKeyToElement(driver,"//input[@id='Password']", password);

        basePage.waitForElementClickable(driver,"//button[@class='button-1 login-button']");
        basePage.waitForElementInvisible(driver, "//a[@class='ico-account']");
    }


    @Test
    public void TC_03_MyAccount() {
        basePage.waitForElementClickable(driver,"//a[@class='ico-account']");
        Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@class='FirstName']",
                "value"), firstName);
        Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@class='LastName']",
                "value"), lastName);
        Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@class='Email']",
                "value"), email);
        Assert.assertEquals(basePage.getElementAttribute(driver, "//input[@class='Company']",
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
