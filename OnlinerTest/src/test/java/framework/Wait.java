package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class Wait {

    private final WebDriver driver;

    public Wait(WebDriver driver) {
        this.driver = driver;
    }

    public void explicitWait(String element) {
        WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(PropertyReader.getTestProperty("longtime")),
                Integer.parseInt(PropertyReader.getTestProperty("shorttime")));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(element)));
    }

    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyReader.getTestProperty("maintime")), TimeUnit.SECONDS);
    }

}
