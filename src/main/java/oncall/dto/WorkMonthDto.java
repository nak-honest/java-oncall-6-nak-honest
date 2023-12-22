package oncall.dto;

public class WorkMonthDto {
    private final int month;
    private final String startDayOfWeek;

    public WorkMonthDto(int month, String startDayOfWeek) {
        this.month = month;
        this.startDayOfWeek = startDayOfWeek;
    }

    public int getMonth() {
        return month;
    }

    public String getStartDayOfWeek() {
        return startDayOfWeek;
    }
}
