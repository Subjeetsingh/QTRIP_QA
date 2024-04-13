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
public class testCase_04 {

    static WebDriver driver;

    public static String lastGeneratedUserName;


    public static void logStatus(String type, String message, String status) {
        System.out.println(String.format("%s |  %s  |  %s | %s",
                String.valueOf(java.time.LocalDateTime.now()), type, message, status));
    }

    @BeforeSuite(alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
      driver=DriverSingleton.getDriverInstance("ChromeDriver");
      driver.manage().window().maximize();
  }





    @Test(description = "Verify the - Testcase 04" , dataProvider = "bookinghistory",groups={"Reliability Flow"},priority = 4, dataProviderClass = ExcelDataProvider.class)
    public  void TestCase04(String username, String password,String dataset1,String dataset2,String dataset3) throws InterruptedException {
       logStatus("Page test", "navigation to Homepage page", "started");
       ArrayList<String> revData=new ArrayList<>();
       revData.add(dataset1);
       revData.add(dataset2);
       revData.add(dataset3);
       


            try{
               Boolean status;
               System.out.println("Driver is :- "+driver);
           HomePage homepage=new HomePage(driver);
           LoginPage loginpage=new LoginPage(driver);
           AdventurePage adventurepage=new AdventurePage(driver);
           RegisterPage registerPage=new RegisterPage(driver);
           AdventureDetailsPage adventureDetailPage=new AdventureDetailsPage(driver);
           HistoryPage historyPage=new HistoryPage(driver);
           logStatus("Page test", "navigation to Homepage", "started");
           homepage.navigateToHomePage();
           Thread.sleep(6000);
           homepage.clickRegister();
           registerPage.checkRegisterPageNavigation();
           logStatus("Page test", "Verify the registration ", "started");
           status=registerPage.registerUser(username, password,true);	
           
           if (!status) {
               logStatus("TestCase 4", "Test Case Pass. User Registration Pass", "FAIL");
               logStatus("End TestCase", "Test Case 4: Verify user Registration : ", status ? "PASS" : "FAIL");
           } else {
               logStatus("TestCase 4", "Test Case Pass. User Registration Pass", "PASS");
           }
           lastGeneratedUserName = registerPage.lastGeneratedUsername;
           loginpage.navigateToLoginPage();
           loginpage.verifyLoginPageNavigation();
           logStatus("Page test", "Verify the login operation ", "started");
           status=loginpage.performLogin(lastGeneratedUserName, password);
           
           logStatus("Test Step", "User Perform Login: ", status ? "PASS" : "FAIL");
       if (!status) {
           logStatus("End TestCase", "Test Case 4: Verify user Registration : ", status ? "PASS" : "FAIL");
       }
       logStatus("Page test", "Search City", "started");

       CharSequence[] numberOfCounts= new CharSequence[2];
           
       for(int i=0;i<revData.size();i++){

        String[] rev= revData.get(i).split(";");
         
           homepage.searchCity(rev[0]);
           Thread.sleep(3000);
           logStatus("Page test", "Get City Location", "started");
           homepage.getCityLoc(rev[0]);
           logStatus("Page test", "Verify assert auto complete functionality ", "started");
           homepage.selectCity(rev[0]);
           logStatus("Page test", "Select City ", "started");
           //homepage.selectCity(cityName);
           Thread.sleep(5000);
           adventurepage.selectAdventure(rev[1]);
           Thread.sleep(5000);
           numberOfCounts[0]=rev[4];

           adventureDetailPage.bookAdventure(rev[2], rev[3], numberOfCounts);
           Thread.sleep(5000);

           historyPage.clickHomeButton();
           Thread.sleep(2000);
           }
        //    adventureDetailPage.isBookingSuccessful();
        //    Thread.sleep(5000);
           homepage.clickReservationButton();
           Thread.sleep(5000);
           historyPage.getTransactionIds();
           Thread.sleep(5000);
           historyPage.validateAllTransactionIdsPresent();

			homepage.clickLogout();
			homepage.isUserLogout();





            logStatus("Page test", "Testcase04 successfull", "success");


        } catch (Exception e) {
            logStatus("Page test", "Testcase04 Failed", "failed");
            e.printStackTrace();
       }
    }

}
