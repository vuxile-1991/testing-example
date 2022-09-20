package pages;

import io.qameta.allure.Step;
import utils.Base;
import utils.Locators;

public class HomePage extends Base {

    /**
     * Method for the verification of successful login
     *
     * @return HomePage object
     */
    @Step("Verify that alert for successful login is displayed")
    public HomePage verifySuccess() {
        verifyIsDisplayed(Locators.SUCCESS_ALERT);
        return this;
    }
}
