package pages;

import io.qameta.allure.Step;
import utils.Base;
import utils.Locators;

public class LoginPage extends Base {


    /**
     * This method is used for getting the username value
     *
     * @return Username text
     */
    public String getUsername() {
        return getText(Locators.USERNAME_TEXT);
    }

    /**
     * This method is used for getting the password value
     *
     * @return Password text
     */
    public String getPassword() {
        return getText(Locators.PASSWORD_TEXT);
    }

    /**
     * Method used for entering a username
     *
     * @return LoginPage object
     */
    @Step("Type username")
    public LoginPage setUsername(String username) {
        enterText(Locators.USERNAME_INPUT, username);
        return this;
    }

    /**
     * Method used for entering a password
     *
     * @return LoginPage object
     */
    @Step("Type password")
    public LoginPage setPassword(String password) {
        enterText(Locators.PASSWORD_INPUT, password);
        return this;
    }

    /**
     * Method used for submitting a valid login form
     *
     * @return LoginPage object
     */
    @Step("Click login button")
    public HomePage submitValidLogin() {
        clickOnElementIfVisible(Locators.LOGIN_BUTTON);
        return new HomePage();
    }

    /**
     * Method used for submitting an invalid login form
     *
     * @return LoginPage object
     */
    @Step("Click login button")
    public LoginPage submitInvalidLogin() {
        clickOnElementIfVisible(Locators.LOGIN_BUTTON);
        return this;
    }

    /**
     * Method that verifies if an error is displayed for invalid login
     *
     * @return LoginPage object
     */
    @Step("Verify that invalid login error is displayed")
    public LoginPage verifyInvalidErrorIsDisplayed() {
        verifyIsDisplayed(Locators.INVALID_ERROR);
        return this;
    }
}