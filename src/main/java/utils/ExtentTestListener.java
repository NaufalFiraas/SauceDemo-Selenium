package utils;

import base.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class ExtentTestListener implements ITestListener {

    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static ExtentTest getTest() {
        return test.get();
    }

    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = ExtentReportManager.getInstance().createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, result.getThrowable().getMessage());
        Object currentClass = result.getInstance();
        WebDriver driver = ((BaseTest) currentClass).getDriver();
        if (driver != null) {
            try {
                String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
                String screenshotName = new File(screenshotPath).getName();
                String relativePath = "screenshots/" + screenshotName;
                test.get().addScreenCaptureFromPath(relativePath);
            } catch (IOException e) {
                test.get().log(Status.WARNING, "Screenshot failed: " + e.getMessage());
            }
        } else {
            test.get().log(Status.WARNING, "Driver was null, screenshot skipped");
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.getInstance().flush();
    }
}
