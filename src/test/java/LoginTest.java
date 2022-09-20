import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.LoginPage;
import utils.Base;


@ExtendWith(Base.class)
@Epic("Login feature to enable user to sign in")
@Feature("Login")
@Severity(SeverityLevel.CRITICAL)
class LoginTest extends Base {

    /**
     * Test for valid logging validation
     */
    @Test
    @Tag("smoke")
    @DisplayName("Valid Login Test")
    @Story("User tries to login the system with valid username and valid password.")
    @Description("Valid Login Test with valid Username and valid Password.")
    void validLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.setUsername(loginPage.getUsername())
                .setPassword(loginPage.getPassword())
                .submitValidLogin()
                .verifySuccess();
    }

    /**
     * Test for invalid logging validation
     */
    @Test
    @Tag("negative")
    @DisplayName("Invalid Login Test")
    @Story("User tries to login the system with invalid username and invalid password.")
    @Description("Invalid Login Test with invalid Username and invalid Password.")
    void invalidLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.setUsername("SamoaPeople")
                .setPassword("Supercomedian")
                .submitInvalidLogin()
                .verifyInvalidErrorIsDisplayed();
    }
}
