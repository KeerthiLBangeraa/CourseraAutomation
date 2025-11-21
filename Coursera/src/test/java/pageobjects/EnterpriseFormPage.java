package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;

public class EnterpriseFormPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for navigating to the form
    private final By forEnterpriseLink = By.linkText("For Enterprise");
    private final By campusProductLink = By.xpath("//a[contains(text(),'For Campus')]");

    // Locators for text input fields
    private final By firstName = By.name("FirstName");
    private final By lastName = By.name("LastName");
    private final By company = By.name("Company");
    private final By email = By.name("Email");
    private final By phone = By.name("Phone");
    private final By submitButton = By.xpath("//button[contains(text(),'Submit')]");

    // Locator for the email error message
    private final By emailError = By.id("ValidMsgEmail");
    
    // Locators for the dropdown menus, now targeting the <select> tag
    private final By institutionTypeDropdown = By.id("Institution_Type__c");
    private final By jobRoleDropdown = By.id("Title");
    private final By departmentDropdown = By.id("Department");
    private final By needsDropdown = By.id("What_the_lead_asked_for_on_the_website__c");
    private final By countryDropdown = By.id("Country");
    private final By stateDropdown = By.id("State"); 

    public EnterpriseFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    public void navigateToCampusForm() {
        WebElement enterpriseLink = wait.until(ExpectedConditions.presenceOfElementLocated(forEnterpriseLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", enterpriseLink);
        wait.until(ExpectedConditions.elementToBeClickable(enterpriseLink)).click();

        WebElement campusLink = wait.until(ExpectedConditions.presenceOfElementLocated(campusProductLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", campusLink);
        wait.until(ExpectedConditions.elementToBeClickable(campusLink)).click();
    }
    
    // Individual methods for each form field to support the specific order
    public void fillFirstName(String fName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fName);
    }

    public void fillLastName(String lName) {
        driver.findElement(lastName).sendKeys(lName);
    }

    public void fillEmail(String mail) {
        driver.findElement(email).sendKeys(mail);
    }
    
    public void fillPhone(String ph) {
        driver.findElement(phone).sendKeys(ph);
    }

    public void fillCompany(String comp) {
        driver.findElement(company).sendKeys(comp);
    }
    
    // Dropdown selection methods remain the same
    public void selectInstitutionType(String type) {
        selectDropdown(institutionTypeDropdown, type);
    }

    public void selectJobRole(String role) {
        selectDropdown(jobRoleDropdown, role);
    }

    public void selectDepartment(String department) {
        selectDropdown(departmentDropdown, department);
    }

    public void selectNeeds(String need) {
        selectDropdown(needsDropdown, need);
    }

    public void selectCountry(String country) {
        selectDropdown(countryDropdown, country);
    }
    public void selectState(String state) {
        selectDropdown(stateDropdown, state);
    }
    
    private void selectDropdown(By locator, String optionText) {
        WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(optionText);
    }

    public void submitForm() {
        driver.findElement(submitButton).click();
    }

    public String getEmailErrorMessage() {
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailError));
        return errorElement.getText();
    }
}