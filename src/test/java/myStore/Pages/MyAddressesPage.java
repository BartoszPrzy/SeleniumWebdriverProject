package myStore.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MyAddressesPage {
    private final WebDriver driver;

    @FindBy(className = "address")
    private List<WebElement> addresses;

    @FindBy(css = "a[href$=address]")
    private WebElement newAddressBtn;

    @FindBy(className = "address-body")
    private WebElement addressContainer;

    @FindBy(className = "alert-success")
    private WebElement addressDeleteAlert;

    public MyAddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //check if address list is empty
    public boolean areAddressesVisible() {
        return !addresses.isEmpty();
    }

    //get address list
    public String getLatestAddressText() {
        return addressContainer.getText();
    }

    //Checks whether the success alert for address deletion is visible
    public boolean isAddressAlertVisible() {
        return addressDeleteAlert.isDisplayed();
    }
}
