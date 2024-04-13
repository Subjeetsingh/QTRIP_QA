
package qtriptest.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

public class HistoryPage {

    WebDriver driver;

    @FindBy(xpath="")
    private WebElement cancelButton;

    @FindBy(xpath="//a[contains(text(),'Home')]")
    private WebElement homeButton;

    // @FindBy(xpath = "//*[@id='navbarNavDropdown']/ul/li[2]/a")
    // private WebElement reservationButtons;

    public HistoryPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public Boolean clickHomeButton() throws InterruptedException{
        homeButton.click();

        Thread.sleep(3000);
        return true;

    }
    
// Function to retrieve transaction IDs
public List<String> getTransactionIds() throws InterruptedException {
    List<String> transactionIds = new ArrayList<>();
    List<WebElement> elements = driver.findElements(By.xpath("//tbody[@id='reservation-table']/tr"));
    int size = elements.size();
    System.out.println(size);
    for (int i = 0; i < size; i++) {
        Thread.sleep(2000);
        WebElement element = elements.get(i);
        Thread.sleep(2000);
        transactionIds.add(element.findElement(By.tagName("th")).getText());
    }
    System.out.println(transactionIds);
    return transactionIds;
}
String transactionIdToDelete;
// Function to delete the first transaction
public void deleteFirstTransaction() throws InterruptedException {
    List<String> transactionIds = getTransactionIds();
 
    System.out.println("Transaction IDs before delete: " + transactionIds);
    Thread.sleep(2000);    
    if (!transactionIds.isEmpty()) {
        transactionIdToDelete = transactionIds.get(0);
        driver.findElement(By.xpath("//tbody[@id='reservation-table']//th[text()='" + transactionIdToDelete + "']/following-sibling::td[7]"))
                .click();
        // TODO: Add wait
    } else {
        transactionIdToDelete = null;
        System.out.println("No transaction found to delete.");
    }
}

// Function to validate transaction deletion
public boolean isTransactionDeleted() throws InterruptedException {
    if (transactionIdToDelete == null) {
        System.out.println("No transaction ID to validate deletion.");
        return false;
    }
    List<String> transactionIdsAfterDelete = getTransactionIds();
    return !transactionIdsAfterDelete.contains(transactionIdToDelete);
}

// Main function calling the above three functions
public void validateTransactionId() throws InterruptedException {
   // deleteFirstTransaction();
    // TODO: Add wait for deletion to complete
    boolean isDeleted = isTransactionDeleted();
    if (isDeleted) {
        System.out.println("Transaction with ID " + transactionIdToDelete + " is successfully deleted.");
    } else {
        System.out.println("Failed to delete transaction with ID " + transactionIdToDelete + ".");
    }
}

// Function to validate if all transaction IDs are present
public boolean validateAllTransactionIdsPresent() throws InterruptedException {
    List<String> transactionIds = getTransactionIds();

    // Get the total number of transactions displayed on the web page
    int totalTransactionsDisplayed = driver.findElements(By.xpath("//tbody[@id='reservation-table']/tr"))
            .size();

    // Check if the number of transaction IDs matches the total transactions displayed
    boolean allIdsPresent = transactionIds.size() == totalTransactionsDisplayed;

    if (allIdsPresent) {
        System.out.println("All transaction IDs are present.");
    } else {
        System.out.println("Some transaction IDs are missing.");
    }

    return allIdsPresent;
}
}

