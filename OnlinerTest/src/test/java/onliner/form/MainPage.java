package onliner.form;

import com.opencsv.CSVWriter;
import framework.PropertyReader;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainPage {

    private CSVWriter writer;
    private ArrayList<String> arrayList;
    private String correctNameUserOnPage = "Maksiman";
    private String valueOfSelectedTopicText;
    private Random random;
    private final WebDriver driver;
    private final Logger logger = LogManager.getRootLogger();

    public MainPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(className = "onliner_logo")
    private WebElement onlinerLogo;

    @FindBy(className = "b-top-profile__image")
    private WebElement profileImage;

    @FindBy(xpath = "//div[@class=\"b-top-profile__logout\"]/a")
    private WebElement buttonLogOut;

    @FindBy(css = ".project-navigation__flex a")
    private List<WebElement> elems;

    @FindBy(className = "project-navigation__flex")
    private WebElement popularTopics;

    public void returnToMainPage()
    {
        onlinerLogo.click();
        logger.info("Main Page");
    }

    public void logOut(){
        profileImage.click();
        buttonLogOut.click();
        logger.info("LogOut performed");
    }

    public WebElement getListPopularTopicsAndChooseRandom(){
        logger.info("Get a list of popular topics and choose random topic");
        random = new Random();
        int index = random.nextInt(elems.size()-1);
        return elems.get(index);
    }

    public void scrollToElementAndClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        valueOfSelectedTopicText = element.getText();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        logger.info("Click on random element from popular topics");
    }

    public String returnNameLoggedUser(){
        profileImage.click();
        WebElement loggedUserName = driver.findElement(By.xpath("//div[@class=\"b-top-profile__name\"]/a"));
        return loggedUserName.getText();
    }

    public void findingOpinionsAndWritingToCsvFile() {
        String pageSource = driver.getPageSource();
        String regexp = "<div class=\"b-opinions-main-2__text\">(.*?)</div>";
        Pattern r = Pattern.compile(regexp);
        Matcher m = r.matcher(pageSource);
        try {
            writer = new CSVWriter(new FileWriter(PropertyReader.getTestProperty("csv")));
            while (m.find()) {
                writer.writeNext(m.group(1).split("-"));
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getValueOfSelectedTopicText(){
        return valueOfSelectedTopicText;
    }

    public String getNameUserOnPage() {
        return correctNameUserOnPage;
    }

    public WebElement getProfileImage(){ return profileImage; }

    public WebElement getPopularTopics(){ return popularTopics; }
}



