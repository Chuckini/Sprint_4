package tests;

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
import pageobject.OrderStatusPage;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class WrongNumberStatus {
    private WebDriver driver;
    private HomePageScooter homePage;
    public OrderStatusPage orderStatusPage;

    String number;

    //конструктор
    public WrongNumberStatus(String number) {
        this.number = number;
    }

    @Parameterized.Parameters
    // тестовые данные
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Fdn$"},
                {"dg674"},
                {" "},

        });
    }

    @Before
    public void startDriverChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        homePage = new HomePageScooter(driver);
        orderStatusPage = new OrderStatusPage(driver);

        driver.get("https://qa-scooter.praktikum-services.ru/");
        homePage.acceptCookies();
    }
    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void wrongNumberStatusTest(){
        homePage.clickOrderStatusButton();
        homePage.enterTrackNumber(number);
        homePage.clikGoButton();
        orderStatusPage.checkOrderNotFoundMessage();

    }

}
