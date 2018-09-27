package framework;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {

    private static String OS = System.getProperty("os.name").toLowerCase();
    private static WebDriver driver;
    private static final Logger logger = LogManager.getRootLogger();
    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    private static final String exe = ".exe";

    private BrowserFactory(){};

    public static String getOperatingSystem(){
        if(OS.contains("win")){
            return "windows";
        }else if(OS.contains("nux")){
            return "linux";
        }
        return "Вы не используете ни Linux, ни Windows";
    }

    public static WebDriver getDriver(String browser){
        if (driver == null){
            if(getOperatingSystem().equals("windows")){
                if(browser.equals("chrome")){
                    System.setProperty(WEBDRIVER_CHROME_DRIVER, PropertyReader.getTestProperty("chromedriver")+exe);
                    driver = new ChromeDriver();
                } else if(browser.equals("firefox")){
                    System.setProperty(WEBDRIVER_GECKO_DRIVER, PropertyReader.getTestProperty("geckodriver")+exe);
                    driver = new FirefoxDriver();
                }
            } else if(getOperatingSystem().equals("linux")){
                if(browser.equals("chrome")){
                    System.setProperty(WEBDRIVER_CHROME_DRIVER, PropertyReader.getTestProperty("chromedriver"));
                    driver = new ChromeDriver();
                } else if(browser.equals("firefox")){
                    System.setProperty(WEBDRIVER_GECKO_DRIVER, PropertyReader.getTestProperty("geckodriver"));
                    driver = new FirefoxDriver();
                }
            }
            driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(PropertyReader.getTestProperty("URL"));
            logger.info("Browser started");
        }

        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
