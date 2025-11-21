package pageobjects;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
 
import java.time.Duration;
import java.util.List;
 
public class SearchPage {
    WebDriver driver;
    WebDriverWait wait;
 
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
 
    // Locator for the main container of a course card
    By courseCards = By.cssSelector("div.cds-ProductCard-base");
 
    // =================================================================
    // BEST LOCATORS: Using CSS Selectors with data-testid attribute.
    // We target the clickable <label> element that contains the input.
    // =================================================================
 
    // CSS Selector: Targets the <label> inside the unique data-testid container for English.
    By englishFilterLabel = By.cssSelector("div[data-testid='language:English-false'] label");
 
    // CSS Selector: Targets the <label> inside the unique data-testid container for Beginner.
    By beginnerFilterLabel = By.cssSelector("div[data-testid='productDifficultyLevel:Beginner-false'] label");
 
 
    /**
     * Clicks the English and Beginner filter checkboxes using robust CSS Selectors.
     */
    public void selectEnglishAndBeginnerFilters() {
        System.out.println("Attempting to click English and Beginner filter checkboxes...");
        try {
            // 1. Click English Checkbox
            // Wait for the visible label element to be clickable
            WebElement englishFilter = wait.until(ExpectedConditions.elementToBeClickable(englishFilterLabel));
            englishFilter.click();
            System.out.println("Successfully clicked the 'English' filter checkbox.");
 
            // 2. Click Beginner Checkbox
            // Re-wait for the element to ensure it's not stale after the first filter click.
            WebElement beginnerFilter = wait.until(ExpectedConditions.elementToBeClickable(beginnerFilterLabel));
            beginnerFilter.click();
            System.out.println("Successfully clicked the 'Beginner' filter checkbox.");
 
            // Optional: Wait for the search results loader to disappear
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".cds-Loader")));
            System.out.println("Waited for search results to refresh after applying filters.");
        } catch (Exception e) {
            System.err.println("🚨 Failed to click one or both filter checkboxes: " + e.getMessage());
            System.err.println("Ensure the filters panel is visible and not loading when this method runs.");
        }
    }
 
    /**
     * Prints the name, duration, and rating of the first two course results.
     */
    public void printFirstTwoCourses() {
        // Wait for at least one course card to be visible before proceeding
        wait.until(ExpectedConditions.visibilityOfElementLocated(courseCards));
 
        List<WebElement> courses = driver.findElements(courseCards);
        for (int i = 0; i < 2 && i < courses.size(); i++) {
            WebElement course = courses.get(i);
            // Gets the course name
            String name = course.findElement(By.cssSelector("h3.cds-CommonCard-title")).getText();
            // Gets the hours/duration information
            String hours = course.findElement(By.cssSelector(".cds-CommonCard-metadata p")).getText();
            // Gets the rating score (using the locator from your initial code)
            String rating = course.findElement(By.cssSelector("div.cds-RatingStat-sizeLabel span.css-6ecy9b")).getText();
            System.out.println("Course : " + name + " | Hours: " + hours + " | Rating: " + rating);
        }
    }
}