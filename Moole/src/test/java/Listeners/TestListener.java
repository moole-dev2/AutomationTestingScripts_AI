
package Listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Reports.ExtentReportManager;

public class TestListener implements ITestListener {

    private static ExtentReports extent;

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        extent = ExtentReportManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest = extent.createTest(
                result.getTestClass().getName()
                + " - "
                + result.getMethod().getMethodName()
        );

        test.set(extentTest);

        test.get().log(Status.INFO, "Test execution started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test passed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail("Test execution failed");

        if (result.getThrowable() != null) {
            test.get().fail(result.getThrowable());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test execution skipped");

        if (result.getThrowable() != null) {
            test.get().skip(result.getThrowable());
        }
    }

    @Override
    public void onFinish(ITestContext context) {

        // Flush the report after all tests in this context finish
        extent.flush();

        test.remove();
    }
}
