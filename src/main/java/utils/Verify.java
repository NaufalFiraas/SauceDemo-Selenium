package utils;

import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;

public class Verify {
    public static void verifyEquals(String expected, String actual, String message) {
        if (actual.equals(expected)) {
            ExtentTest extentTest = ExtentTestListener.getTest();
            if (extentTest != null) {
                extentTest.pass(message + "<br>Expected: " + expected + "<br>Actual: " + actual);
                Log.info("TEST PASSED - " + message + " | Expected: " + expected + " | Actual: " + actual);
            }
        } else {
            ExtentTest extentTest = ExtentTestListener.getTest();
            if (extentTest != null) {
                Log.error("TEST FAILED - " + message + " | Expected: " + expected + " | Actual: " + actual);
                extentTest.fail(message + "<br>Expected: " + expected + "<br>Actual: " + actual);
                Assert.fail(message + " | Expected: " + expected + " | Actual: " + actual);
            }
        }
    }

    public static void verifyContains(String expected, String actual, String message) {
        if (actual.contains(expected)) {
            ExtentTest extentTest = ExtentTestListener.getTest();
            if (extentTest != null) {
                ExtentTestListener.getTest().pass(message + "<br>Expected: " + expected + "<br>Actual: " + actual);
                Log.info("TEST PASSED - " + message + " | Expected: " + expected + " | Actual: " + actual);
            }
        } else {
            ExtentTest extentTest = ExtentTestListener.getTest();
            if (extentTest != null) {
                Log.error("TEST FAILED - " + message + " | Expected: " + expected + " | Actual: " + actual);
                extentTest.fail(message + "<br>Expected: " + expected + "<br>Actual: " + actual);
                Assert.fail(message + " | Expected: " + expected + " | Actual: " + actual);
            }

        }
    }

    public static void verifyTrue(boolean condition, String message) {
        if (condition) {
            ExtentTest extentTest = ExtentTestListener.getTest();
            if (extentTest != null) {
                extentTest.pass(message + "<br>Status: Passed");
            }
        } else {
            ExtentTest extentTest = ExtentTestListener.getTest();
            if (extentTest != null) {
                Log.error("TEST FAILED - " + message);
                extentTest.fail(message + "<br>Status: Failed");
                Assert.fail(message + " | Status: Failed");
            }

        }
    }
}
