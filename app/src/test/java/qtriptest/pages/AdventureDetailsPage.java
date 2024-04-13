package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdventureDetailsPage extends SeleniumWrapper{

    WebDriver driver;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement guestName;

    @FindBy(xpath = "//input[@name='date']")
    private WebElement bookingDate;

    @FindBy(xpath="//input[@name='person']")
    private WebElement personCount;

    @FindBy(xpath="//button[@type='submit']")
    private WebElement reserveCTA;

    @FindBy(xpath = "//*[@id='reserved-banner']")
    private WebElement bookingSuccessfullText;

    public AdventureDetailsPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void bookAdventure(String name, String date, CharSequence[] perCount){
        sendKeys(guestName, name);
      //  guestName.sendKeys(name);
      sendKeys(bookingDate, date);
        //bookingDate.sendKeys(date);
        //sendKeys(personCount, perCount[0]);
        personCount.clear();
        personCount.sendKeys(perCount[0]);
        //reserveCTA.click();
        click(reserveCTA, driver);
    }

    public boolean isBookingSuccessful() throws InterruptedException{
        Thread.sleep(2000);
        return bookingSuccessfullText.getText().split("\\(")[0].trim().equals("Greetings! Reservation for this adventure is successful.");
        
    }






}