package ru.praktikum.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    //Заголовок страницы заказа
    private final By orderHeader = By.className("Order_Header__BZXOb");
    //Поле ввода имени
    private final By firstName = By.xpath(".//input[@placeholder='* Имя']");
    //Поле ввода фамилии
    private final By lastName = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле ввода адреса доставки
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле ввода станции метро из списка
    private final By metroStationName = By.className("select-search__input");
    //Строка значения станции метро с подстановкой номера станции (ключ number)
    private final String metroStation = ".//button[@value='%s']";
    //Поле ввода номера телефона
    private final By phone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка "Далее"
    private final By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");
    WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод ожидания загрузки страницы заказа
    public void waitForLoadOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> {
            driver.findElement(orderHeader).getText();
            return !driver.findElement(orderHeader).getText().isEmpty();
        });
    }
    //Метод ввода имени
    public void inputFirstName(String newName) {
        driver.findElement(firstName).sendKeys(newName);
    }
    //Метод ввода фамилии
    public void inputLastName(String newSurname) {
        driver.findElement(lastName).sendKeys(newSurname);
    }
    //Метод ввода адреса доставки
    public void inputAddress(String newAddress) {
        driver.findElement(address).sendKeys(newAddress);
    }
    //Метод ввода станции метро
    public void changeMetroStation(int stateNumber) {
        driver.findElement(metroStationName).click();
        By metroStation = By.xpath(String.format(this.metroStation, stateNumber));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(metroStation));
        driver.findElement(metroStation).click();
    }
    //Метод ввода номера телефона
    public void inputPhone(String newTelephone) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(phone));
        driver.findElement(phone).sendKeys(newTelephone);
    }
    //Метод нажатия кнопки "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }
}

