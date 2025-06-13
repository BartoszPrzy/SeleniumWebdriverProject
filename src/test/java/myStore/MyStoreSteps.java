package myStore;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class MyStoreSteps {
    private WebDriver driver;
    private MyAddressesPage myAddressesPage;
    private String alias, address, city, postalCode, phoneNumber;

    @Given("I'm on mystore main page")
    public void imOnMyStoreMainPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://mystore-testlab.coderslab.pl");
    }

    @When("I click sign in")
    public void iSignIn() {
        driver.findElement(By.className("user-info")).click();
    }

    @And("I click on create new account")
    public void iClickOnCreateNewAccount() {
        driver.findElement(By.className("no-account")).click();
    }

    @And("I pick gender enter name surname email password birthdate and select checkboxes")
    public void formFill() {
        RegistrationFormPage registrationFormPage = new RegistrationFormPage(driver);
        registrationFormPage.selectRandomGender();
        registrationFormPage.inputRandomName();
        registrationFormPage.inputRandomLastName();
        registrationFormPage.inputRandomEmail();
        registrationFormPage.inputRandomPassword();
        registrationFormPage.inputRandomBirthday();
        registrationFormPage.checkboxSelect();
    }

    @Then("I'm redirected to the homepage and logged in")
    public void iMRedirectedToTheHomepageAndLoggedIn() {
        driver.findElement(By.xpath("//button[@data-link-action='save-customer']")).click();
    }

    @And("I click on my name")
    public void iClickOnMyName() {
        driver.findElement(By.xpath("//a[@title='View my customer account']")).click();
    }

    @Then("I go to add first address")
    public void iGoToAddFirstAddress() {
        driver.findElement(By.id("address-link")).click();
    }

    @And("^I enter alias (.*) address (.*) city (.*) postal code (.*) phone (.*)$")
    public void iEnterNewAddress(String alias, String address, String city, String postalCode, String phoneNumber) {
        this.alias = alias;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;

        NewAddressPage newAddressPage = new NewAddressPage(driver);
        newAddressPage.enterNewAddressData(alias, address, city, postalCode, phoneNumber);
    }

    @Then("I can see new address")
    public void iCanSeeNewAddress() {
        myAddressesPage = new MyAddressesPage(driver);
        Assertions.assertTrue(myAddressesPage.areAddressesVisible(), "Created address should be visible");
    }

    @And("I verify created address")
    public void iVerifyCreatedAddress() {

        String actualAddress = myAddressesPage.getLatestAddressText();

        Assertions.assertTrue(actualAddress.contains(alias), "Alias not found in address");
        Assertions.assertTrue(actualAddress.contains(address), "Street address not found");
        Assertions.assertTrue(actualAddress.contains(city), "City not found");
        Assertions.assertTrue(actualAddress.contains(postalCode), "Postal code not found");
        Assertions.assertTrue(actualAddress.contains(phoneNumber), "Phone number not found");
    }

    @And("I close the browser")
    public void iCloseTheBrowser() {
        driver.quit();
    }
}

