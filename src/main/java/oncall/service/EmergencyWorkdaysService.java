package oncall.service;

import oncall.domain.*;
import oncall.domain.date.DateFactory;
import oncall.domain.date.Dates;
import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Month;
import oncall.dto.WorkMonthDto;

import java.util.List;

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

    public EmergencyWorkerOrder getEmergencyWorkerOrder(List<String> workers) {
        return new EmergencyWorkerOrder(workers.stream()
                .map(Worker::new)
                .toList());
    }

    public EmergencyWorkdaysSchedule schedule(Dates workDates, EmergencyWorkerOrders emergencyWorkerOrders) {
        return emergencyWorkdaysScheduler.schedule(workDates, emergencyWorkerOrders);
    }
}
