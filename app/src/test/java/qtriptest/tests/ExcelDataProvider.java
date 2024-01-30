package qtriptest.tests;
import qtriptest.DP;
import org.testng.annotations.DataProvider;
public class ExcelDataProvider {

    @DataProvider(name="userOnboard")
    public String[][] userOnboardData(){
        return DP.readExcelFile("TestCase01");
    }
    
    }

    

