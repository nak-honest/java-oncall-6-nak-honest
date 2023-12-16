package oncall.domain.date;

import java.util.Arrays;

public enum Month {
    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    private int value;
    private int maxDay;

    Month(int value, int maxDay) {
        this.value = value;
        this.maxDay = maxDay;
    }

    public static Month of(int value) {
        return Arrays.stream(values())
                .filter(month -> value == month.value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 월입니다."));
    }

    public int getMaxDay() {
        return maxDay;
    }

    public int getValue() {
        return value;
    }
}
