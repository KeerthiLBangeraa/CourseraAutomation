package stepdefinitions;

import factory.DriverFactory;
import pageobjects.EnterpriseFormPage;
import pageobjects.HomePage;
import io.cucumber.java.en.*;

public class FormValidationSteps {
    private HomePage homePage = new HomePage(DriverFactory.getDriver());
    private EnterpriseFormPage formPage = new EnterpriseFormPage(DriverFactory.getDriver());

    @And("User navigates to Coursera for Enterprise Campus form")
    public void user_navigates_to_enterprise_form() {
        formPage.navigateToCampusForm();
    }

    @When("User fills the form with invalid email")
    public void user_fills_form_with_invalid_email() {
        // Step 1: Fill in the text fields individually and in the specified order
        formPage.fillFirstName("Ritul");
        formPage.fillLastName("Mehta");
        formPage.fillEmail("Ritul@invalid");
        formPage.fillPhone("1234567890");

        // Step 2: Select the dropdowns in the specified order
        formPage.selectInstitutionType("University/4 Year College");
        formPage.fillCompany("Cognizant"); // Institution Name
        formPage.selectJobRole("Professor");
        formPage.selectDepartment("Academic Affairs");
        formPage.selectNeeds("Courses for myself");
        formPage.selectCountry("India");
        formPage.selectState("Tamil Nadu");
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // Step 3: Submit the form
        formPage.submitForm();
    }

    @Then("Capture and display the error message")
    public void capture_and_display_error_message() {
        String error = formPage.getEmailErrorMessage();
        System.out.println("Captured Error Message: " + error);
    }
}