package myStore.Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class RegistrationFormPage {
    private final WebDriver driver;

    @FindBy(id = "field-id_gender-1")
    private WebElement socialMr;

    @FindBy(id = "field-id_gender-2")
    private WebElement socialMrs;

    @FindBy(id = "field-firstname")
    private WebElement firstNameInput;

    @FindBy(id = "field-lastname")
    private WebElement lastNameInput;

    @FindBy(id = "field-email")
    private WebElement emailInput;

    @FindBy(id = "field-password")
    private WebElement passwordInput;

    @FindBy(id = "field-birthday")
    private WebElement birthdayInput;

    @FindBy(xpath = "//input[@name='optin']")
    private WebElement receiveOffercheckbox;

    @FindBy(xpath = "//input[@name='customer_privacy']")
    private WebElement customerDataCheckbox;

    @FindBy(xpath = "//input[@name='newsletter']")
    private WebElement newsletterCheckbox;

    @FindBy(xpath = "//input[@name='psgdpr']")
    private WebElement termsAndConditionsCheckbox;

    public RegistrationFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //gender randomizer
    Random gender = new Random();
    boolean isMale = gender.nextBoolean();

    //Social title randomize
    public void selectRandomGender() {

        if (isMale) {
            socialMr.click();
        } else {
            socialMrs.click();
        }
    }

    //Random name input
    public void inputRandomName() {
        String firstName = getRandomName(isMale);
        firstNameInput.sendKeys(firstName);
    }

    //Random Last name input
    public void inputRandomLastName() {
        String lastName = getRandomLastName(isMale);
        lastNameInput.sendKeys(lastName);
    }

    //Random email input
    public void inputRandomEmail() {
        long milisecs = System.currentTimeMillis();
        emailInput.sendKeys("test" + milisecs + "@test.com");
    }

    //Random password input
    public void inputRandomPassword() {
        passwordInput.sendKeys(generateRandomPassword());
    }

    //Random birthday input
    public void inputRandomBirthday() {
        birthdayInput.sendKeys(generateRandomBirthday());
    }

    //Checkbox click
    public void checkboxSelect() {
        receiveOffercheckbox.click();
        customerDataCheckbox.click();
        newsletterCheckbox.click();
        termsAndConditionsCheckbox.click();
    }

    //Random name method
    public static String getRandomName(boolean isMale) {
        String[] maleNames = {"Bartosz", "Piotr", "Kamil", "Szymon", "Krzysztof", "Zdzisław", "Andrzej", "Stanisław"};
        String[] femaleNames = {"Anna", "Katarzyna", "Paulina", "Magda", "Jagoda", "Patrycja", "Agnieszka", "Aleksandra"};

        Random firstNameRandom = new Random();
        if (isMale) {
            return maleNames[firstNameRandom.nextInt(maleNames.length)];
        } else {
            return femaleNames[firstNameRandom.nextInt(femaleNames.length)];
        }
    }

    //Random last name method
    public static String getRandomLastName(boolean isMale) {
        String[] maleLastNames = {"Nowak", "Kowalski", "Wiśniewski", "Wójcik", "Kamiński", "Lewandowski", "Zieliński", "Woźniak"};
        String[] femaleLastNames = {"Nowak", "Kowalska", "Wiśniewska", "Wójcik", "Kamińska", "Lewandowska", "Zielińska", "Woźniak"};
        Random lastNameRandom = new Random();
        if (isMale) {
            return maleLastNames[lastNameRandom.nextInt(maleLastNames.length)];
        } else {
            return femaleLastNames[lastNameRandom.nextInt(femaleLastNames.length)];
        }
    }

    //Random password method
    public static String generateRandomPassword() {
        return "Test" + new Random().nextInt(10000, 99999) + "!";
    }

    //Random birthday method
    public static String generateRandomBirthday() {
        Random randBirthday = new Random();
        int randomDay = randBirthday.nextInt(1, 31);
        int randomMonth = randBirthday.nextInt(1, 13);
        int randomYear = randBirthday.nextInt(1960, 2009);
        if (randomMonth == 2) randomDay = randBirthday.nextInt(1, 29);
        return String.format("%02d/%02d/%04d", randomMonth, randomDay, randomYear);
    }
}


