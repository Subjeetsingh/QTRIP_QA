package qtriptest.tests;
import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class testCase_01 {

    static WebDriver driver;
    static ExtentReports extent;
    static ExtentTest test;
	public static String lastGeneratedUserName;
	static ReportSingleton report;

	public static void logStatus(String type, String message, String status) {
		System.out.println(String.format("%s |  %s  |  %s | %s",
				String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	}
	

    @BeforeSuite(alwaysRun=true,enabled = true)
    public static void createDriver() throws MalformedURLException {
        driver = DriverSingleton.getDriverInstance("ChromeDriver");
        driver.manage().window().maximize();
		report = new ReportSingleton();
		// Initialize ExtentReports
		extent = new ExtentReports("app/src/test/java/qtriptest/extent-report.html", true);


		
        
        // Initialize ExtentReports instance
        //extent = new ExtentReports("test-report.html", true);
    }

    @Test(description = "Verify the - Testcase 01",priority = 1,groups={"Login Flow"}, dataProvider = "userOnboard", dataProviderClass = ExcelDataProvider.class)
    public void TestCase01(String username, String password) throws InterruptedException {
        // Start a new test
		test = extent.startTest("Verify the - Testcase 01");


        logStatus("Page test", "Navigation to Login page", "started");
        try {
            System.out.println("Driver is: " + driver);
            HomePage homepage = new HomePage(driver);
            Boolean status;
            
            logStatus("Page test", "Navigation to Register page", "started");
            homepage.navigateToHomePage();
            test.log(LogStatus.PASS, "Navigation to Home Page", "Navigation is successful");

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
                test.log(LogStatus.PASS, "User Registration", "User registration is successful");
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
            } else {
                test.log(LogStatus.PASS, "User Login", "User login is successful");
            }
            
            homepage.isUserLogin();
            homepage.clickLogout();
            homepage.isUserLogout();
            
            logStatus("Page test", "Testcase01 successful", "success");
            test.log(LogStatus.PASS, "Testcase01", "Testcase01 is successful");
        } catch (Exception e) {
            logStatus("Page test", "Testcase01 Failed", "failed");
            e.printStackTrace();
        }
        Thread.sleep(2000);
        
        // End the test
        extent.endTest(test);
    }



	@AfterSuite
    public static void quitDriver() throws MalformedURLException {
        if (driver != null) {
            driver.quit();
        }
        
        // Flush the ExtentReports instance to write the report to the file
        extent.flush();
    }
}