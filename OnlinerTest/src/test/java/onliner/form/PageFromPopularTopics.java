package onliner.form;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageFromPopularTopics {
    private final WebDriver driver;
    private final Logger logger = LogManager.getRootLogger();

    public PageFromPopularTopics(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(className = "schema-header__title")
    private WebElement nameTopic;

    public WebElement getNameTopic(){
        return nameTopic;
    }


}
