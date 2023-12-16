package oncall.domain.date;

import oncall.domain.date.Date;
import oncall.domain.date.Dates;
import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Month;
import oncall.domain.iterator.LoopIterator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class DateFactory {
    public static Dates createDates(Month month, DayOfWeek startDayOfWeek) {
        List<DayOfWeek> dayOfWeeksOrder = Arrays.asList(DayOfWeek.values());
        initDayOfWeeks(dayOfWeeksOrder, startDayOfWeek);
        LoopIterator<DayOfWeek> dayOfWeekIterator = new LoopIterator<>(dayOfWeeksOrder);

        return new Dates(IntStream.rangeClosed(1, month.getMaxDay())
                .mapToObj(day -> createDate(month, day, dayOfWeekIterator))
                .toList());
    }

    private static void initDayOfWeeks(List<DayOfWeek> dayOfWeeksOrder, DayOfWeek startDayOfWeek) {
        while (dayOfWeeksOrder.get(0) != startDayOfWeek) {
            Collections.rotate(dayOfWeeksOrder, -1);
        }
    }

    private static Date createDate(Month month, int day, LoopIterator<DayOfWeek> dayOfWeekIterator) {
        return new Date(month, day, dayOfWeekIterator.next());
    }
}
