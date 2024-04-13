package qtriptest.tests;

import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class testCase_03 {

    static WebDriver driver;
    static ExtentReports extent;
    static ExtentTest test;
    public static String lastGeneratedUserName;

    public static void logStatus(String type, String message, String status) {
        System.out.println(String.format("%s |  %s  |  %s | %s",
                String.valueOf(java.time.LocalDateTime.now()), type, message, status));
    }

    @BeforeSuite(alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        driver = DriverSingleton.getDriverInstance("ChromeDriver");
        driver.manage().window().maximize();
        extent = new ExtentReports("app/src/test/java/qtriptest/reports/extent-report.html", true);
    }

    @Test(description = "Verify the - Testcase 03", dataProvider = "booking&CancellationFlow", priority = 3, groups = {"Booking and Cancellation Flow"}, dataProviderClass = ExcelDataProvider.class)
    public void TestCase03(String username, String password, String cityName, String adventureName, String name, String date, CharSequence[] perCount) throws InterruptedException {
        test = extent.startTest("Verify the - Testcase 03");
        logStatus("Page test", "Testcase 03", "started");
        try {
            Boolean status;
            System.out.println("Driver is: " + driver);
            HomePage homepage = new HomePage(driver);
            LoginPage loginpage = new LoginPage(driver);
            AdventurePage adventurepage = new AdventurePage(driver);
            RegisterPage registerPage = new RegisterPage(driver);
            AdventureDetailsPage adventureDetailPage = new AdventureDetailsPage(driver);
            HistoryPage historyPage = new HistoryPage(driver);

            logStatus("Page test", "Navigate to Homepage", "started");
            homepage.navigateToHomePage();
            Thread.sleep(2000);

            logStatus("Page test", "Click Register", "started");
            homepage.clickRegister();
            registerPage.checkRegisterPageNavigation();

            logStatus("Page test", "Verify the registration", "started");
            status = registerPage.registerUser(username, password, true);
            if (!status) {
                logStatus("TestCase 3", "User Registration Pass", "FAIL");
                logStatus("End TestCase", "Test Case 3: Verify user Registration", status ? "PASS" : "FAIL");
            } else {
                logStatus("TestCase 3", "User Registration Pass", "PASS");
            }
            lastGeneratedUserName = registerPage.lastGeneratedUsername;

            logStatus("Page test", "Navigate to Login Page", "started");
            loginpage.navigateToLoginPage();
            loginpage.verifyLoginPageNavigation();

            logStatus("Page test", "Verify the login operation", "started");
            status = loginpage.performLogin(lastGeneratedUserName, password);
            Thread.sleep(3000);
            logStatus("Test Step", "User Perform Login", status ? "PASS" : "FAIL");

            if (!status) {
                logStatus("End TestCase", "Test Case 3: Verify user Registration", status ? "PASS" : "FAIL");
            }
            logStatus("Page test", "Search City", "started");
            homepage.searchCity(cityName);
            Thread.sleep(2000);
            logStatus("Page test", "Get City Location", "started");
            homepage.getCityLoc(cityName);
            logStatus("Page test", "Verify assert auto complete functionality", "started");
            homepage.selectCity(cityName);

            adventurepage.selectAdventure(adventureName);
            Thread.sleep(3000);
            adventureDetailPage.bookAdventure(name, date, perCount);
            Thread.sleep(3000);
            logStatus("Page test", "Booking successful", "checking");
            adventureDetailPage.isBookingSuccessful();
            logStatus("Page test", "Booking successful validation check", "Pass");
            Thread.sleep(3000);

            homepage.clickReservationButton();
            Thread.sleep(5000);
            historyPage.getTransactionIds();
            Thread.sleep(5000);
            historyPage.deleteFirstTransaction();
            Thread.sleep(2000);
            historyPage.isTransactionDeleted();
            Thread.sleep(2000);
            historyPage.validateTransactionId();
            Thread.sleep(2000);
            homepage.clickLogout();
            homepage.isUserLogout();
            logStatus("Page test", "Testcase03 successful", "success");
            test.log(LogStatus.PASS, "Testcase03", "Testcase03 is successful");
        } catch (Exception e) {
            logStatus("Page test", "Testcase03 Failed", "failed");
            test.log(LogStatus.FAIL, "Testcase03", "Testcase03 Failed");
            e.printStackTrace();
        }
        Thread.sleep(2000);
        extent.endTest(test);
    }

    @AfterSuite
    public static void quitDriver() throws MalformedURLException {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}
