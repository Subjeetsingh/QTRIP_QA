package qtriptest.tests;

import qtriptest.DriverSingleton;
import qtriptest.ReportSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testCase_02 {

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
        extent = new ExtentReports("app/src/test/java/qtriptest/reports/extent-report.html", true);
    }

    @Test(description = "Verify the - Testcase 02", priority = 2 ,groups={"Search and Filter flow"},dataProvider = "searchFilter", dataProviderClass = ExcelDataProvider.class)
    public void TestCase02(String cityName, String categoryValue, String durationValue, String expectedFilteredResult, String expectedUnfilteredResult) throws InterruptedException {
        test = extent.startTest("Verify the - Testcase 02");
        logStatus("Page test", "Navigation to Login page", "started");
        try {
            System.out.println("Driver is: " + driver);
            HomePage homepage = new HomePage(driver);
            AdventurePage adventurepage = new AdventurePage(driver);
            
            logStatus("Page test", "Navigation to Homepage", "started");
            homepage.navigateToHomePage();
            homepage.searchCity(cityName);
            test.log(LogStatus.PASS, "Search City", "completed");
            
            Thread.sleep(3000); // Replace with explicit wait
            
            homepage.getCityLoc(cityName);
            test.log(LogStatus.PASS, "Get City Location", "completed");
            
            homepage.assertAutoCompleteText(cityName);
            test.log(LogStatus.PASS, "Verify assert auto complete functionality", "completed");
            
            homepage.searchCity(cityName);
            test.log(LogStatus.PASS, "Search City", "completed");
            
            homepage.selectCity(cityName);
            test.log(LogStatus.PASS, "Select City", "completed");
            
            adventurepage.selectFilterValue(durationValue);
            test.log(LogStatus.PASS, "Select Filter", "completed");
            
            adventurepage.selectCategoryValue(categoryValue);
            test.log(LogStatus.PASS, "Select Category", "completed");
            
            adventurepage.verifyFilteredResult(expectedFilteredResult);
            test.log(LogStatus.PASS, "Verify Category value", "completed");
            
            adventurepage.clearButton();
            test.log(LogStatus.PASS, "Verify Clear Button functionality", "completed");
            
            adventurepage.verifyUnfilteredResult(expectedUnfilteredResult);
            test.log(LogStatus.PASS, "Verify the result", "completed");
            
            logStatus("Page test", "Testcase02 successful", "success");
            test.log(LogStatus.PASS, "Testcase02", "Testcase02 is successful");
        } catch (Exception e) {
            logStatus("Page test", "Testcase02 Failed", "failed");
            test.log(LogStatus.FAIL, "Testcase02", "Testcase02 Failed");
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