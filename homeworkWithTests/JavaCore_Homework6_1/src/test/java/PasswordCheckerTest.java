
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


;

class PasswordCheckerTest {


    @ParameterizedTest
    @CsvSource(value = {
            "password, 5, 2, true",
            "pas, 5, 2, false",
            "passsword, 5, 2, false",
            "passworrrrd, 3, 3, false",
            "passs, 6, 2, false",
            "passswwword, 4, 3, true",

    })
    void verify(String password, int minLenght, int maxRepeats, boolean result) throws Exception{
        PasswordChecker passwordChecker = new PasswordChecker();
        boolean actual = passwordChecker.verify(password, minLenght, maxRepeats);
        Assertions.assertEquals(actual, result);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "-1, 0",
            "-3, -1",
            "-23, -3",
            "-1, -1"
    })
    void passwordCheckerPropertyTest(int minLength, int maxRepeats) throws Exception{
        PasswordChecker passwordChecker = new PasswordChecker();
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                passwordChecker.setMinLength(minLength);
        });
        IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            passwordChecker.setMaxRepeats(maxRepeats);
        });

        assertThat(thrown.getMessage(), equalTo("Минимальный размер пароля не может быть меньше нуля"));
        assertThat(thrown1.getMessage(), allOf(containsString("Максимальное количество"), endsWith(String.valueOf(maxRepeats))));

    }
}