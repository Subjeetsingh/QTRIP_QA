package qtriptest;

import java.io.ObjectInputFilter.Status;
import java.util.List;
import org.apache.commons.collections4.map.StaticBucketMap;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;


public class SeleniumWrapper {


    public static boolean click(WebElement elementToClick, WebDriver driver){
        boolean elementVisible=false;
        try{
            elementVisible=elementToClick.isDisplayed();
            if(elementVisible){
                scrollToElement(driver, elementToClick);
                elementToClick.click();
            }
            else{
                return elementVisible;
            }
        }
        catch(WebDriverException  e){
            e.printStackTrace();
            return elementVisible;

        }
        return elementVisible;
    }


    public static void sendKeys(WebElement inputBox, String keysToSend){
        inputBox.clear();
        inputBox.sendKeys(keysToSend);;
    }

    public static void navigate(WebDriver driver, String url){
       String currentURL= driver.getCurrentUrl();
       if(!currentURL.equals(url)){
        driver.get(url);
       }
    }

    public static WebElement findElemetWithRetry(WebDriver driver, By by, int retryCount) throws InterruptedException {
       // int maxRetries = 3;
        WebElement element = null;
        for (int i = 0; i < retryCount; i++) {
            try {
                element = driver.findElement(by);
                if (element != null) {
                    break; // Exit the loop if element is found
                }
            } catch (Exception e) {
                // Log or handle the exception if needed
                Thread.sleep(2000); // Wait for 2 seconds before retrying
            }
        }
        return element;
    }

    public static void scrollToElement(WebDriver driver , WebElement elementToScrollTo){

          // Scroll to the web element using JavaScript
          ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToScrollTo);
    }
}
