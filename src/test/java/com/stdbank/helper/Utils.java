package com.stdbank.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.log4testng.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Utils {
    public static WebDriver driver;
    private static Logger log = Logger.getLogger(Utils.class);

    /**
     * this sets up the the web driver for testNG multi-platform run takes only the
     * browser name and then sets up the web driver for the specified driver
     *
     */
    public WebDriver setupWebDriver(String browserName) {
        String macDriverLocation = "./drivers/mac/";
        String linuxDriverLocation = "./drivers/linux/";
        String windowsDriverLocation = "./drivers/windows/";

        if (browserName.equalsIgnoreCase("chrome")) {
            String chromeDriverPath = null;

            if (this.getOsName().equalsIgnoreCase("Windows")) {
                chromeDriverPath = windowsDriverLocation + "chromedriver.exe";
            } else if (this.getOsName().equalsIgnoreCase("Mac OS")) {
                chromeDriverPath = macDriverLocation + "chromedriver";
            } else if (this.getOsName().equalsIgnoreCase("Linux")) {
                chromeDriverPath = linuxDriverLocation + "chromedriver";
            }

            log.info("This is the chrome driver path is :::: " + chromeDriverPath);

            String absoluteChromeDriverPath = toAbsolutePath(chromeDriverPath);
            log.info("This is the chrome driver real path is :::: " + absoluteChromeDriverPath);

            System.setProperty("webdriver.chrome.driver", absoluteChromeDriverPath);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-extensions"); // disabling extensions
            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//            options.addArguments("--headless");
            options.addArguments("Zoom 50%");
            options.addArguments("start-maximized"); // open Browser in maximized mode
            options.addArguments("disable-infobars"); // disabling infobars

            driver = new ChromeDriver(options);

        }
        if (browserName.equalsIgnoreCase("firefox")) {
            String firefoxDriverPath = null;

            if (this.getOsName().equalsIgnoreCase("Windows")) {
                firefoxDriverPath = windowsDriverLocation + "geckodriver.exe";
            } else if (this.getOsName().equalsIgnoreCase("Mac OS")) {
                firefoxDriverPath = macDriverLocation + "geckodriver";
            } else if (this.getOsName().equalsIgnoreCase("Linux")) {
                firefoxDriverPath = linuxDriverLocation + "geckodriver";
            }

            log.info("This is the firefox driver path is :::: " + firefoxDriverPath);

            String absoluteChromeDriverPath = toAbsolutePath(firefoxDriverPath);
            log.info("This is the firefox driver real path is :::: " + absoluteChromeDriverPath);

            System.setProperty("webdriver.gecko.driver", absoluteChromeDriverPath);

            driver= new FirefoxDriver();
        }
        return driver;
    }

    /**
     * get real path on a machine using a relative path with respect to the current
     * root directory (i.e project root directory) it takes one argument just the
     * relative path
     */
    public static String toAbsolutePath(String relativePath) {
        Path relPath = Paths.get(relativePath);
        Path absolutePath = null;
        if (!relPath.isAbsolute()) {
            Path base = Paths.get("");
            absolutePath = base.resolve(relPath).toAbsolutePath();
        }
        return absolutePath.normalize().toString();
    }

    public String getConfigPropertyValue(String propertyFileName, String propertyName) {
        String Value = null;
        try {
            FileInputStream fileIS = new FileInputStream(new File(propertyFileName));
            Properties prop = new Properties();
            prop.load(fileIS);

            Value = prop.getProperty(propertyName);
        } catch (IOException e) {
            log.info(e.getStackTrace()) ;
        }

        return Value;
    }
    public void teardown(){
        driver.quit();
    }

    /**
     * get the os type that the code is running on takes no variable but returns the
     * os type such as: Windows, Mac OS, Linux
     */
    public String getOsName() {
        String osType;
        String osName = "";

        osType = System.getProperty("os.name");

        if (osType.contains("Windows") || osType.contains("windows")) {
            osName = "Windows";
        } else if (osType.contains("Mac") || osType.contains("mac")) {
            osName = "Mac OS";
        } else if (osType.contains("Linux") || osType.contains("linux")) {
            osName = "Linux";
        }

        log.info("os Type is ::: " + osType + "found os Name ::: " + osName);

        return osName;
    }

}
