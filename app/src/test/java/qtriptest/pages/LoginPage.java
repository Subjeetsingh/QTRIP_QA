package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.TestNGException;


public class LoginPage {

    RemoteWebDriver driver;
    //String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";


    private final static String LOGIN_PAGE_ENDPOINT="login/";

    @FindBy(name="email")
    private WebElement emailTextBox;

    @FindBy(name="password")
    private WebElement passwordTextBox;

    @FindBy(xpath="//button[text()='Login to QTrip']")
    private WebElement loginButton;


    public LoginPage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    

    public void navigateToLoginPage(){
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/login/");
    }

    public boolean verifyLoginPageNavigation(){
        return driver.getCurrentUrl().contains(LOGIN_PAGE_ENDPOINT);
    }

    public Boolean performLogin(String username ,String password) throws InterruptedException{
        emailTextBox.sendKeys(username);
        passwordTextBox.sendKeys(password);
        loginButton.click();

        Thread.sleep(3000);
        return true;

    }

    
}
