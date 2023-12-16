package oncall.view;

import oncall.domain.EmergencyWorkdaysSchedule;
import oncall.domain.date.DateType;
import oncall.domain.date.DayType;
import oncall.domain.date.WorkDate;

public class OutputView {
    private static final String SCHEDULE_FORMAT = "%d월 %d일 %s %s";
    private static final String HOLIDAY_SCHEDULE_FORMAT = "%d월 %d일 %s(휴일) %s";

    private final Writer writer;

    public OutputView(Writer writer) {
        this.writer = writer;
    }

    public void writeEmergencyWorkdaysSchedule(EmergencyWorkdaysSchedule emergencyWorkdaysSchedule) {
        writer.writeLine("");

        emergencyWorkdaysSchedule.getWorkDates().forEach(this::writeWorkDate);
    }

    private void writeWorkDate(WorkDate workDate) {
        int month = workDate.getMonth();
        int day = workDate.getDay();
        String dayOfWeek = workDate.getDayOfWeek();
        String workerName = workDate.getWorkerName();

        if (workDate.isDateType(DateType.HOLIDAY) && workDate.isDayType(DayType.WEEKDAY)) {
            writer.writeLine(String.format(HOLIDAY_SCHEDULE_FORMAT, month, day, dayOfWeek, workerName));
            return;
        }

        writer.writeLine(String.format(SCHEDULE_FORMAT, month, day, dayOfWeek, workerName));
    }
}
