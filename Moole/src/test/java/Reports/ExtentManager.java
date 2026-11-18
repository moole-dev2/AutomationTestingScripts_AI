
package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter spark =
                new ExtentSparkReporter("test-output/ExtentReport.html");

            spark.config().setDocumentTitle("Automation Test Report");
            spark.config().setReportName("Selenium Automation Execution");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Automation Tool", "Selenium");
            extent.setSystemInfo("Language", "Java");
            extent.setSystemInfo("Framework", "TestNG");
            extent.setSystemInfo("Browser", "Chrome");
        }

        return extent;
    }
}
