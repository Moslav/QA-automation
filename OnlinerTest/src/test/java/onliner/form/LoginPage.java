package onliner.form;

import framework.Wait;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;
    private final Logger logger = LogManager.getRootLogger();
    private By entryButton = By.xpath("//*[@id=\"userbar\"]/div/div[1]");
    private By inputLogin = By.xpath("//div[@class=\"auth-box__line\"]/input[@type=\"text\"]");
    private By inputPassword = By.xpath("//div[@class=\"auth-box__line\"]/input[@type=\"password\"]");
    private By buttonSubmit = By.cssSelector(".auth-box__auth-submit");
    private final String elementForWait = "//div[@class=\"b-top-profile__image\"]";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void loginUser(String username, String password) {
        driver.findElement(entryButton).click();
        driver.findElement(inputLogin).sendKeys(username);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(buttonSubmit).click();
        logger.info("Login performed");
    }

    public void waitPicture () {
        new Wait(driver).explicitWait(elementForWait);
    }

    public WebElement getEntryButton() {
        return driver.findElement(entryButton);
    }
}