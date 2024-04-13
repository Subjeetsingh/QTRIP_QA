package qtriptest.pages;

import qtriptest.SeleniumWrapper;
import java.util.UUID;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterPage extends SeleniumWrapper {
    static WebDriver driver;

    public String lastGeneratedUsername = "";

    String test_data_username;


    private final static String Register_PAGE_ENDPOINT="register/";

    @FindBy(xpath="//input[@name='email']")
    private WebElement emailTextBox;

    @FindBy(xpath="//input[@name='password']")
    private WebElement passwordTextBox;

    @FindBy(xpath="//input[@name='confirmpassword']")
    private WebElement confirmPasswordTextBox;

    @FindBy(xpath="//button[text()='Register Now']")
    private WebElement registerNowButton;

    public RegisterPage(WebDriver driver2){
        this.driver=driver2;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver2, 20), this);
    }

    public void navigateToRegisterPage(){
        //driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");
        String url="https://qtripdynamic-qa-frontend.vercel.app/register/";
        SeleniumWrapper.navigate(driver,url );
    }

    public boolean checkRegisterPageNavigation(){
        return driver.getCurrentUrl().contains(Register_PAGE_ENDPOINT);
    }

    public boolean registerUser(String username , String password, Boolean generateRandomUsername) throws InterruptedException{

      if (generateRandomUsername)
      test_data_username = username+UUID.randomUUID().toString();
      else
      Thread.sleep(2000);
        //test_data_username = username;
        sendKeys(emailTextBox, test_data_username );

       // emailTextBox.sendKeys(test_data_username);
       String test_data_password = password;
       sendKeys(passwordTextBox, test_data_password);
        // passwordTextBox.sendKeys(test_data_password);

       sendKeys(confirmPasswordTextBox, test_data_password);
     //   confirmPasswordTextBox.sendKeys(test_data_password);
     
        click(registerNowButton, driver);

        Thread.sleep(3000);

        this.lastGeneratedUsername = test_data_username;
        return true;
    }
    
}
