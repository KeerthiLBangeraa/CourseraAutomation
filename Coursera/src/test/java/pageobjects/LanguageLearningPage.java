package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;

public class LanguageLearningPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for the filter sections (parent elements)
    private final By languageSection = By.xpath("//div[@data-testid='search-filter-group-Language']");
    private final By levelSection = By.xpath("//div[@data-testid='search-filter-group-Level']");

    // Locators for the actual list items (languages and levels)
    private final By languageItems = By.xpath("//div[@data-testid='search-filter-group-Language']//label//span[@class='cds-checkboxAndRadio-labelContent css-tvqrra']");
    private final By levelItems = By.xpath("//div[@data-testid='search-filter-group-Level']//label//span[@class='cds-checkboxAndRadio-labelContent css-tvqrra']");

    // Locator for the "Show More" button for languages
    private final By showMoreLanguageBtn = By.xpath("//div[@data-testid='search-filter-group-Language']//button[contains(.,'Show')]");

    public LanguageLearningPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void expandAllFilters() {
        // Wait for the main filter sections to be present and scroll to them
        WebElement langSection = wait.until(ExpectedConditions.visibilityOfElementLocated(languageSection));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", langSection);
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Wait for "Show More" button and click it if it exists
        try {
            WebElement showMoreBtn = wait.until(ExpectedConditions.elementToBeClickable(showMoreLanguageBtn));
            showMoreBtn.click();
            System.out.println("Clicked 'Show More' button for Languages.");
            // Wait for the new elements to load after click
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(languageItems));
        } catch (NoSuchElementException e) {
            System.out.println("'Show More' button for Languages not found, continuing without clicking.");
        }
    }

    public List<WebElement> getLanguages() {
        // Return all language elements, including those revealed by "Show More"
        return driver.findElements(languageItems);
    }

    public List<WebElement> getLevels() {
        // Return all level elements
        return driver.findElements(levelItems);
    }
}
