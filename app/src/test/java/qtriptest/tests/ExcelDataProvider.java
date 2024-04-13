package qtriptest.tests;
import qtriptest.DP;
import org.testng.annotations.DataProvider;
public class ExcelDataProvider {

    @DataProvider(name="userOnboard")
    public String[][] userOnboardData(){
        return DP.readExcelFile("TestCase01");
    }

    @DataProvider(name="searchFilter")
    public String[][] searchFilterData(){
        return DP.readExcelFile("TestCase02");
    }

        @DataProvider(name="booking&CancellationFlow")
    public String[][] bookingCancellationFlow(){
        return DP.readExcelFile("TestCase03");
    }
    @DataProvider(name="bookinghistory")
    public String[][] bookingHistoryFlow(){
        
        return DP.readExcelFile("TestCase04");
    }
}
    
    

    

