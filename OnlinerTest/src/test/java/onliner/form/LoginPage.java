package onliner.form;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final Logger logger = LogManager.getRootLogger();

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"userbar\"]/div/div[1]")
    private WebElement entryButton;

    @FindBy(xpath = "//div[@class=\"auth-box__line\"]/input[@type=\"text\"]")
    private WebElement inputLogin;

    @FindBy(xpath = "//div[@class=\"auth-box__line\"]/input[@type=\"password\"]")
    private WebElement inputPassword;

    @FindBy(css = ".auth-box__auth-submit")
    private WebElement buttonSubmit;

    public void loginUser(String username, String password) {
        entryButton.click();
        inputLogin.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonSubmit.click();
        logger.info("Login performed");
    }

    public void waitPicture (){
        WebDriverWait wait = new WebDriverWait(driver, 100, 5);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"userbar\"]/div[1]/div[1]/a/div")));
    }

    public WebElement getEntryButton(){
        return entryButton;
    }
}