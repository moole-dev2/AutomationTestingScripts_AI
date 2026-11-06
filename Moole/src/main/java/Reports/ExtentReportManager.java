
package Reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {

        if (extent == null) {

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());

            String reportFolder = System.getProperty("user.dir")
                    + "/target/extent-reports";

            new File(reportFolder).mkdirs();

            String reportPath = reportFolder
                    + "/MooleAutomationReport_" + timestamp + ".html";

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);

            sparkReporter.config().setDocumentTitle(
                    "Moole AI Automation Report");

            sparkReporter.config().setReportName(
                    "Moole AI Selenium Test Execution");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Project", "Moole AI");
            extent.setSystemInfo("Automation Tool", "Selenium WebDriver");
            extent.setSystemInfo("Programming Language", "Java");
            extent.setSystemInfo("Test Framework", "TestNG");
            extent.setSystemInfo("Browser", "Chrome");
        }

        return extent;
    }
}
