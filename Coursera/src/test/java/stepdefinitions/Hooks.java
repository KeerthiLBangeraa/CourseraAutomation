package stepdefinitions;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtils;

public class Hooks {

    @Before
    public void setup() {
        DriverFactory.initDriver();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @AfterStep
    public void captureScreenshotAfterStep(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver(); // Get ThreadLocal driver
        byte[] screenshot = ScreenshotUtils.takeScreenshot(driver);
        scenario.attach(screenshot, "image/png", "Screenshot after step");
    }
}