package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class LoginPage extends SeleniumWrapper{

    static WebDriver driver;
    //String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";


    private final static String LOGIN_PAGE_ENDPOINT="login/";

    @FindBy(name="email")
    private WebElement emailTextBox;

    @FindBy(name="password")
    private WebElement passwordTextBox;

    @FindBy(xpath="//button[text()='Login to QTrip']")
    private WebElement loginButton;


    public LoginPage(WebDriver driver2){
        this.driver=driver2;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver2, 20), this);
    }

    

    public void navigateToLoginPage(){
        String url="https://qtripdynamic-qa-frontend.vercel.app/pages/login/";
        SeleniumWrapper.navigate(driver,url );
    }

    public boolean verifyLoginPageNavigation(){
        return driver.getCurrentUrl().contains(LOGIN_PAGE_ENDPOINT);
    }

    public Boolean performLogin(String username ,String password) throws InterruptedException{
try{
        sendKeys(emailTextBox, username);
        //emailTextBox.sendKeys(username);
        sendKeys(passwordTextBox, password);
      //  passwordTextBox.sendKeys(password);
     // Thread.sleep(2000);
      click(loginButton, driver);
      // loginButton.click();

        

    }
    catch(NoSuchElementException e){
        e.printStackTrace();
    }
    Thread.sleep(1000);
return true;
}

    
}
