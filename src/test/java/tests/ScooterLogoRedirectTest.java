package tests;

import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;


public class ScooterLogoRedirectTest extends BaseTest {

    @Test
    public void testLogoRedirectToHome() {
        // клик по кнопке принять кукис да все привыкли
        homePage.acceptCookies();
        // клик по кнопке Заказать
        homePage.clickFirstOrderButton();
        //ожидание загрузки страницы
        new WebDriverWait(driver, Duration.ofSeconds(5));
        // метод для клика на иконку Самокат
        scooterOrder.clickIconScooter();
        //проверить, что происходит переход на домашнюю страницу через URL
        assertEquals("Логотип не переводит на домашнюю страницу", "https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
        }
    }

