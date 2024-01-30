package qtriptest.tests;

import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
// import QKART_TestNG.pages.Register;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class testCase_01 {

    static RemoteWebDriver driver;

    // LoginPage loginpage;
	// RegisterPage registerPage;
	// HomePage homepage;
	public static String lastGeneratedUserName;


    // public void setLoginpage(LoginPage loginpage) {
    //     this.loginpage = loginpage;
    // }


    public static void logStatus(String type, String message, String status) {
		System.out.println(String.format("%s |  %s  |  %s | %s",
				String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	}


    @BeforeSuite(alwaysRun = true)
	public static void createDriver() throws MalformedURLException {
		logStatus("driver", "Initializing driver", "Started");
		final DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
		logStatus("driver", "Initializing driver", "Success");
		driver.manage().window().maximize();
	}


    @Test(description = "Verify the - Testcase 01" , dataProvider = "userOnboard", dataProviderClass = ExcelDataProvider.class)
    //@Parameters({"username", "password","confirmpassword"})
	public  void TestCase01(String username, String password) throws InterruptedException {
		logStatus("Page test", "navigation to Login page", "started");
		try {
			System.out.println("Driver is :- "+driver);
			HomePage homepage=new HomePage(driver);
			
			Boolean status;
			logStatus("Page test", "navigation to Register page", "started");
			
			homepage.navigateToHomePage();
			Thread.sleep(5000);
			homepage.clickRegister();
			RegisterPage registerPage=new RegisterPage(driver);
			registerPage.checkRegisterPageNavigation();
			logStatus("Page test", "Verify the registration ", "started");
			status=registerPage.registerUser(username, password,true);	
			
			if (!status) {
				logStatus("TestCase 1", "Test Case Pass. User Registration Pass", "FAIL");
				logStatus("End TestCase", "Test Case 1: Verify user Registration : ", status ? "PASS" : "FAIL");
			} else {
				logStatus("TestCase 1", "Test Case Pass. User Registration Pass", "PASS");
			}
			lastGeneratedUserName = registerPage.lastGeneratedUsername;
			LoginPage loginpage = new LoginPage(driver);
            loginpage.navigateToLoginPage();
			loginpage.verifyLoginPageNavigation();
			logStatus("Page test", "Verify the login operation ", "started");
			status=loginpage.performLogin(lastGeneratedUserName, password);
			
			logStatus("Test Step", "User Perform Login: ", status ? "PASS" : "FAIL");
        if (!status) {
            logStatus("End TestCase", "Test Case 1: Verify user Registration : ", status ? "PASS" : "FAIL");
        }
			homepage.isUserLogin();
			homepage.clickLogout();
			homepage.isUserLogout();
					
			logStatus("Page test", "Testcase01 successfull", "success");
		} catch (Exception e) {
			logStatus("Page test", "Testcase01 Failed", "failed");
			e.printStackTrace();
		}
	}


	@AfterClass(enabled=true)
    public static void quitDriver() {
		driver.close();
        driver.quit();
        logStatus("driver", "Quitting driver", "Success");
	}



}
