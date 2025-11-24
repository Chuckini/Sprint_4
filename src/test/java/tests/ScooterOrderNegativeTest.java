package tests;
import static constants.ValidationMessages.*;
import static org.junit.Assert.assertEquals;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.HomePageScooter;
import pageobject.OrderDetailsPage;
import pageobject.ScooterOrderPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;


@RunWith(Parameterized.class)
public class ScooterOrderNegativeTest {
    public WebDriver driver;
    public HomePageScooter homePage;
    public ScooterOrderPage scooterOrderPage;
    public OrderDetailsPage orderDetailsPage;

    // параметры, которые будут применяться в тестах
    String caseName;
    String firstName;
    String lastName;
    String address;
    String metroStation;
    String phoneNumber;
    String date;
    String rentDays;
    String colorId;
    String comment;
    //поле для ошибки
    String expectedError;


    // конструктор
    public ScooterOrderNegativeTest(String caseName, String firstName, String lastName, String address, String metroStation, String phoneNumber, String date, String rentDays, String colorId, String comment, String expectedError) {
        //название поля проверки
        this.caseName = caseName;
        //Имя
        this.firstName = firstName;
        // Фамилия
        this.lastName = lastName;
        // адрес
        this.address = address;
        //станция Метро
        this.metroStation = metroStation;
        // номер телефона формат: 87463546374 (11-13 символов в поле, только цифры и + начинаются с 8 или +7)
        this.phoneNumber = phoneNumber;
        // дата формат: 29.11.2025
        this.date = date;
        // количество суток (сутки/двое суток/трое суток/четверо суток/пятеро суток/шестеро суток/семеро суток)
        this.rentDays = rentDays;
        // чекбокс два цвета: серы й и черный. Формат ввода: black и grey можно выбрать два сразу
        this.colorId = colorId;
        // комментарии для курьера
        this.comment = comment;
        // сообщение об ошибке
        this.expectedError = expectedError;
        this.rentDays = rentDays;
    }

    @Parameterized.Parameters(name = "{index}: {0}")
    // тестовые данные
    public static Collection<Object[]> getOrderTest() {
        return Arrays.asList(new Object[][]{
                // пустое поле Имя
                {
                        "Пустое поле Имя", "", "Коваленко", "Москва, Ленина 10", "Комсомольская",
                        "89051234567", "10.12.2025", "сутки", "black",
                        "Комментарий", FIRST_NAME_INVALID
                },
                // невалидное Имя
                {
                        "Невалидное имя", "А", "Петров", "Москва, Тверская 5", "Арбатская",
                        "+79053334455", "15.01.2026", "двое суток", "grey",
                        "Доставить вечером", FIRST_NAME_INVALID
                },
                // Пустое поле фамилия
                {
                        "Пустое поле Фамилия", "Анна", "", "Питер, Невский 1", "Гостиный двор",
                        "89056667788", "05.02.2026", "семеро суток", "black",
                        "", LAST_NAME_INVALID
                },
                // невалидное имя
                {
                        "Невалидное Фамилия", "Виталий", "G", "Кемерово, Ленина 17", "Гоусинный двор",
                        "+79056667788", "05.02.2026", "семеро суток", "black",
                        "Памагите, я уже больше не могу делать этот проект дайте мне самокат, я уеду в дурку", LAST_NAME_INVALID
                },
                // пустое поле адрес
                {
                        "Пустое поле адрес", "Дмитрий", "Орлов", "", "Бауманская",
                        "89055557777", "25.12.2025", "сутки", "black",
                        "Позвонить заранее", ADDRESS_INVALID
                },
                // пустое поле метро
                {
                        "Пустое поле метро", "Екатерина", "Фролова", "Новосибирск, Кирова 30", "",
                        "89135556677", "14.12.2025", "трое суток", "grey",
                        "Оставить на вахте", METRO_REQUIRED
                },
                // пустое поле номер телефона
                {
                        "Пустое поле номер телефона", "Иван", "Рубцов", "Казань, Кремлёвская 2", "Площадь Тукая",
                        "", "20.12.2025", "сутки", "black",
                        "Без звонка", PHONE_INVALID_FORMAT
                },
                // невалидный номер телефона
                {
                        "Невалидный номер телефона", "Артур", "Ли", "Москва, Крылатское 14", "Крылатское",
                        "12345", "22.12.2025", "четверо суток", "grey",
                        "Позвонить", PHONE_INVALID_FORMAT
                },
                // пустое поле дата аренды
                {
                        "Пустое поле дата аренды", "Мария", "Соколова", "Химки, Ленина 3", "Речной вокзал",
                        "89051239876", "", "сутки", "black",
                        "Без комментариев", DATE_INVALID
                }
        });
    }


// метод для старта драйвера хром
     @Before
     public void startDriverChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
         homePage = new HomePageScooter(driver);
         scooterOrderPage = new ScooterOrderPage(driver);
         orderDetailsPage = new OrderDetailsPage(driver);

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://qa-scooter.praktikum-services.ru/");
         homePage.acceptCookies();
    }

    /* метод для старта драйвера хром фокс
    @Before
    public void startDriverFirefox() {

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(options);
        homePage = new HomePageScooter(driver);
        scooterOrderPage = new ScooterOrderPage(driver);
        orderDetailsPage = new OrderDetailsPage(driver);


        driver.get("https://qa-scooter.praktikum-services.ru/");
        // клик по кнопке принять кукис да все привыкли
        homePage.acceptCookies();
    } */

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void invalidTest() {
        // неявное ожидание
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        // клик по кнопке Заказать
        homePage.clickFirstOrderButton();

        //  заполняем только одно поле, которое указано в caseName
        switch (caseName) {
            case "Пустое поле Имя":
            case "Невалидное имя":
                scooterOrderPage.setFirstName(firstName);
                scooterOrderPage.unfocus();
                break;

            case "Пустое поле Фамилия":
            case "Невалидная фамилия":
                scooterOrderPage.setLastName(lastName);
                scooterOrderPage.unfocus();
                break;

            case "Пустое поле адрес":
                scooterOrderPage.setAddress(address);
                scooterOrderPage.unfocus();
                break;

            case "Пустое поле метро":
                scooterOrderPage.unfocus(); // ничего не выбираем
                break;

            case "Пустое поле номер телефона":
            case "Невалидный номер телефона":
                scooterOrderPage.setPhone(phoneNumber);
                scooterOrderPage.unfocus();
                break;
        }

        String actualError = getErrorByCaseName(caseName);
        assertEquals("Ошибка в случае: " + caseName, expectedError, actualError);

    }

    private String getErrorByCaseName(String caseName) {
        switch (caseName) {
            case "Пустое поле Имя":
            case "Невалидное имя":
                return scooterOrderPage.getFirstNameErrorText();

            case "Пустое поле Фамилия":
            case "Невалидная фамилия":
                return scooterOrderPage.getLastNameErrorText();

            case "Пустое поле адрес":
                return scooterOrderPage.getAddressErrorText();

            case "Пустое поле метро":
                return scooterOrderPage.getMetroErrorText();

            case "Пустое поле номер телефона":
            case "Невалидный номер телефона":
                return scooterOrderPage.getPhoneErrorText();

            default:
                return "";
        }
    }
}





