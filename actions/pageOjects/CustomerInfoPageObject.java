package pageOjects;

import comons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerInfoPageUI;

public class CustomerInfoPageObject extends BasePage {
    private WebDriver driver;

    public CustomerInfoPageObject(WebDriver driver){
        this.driver = driver;
    }

    public boolean isGenderMaleSelected() {
        waitForElementSelected(driver, CustomerInfoPageUI.GENDER_MALE_RADIO);
        return isElementSelected(driver, CustomerInfoPageUI.GENDER_MALE_RADIO);
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX);
        return getElementAttribute(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX, "value");

    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX);
        return getElementAttribute(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getEmailTextboxValue() {
        waitForElementVisible(driver, CustomerInfoPageUI.EMAIL_TEXTBOX);
        return getElementAttribute(driver, CustomerInfoPageUI.EMAIL_TEXTBOX, "value");
    }

    public String getCompanyTextboxValue() {
        waitForElementVisible(driver, CustomerInfoPageUI.COMPANY_TEXTBOX);
        return getElementAttribute(driver, CustomerInfoPageUI.COMPANY_TEXTBOX , "value");
    }
}
