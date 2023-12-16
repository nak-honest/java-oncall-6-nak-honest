package oncall.domain.date;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dates {
    private final List<Date> dates;

    public Dates(List<Date> dates) {
        this.dates = new ArrayList<>(dates);
    }

    public Dates getHolidayDates() {
        List<Date> holidayDates = new ArrayList<>();
        for (Date date : dates) {
            if (isHoliday(date)) {
                holidayDates.add(date);
            }
        }
        return new Dates(holidayDates);
    }

    public Dates getNonHolidayDates() {
        List<Date> nonHolidayDates = new ArrayList<>();
        for (Date date : dates) {
            if (!isHoliday(date)) {
                nonHolidayDates.add(date);
            }
        }
        return new Dates(nonHolidayDates);
    }

    private boolean isHoliday(Date date) {
        return StatutoryHolidays.isStatutoryHoliday(date) || date.isDayType(DayType.WEEKEND);
    }

    public List<Date> getDates() {
        return Collections.unmodifiableList(dates);
    }
}
