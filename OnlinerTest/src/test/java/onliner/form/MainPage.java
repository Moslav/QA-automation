package onliner.form;

import framework.CSVWrite;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class MainPage {

    private String regexp = "<div class=\"b-opinions-main-2__text\">(.*?)</div>";
    private String correctNameUserOnPage = "Maksiman";
    private String valueOfSelectedTopicText;
    private Random random;
    private final WebDriver driver;
    private final Logger logger = LogManager.getRootLogger();
    private By onlinerLogo = By.className("onliner_logo");
    private By profileImage = By.className("b-top-profile__image");
    private By buttonLogOut = By.xpath("//div[@class=\"b-top-profile__logout\"]/a");
    private By popularTopics = By.className("project-navigation__flex");
    private By elemsTopic = By.cssSelector(".project-navigation__flex a");
    private List<WebElement> elems;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void returnToMainPage() {
        driver.findElement(onlinerLogo).click();
        logger.info("Main Page");
    }

    public void logOut() {
        driver.findElement(profileImage).click();
        driver.findElement(buttonLogOut).click();
        logger.info("LogOut performed");
    }

    public WebElement getListPopularTopicsAndChooseRandom() {
        logger.info("Get a list of popular topics and choose random topic");
        random = new Random();
        elems = driver.findElements(elemsTopic);
        int index = random.nextInt(elems.size()-1);
        return elems.get(index);
    }

    public void scrollToElementAndClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        valueOfSelectedTopicText = element.getText();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        logger.info("Click on random element from popular topics");
    }

    public String returnNameLoggedUser() {
        driver.findElement(profileImage).click();
        WebElement loggedUserName = driver.findElement(By.xpath("//div[@class=\"b-top-profile__name\"]/a"));
        return loggedUserName.getText();
    }

    public void findingOpinionsAndWritingToCsvFile() {
        String pageSource = driver.getPageSource();
        CSVWrite.createCSVFileAndWriteValue(pageSource, regexp);
    }

    public String getValueOfSelectedTopicText() {
        return valueOfSelectedTopicText;
    }

    public String getNameUserOnPage() {
        return correctNameUserOnPage;
    }

    public WebElement getProfileImage() {
        return driver.findElement(profileImage);
    }

    public WebElement getPopularTopics() {
        return driver.findElement(popularTopics);
    }
}



