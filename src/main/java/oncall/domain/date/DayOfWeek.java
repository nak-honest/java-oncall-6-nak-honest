package oncall.domain.date;

import java.util.Arrays;

public enum DayOfWeek {
    MONDAY("월", DayType.WEEKDAY),
    TUESDAY("화", DayType.WEEKDAY),
    WEDNESDAY("수", DayType.WEEKDAY),
    THURSDAY("목", DayType.WEEKDAY),
    FRIDAY("금", DayType.WEEKDAY),
    SATURDAY("토", DayType.WEEKEND),
    SUNDAY("일", DayType.WEEKEND);

    private String value;
    private DayType dayType;

    DayOfWeek(String value, DayType dayType) {
        this.value = value;
        this.dayType = dayType;
    }

    public static DayOfWeek of(String value) {
        return Arrays.stream(values())
                .filter(dayOfWeek -> dayOfWeek.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 요일입니다."));
    }

    public boolean isDayType(DayType dayType) {
        return dayType.equals(this.dayType);
    }
}
