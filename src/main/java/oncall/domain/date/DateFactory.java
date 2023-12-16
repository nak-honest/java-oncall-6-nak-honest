package oncall.domain.date;

import oncall.domain.date.Date;
import oncall.domain.date.Dates;
import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Month;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class DateFactory {
    public static Dates createDates(Month month, DayOfWeek startDayOfWeek) {
        List<DayOfWeek> dayOfWeeksOrder = Arrays.asList(DayOfWeek.values());
        initDayOfWeeks(dayOfWeeksOrder, startDayOfWeek);

        return new Dates(IntStream.rangeClosed(1, month.getMaxDay())
                .mapToObj(day -> createDate(month, day, dayOfWeeksOrder))
                .toList());
    }

    private static void initDayOfWeeks(List<DayOfWeek> dayOfWeeksOrder, DayOfWeek startDayOfWeek) {
        while (dayOfWeeksOrder.get(0) != startDayOfWeek) {
            Collections.rotate(dayOfWeeksOrder, -1);
        }
    }

    private static Date createDate(Month month, int day, List<DayOfWeek> dayOfWeeksOrder) {
        DayOfWeek dayOfWeek = dayOfWeeksOrder.get(0);
        Collections.rotate(dayOfWeeksOrder, -1);

        return new Date(month, day, dayOfWeek);
    }
}
