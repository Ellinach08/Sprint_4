package ru.praktikum.pageobject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static ru.praktikum.pageobject.constants.HomePageConstants.URL_NAME;
import static ru.praktikum.pageobject.constants.OrderButtons.BOTTOM_ORDER_BUTTON;
import static ru.praktikum.pageobject.constants.OrderButtons.TOP_ORDER_BUTTON;
import static ru.praktikum.pageobject.constants.LeaseTimeConstants.*;
import static ru.praktikum.pageobject.constants.ScooterColours.BLACK_COLOUR;
import static ru.praktikum.pageobject.constants.ScooterColours.GREY_COLOUR;

@RunWith(Parameterized.class)
public class TestCreateOrder{
    //Тестирование заказа самоката
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStationNumber;
    private final String phoneNumber;
    private final String deliveryDate;
    private final String leaseDays;
    private final String scooterColour;
    private final String comment;
    private final String createOrderButton;
    private WebDriver driver;

    public TestCreateOrder(String createOrderButton, String firstName, String lastName, String address,
                           int metroStationNumber, String phoneNumber, String deliveryDate, String leaseDays, String scooterColour, String comment) {
        this.createOrderButton = createOrderButton;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStationNumber = metroStationNumber;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.leaseDays = leaseDays;
        this.scooterColour = scooterColour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {TOP_ORDER_BUTTON, "Иван", "Иванов", "Адрес 1", 123, "79998887777", "08.04.2025", ONE_DAY, BLACK_COLOUR, "comments one"},
                {TOP_ORDER_BUTTON, "Петр", "Петров", "Адрес 2", 8, "79998886666", "12.04.2025", THREE_DAYS, BLACK_COLOUR, "comments two"},
                {TOP_ORDER_BUTTON, "Роман", "Романов", "Адрес 3", 10, "79998885555", "12.04.2025", SEVEN_DAYS, BLACK_COLOUR, "comments three"},
                {BOTTOM_ORDER_BUTTON, "Юрий", "Сидоров", "Адрес 4", 19, "79998884444", "12.04.2025", ONE_DAY, GREY_COLOUR, "comments one"},
                {BOTTOM_ORDER_BUTTON, "Сергей", "Сергеев", "Адрес 5", 27, "79998883333", "12.04.2025", THREE_DAYS, GREY_COLOUR, "comments two"},
                {BOTTOM_ORDER_BUTTON, "Дмитрий", "Дмитриев", "Адрес 6", 34, "79998882222", "12.04.2025", SEVEN_DAYS, GREY_COLOUR, "comments three"},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(URL_NAME);
    }

    @Test
    public void testCreateOrderButton() {
        HomePage homePage = new HomePage(driver);
        homePage.waitForLoadHomePage();
        homePage.clickCookiesButton();
        homePage.clickCreateOrderButton(createOrderButton);

        OrderPage orderPage = new OrderPage(driver);
        orderPage.waitForLoadOrderPage();
        orderPage.inputFirstName(firstName);
        orderPage.inputLastName(lastName);
        orderPage.inputAddress(address);
        orderPage.changeMetroStation(metroStationNumber);
        orderPage.inputPhone(phoneNumber);
        orderPage.clickNextButton();

        LeasePage leasePage = new LeasePage(driver);
        leasePage.waitLoadLeasePage();
        leasePage.inputDeliveryDate(deliveryDate);
        leasePage.inputLeaseDate(leaseDays);
        leasePage.selectColour(scooterColour);
        leasePage.inputComment(comment);
        leasePage.clickCreateOrderButton();

        ApproveOrderPage approveOrderPage = new ApproveOrderPage(driver);
        approveOrderPage.waitForLoadApproveOrderPage();
        approveOrderPage.clickApproveButton();
        approveOrderPage.waitHeaderAfterCreateOrder();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}