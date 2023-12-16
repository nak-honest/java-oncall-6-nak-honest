package oncall.domain.date;

import java.util.Arrays;

public enum DayOfWeek {
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일");

    private String value;

    DayOfWeek(String value) {
        this.value = value;
    }

    public static DayOfWeek of(String value) {
        return Arrays.stream(values())
                .filter(dayOfWeek -> dayOfWeek.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 요일입니다."));
    }
}
