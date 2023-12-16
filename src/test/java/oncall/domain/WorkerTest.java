package oncall.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WorkerTest {
    @ParameterizedTest
    @ValueSource(strings = {"이낙헌짱짱맨", "우아한테크코스가즈아", ""})
    void 근무자의_이름이_1자에서_5자_사이가_아니라면_예외를_발생시킨다(String name) {
        assertThatThrownBy(() -> new Worker(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 1자 이상 5자 이하로 입력해주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "Q"})
    void 근무자의_이름이_1자에서_5자_사이라면_예외를_발생시키지_않는다(String name) {
        assertThatCode(() -> new Worker(name))
                .doesNotThrowAnyException();
    }
}
