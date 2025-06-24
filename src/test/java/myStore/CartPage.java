package myStore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    private final WebDriver driver;

    @FindBy(className = "add-to-cart")
    private WebElement addToCartBtn;

    @FindBy(css = "a[href*=\"show\"][class]")
    private WebElement checkoutCartBtn;

    @FindBy(id = "delivery_option_8")
    private WebElement selfPickUpRadioBtn;

    @FindBy(name = "confirmDeliveryOption")
    private WebElement deliveryContinueBtn;

    @FindBy(id = "payment-option-1")
    private WebElement payByCheckRadioBtn;

    @FindBy(id = "conditions_to_approve[terms-and-conditions]")
    private WebElement termsAndConditionsSelectBtn;

    @FindBy(css = "div[class$=\"cart-total\"]>span[class=value]")
    private WebElement paymentAmountText;

    @FindBy(css = "div>button[class$=center-block]")
    private WebElement paymentProceedBtn;


    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void cartBtn() {
        addToCartBtn.click();
        checkoutCartBtn.click();
    }

    public void deliveryBtn() {
        if (!selfPickUpRadioBtn.isSelected()) {
            selfPickUpRadioBtn.click();
        }
        deliveryContinueBtn.click();
    }

    public void deliveryPaymentSelect() {
        if (!payByCheckRadioBtn.isSelected()) {
            payByCheckRadioBtn.click();
        }
        if (!termsAndConditionsSelectBtn.isSelected()) {
            termsAndConditionsSelectBtn.click();
        }
        String paymentAmountClean = paymentAmountText.getText().replaceAll("[^\\d.]", ""); //Removing unnecessary characters
        double paymentAmount = Double.parseDouble(paymentAmountClean);
        System.out.println(paymentAmount);

        paymentProceedBtn.click();

    }


}
