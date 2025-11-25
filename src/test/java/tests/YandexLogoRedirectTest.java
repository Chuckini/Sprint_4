package tests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class YandexLogoRedirectTest extends BaseTest{

    @Test
    public void YandexLogoRedirectTest() {
            homePage.clickYandexLogo();
            String href = homePage.getYandexLogoHref();
            assertEquals("https://yandex.ru/", href);
    }
}


