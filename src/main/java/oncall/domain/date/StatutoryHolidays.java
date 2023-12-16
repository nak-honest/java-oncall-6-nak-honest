package oncall.domain.date;

import java.util.Arrays;

/*
1월 1일
3월 1일
5월 5일
6월 6일
8월 15일
10월 3일
10월 9일
12월 25일
 */
public enum StatutoryHolidays {
    NEW_YEAR(Month.JANUARY, 1),
    INDEPENDENCE_MOVEMENT_DAY(Month.MARCH, 1),
    CHILDREN_DAY(Month.MAY, 5),
    MEMORIAL_DAY(Month.JUNE, 6),
    LIBERATION_DAY(Month.AUGUST, 15),
    NATIONAL_FOUNDATION_DAY(Month.OCTOBER, 3),
    HANGEUL_DAY(Month.OCTOBER, 9),
    CHRISTMAS(Month.DECEMBER, 25);

    private final Month month;
    private final int day;

    StatutoryHolidays(Month month, int day) {
        this.month = month;
        this.day = day;
    }

    public static boolean isStatutoryHoliday(Date date) {
        return Arrays.stream(values())
                .anyMatch(statutoryHoliday -> statutoryHoliday.isSameMonth(date) && statutoryHoliday.isSameDay(date));
    }

    private boolean isSameMonth(Date date) {
        return month == date.getMonth();
    }

    private boolean isSameDay(Date date) {
        return day == date.getDay();
    }
}
