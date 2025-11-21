package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys; // Import the Keys class for keyboard actions
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    WebDriver driver;
    // Add a WebDriverWait instance
    WebDriverWait wait;

    public HomePage(WebDriver driver)  {
        this.driver = driver;
        // Initialize WebDriverWait with a reasonable timeout
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    By searchBox = By.id("search-autocomplete-input");
    

    public void searchCourse(String courseName) {
        // Wait until the search box is clickable before typing
        wait.until(ExpectedConditions.elementToBeClickable(searchBox)).sendKeys(courseName);
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Send the ENTER key to the search box to trigger the search
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
    }
}
