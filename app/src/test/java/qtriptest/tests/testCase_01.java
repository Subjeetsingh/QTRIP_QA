package qtriptest.tests;

import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
// import QKART_TestNG.pages.Register;
import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testCase_01 {

    static WebDriver driver;

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

	@BeforeSuite(alwaysRun=true,enabled = true)
	public static void createDriver() throws MalformedURLException {
	  driver=DriverSingleton.getDriverInstance("ChromeDriver");
	  driver.manage().window().maximize();
  }





  @Test(description = "Verify the - Testcase 01",priority = 1,groups={"Login Flow"}, dataProvider = "userOnboard", dataProviderClass = ExcelDataProvider.class)
  public void TestCase01(String username, String password) throws InterruptedException {
	  logStatus("Page test", "Navigation to Login page", "started");
	  try {
		  System.out.println("Driver is: " + driver);
		  HomePage homepage = new HomePage(driver);
		  Boolean status;
		  
		  logStatus("Page test", "Navigation to Register page", "started");
		  homepage.navigateToHomePage();
		  Thread.sleep(5000);
		  
		  homepage.clickRegister();
		  RegisterPage registerPage = new RegisterPage(driver);
		  registerPage.checkRegisterPageNavigation();
		  logStatus("Page test", "Verify the registration", "started");
		  status = registerPage.registerUser(username, password, true);
  
		  if (!status) {
			  logStatus("TestCase 1", "Test Case Pass. User Registration Pass", "FAIL");
			  logStatus("End TestCase", "Test Case 1: Verify user Registration", status ? "PASS" : "FAIL");
		  } else {
			  logStatus("TestCase 1", "Test Case Pass. User Registration Pass", "PASS");
		  }
		  lastGeneratedUserName = registerPage.lastGeneratedUsername;
		  
		  LoginPage loginpage = new LoginPage(driver);
		  loginpage.navigateToLoginPage();
		  loginpage.verifyLoginPageNavigation();
		  logStatus("Page test", "Verify the login operation", "started");
		  status = loginpage.performLogin(lastGeneratedUserName, password);
		  
		  logStatus("Test Step", "User Perform Login", status ? "PASS" : "FAIL");
		  if (!status) {
			  logStatus("End TestCase", "Test Case 1: Verify user Registration", status ? "PASS" : "FAIL");
		  }
		  
		  homepage.isUserLogin();
		  homepage.clickLogout();
		  homepage.isUserLogout();
		  
		  logStatus("Page test", "Testcase01 successful", "success");
	  } catch (Exception e) {
		  logStatus("Page test", "Testcase01 Failed", "failed");
		  e.printStackTrace();
	  }	  
	  Thread.sleep(2000);

  }
  @AfterSuite
  public static void quitDriver() throws MalformedURLException {
   if (driver != null) {
	   driver.quit();     }
   }
}