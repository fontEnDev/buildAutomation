package com.personal.outbound.lab;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class EnvSetup {
    public static volatile String baseUrl = "";
    public static WebDriver WEBDRIVER;
    public static int ImplicitWait = 60;
    public static int ExplicitWait = 120;
    public static String DRIVER_PATH = "";
    public static String BROWSER_NAME = "";
    public static String USER_PROPERTY_FILE_PATH = "";
    public static String SUITE_PATH = System.getProperty("user.dir") + "/";
    public static String DEFAULT_BROWSER_NAME = "";

    public static String JMETER_HOME = "";


    public EnvSetup() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        String configFile = System.getProperty("CONFIG_FILE", "");
        UtilityFun utilityFun = new UtilityFun();
        USER_PROPERTY_FILE_PATH = SUITE_PATH + "src/test/resources/config/fileConfigure.properties";
        Properties prop = new Properties();
        try (InputStream input = Files.newInputStream(Paths.get(USER_PROPERTY_FILE_PATH))) {
            prop.load(input);
            prop.entrySet().removeIf(e -> e.getValue().toString().matches("[`'\"]\\s*[`'\"]|\\s+|[`'\"]\\s*|\\s*[`'\"]|^$"));
            prop.putAll(System.getProperties());
        } catch (Exception e) {
            System.out.println("Exception when load properties file");
            e.printStackTrace();
        }
        BROWSER_NAME = prop.getProperty("Browser", "FIREFOX");
        DEFAULT_BROWSER_NAME = BROWSER_NAME;
        DRIVER_PATH = prop.getProperty("DriverPath", SUITE_PATH + "src/test/resources/drivers/");
        DRIVER_PATH = DRIVER_PATH.matches(".*[/\\\\]$") ? DRIVER_PATH : DRIVER_PATH + "\\";
        baseUrl = prop.getProperty("BASE_URL");
        JMETER_HOME = prop.getProperty("Jmeter_Home");
        //

    }

    public static WebDriver setupDriverInstance() {
        return setupDriverInstance(BROWSER_NAME);
    }

    public static WebDriver setupDriverInstance(String browserName) {
        WebDriver driver;
        Browsers browser = Browsers.browserForName(browserName);
        switch (browser) {
            case FIREFOX:
                driver = createFirefoxDriver();
                break;
            case CHROME:
                driver = createChromeDriver();
                break;
            default:
                BROWSER_NAME = "FIREFOX";
                driver = createFirefoxDriver();
                break;
        }
        return driver;
    }


    public static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        options.addArguments("test-type");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("use-fake-ui-for-media-stream");
        options.addArguments("disable-features=WebRtcHideLocalIpsWithMdns");
        // disable Chrome popups
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("excludeSwitches");
        return new ChromeDriver(options);
    }

    public static WebDriver createFirefoxDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("marionette", true);
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        FirefoxOptions options = new FirefoxOptions();
        options.merge(capabilities);
        options.addPreference("permissions.default.microphone", 1);
        options.addPreference("permissions.default.camera", 1);
        options.setProfile(profile);
        return new FirefoxDriver(options);
    }

    public enum Browsers {
        SAFARI,
        FIREFOX,
        CHROME,
        CHROMEHEADLESS,
        EDGE,
        IE,
        NOBROWSER,
        FIREFOXGRID,
        CHROMEGRID,
        IEGRID,
        GRID;

        public static Browsers browserForName(String browser) throws IllegalArgumentException {
            for (Browsers b : values()) {
                if (b.toString().equalsIgnoreCase(browser)) {
                    return b;
                }
            }
            return NOBROWSER;
        }
    }
}
