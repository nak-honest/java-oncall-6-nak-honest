package oncall.domain;

import oncall.domain.date.*;
import oncall.domain.date.Date;
import oncall.domain.iterator.LoopIterator;

import java.util.*;

public class EmergencyWorkdaysScheduler {
    public EmergencyWorkdaysSchedule schedule(Dates dates, EmergencyWorkerOrder holidayWorkerOrder,
                                              EmergencyWorkerOrder nonHolidayWorkerOrder) {
        Queue<Worker> restHolidayWorkers = new LinkedList<>();
        Queue<Worker> restNonHolidayWorkers = new LinkedList<>();
        LoopIterator<Worker> holidayWorkerOrderIterator = holidayWorkerOrder.getLoopIterator();
        LoopIterator<Worker> nonHolidayWorkerOrderIterator = nonHolidayWorkerOrder.getLoopIterator();
        List<WorkDate> workDates = new ArrayList<>();

        for (Date date : dates.getDates()) {
            if (isHoliday(date)) {
                Worker nextWorker =
                        getNextWorker(workDates, holidayWorkerOrderIterator, restHolidayWorkers);
                workDates.add(new WorkDate(date, DateType.HOLIDAY, nextWorker));
                continue;
            }

            Worker nextWorker =
                    getNextWorker(workDates, nonHolidayWorkerOrderIterator, restNonHolidayWorkers);
            workDates.add(new WorkDate(date, DateType.NON_HOLIDAY, nextWorker));
        }

        return new EmergencyWorkdaysSchedule(workDates);
    }

    private boolean isHoliday(Date date) {
        return StatutoryHolidays.isStatutoryHoliday(date) || date.isDayType(DayType.WEEKEND);
    }

    private Worker getNextWorker(List<WorkDate> workDates, LoopIterator<Worker> workerOrder,
                              Queue<Worker> restWorkers) {
        if (workDates.isEmpty()) {
            return workerOrder.next();
        }

        Worker previousWorker = workDates.get(workDates.size() - 1).getWorker();

        if (!restWorkers.isEmpty()) {
            if (previousWorker.equals(restWorkers.peek())) {
                return workerOrder.next();
            }

            return restWorkers.poll();
        }

        Worker nextWorker = workerOrder.next();

        if (previousWorker.equals(nextWorker)) {
            restWorkers.add(nextWorker);
            return workerOrder.next();
        }

        return nextWorker;
    }
}
