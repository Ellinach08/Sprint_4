package ru.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class ApproveOrderPage {
    //Заголовок страницы подтверждения заказа
    private final By approveOrderHeader = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Хотите оформить заказ?']");
    //Кнопка подтверждения оформления заказа
    private final By approveButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Да']");
    //Заголовок страницы успешного заказа
    private final By successOrderHeader = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']");
    private final WebDriver driver;

    public ApproveOrderPage(WebDriver driver) {
        this.driver = driver;
    }
    //Метод загрузки страницы подтверждения заказа
    public void waitForLoadApproveOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> {
            driver.findElement(approveOrderHeader).getText();
            return !driver.findElement(approveOrderHeader).getText().isEmpty();
        });
    }
    //Метод нажатия кнопки подтверждения оформления заказа
    public void clickApproveButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(approveButton)).click();
    }
    //Метод получения заголовка страницы после оформления заказа
    public void waitHeaderAfterCreateOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> {
            driver.findElement(successOrderHeader).getText();
            return !driver.findElement(successOrderHeader).getText().isEmpty();
        });
        String expectedHeader = "Заказ оформлен";
        assertTrue(driver.findElement(successOrderHeader).getText().contains(expectedHeader));
    }
}
