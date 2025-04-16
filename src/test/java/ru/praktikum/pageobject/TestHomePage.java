package ru.praktikum.pageobject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static ru.praktikum.pageobject.constants.HomePageConstants.*;

@RunWith(Parameterized.class)
public class TestHomePage {
    //Тестирование блока "Вопросы о важном" на главной странице
    private final String qaNum;
    private final String expected;
    private WebDriver driver;

    public TestHomePage(String qaNum, String expected) {
        this.qaNum = qaNum;
        this.expected = expected;
    }

     @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"0", HOW_MUCH_ANSWER},
                {"1", COUPLE_OF_SCOOTER_ANSWER},
                {"2", LEASE_TIME_COUNT_ANSWER},
                {"3", ORDER_DAY_ANSWER},
                {"4", ORDER_PROLONG_ANSWER},
                {"5", SCOOTER_CHARGE_ANSWER},
                {"6", ORDER_CANCEL_ANSWER},
                {"7", LOCATION_ANSWER},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL_NAME);
    }

    @Test
    public void checkQuestions() {
        HomePage homePage = new HomePage(driver);
        homePage.waitForLoadHomePage();
        homePage.scrollToQuestions();
        homePage.clickQuestion(qaNum);
        homePage.waitLoadAfterClickQuestion(qaNum);
        homePage.compareAnswer(qaNum, expected);
    }

    @After
    public void tearDown() { driver.quit(); }

}
