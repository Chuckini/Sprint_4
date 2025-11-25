package tests;

import static constants.ValidationMessages.*;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ScooterOrderNegativeTest extends BaseTest{

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

    @Test
    public void invalidTest() {

        // клик по кнопке Заказать
        homePage.clickFirstOrderButton();

        //  заполняем только одно поле, которое указано в caseName
        switch (caseName) {
            case "Пустое поле Имя":
            case "Невалидное имя":
                scooterOrder.setFirstName(firstName);
                scooterOrder.unfocus();
                break;

            case "Пустое поле Фамилия":
            case "Невалидная фамилия":
                scooterOrder.setLastName(lastName);
                scooterOrder.unfocus();
                break;

            case "Пустое поле адрес":
                scooterOrder.setAddress(address);
                scooterOrder.unfocus();
                break;

            case "Пустое поле метро":
                scooterOrder.unfocus(); // ничего не выбираем
                break;

            case "Пустое поле номер телефона":
            case "Невалидный номер телефона":
                scooterOrder.setPhone(phoneNumber);
                scooterOrder.unfocus();
                break;
        }

        String actualError = getErrorByCaseName(caseName);
        assertEquals("Ошибка в случае: " + caseName, expectedError, actualError);

    }

    private String getErrorByCaseName(String caseName) {
        switch (caseName) {
            case "Пустое поле Имя":
            case "Невалидное имя":
                return scooterOrder.getFirstNameErrorText();

            case "Пустое поле Фамилия":
            case "Невалидная фамилия":
                return scooterOrder.getLastNameErrorText();

            case "Пустое поле адрес":
                return scooterOrder.getAddressErrorText();

            case "Пустое поле метро":
                return scooterOrder.getMetroErrorText();

            case "Пустое поле номер телефона":
            case "Невалидный номер телефона":
                return scooterOrder.getPhoneErrorText();

            default:
                return "";
        }
    }
}





