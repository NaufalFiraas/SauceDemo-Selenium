package utils;

import org.testng.Assert;

public class Verify {
    public static void verifyEquals(String expected, String actual, String message) {
        if (actual.equals(expected)) {
            ExtentTestListener.getTest().pass(message + "<br>Expected: " + expected + "<br>Actual: " + actual);
        } else {
            Log.error("TEST FAILED - " + message + " | Expected: " + expected + " | Actual: " + actual);
            ExtentTestListener.getTest().fail(message + "<br>Expected: " + expected + "<br>Actual: " + actual);
            Assert.fail(message + " | Expected: " + expected + " | Actual: " + actual);
        }
    }

    public static void verifyContains(String expected, String actual, String message) {
        if (actual.contains(expected)) {
            ExtentTestListener.getTest().pass(message + "<br>Expected: " + expected + "<br>Actual: " + actual);
        } else {
            Log.error("TEST FAILED - " + message + " | Expected: " + expected + " | Actual: " + actual);
            ExtentTestListener.getTest().fail(message + "<br>Expected: " + expected + "<br>Actual: " + actual);
            Assert.fail(message + " | Expected: " + expected + " | Actual: " + actual);
        }
    }
}
