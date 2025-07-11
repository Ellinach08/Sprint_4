package ru.praktikum.pageobject.constants;

public class HomePageConstants {
    //URL тестируемого сайта
    public static final String URL_NAME = "https://qa-scooter.praktikum-services.ru/";

    //Ожидаемый текст в ответах
    public static final String HOW_MUCH_ANSWER = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String COUPLE_OF_SCOOTER_ANSWER = "Пока что у нас так: один заказ — один самокат. "
            + "Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String LEASE_TIME_COUNT_ANSWER = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. "
            + "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
            "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String ORDER_DAY_ANSWER = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String ORDER_PROLONG_ANSWER = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static final String SCOOTER_CHARGE_ANSWER = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. "
            + "Зарядка не понадобится.";
    public static final String ORDER_CANCEL_ANSWER = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public static final String LOCATION_ANSWER = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";
}
