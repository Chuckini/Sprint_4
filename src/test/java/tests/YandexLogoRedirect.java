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
import pageobject.YandexHomepage;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class YandexLogoRedirect {
    private WebDriver driver;
    private HomePageScooter homePage;
    private YandexHomepage yandexHomepage;

    @Before
    public void startDriverChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        homePage = new HomePageScooter(driver);
        yandexHomepage = new YandexHomepage(driver);

        driver.get("https://qa-scooter.praktikum-services.ru/");
        // клик по кнопке принять кукис да все привыкли
        homePage.acceptCookies();

    }

    @After
    // после теста закрыть браузер
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void YandexLogoRedirectTest() {
        // новое окно яндекса
        String mainWindow = driver.getWindowHandle();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        // клик на Яндекс
        homePage.clickYandexLogo();
        String href = homePage.getYandexLogoHref();
        assertEquals("https://yandex.ru/", href);
    }
}


