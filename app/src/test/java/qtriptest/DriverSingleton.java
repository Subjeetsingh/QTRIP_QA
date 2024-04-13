package qtriptest;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class DriverSingleton {

    public static WebDriver driver=null;

    private DriverSingleton(){}

    public static WebDriver getDriverInstance(String browserType) throws MalformedURLException{
        if(driver==null){

            final DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(BrowserType.CHROME);
            capabilities.setCapability("idleTimeout", 2000);
            RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities); // This line creates a new instance of RemoteWebDriver in each test class
            switch(browserType){
                case "CHROME":
                //WebDriverManager.chromedriver().setup();
                driver=new ChromeDriver();
                break;

                case "FIREFOX":
                //WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
                break;
            }
            return driver;
        }
        return driver;

    }

    

    public static void closeDriverInstance(){
        driver.quit();
    }

    


// @AfterTest(enabled=true)
//     public static void quitDriver(RemoteWebDriver driver) {
//         driver.close();
//         driver.quit();
//         logStatus("driver", "Quitting driver", "Success");
// 	}

    // public static void logStatus(String type, String message, String status) {
	// 	System.out.println(String.format("%s |  %s  |  %s | %s",
	// 			String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	// }
}