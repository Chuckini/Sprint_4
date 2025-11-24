package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// класс страницы статуса заказа
public class OrderStatusPage {
    private WebDriver driver;


    public OrderStatusPage(WebDriver driver) {
        this.driver = driver;
    }
    //локатор для png с надписью "Такого заказа нет"
    private By notFoundImg = By.cssSelector("img[alt='Not found']");

    public boolean checkOrderNotFoundMessage() {// надпись "Такого заказа нет" на изображении png, если введен неверный номер заказа
        WebElement notFoundImage = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(notFoundImg));
        return notFoundImage.isDisplayed();
    }
}