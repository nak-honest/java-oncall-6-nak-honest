package oncall.domain;

import oncall.domain.date.*;
import oncall.domain.date.Date;
import oncall.domain.iterator.LoopIterator;

import java.util.*;

public class EmergencyWorkdaysScheduler {
    private final Queue<Worker> restHolidayWorkers = new LinkedList<>();
    private final Queue<Worker> restNonHolidayWorkers = new LinkedList<>();
    private final List<WorkDate> workDates = new ArrayList<>();

    public EmergencyWorkdaysScheduler() {
    }

    public EmergencyWorkdaysSchedule schedule(Dates dates, EmergencyWorkerOrders emergencyWorkerOrders) {
        LoopIterator<Worker> holidayWorkerIterator = emergencyWorkerOrders.getHolidayWorkerIterator();
        LoopIterator<Worker> nonHolidayWorkerIterator = emergencyWorkerOrders.getNonHolidayWorkerIterator();
        assignWorkerDates(dates, holidayWorkerIterator, nonHolidayWorkerIterator);

        return new EmergencyWorkdaysSchedule(new ArrayList<>(workDates));
    }

    private void assignWorkerDates(Dates dates, LoopIterator<Worker> holidayWorkerIterator,
                                   LoopIterator<Worker> nonHolidayWorkerIterator) {
        dates.getDates().forEach(date -> assignWorkDate(date, holidayWorkerIterator, nonHolidayWorkerIterator));
    }

    private void assignWorkDate(Date date, LoopIterator<Worker> holidayWorkerIterator,
                                    LoopIterator<Worker> nonHolidayWorkerIterator) {
        if (isHoliday(date)) {
            Worker nextWorker = getNextWorker(workDates, holidayWorkerIterator, restHolidayWorkers);
            workDates.add(new WorkDate(date, DateType.HOLIDAY, nextWorker));
            return;
        }

        Worker nextWorker = getNextWorker(workDates, nonHolidayWorkerIterator, restNonHolidayWorkers);
        workDates.add(new WorkDate(date, DateType.NON_HOLIDAY, nextWorker));
    }

    private boolean isHoliday(Date date) {
        return StatutoryHolidays.isStatutoryHoliday(date) || date.isDayType(DayType.WEEKEND);
    }

    private Worker getNextWorker(List<WorkDate> workDates, LoopIterator<Worker> workerIterator,
                                 Queue<Worker> restWorkers) {
        if (workDates.isEmpty()) {
            return workerIterator.next();
        }
        Worker previousWorker = workDates.get(workDates.size() - 1).getWorker();

        if (!restWorkers.isEmpty()) {
            return getNextWorkerWithRestWorkers(previousWorker, workerIterator, restWorkers);
        }

        return getNextWorkerWithoutRestWorkers(previousWorker, workerIterator, restWorkers);
    }

    private Worker getNextWorkerWithRestWorkers(Worker previousWorker, LoopIterator<Worker> workerOrder,
                                                Queue<Worker> restWorkers) {
        if (previousWorker.equals(restWorkers.peek())) {
            return workerOrder.next();
        }

        return restWorkers.poll();
    }

    private Worker getNextWorkerWithoutRestWorkers(Worker previousWorker, LoopIterator<Worker> workerIterator,
                                                   Queue<Worker> restWorkers) {
        Worker nextWorker = workerIterator.next();

        if (previousWorker.equals(nextWorker)) {
            restWorkers.add(nextWorker);
            return workerIterator.next();
        }

        return nextWorker;
    }
}
