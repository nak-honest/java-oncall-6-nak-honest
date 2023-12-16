package oncall.service;

import oncall.domain.*;
import oncall.domain.date.DateFactory;
import oncall.domain.date.Dates;
import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Month;
import oncall.dto.WorkMonthDto;

import java.util.List;

public class EmergencyWorkdaysService {
    private final WorkdaysScheduler workdaysScheduler;

    public EmergencyWorkdaysService(WorkdaysScheduler workdaysScheduler) {
        this.workdaysScheduler = workdaysScheduler;
    }

    public Dates getWorkDates(WorkMonthDto workMonthDto) {
        Month month = Month.of(workMonthDto.getMonth());
        DayOfWeek startDayOfWeek = DayOfWeek.of(workMonthDto.getStartDayOfWeek());

        return DateFactory.createDates(month, startDayOfWeek);
    }

    public WorkerOrder getWorkerOrder(List<String> workers) {
        return new WorkerOrder(workers.stream()
                .map(Worker::new)
                .toList());
    }

    public WorkdaysSchedule schedule(Dates workDates, WorkerOrders workerOrders) {
        return workdaysScheduler.schedule(workDates, workerOrders);
    }
}
