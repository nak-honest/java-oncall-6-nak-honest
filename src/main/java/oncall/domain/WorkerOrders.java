package oncall.domain;

import oncall.domain.iterator.LoopIterator;

public class WorkerOrders {
    private final WorkerOrder nonHolidayWorkerOrder;
    private final WorkerOrder holidayWorkerOrder;

    public WorkerOrders(WorkerOrder nonHolidayWorkerOrder,
                        WorkerOrder holidayWorkerOrder) {
        validateSameWorker(nonHolidayWorkerOrder, holidayWorkerOrder);

        this.nonHolidayWorkerOrder = nonHolidayWorkerOrder;
        this.holidayWorkerOrder = holidayWorkerOrder;
    }

    private void validateSameWorker(WorkerOrder nonHolidayWorkerOrder,
                                    WorkerOrder holidayWorkerOrder) {
        if (!nonHolidayWorkerOrder.isSameWorkers(holidayWorkerOrder)) {
            throw new IllegalArgumentException("[ERROR] 평일 근무자와 휴일 근무자는 같아야 합니다.");
        }
    }

    public LoopIterator<Worker> getNonHolidayWorkerIterator() {
        return nonHolidayWorkerOrder.getLoopIterator();
    }

    public LoopIterator<Worker> getHolidayWorkerIterator() {
        return holidayWorkerOrder.getLoopIterator();
    }
}
