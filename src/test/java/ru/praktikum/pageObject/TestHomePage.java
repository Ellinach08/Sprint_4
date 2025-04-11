package ru.praktikum.pageObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;
import static ru.praktikum.pageObject.constants.HomePageConstants.*;

@RunWith(Parameterized.class)
public class TestHomePage {
    //Тестирование блока "Вопросы о важном" на главной странице
    private WebDriver driver;
    private final By question;
    private final By answer;
    private final String expected;
    private final String site = "https://qa-scooter.praktikum-services.ru/";

    public TestHomePage(By question, By answer, String expected) {
        this.question = question;
        this.answer = answer;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {QUESTION_0, ANSWER_0, TEXT_ANSWER_0},
                {QUESTION_1, ANSWER_1, TEXT_ANSWER_1},
                {QUESTION_2, ANSWER_2, TEXT_ANSWER_2},
                {QUESTION_3, ANSWER_3, TEXT_ANSWER_3},
                {QUESTION_4, ANSWER_4, TEXT_ANSWER_4},
                {QUESTION_5, ANSWER_5, TEXT_ANSWER_5},
                {QUESTION_6, ANSWER_6, TEXT_ANSWER_6},
                {QUESTION_7, ANSWER_7, TEXT_ANSWER_7},
        };
    }

    @Before
    public void startUp() {
        //Тестирование в Firefox
        //WebDriverManager.firefoxdriver().setup();
        //driver = new FirefoxDriver();

        //Тестирование в Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get(site);
    }

    @Test
    public void checkQuestions() {
        HomePage homePage = new HomePage(driver);
        homePage.waitForLoadHomePage();
        homePage.scrollToQuestions();
        homePage.clickQuestion(question);
        homePage.waitLoadAfterClickQuestion(answer);
        String result = driver.findElement(answer).getText();
        assertEquals(expected, result);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
