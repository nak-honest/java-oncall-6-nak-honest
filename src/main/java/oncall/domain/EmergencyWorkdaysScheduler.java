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
        LoopIterator<Worker> holidayWorkerOrderIterator =
                emergencyWorkerOrders.getHolidayEmergencyWorkerOrder().getLoopIterator();
        LoopIterator<Worker> nonHolidayWorkerOrderIterator =
                emergencyWorkerOrders.getNonHolidayEmergencyWorkerOrder().getLoopIterator();
        assignWorkerDates(dates, holidayWorkerOrderIterator, nonHolidayWorkerOrderIterator);

        return new EmergencyWorkdaysSchedule(new ArrayList<>(workDates));
    }

    private void assignWorkerDates(Dates dates, LoopIterator<Worker> holidayWorkerOrderIterator,
                                                   LoopIterator<Worker> nonHolidayWorkerOrderIterator) {
        dates.getDates().forEach(date ->
                assignWorkDate(date, holidayWorkerOrderIterator, nonHolidayWorkerOrderIterator));
    }

    private void assignWorkDate(Date date, LoopIterator<Worker> holidayWorkerOrderIterator,
                                    LoopIterator<Worker> nonHolidayWorkerOrderIterator) {
        if (isHoliday(date)) {
            Worker nextWorker = getNextWorker(workDates, holidayWorkerOrderIterator, restHolidayWorkers);
            workDates.add(new WorkDate(date, DateType.HOLIDAY, nextWorker));
            return;
        }

        Worker nextWorker = getNextWorker(workDates, nonHolidayWorkerOrderIterator, restNonHolidayWorkers);
        workDates.add(new WorkDate(date, DateType.NON_HOLIDAY, nextWorker));
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
            return getNextWorkerWithRestWorkers(previousWorker, workerOrder, restWorkers);
        }

        return getNextWorkerWithoutRestWorkers(previousWorker, workerOrder, restWorkers);
    }

    private Worker getNextWorkerWithRestWorkers(Worker previousWorker, LoopIterator<Worker> workerOrder,
                                                Queue<Worker> restWorkers) {
        if (previousWorker.equals(restWorkers.peek())) {
            return workerOrder.next();
        }

        return restWorkers.poll();
    }

    private Worker getNextWorkerWithoutRestWorkers(Worker previousWorker, LoopIterator<Worker> workerOrder,
                                                   Queue<Worker> restWorkers) {
        Worker nextWorker = workerOrder.next();

        if (previousWorker.equals(nextWorker)) {
            restWorkers.add(nextWorker);
            return workerOrder.next();
        }

        return nextWorker;
    }
}
