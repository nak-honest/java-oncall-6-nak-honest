package oncall.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DayOfWeekTest {
    @ParameterizedTest
    @ValueSource(strings = {"월요일", "슈", " ", "2"})
    void 요일이_월_화_수_목_금_토_일_중_하나가_아니라면_예외를_발생시킨다(String dayOfWeek) {
        assertThatThrownBy(() -> DayOfWeek.of(dayOfWeek))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 존재하지 않는 요일입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"월", "화", "수", "목", "금", "토", "일"})
    void 요일이_월_화_수_목_금_토_일_중_하나라면_예외를_발생시키지_않는다(String dayOfWeek) {
        assertThatCode(() -> DayOfWeek.of(dayOfWeek))
                .doesNotThrowAnyException();
    }
}
