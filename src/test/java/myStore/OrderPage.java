package myStore;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;

public class OrderPage {
    private final WebDriver driver;

    @FindBy(id = "main")
    private WebElement orderList;

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

            System.out.println("Screenshot zapisany: " + outputFilePath);

        } catch (IOException e) {
            System.err.println("Błąd przy zapisie screenshotu: " + e.getMessage());
        }
    }
}
