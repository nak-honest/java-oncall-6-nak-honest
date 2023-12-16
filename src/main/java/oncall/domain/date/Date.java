package oncall.domain.date;

public class Date {
    private final Month month;
    private final int day;
    public final DayOfWeek DayOfWeek;

    public Date(Month month, int day, DayOfWeek DayOfWeek) {
        this.month = month;
        this.day = day;
        this.DayOfWeek = DayOfWeek;
    }
}
