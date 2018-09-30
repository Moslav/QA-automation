package onliner.form;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageFromPopularTopics {
    private final WebDriver driver;
    private By nameTopic = By.className("schema-header__title");

    public PageFromPopularTopics(WebDriver driver){
        this.driver = driver;
    }

    public String getNameTopic(){
        return driver.findElement(nameTopic).getAttribute("innerText");
    }


}
