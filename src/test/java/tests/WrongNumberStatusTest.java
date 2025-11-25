package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class WrongNumberStatusTest extends BaseTest{

    String number;

    //конструктор
    public WrongNumberStatusTest(String number) {
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

    @Test
    public void wrongNumberStatusTest(){
        homePage.clickOrderStatusButton();
        homePage.enterTrackNumber(number);
        homePage.clikGoButton();
        orderStatusPage.checkOrderNotFoundMessage();

    }
}
