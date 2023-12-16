package oncall.service;

import oncall.domain.EmergencyWorkdaysSchedule;
import oncall.domain.EmergencyWorkdaysScheduler;
import oncall.domain.date.DateFactory;
import oncall.domain.date.Dates;
import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Month;
import oncall.dto.WorkMonthDto;

public class EmergencyWorkdaysService {
    private final EmergencyWorkdaysScheduler emergencyWorkdaysScheduler;

    public EmergencyWorkdaysService(EmergencyWorkdaysScheduler emergencyWorkdaysScheduler) {
        this.emergencyWorkdaysScheduler = emergencyWorkdaysScheduler;
    }

    public Dates getWorkDates(WorkMonthDto workMonthDto) {
        Month month = Month.of(workMonthDto.getMonth());
        DayOfWeek startDayOfWeek = DayOfWeek.of(workMonthDto.getStartDayOfWeek());

        return DateFactory.createDates(month, startDayOfWeek);
    }
}
