package utilities.chainTestReport;

import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChainTestScreenshotUtility {

    // Screenshot as BYTE
    public static void chainTestScreenshotByte(WebDriver driver) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        ChainTestListener.embed(screenshot, "image/png");
    }

    // Screenshot as BASE64
    public static void chainTestScreenshotBase64(WebDriver driver) {
        String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        ChainTestListener.embed(screenshot, "image/png");
    }

    // Screenshot saved as FILE
    public static void chainTestScreenshotFile(WebDriver driver, String filename) throws IOException {

        String timestamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(new Date());

        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        File folder = new File(System.getProperty("user.dir") + File.separator + "ScreenShot");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File destinationFile = new File(folder, filename + "_" + timestamp + ".png");

        FileHandler.copy(sourceFile, destinationFile);

        ChainTestListener.embed(destinationFile, "image/png");
    }

    // Full Screen Screenshot using Robot
    public static void chainTestFullPageScreenshotFile(String filename) throws AWTException, IOException {

        String timestamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(new Date());

        Robot robot = new Robot();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rectangle = new Rectangle(screenSize);

        BufferedImage sourceFile = robot.createScreenCapture(rectangle);

        File folder = new File(System.getProperty("user.dir") + File.separator + "ScreenShot");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File destinationFile = new File(folder, filename + "_" + timestamp + ".png");

        ImageIO.write(sourceFile, "png", destinationFile);

        ChainTestListener.embed(destinationFile, "image/png");
    }
}
