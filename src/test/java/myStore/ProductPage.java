package myStore;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    private final WebDriver driver;

    @FindBy(className = "product-discount")
    private WebElement regularPrice;

    @FindBy(className = "current-price-value")
    private WebElement currentPrice;

    @FindBy(id = "group_1")
    private WebElement sizeGroup;

    @FindBy(css = ".bootstrap-touchspin-up")
    private WebElement productQuantity;


    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //verifying discount method
    public void discountCheck() {
        String regPriceString = regularPrice.getText(); // retrieving price
        String regPriceClean = regPriceString.replaceAll("[^\\d.]", ""); //Removing unnecessary characters
        double regPrice = Double.parseDouble(regPriceClean);

        double currPrice = Double.parseDouble(currentPrice.getAttribute("content"));

        double expectedPrice = regPrice * 0.8;

        Assertions.assertEquals(currPrice, expectedPrice);
    }

    public void sizePick(String size) {
        Select sizeDropdown = new Select(sizeGroup);
        sizeDropdown.selectByVisibleText(size);
    }

    public void selectQuantity (int quantity) {
        int defaultValue = 1;
        int clicksNeeded = quantity - defaultValue;
        for (int i = 0; i < clicksNeeded; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            productQuantity.click();
        }
    }
}
