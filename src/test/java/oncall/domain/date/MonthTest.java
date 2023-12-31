package oncall.domain.date;

import oncall.domain.date.Month;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MonthTest {
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 13})
    void 월이_1에서_12_사이의_숫자가_아니라면_예외를_발생시킨다(int month) {
        assertThatThrownBy(() -> Month.of(month))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 월입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9 ,10, 11, 12})
    void 월이_1에서_12_사이의_숫자라면_예외를_발생시키지_않는다(int month) {
        assertThatCode(() -> Month.of(month))
                .doesNotThrowAnyException();
    }
}
