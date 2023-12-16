package oncall.domain.date;

public class Date {
    private final Month month;
    private final int day;
    public final DayOfWeek dayOfWeek;

    public Date(Month month, int day, DayOfWeek DayOfWeek) {
        this.month = month;
        this.day = day;
        this.dayOfWeek = DayOfWeek;
    }

    public boolean isDayType(DayType dayType) {
        return dayOfWeek.isDayType(dayType);
    }

    public Month getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public String getDayOfWeek() {
        return dayOfWeek.getValue();
    }
}
