package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.HomePageScooter;
import pageobject.ScooterOrderPage;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;


public class ScooterLogoRedirectTest {
    private WebDriver driver;
    private HomePageScooter homePage;
    private ScooterOrderPage scooterOrderPage;

    @Before
    public void startDriverChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        homePage = new HomePageScooter(driver);
        scooterOrderPage = new ScooterOrderPage(driver);

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://qa-scooter.praktikum-services.ru/");

    }

    @After
    // после теста закрыть браузер
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testLogoRedirectToHome() {

        homePage.acceptCookies();
        // неявное ожидание
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        HomePageScooter homePage = new HomePageScooter(driver);
        // клик по кнопке принять кукис да все привыкли
        homePage.acceptCookies();
        // клик по кнопке Заказать
        homePage.clickFirstOrderButton();
        //ожидание загрузки страницы
        new WebDriverWait(driver, Duration.ofSeconds(5));
        // метод для клика на иконку Самокат
        scooterOrderPage.clickIconScooter();
        //проверить, что происходит переход на домашнюю страницу через URL
        assertEquals("Логотип не переводит на домашнюю страницу", "https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
        }
    }

