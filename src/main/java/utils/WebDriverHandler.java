package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public abstract class WebDriverHandler implements TestWatcher, AfterAllCallback {

    private static final Logger LOGGER = Logger.getLogger(WebDriverHandler.class.getName());
    private final List<TestResultStatus> testResultsStatus = new ArrayList<>();

    private enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED
    }

    /**
     * This method overrides TestWatcher method for disabled test methods
     */
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        LOGGER.info("Test Disabled for test: " + context.getDisplayName());

        testResultsStatus.add(TestResultStatus.DISABLED);
    }

    /**
     * This method overrides TestWatcher method for successful test methods
     */
    @Override
    public void testSuccessful(ExtensionContext context) {
        LOGGER.info("Test Successful for test: " + context.getDisplayName());

        testResultsStatus.add(TestResultStatus.SUCCESSFUL);
    }


    /**
     * This method overrides TestWatcher method for aborted test methods
     */
    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        LOGGER.info("Test Aborted for test: " + context.getDisplayName());

        testResultsStatus.add(TestResultStatus.ABORTED);
    }

    /**
     * This method overrides TestWatcher method for failed test methods
     */
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        LOGGER.info("Test Failed for test: " + context.getDisplayName() + " " + cause.getCause());

        testResultsStatus.add(TestResultStatus.FAILED);
    }

    /**
     * This method overrides AfterAllCallback method for reporting the result summary
     */
    @Override
    public void afterAll(ExtensionContext context) {
        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        LOGGER.log(Level.INFO, "Test result summary for {0} ", context.getDisplayName() + " " + summary.toString());
    }

    /**
     * Getter method for WebDriver
     *
     * @return WebDriver
     */
    public static WebDriver getDriver() {
        return driver;
    }

    private static WebDriver driver;

    /**
     * Method that starts the WebDriver
     */
    @BeforeEach
    public void startDriver() {
        initializeDriver();
    }

    /**
     * Method to kill WebDriver
     */
    @AfterEach
    public void quitDriver() {
        exitBrowser();
    }

    /**
     * This method is used for initializing a WebDriver and getting the url page
     */
    public static void initializeDriver() {
        LOGGER.info("Initializing WebDriver");
        System.setProperty("webdriver.chrome.driver", getOsWebDriver());
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        LOGGER.info("WebDriver initialized");
    }

    /**
     * This method is used for returning the correct WebDriver for current operating system
     *
     * @return Path of the correct operating system WebDriver
     */
    public static String getOsWebDriver() {
        String absolutePath = null;
        String userDir = System.getProperty("user.dir");
        String osName = System.getProperty("os.name").toLowerCase();
        String osArch = System.getProperty("os.arch");
        String bit = osArch.substring(osArch.length() - 2) + "bit";

        if (osName.contains("windows")) absolutePath = userDir + "/drivers/chromedriver-windows-32bit.exe";
        else if (osName.contains("mac")) absolutePath = userDir + "/drivers/chromedriver-mac-" + bit;
        else if (osName.contains("linux")) absolutePath = userDir + "/drivers/chromedriver-linux-" + bit;
        else LOGGER.log(Level.SEVERE, "Driver not found.");
        return absolutePath;
    }

    /**
     * This method is used for exiting the browser
     */
    public static void exitBrowser() {
        LOGGER.info("Exiting the browser");
        driver.quit();
        LOGGER.info("Exited the browser");
    }
}
