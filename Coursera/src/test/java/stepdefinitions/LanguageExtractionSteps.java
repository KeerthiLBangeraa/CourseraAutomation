package stepdefinitions;

import factory.DriverFactory;
import pageobjects.HomePage;
import pageobjects.LanguageLearningPage;
import io.cucumber.java.en.*;
import java.util.List;
import org.openqa.selenium.WebElement;

public class LanguageExtractionSteps {
    private HomePage homePage = new HomePage(DriverFactory.getDriver());
    private LanguageLearningPage langPage = new LanguageLearningPage(DriverFactory.getDriver());

    @When("User searches for language learning courses")
    public void user_searches_for_language_learning_courses() {
        homePage.searchCourse("Language Learning");
        
        
    }

    @And("User clicks on the Language and Level filter dropdowns")
    public void user_clicks_on_the_language_and_level_filter_dropdowns() {
        // Call the correct method from the LanguageLearningPage
        // The previous method `waitForFiltersToLoad()` no longer exists.
        langPage.expandAllFilters();
    }

    @Then("Extract and display all languages and levels with count")
    public void extract_languages_and_levels() {
        List<WebElement> languages = langPage.getLanguages();
        List<WebElement> levels = langPage.getLevels();

        System.out.println("Extracted Languages:");
        for (WebElement lang : languages) {
            String text = lang.getText();
            String name = text.replaceAll("\\s*\\(.*\\)", "");
            String count = text.replaceAll(".*\\((\\d[\\d,]*)\\)", "$1");
            System.out.println("Language: " + name + ", Count: " + count);
        }
        System.out.println("Total Languages: " + languages.size());

        System.out.println("\nExtracted Levels:");
        for (WebElement level : levels) {
            String text = level.getText();
            String name = text.replaceAll("\\s*\\(.*\\)", "");
            String count = text.replaceAll(".*\\((\\d[\\d,]*)\\)", "$1");
            System.out.println("Level: " + name + ", Count: " + count);
        }
        System.out.println("Total Levels: " + levels.size());
    }
}
