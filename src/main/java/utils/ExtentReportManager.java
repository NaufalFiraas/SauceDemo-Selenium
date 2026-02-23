package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(new Date());
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport" + timestamp + ".html");
            spark.config().setDocumentTitle("SauceDemo Automation");
            spark.config().setReportName("SauceDemo Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
}
