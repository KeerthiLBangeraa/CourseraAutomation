package stepdefinitions;
 
import factory.DriverFactory;
import pageobjects.HomePage;
import pageobjects.SearchPage;
import io.cucumber.java.en.*;
 
public class CourseSearchSteps {
    HomePage home;
    SearchPage search;
 
    @Given("User is on Coursera homepage")
    public void user_is_on_homepage() {
        // Assuming DriverFactory.initDriver() also handles maximization and implicit waits
        DriverFactory.initDriver().get("https://www.coursera.org/");
    }
 
    @When("User searches for beginner web development courses in English")
    public void user_searches_courses() {
        // 1. Initialize HomePage and perform the initial search
        home = new HomePage(DriverFactory.getDriver());
        home.searchCourse("Web Development"); // This method must exist in HomePage
        // 2. Initialize SearchPage and apply filters
        search = new SearchPage(DriverFactory.getDriver());
        search.selectEnglishAndBeginnerFilters(); // This method applies the filters
    }
 
    @Then("Display first two course names, hours and ratings")
    public void display_course_info() {
        // Since 'search' is already initialized in the @When step, we can reuse it.
        // If the 'search' object was not guaranteed to be initialized, we'd uncomment the line below:
        // search = new SearchPage(DriverFactory.getDriver());
        search.printFirstTwoCourses();
    }
}