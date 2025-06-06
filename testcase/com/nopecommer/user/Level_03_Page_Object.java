package com.nopecommer.user;

import comons.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageOjects.CustomerInfoPageObject;
import pageOjects.HomePageObject;
import pageOjects.LoginPageObject;
import pageOjects.RegisterPageObject;

import java.time.Duration;

public class Level_03_Page_Object extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(Level_03_Page_Object.class);
    // Declare Variable

    private WebDriver driver;
    private LoginPageObject loginPage;
    private RegisterPageObject registerPage;
    private HomePageObject homePage;
    private CustomerInfoPageObject customerInfoPage;

    private String firstName, lastName, companyName, emailAddress, password;

    // Pre-condition
    @BeforeClass
    public void beforeClass() throws InterruptedException {
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

//        homePage = new HomePageObject(driver);

        firstName = "Annie";
        lastName = "Dinh";
        companyName = "Spree";
        emailAddress = "annie" + randomEmail() + "@gmail.com";
        password = "12345678";
    }

    //Testcase
    @Test
    public void User_01_Register(){
//        homePage.clickToRegisterLink();

        registerPage = new RegisterPageObject(driver);

        registerPage.clickToMaleRadio();

        registerPage.enterToFirstNameTextbox(firstName);

        registerPage.enterToLastnameTextbox(lastName);

        registerPage.enterToEmail(emailAddress);

        registerPage.enterToCompany(companyName);

        registerPage.enterToPassword(password);

        registerPage.enterToConfirmPassword(password);

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
    }

//    @Test
    public void User_02_Login(){
        registerPage.clickToLogOutLink();
        homePage.clickToLoginLink();

        loginPage = new LoginPageObject(driver);

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickToLoginButton();

        homePage = new HomePageObject(driver);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

    }

//    @Test
    public void User_03_MyAccount(){
        homePage.clickToMyAccountLink();

        customerInfoPage = new CustomerInfoPageObject(driver);

        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerInfoPage.getEmailTextboxValue(), emailAddress);
        Assert.assertEquals(customerInfoPage.getCompanyTextboxValue(), companyName);

    }

    //Post-condition
    @AfterClass
    public void afterClass(){
        driver.quit();
    }


}
