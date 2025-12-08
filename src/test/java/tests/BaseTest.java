package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobject.*;
import constants.Urls;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected HomePageScooter homePage;
    protected ScooterOrderPage scooterOrder;
    protected OrderDetailsPage orderDetails;
    public OrderStatusPage orderStatusPage;
    private YandexHomepage yandexHomepage;

    // метод для старта драйвера хром
    @Before
    public void startDriverChrome() {
        ChromeOptions options = new ChromeOptions();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new HomePageScooter(driver);
        scooterOrder = new ScooterOrderPage(driver);
        orderDetails = new OrderDetailsPage(driver);
        orderStatusPage = new OrderStatusPage(driver);
        yandexHomepage = new YandexHomepage(driver);
        //открыть домашнюю страницу
        driver.get(Urls.BASE_URL);
        // клик по кнопке принять кукис да все привыкли
        homePage.acceptCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    /* метод для старта драйвера хром фокс
    @Before
    public void startDriverFirefox() {

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver(options);
        homePage = new HomePageScooter(driver);
        scooterOrder = new ScooterOrderPage(driver);
        orderDetails = new OrderDetailsPage(driver);
        orderStatusPage = new OrderStatusPage(driver);
        yandexHomepage = new YandexHomepage(driver);

        //открыть домашнюю страницу
        driver.get(Urls.BASE_URL);
        // клик по кнопке принять кукис да все привыкли
        homePage.acceptCookies();
        String mainWindow = driver.getWindowHandle();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    } */

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
