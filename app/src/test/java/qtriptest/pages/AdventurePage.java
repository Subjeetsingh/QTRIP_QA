package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.List;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
// import org.testng.Asserts.*;


public class AdventurePage extends SeleniumWrapper{

    WebDriver driver;

    SoftAssert softAssert=new SoftAssert();

    @FindBy(xpath ="//select[@id='duration-select']")
    private WebElement clickDuration;

    // @FindBy(xpath="//*[@id='duration-select']/option")
    // private List<WebElement> selectDurationValues;

    @FindBy(xpath ="//select[@id='category-select']")
    private WebElement clickCategory;

    // @FindBy(xpath="//*[@id='category-select']/option")
    // private List<WebElement> selectCategoryValues;

    @FindBy(xpath ="//div[@onclick='clearDuration(event)']")
    private WebElement clearButtonForDuration;

    @FindBy(xpath ="//div[@onclick='clearCategory(event)']")
    private WebElement clearButtonForCategory;

    @FindBy(xpath="//*[@class='activity-card']/div/div[2]/p")
    private List<WebElement> verifyResultCount;

    @FindBy(xpath="//*[@class='category-banner']")
    private List<WebElement> verifyResultCategory;

    public AdventurePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void selectFilterValue(String durationValue) throws InterruptedException{
       try{
        click(clickDuration, driver);
       } 
       catch(WebDriverException e){
        e.printStackTrace();
        click(clickDuration, driver);

       }

        //clickDuration.click();
        //Thread.sleep(3000);

        Select dropdown=new Select(clickDuration);
        dropdown.selectByVisibleText(durationValue);
        Thread.sleep(3000);
        
    }
    

    public void selectCategoryValue(String categoryValue) throws InterruptedException{

       // clickCategory.click();

        Select dropdown=new Select(clickCategory);
        dropdown.selectByVisibleText(categoryValue);
        Thread.sleep(3000);

    }

    public void verifyFilteredResult(String expectedFilteredResult){
        softAssert.assertTrue(verifyResultCount.size()==Integer.parseInt(expectedFilteredResult));
        // int valueOnUI=0;
        // String hrs =durationValue.split(" ")[0];
        // int lowerLimit=Integer.parseInt(hrs.split("-")[0]);
        // int upperLimit=Integer.parseInt(hrs.split("-")[1]);
        
    //     if(verifyResultCount.size()==0){
    //         //TODO
    //         // Assert.assertTrue(false);
    //     }
    //    for(int i=0; i<verifyResultCount.size();i++) {
    //     valueOnUI=Integer.parseInt(verifyResultCount.get(i).getText().split(" ")[0]);

    //     softAssert.assertTrue(valueOnUI<=lowerLimit && valueOnUI>=upperLimit);
           
    //    }
       softAssert.assertAll();
        
    }

    public void verifyUnfilteredResult(String expectedUnfilteredResult){
        softAssert.assertTrue(verifyResultCount.size()==Integer.parseInt(expectedUnfilteredResult));
        softAssert.assertAll();
    }
    

    // public void verifyResultCategory(int ){

    //     softAssert.assertTrue(verifyResultCount.size()==expectedUnfilteredResult);

    // }

    public void clearButton(){
        click(clearButtonForDuration, driver);
       // clearButtonForDuration.click();
       click(clearButtonForCategory, driver);
       // clearButtonForCategory.click();
    }   
    
    public void selectAdventure(String adventureName) throws InterruptedException{
        By by=By.xpath("//h5[text()='"+adventureName+"']");
       // driver.findElement(By.xpath(s)).click();;
       // findElemetWithRetry(driver, s, 3)
        click(findElemetWithRetry(driver, by, 3), driver);

    }
        
    }


