package qtriptest.pages;

import java.util.UUID;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterPage {

    RemoteWebDriver driver;

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

    public RegisterPage(RemoteWebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void navigateToRegisterPage(){
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");
    }

    public boolean checkRegisterPageNavigation(){
        return driver.getCurrentUrl().contains(Register_PAGE_ENDPOINT);
    }

    public boolean registerUser(String username , String password, Boolean generateRandomUsername) throws InterruptedException{

      if (generateRandomUsername)
      test_data_username = username+UUID.randomUUID().toString();
      else
        test_data_username = username;

        emailTextBox.sendKeys(test_data_username);
        String test_data_password = password;
        passwordTextBox.sendKeys(test_data_password);
        confirmPasswordTextBox.sendKeys(test_data_password);
        registerNowButton.click();

        Thread.sleep(3000);

        this.lastGeneratedUsername = test_data_username;
        return true;
    }
    
}
