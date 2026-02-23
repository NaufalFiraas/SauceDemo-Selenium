package utils;

public class Verify {
    public static void verifyEquals(String expected, String actual, String message) {
        if (actual.equals(expected)) {
            ExtentTestListener.getTest().pass(message + "<br>Expected: " + expected + "<br>Actual: " + actual);
        } else {
            ExtentTestListener.getTest().fail(message + "<br>Expected: " + expected + "<br>Actual: " + actual);
        }
    }
}
