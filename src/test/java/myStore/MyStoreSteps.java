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
    private ProductPage productPage;
    private NewAddressPage newAddressPage;
    private CartPage cartPage;
    private OrderPage orderPage;


    @Given("I'm on mystore main page")
    public void imOnMyStoreMainPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://mystore-testlab.coderslab.pl");

        productPage = new ProductPage(driver);


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

    @When("I click on my name")
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

        newAddressPage = new NewAddressPage(driver);
        newAddressPage.enterNewAddressData(alias, address, city, postalCode, phoneNumber);
        driver.findElement(By.xpath("//button[contains(@class, 'btn-primary')]")).click();
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

    @When("I go to the main page")
    public void iGoToTheMainPage() {
        driver.findElement(By.className("logo")).click();
    }

    @Then("I click on Hummingbird printed sweater")
    public void iClickOnHummingbirdPrintedSweater() {
        driver.findElement(By.cssSelector("a[href$=size-s]")).click();
    }

    @And("I check if there is a discount")
    public void iCheckIfThereIsADiscount() {
        productPage.discountCheck();
    }

    @And("I choose size {string}")
    public void iChooseSizeSize(String size) {
        productPage.sizePick(size);
    }

    @And("I set quantity {int}")
    public void iSetQuantityQuantity(int quantity) {
        productPage.selectQuantity(quantity);

    }

    @Then("I add it to the cart")
    public void iAddItToTheCart() {
        cartPage = new CartPage(driver);
        cartPage.cartBtn();
    }

    @And("I click on proceed to checkout")
    public void iClickOnProceedToCheckout() {
        driver.findElement(By.cssSelector("a[href$=order]")).click();
    }

    @Then("I delete the address")
    public void iDeleteTheAddress() {
        driver.findElement(By.className("delete-address")).click();
    }

    @And("I check if address is successfully deleted")
    public void iCheckIfAddressIsSuccessfullyDeleted() {
        myAddressesPage = new MyAddressesPage(driver);
        Assertions.assertTrue(myAddressesPage.isAddressAlertVisible(), "The address was not successfully deleted");
    }


    @Then("^I enter new alias (.*) address (.*) city (.*) postal code (.*) phone (.*)$")
    public void iAddNewAddress(String alias, String address, String city, String postalCode, String phoneNumber) {
        this.alias = alias;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;

        newAddressPage.enterNewAddressData(alias, address, city, postalCode, phoneNumber);
        driver.findElement(By.name("confirm-addresses")).click();
    }

    @And("I choose pick up in-store")
    public void iChoosePickUpInStore() {
        cartPage.deliveryBtn();
    }

    @And("I choose pay by Check")
    public void iChoosePayByCheck() {
        cartPage.deliveryPaymentSelect();
    }

    @And("I place order")
    public void iPlaceOrder() {
        driver.findElement(By.cssSelector("div>button[class$=center-block]")).click();
    }

    @Then("I take a screenshot of the order")
    public void iTakeAScreenshotOfTheOrder() {
        orderPage = new OrderPage(driver);
        orderPage.createDirectoryIfNotExists("screenshots");
        String fileName = "screenshots/order_" + System.currentTimeMillis() + ".png";
        orderPage.takeAScreenshot(fileName);
    }

    @And("I go to order history and details")
    public void iGoToOrderHistoryAndDetails() {

        orderPage.orderHistoryAndDetails();
        Assertions.assertFalse(orderPage.isOrderAlertVisible(), "There is no orders");
    }

    @And("I check if the order has correct amount and status")
    public void iCheckIfTheOrderHasCorrectAmountAndStatus() {
        orderPage.orderAmountFromTheOrderStatus();
        Assertions.assertEquals(cartPage.getPaymentAmount(), orderPage.getPaymentOrderAmount());
        orderPage.isOrderWithStatusPresent("Awaiting check payment");
    }

    @And("I close the browser")
    public void iCloseTheBrowser() {
        driver.quit();
    }
}

