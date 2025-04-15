package ru.praktikum.pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static ru.praktikum.pageobject.constants.OrderButtons.BOTTOM_ORDER_BUTTON;
import static ru.praktikum.pageobject.constants.OrderButtons.TOP_ORDER_BUTTON;

public class HomePage {
    //Заголовок страницы
    private final By homeHeader = By.className("Home_Header__iJKdX");
    //Верхняя кнопка "Заказать"
    private final By topOrderButton = By.className("Button_Button__ra12g");
    //Нижняя кнопка "Заказать"
    private final By bottomOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Блок "Вопросы о важном"
    private final By questionsHeader = By.className("Home_FourPart__1uthg");
    //Переменная с вопросом на главной странице с подстановкой
    private final String question = "accordion__heading-%s";
    //Переменная с ответом на главной странице с подстановкой
    private final String answer = "accordion__panel-%s";

    //Кнопка Cookies
    private final By cookiesButton = By.className("App_CookieButton__3cvqF");
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод ожидания загрузки главной страницы
    public void waitForLoadHomePage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> {
            driver.findElement(homeHeader).getText();
            return !driver.findElement(homeHeader).getText().isEmpty();
        });
    }

    //Метод нажатия верхней кнопки "Заказать"
    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    //Метод нажатия нижней кнопки "Заказать"
    public void clickBottomOrderButton() {
        driver.findElement(bottomOrderButton).click();
    }

    //Метод определения нажатия кнопки "Заказать"
    public void clickCreateOrderButton(String button) {
        if (button.equals(TOP_ORDER_BUTTON)) {
            clickTopOrderButton();
        } else if (button.equals(BOTTOM_ORDER_BUTTON)) {
            clickBottomOrderButton();
        }
    }

    //Метод прокрутки к блоку "Вопросы о важном"
    public void scrollToQuestions() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsHeader));
    }

    //Метод нажатия вопросов в блоке "Вопросы о важном"
    public void clickQuestion(String questionNum) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(String.format(question, questionNum)))))
                .click();
    }

    //Метод ожидания загрузки ответа на вопрос
    public void waitLoadAfterClickQuestion(String answerNum) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> {
            driver.findElement(By.id(String.format(answer, answerNum))).getText();
            return !driver.findElement(By.id(String.format(answer, answerNum))).getText().isEmpty();
        });
    }

    //Метод сравнения ожидаемого текста в ответе
    public void compareAnswer(String answerNum, String expected) {
        String result = driver.findElement(By.id(String.format(answer, answerNum))).getText();
        assertEquals(expected, result);
    }

    //Метод нажатия кнопки с Cookie
    public void clickCookiesButton() {
       driver.findElement(cookiesButton).click();
    }
}

