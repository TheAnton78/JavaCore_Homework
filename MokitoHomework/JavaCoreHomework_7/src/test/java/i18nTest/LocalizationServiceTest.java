package i18nTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;


public class LocalizationServiceTest {

    @ParameterizedTest
    @CsvSource(value = {
            "USA, Welcome",
            "RUSSIA, Добро пожаловать",
            "GERMANY, Welcome",
            "BRAZIL, Welcome"
    })
    void locateTest(String country, String result){
        LocalizationService localizationService = new LocalizationServiceImpl();
        String actual = localizationService.locale(Country.valueOf(country));
        Assertions.assertEquals(actual, result);

    }
}
