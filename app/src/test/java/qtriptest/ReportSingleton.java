package qtriptest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportSingleton {

    private static ReportSingleton instance;
    private ExtentReports extent;
    private ExtentTest test;

    

    public ReportSingleton() {
        // Define the path where you want to generate the report
        extent = new ExtentReports("test-report.html", true);
    }

    public static ReportSingleton getInstance() {
        if (instance == null) {
            instance = new ReportSingleton();
        }
        return instance;
    }

    //start
    public void startTest(String testName) {
        test = extent.startTest(testName);
    }

    //log messsage
    public void log(LogStatus status, String message) {
        test.log(status, message);
    }

    //end
    public void endTest() {
        extent.endTest(test);
    }

    //flush the report
    public void flushReport() {
        extent.flush();
    }
}