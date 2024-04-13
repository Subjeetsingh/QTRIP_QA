package qtriptest.tests;

import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testCase_02 {

    //private static final WebElement webElement = null;
    static WebDriver driver;


    public static void logStatus(String type, String message, String status) {
		System.out.println(String.format("%s |  %s  |  %s | %s",
				String.valueOf(java.time.LocalDateTime.now()), type, message, status));
	}

    @BeforeSuite()
  public static void createDriver() throws MalformedURLException {
    driver=DriverSingleton.getDriverInstance("ChromeDriver");
    driver.manage().window().maximize();
}

    

@Test(description = "Verify the - Testcase 02", priority = 2 ,groups={"Search and Filter flow"},dataProvider = "searchFilter", dataProviderClass = ExcelDataProvider.class)
public void TestCase02(String cityName, String categoryValue, String durationValue, String expectedFilteredResult, String expectedUnfilteredResult) throws InterruptedException {
    logStatus("Page test", "Navigation to Login page", "started");
    try {
        System.out.println("Driver is: " + driver);
        HomePage homepage = new HomePage(driver);
        AdventurePage adventurepage = new AdventurePage(driver);
        
        logStatus("Page test", "Navigation to Homepage", "started");
        homepage.navigateToHomePage();
        homepage.searchCity(cityName);
        logStatus("Page test", "Search City", "completed");
        
        Thread.sleep(3000); // Replace with explicit wait
        
        homepage.getCityLoc(cityName);
        logStatus("Page test", "Get City Location", "completed");
        
        homepage.assertAutoCompleteText(cityName);
        logStatus("Page test", "Verify assert auto complete functionality", "completed");
        
        homepage.searchCity(cityName);
        logStatus("Page test", "Search City", "completed");
        
        homepage.selectCity(cityName);
        logStatus("Page test", "Select City", "completed");
        
        adventurepage.selectFilterValue(durationValue);
        logStatus("Page test", "Select Filter", "completed");
        
        adventurepage.selectCategoryValue(categoryValue);
        logStatus("Page test", "Select Category", "completed");
        
        adventurepage.verifyFilteredResult(expectedFilteredResult);
        logStatus("Page test", "Verify Category value", "completed");
        
        adventurepage.clearButton();
        logStatus("Page test", "Verify Clear Button functionality", "completed");
        
        adventurepage.verifyUnfilteredResult(expectedUnfilteredResult);
        logStatus("Page test", "Verify the result", "completed");
        // homepage.clickLogout();
		// homepage.isUserLogout();
        
        logStatus("Page test", "Testcase02 successful", "success");
    } catch (Exception e) {
        logStatus("Page test", "Testcase02 Failed", "failed");
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
