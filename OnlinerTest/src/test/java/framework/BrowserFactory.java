package framework;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {

    private static String OS = System.getProperty("os.name").toLowerCase();
    private static WebDriver driver;
    private static final Logger logger = LogManager.getRootLogger();
    private static String exe;
    private static final String driverPath = BrowserFactory.class.getClassLoader().getResource(getDriverName()).getPath();
    private static String nameBrowserDriver;
    private static String WEBDRIVERVER ;
    private static String path;


    private BrowserFactory(){};

    public static String getCanonicalPathToDriver(){
        File file = new File(driverPath);
        try {
            path = file.getCanonicalPath();
        }catch (IOException e){
            logger.info("FIle not found");
        }
        return path;
    }

    public static String getDriverName() {
        switch (PropertyReader.getTestProperty("browser")) {
            case "chrome":
                nameBrowserDriver = "chromedriver";
                WEBDRIVERVER = "webdriver.chrome.driver";
                break;
            case "firefox":
                nameBrowserDriver = "geckodriver";
                WEBDRIVERVER = "webdriver.gecko.driver";
                break;
        }
        return nameBrowserDriver;
    }

    public static String getFileExtans(){
        if(OS.contains("win")){
            exe = ".exe";
        } else if(OS.contains("nux")){
            exe = "";
        }
        return exe;
    }

    public static WebDriver getDriver(){
          if (driver == null){
              switch (getDriverName()) {
                  case "chromedriver":
                      System.setProperty(WEBDRIVERVER, getCanonicalPathToDriver() + getFileExtans());
                      driver = new ChromeDriver();
                      break;
                  case "geckodriver":
                      System.setProperty(WEBDRIVERVER, getCanonicalPathToDriver() + getFileExtans());
                      driver = new FirefoxDriver();
                      break;
              }
             // driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(PropertyReader.getTestProperty("maintime")),TimeUnit.SECONDS);
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
