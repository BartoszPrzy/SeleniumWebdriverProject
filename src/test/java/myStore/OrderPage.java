package myStore;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OrderPage {
    private final WebDriver driver;

    @FindBy(id = "main")
    private WebElement orderList;

    @FindBy(className = "account")
    private WebElement accountBtn;

    @FindBy(id = "history-link")
    private WebElement orderHistoryBtn;

    @FindBy(className = "alert")
    private WebElement alertNotification;

    @FindBy(css = "td.text-xs-right")
    private WebElement orderFinalAmount;

    private double paymentOrderAmount;

    public double getPaymentOrderAmount() {
        return paymentOrderAmount;
    }

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Screenshot folder create method
    public void createDirectoryIfNotExists(String directoryPath) {
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            boolean created = dir.mkdirs(); // creates directories if they do not exist
            if (created) {
                System.out.println("The directory has been created: " + directoryPath);
            } else {
                System.err.println("Failed to create the directory: " + directoryPath);
            }
        }
    }

    // take a screenshot method
    public void takeAScreenshot(String outputFilePath) {

        try {
            // take a screenshot
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", orderList);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            File screenshot = orderList.getScreenshotAs(OutputType.FILE);

            // save the file
            FileHandler.copy(screenshot, new File(outputFilePath));

            System.out.println("Screenshot saved: " + outputFilePath);

        } catch (IOException e) {
            System.err.println("Error while saving the screenshot: " + e.getMessage());
        }
    }

    //Checks whether the alert about no orders being available is displayed
    public boolean isOrderAlertVisible() {
        try {
            boolean visible = alertNotification.isDisplayed();

            if (visible) {
                System.out.println("There is no orders");
            } else {
                System.out.println("Order found");
            }

            return visible;
            //If alert is not visible(there is at least one order)
        } catch (NoSuchElementException e) {
            System.out.println("Order found (alert not present)");
            return false;
        }
    }

    //Order history and Details tile
    public void orderHistoryAndDetails() {
        accountBtn.click();
        orderHistoryBtn.click();

    }

    //Checks if "Awaiting check payment" status is visible
    public void isOrderWithStatusPresent(String expectedStatus) {
        List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));
        for (WebElement row : rows) {
            String statusText = row.findElement(By.cssSelector("td span.label")).getText().trim();
            if (statusText.equalsIgnoreCase(expectedStatus)) {
                System.out.println("The order status is correct: " + expectedStatus);
                return;
            }
        }
        System.out.println("Incorrect order status: " + expectedStatus);
    }

    //Retrieves the order amount from orders status
    public void orderAmountFromTheOrderStatus() {
        String paymentOrderAmountText = orderFinalAmount.getText().replaceAll("[^\\d.]", ""); //Removing unnecessary characters
        paymentOrderAmount = Double.parseDouble(paymentOrderAmountText);
        System.out.println("Order amount: " + paymentOrderAmount);
    }
}
