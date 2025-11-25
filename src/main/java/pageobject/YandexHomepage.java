package pageobject;

import org.openqa.selenium.WebDriver;

public class YandexHomepage {
    public WebDriver driver;
    public YandexHomepage(WebDriver driver) {
        this.driver = driver;
    }

        public String getURL(){
        return "https://dzen.ru/?yredirect=true";
        }
}
