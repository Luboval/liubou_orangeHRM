package eu.senla.management.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public final class Driver {
   // private static WebDriver driver;
   private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    //Private constructor to prevent creation a new instance from outside
    private Driver() { }

    public static WebDriver driverRun() {
        if (driver.get() == null) {
//            switch (System.getProperty("browser").toLowerCase()) {
//                case "chrome" -> driver.set(new ChromeDriver()); //= new ChromeDriver();
//                case "firefox" -> driver.set(new FirefoxDriver()); //driver = new FirefoxDriver();
//                default -> throw new IllegalArgumentException("Unknown browser");
//            }
            String browser = System.getProperty("browser").toLowerCase();
            if (Boolean.parseBoolean(System.getProperty("selenoidEnable"))) {
                initRemoteDriver(browser);
            } else {
                initLocalDriver(browser);
            }

            //driver.manage().window().maximize();
            driver.get().manage().window().maximize();
        }
        return driver.get();
    }

    private static void initRemoteDriver(String browser) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            switch (browser) {
                case "chrome" -> {
                    ChromeOptions chromeOptions = new ChromeOptions();
                    // required for Docker/Selenoid stability. (info by ChatGPT)
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    if (Boolean.parseBoolean(System.getProperty("headless"))) {
                        chromeOptions.addArguments("--headless=new");
                    }
                    chromeOptions.setCapability("selenoid:options", Map.of(
                            "enableVNC", true,
                            "enableVideo", true,
                            "enableLog", true,
                            "videoName", System.getProperty("videoTestName") + "_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH_mm_ss")) + ".mp4",
                            "sessionTimeout", "2m"
                    ));
                    capabilities.merge(chromeOptions);
                }
                case "firefox" -> {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    if (Boolean.parseBoolean(System.getProperty("headless"))) {
                        firefoxOptions.addArguments("--headless");
                    }
                    firefoxOptions.setCapability("selenoid:options", Map.of(
                            "enableVNC", true,
                            "enableVideo", true,
                            "enableLog", true
                    ));
                    capabilities.merge(firefoxOptions);
                }
                default -> throw new IllegalArgumentException("Unsupported remote browser: " + browser);
            }
            driver.set(new RemoteWebDriver(
                    new URL(System.getProperty("selenoidUrl")), capabilities));

        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenoid URL", e);
        }
    }
    private static void initLocalDriver(String browser) {
        switch (browser) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
/*                if (Boolean.parseBoolean(getProperty("headless"))) {
                   options.addArguments("--headless=new");
               }*/
                driver.set(new ChromeDriver(options));
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
/*                if (Boolean.parseBoolean(getProperty("headless"))) {
                   options.addArguments("--headless");
               }*/
                driver.set(new FirefoxDriver(options));
            }
            default -> throw new IllegalArgumentException("Передан неподдерживаемый браузер");
        }
    }

    public static void driverTearDown() {
        driver.get().quit();
       // driver = null;
        driver.remove();
    }
}
