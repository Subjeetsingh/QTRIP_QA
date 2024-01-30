package qtriptest.pages;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {
    RemoteWebDriver driver;


    @FindBy(xpath="//a[text()='Register']")
    private WebElement registerButton;

    @FindBy(xpath="//div[text()='Logout']")
    private WebElement logoutButton;

    
    public HomePage(RemoteWebDriver driver){
        System.out.println(driver);
        // driver=this.driver;
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    public void navigateToHomePage(){
        System.out.println("Driver here is :- "+driver);
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
    }


    public Boolean isUserLogin(){
                    return logoutButton.getText().equals("Logout");
        }

    public Boolean isUserLogout(){
            return registerButton.getText().equals("Register");
}


        public Boolean clickRegister() throws InterruptedException{
            registerButton.click();
            Thread.sleep(3000);
            return true;
        }

        public Boolean clickLogout() throws InterruptedException{
            logoutButton.click();
            Thread.sleep(3000);
            return true;
        }
        
    }
