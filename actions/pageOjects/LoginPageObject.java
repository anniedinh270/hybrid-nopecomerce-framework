package pageOjects;

import comons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailAddress);

    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public void clickToLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
    }
}
