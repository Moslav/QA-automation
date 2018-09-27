package onliner.tests;

import static org.testng.Assert.*;
import framework.BrowserFactory;
import onliner.form.LoginPage;
import onliner.form.MainPage;
import onliner.form.PageFromPopularTopics;
import org.openqa.selenium.*;
import org.testng.annotations.*;

public class OnlinerTest {

    private LoginPage loginPage;
    private MainPage mainPage;
    private PageFromPopularTopics pageFromPopularTopics;
    private static WebDriver driver;
    private static String username_login;
    private static String password_login;

    @Parameters({"username","password","browser"})
    @BeforeTest
    public static void setup(String username, String password, String browser){
        driver = BrowserFactory.getDriver(browser);
        username_login = username;
        password_login = password;
    }

    @Test
    public void entryFiled() {
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        pageFromPopularTopics = new PageFromPopularTopics(driver);
        assertTrue(mainPage.getPopularTopics().isDisplayed());
        loginPage.loginUser(username_login,password_login);
        loginPage.waitPicture();
        assertEquals(mainPage.returnNameLoggedUser(), mainPage.getNameUserOnPage());
        mainPage.scrollToElementAndClick(mainPage.getListPopularTopicsAndChooseRandom());
        assertEquals(pageFromPopularTopics.getNameTopic().getText(), mainPage.getValueOfSelectedTopicText());
        mainPage.returnToMainPage();
        mainPage.findingOpinionsAndWritingToCsvFile();
        mainPage.logOut();
        assertTrue(loginPage.getEntryButton().isDisplayed());
    }

    @AfterTest
    public static void tearDown(){
        BrowserFactory.closeDriver();
    }



}
