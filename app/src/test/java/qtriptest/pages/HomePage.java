package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage extends SeleniumWrapper{
   WebDriver driver;

    private final static String Search_Result_ENDPOINT="?city=goa";



    @FindBy(xpath="//a[text()='Register']")
    private WebElement registerButton;

    @FindBy(xpath="//div[text()='Logout']")
    private WebElement logoutButton;

    @FindBy(xpath="//input[@placeholder='Search a City ']")
    private WebElement searchCityTextbox;

    @FindBy(xpath="//h5[text()='No City found']")
    private WebElement noCityFoundMessage;

    @FindBy(xpath="//a[text()='Reservations']")
    private WebElement reservationsButton;

    

    // @FindBy(xpath="//li[@id='cityName']")
    // private WebElement selectMatchedCityName;



    
    public HomePage(WebDriver driver){
        System.out.println(driver);
        // driver=this.driver;
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void navigateToHomePage(){
        System.out.println("Driver here is :- "+driver);
        String url="https://qtripdynamic-qa-frontend.vercel.app/";
        SeleniumWrapper.navigate(driver,url );
        // driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
    }

   


    public Boolean isUserLogin() throws InterruptedException{
        //Thread.sleep(2000);
                    return logoutButton.getText().equals("Logout");
        }

    public Boolean isUserLogout(){
            return registerButton.getText().equals("Register");
}


        public Boolean clickRegister() throws InterruptedException{
            click(registerButton, driver);
           // registerButton.click();
            Thread.sleep(1000);
            return true;
        }

        public Boolean clickLogout() throws InterruptedException{
            click(logoutButton, driver);
            //logoutButton.click();
            Thread.sleep(1000);
            return true;
        }

        public void searchCity(String cityName) throws InterruptedException{
            // searchCityTextbox.click();
            
            searchCityTextbox.clear();
            sendKeys(searchCityTextbox, cityName);
           // searchCityTextbox.sendKeys(cityName);
            Thread.sleep(3000);
            if(cityName.contains("abcde")){
                noCityFoundMessage.getText().equals("No City found");
            } 
            // Thread.sleep(3000);
            

        }

        public WebElement getCityLoc(String cityName){
            By s=By.xpath("//*[@id='"+cityName.toLowerCase()+"']");

            return driver.findElement(s);
            
        }

        public boolean assertAutoCompleteText(String cityName) throws InterruptedException{
            return getCityLoc(cityName).getText().equals(cityName);
            
            //driver.getCurrentUrl().contains(Search_Result_ENDPOINT);
        }

        public void selectCity(String cityName) throws InterruptedException{
            getCityLoc(cityName).click();
            //click(getCityLoc, cityName);
            Thread.sleep(3000);

        }
        public void clickReservationButton(){
            //reservationsButton.click();
            click(reservationsButton, driver);
        }
    }

        
    
