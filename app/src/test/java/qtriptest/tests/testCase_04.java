package qtriptest.tests;

import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class testCase_04 {

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

    @Test(description = "Verify the - Testcase 04", groups = {"Reliability Flow"}, priority = 4, dataProvider = "bookinghistory", dataProviderClass = ExcelDataProvider.class)
    public void TestCase04(String username, String password, String dataset1, String dataset2, String dataset3) throws InterruptedException {
        test = extent.startTest("Verify the - Testcase 04");
        logStatus("Page test", "Testcase 04", "started");
        ArrayList<String> revData = new ArrayList<>();
        revData.add(dataset1);
        revData.add(dataset2);
        revData.add(dataset3);

        try {
            Boolean status;
            System.out.println("Driver is :- " + driver);
            HomePage homepage = new HomePage(driver);
            LoginPage loginpage = new LoginPage(driver);
            AdventurePage adventurepage = new AdventurePage(driver);
            RegisterPage registerPage = new RegisterPage(driver);
            AdventureDetailsPage adventureDetailPage = new AdventureDetailsPage(driver);
            HistoryPage historyPage = new HistoryPage(driver);
            logStatus("Page test", "Navigation to Homepage", "started");
            homepage.navigateToHomePage();
            Thread.sleep(6000);
            homepage.clickRegister();
            registerPage.checkRegisterPageNavigation();
            logStatus("Page test", "Verify the registration", "started");
            status = registerPage.registerUser(username, password, true);

            if (!status) {
                logStatus("TestCase 4", "Test Case Pass. User Registration Pass", "FAIL");
                logStatus("End TestCase", "Test Case 4: Verify user Registration: ", status ? "PASS" : "FAIL");
            } else {
                logStatus("TestCase 4", "Test Case Pass. User Registration Pass", "PASS");
            }
            lastGeneratedUserName = registerPage.lastGeneratedUsername;
            loginpage.navigateToLoginPage();
            loginpage.verifyLoginPageNavigation();
            logStatus("Page test", "Verify the login operation", "started");
            status = loginpage.performLogin(lastGeneratedUserName, password);

            logStatus("Test Step", "User Perform Login: ", status ? "PASS" : "FAIL");
            if (!status) {
                logStatus("End TestCase", "Test Case 4: Verify user Registration: ", status ? "PASS" : "FAIL");
            }
            logStatus("Page test", "Search City", "started");
            CharSequence[] numberOfCounts = new CharSequence[2];

            for (int i = 0; i < revData.size(); i++) {
                String[] rev = revData.get(i).split(";");
                homepage.searchCity(rev[0]);
                Thread.sleep(3000);
                logStatus("Page test", "Get City Location", "started");
                homepage.getCityLoc(rev[0]);
                logStatus("Page test", "Verify assert auto complete functionality", "started");
                homepage.selectCity(rev[0]);
                logStatus("Page test", "Select City", "started");
                Thread.sleep(5000);
                adventurepage.selectAdventure(rev[1]);
                Thread.sleep(5000);
                numberOfCounts[0] = rev[4];
                adventureDetailPage.bookAdventure(rev[2], rev[3], numberOfCounts);
                Thread.sleep(5000);
                historyPage.clickHomeButton();
                Thread.sleep(2000);
            }

            homepage.clickReservationButton();
            Thread.sleep(5000);
            historyPage.getTransactionIds();
            Thread.sleep(5000);
            historyPage.validateAllTransactionIdsPresent();
            homepage.clickLogout();
            homepage.isUserLogout();
            logStatus("Page test", "Testcase 04 successful", "success");
            test.log(LogStatus.PASS, "Testcase 04", "Testcase 04 is successful");
        } catch (Exception e) {
            logStatus("Page test", "Testcase 04 Failed", "failed");
            test.log(LogStatus.FAIL, "Testcase 04", "Testcase 04 Failed");
            e.printStackTrace();
        }
        extent.endTest(test);
    }
}
