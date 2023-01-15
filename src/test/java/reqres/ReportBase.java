package reqres;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;

public class ReportBase {
    public ExtentSparkReporter extentSparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    public void generateReport(){
        extentReports = new ExtentReports();
        extentSparkReporter = new ExtentSparkReporter("target/Spark.html");

        extentReports.attachReporter(extentSparkReporter);
        extentSparkReporter.config().setDocumentTitle("Reqres.in");
        extentSparkReporter.config().setReportName("Reqres.in API Testing");
        extentSparkReporter.config().setTheme(Theme.DARK);
    }
    public void generateHtml(ITestResult result) throws Exception{
        if (result.getStatus() == ITestResult.FAILURE){
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
        } else if(result.getStatus() == ITestResult.SKIP){
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }

    }
    public void flush(){
        extentReports.flush();
    }
}
