package constants;

public final class ValidationMessages {
    private ValidationMessages() {
    }

    // невалидные данные в поле Имя
    public static final String FIRST_NAME_INVALID  = "Введите корректное имя";

    // невалидные данные в поле Фамилия
    public static final String LAST_NAME_INVALID = "Введите корректную фамилию";

    // невалидные данные в поле адрес
    public static final String ADDRESS_INVALID = "Введите корректный адрес";
    // выбор станции метро
    public static final String METRO_REQUIRED = "Выберите станцию";
    // пустое поле телефон

    // невалидные данные в телефон
    public static final String PHONE_INVALID_FORMAT = "Введите корректный номер";
    // пустое поле когда привезти самокат

    // невалидные данные поле когда привезти самокат
    public static final String DATE_INVALID = "Введите корректную дату";
    //
    public static final String RENT_DAYS_REQUIRED = "Выберите срок аренды";
    //
}
