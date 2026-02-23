package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String captureScreenshot(WebDriver driver, String testName) throws IOException {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(new Date());
        String path = System.getProperty("user.dir") + "/reports/screenshots/" + testName + "_" + timestamp + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            Path screenshotDir = Path.of(System.getProperty("user.dir"), "reports", "screenshots");
            Files.createDirectories(screenshotDir);
            Path destination = screenshotDir.resolve(testName + "_" + timestamp + ".png");
            Files.copy(src.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            return destination.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}
