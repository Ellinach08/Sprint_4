package ru.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum.pageobject.constants.ScooterColours;

import java.time.Duration;

public class LeasePage {
    //Заголовок страницы "Про аренду"
    private final By leaseHeader = By.className("Order_Header__BZXOb");
    //Поле ввода даты доставки
    private final By deliveryDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Поле ввода срока аренды
    private final By leaseDays = By.xpath(".//span[@class='Dropdown-arrow']");
    //Меню выбора срока аренды
    private final By leaseDateMenu = By.className("Dropdown-menu");
    //Значения в меню выбора срока аренды с подстановкой
    private final String leaseDateSelect = ".//div[@class='Dropdown-option' and contains(text(),'%s')]";
    //Цвета самоката
    private final By blackColour = By.id("black");
    private final By greyColour = By.id("grey");
    //Поле ввода комментария для курьера
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка "Заказать"
    private final By createOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final WebDriver driver;

    public LeasePage(WebDriver driver) {
        this.driver = driver;
    }
    //Метод ожидания загрузки страницы заказа
    public void waitLoadLeasePage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> {
            driver.findElement(leaseHeader).getText();
            return !driver.findElement(leaseHeader).getText().isEmpty();
        });
    }
    //Метод ввода даты доставки
    public void inputDeliveryDate(String Date) {
        driver.findElement(deliveryDate).sendKeys(Date);
    }
    //Метод ввода срока аренды
    public void inputLeaseDate(String lease) {
        driver.findElement(leaseDays).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(leaseDateMenu));
        driver.findElement(By.xpath(String.format(leaseDateSelect, lease))).click();
    }
    //Метод выбора цвета самоката
    public void selectColour(String colour) {
        if (colour.equals(ScooterColours.BLACK_COLOUR)) {
            driver.findElement(blackColour).click();
        } else if (colour.equals(ScooterColours.GREY_COLOUR)) {
            driver.findElement(greyColour).click();
        }
    }
    //Метод ввода комментария для курьера
    public void inputComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
    }
    //Метод нажатия кнопки "Заказать"
    public void clickCreateOrderButton() {
        driver.findElement(createOrderButton).click();
    }
}
