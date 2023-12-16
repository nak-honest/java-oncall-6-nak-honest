package oncall.domain;

import oncall.domain.iterator.LoopIterator;

import java.util.ArrayList;
import java.util.List;

public final class WorkerOrder {
    private static final int MIN_WORKER_COUNT = 5;
    private static final int MAX_WORKER_COUNT = 35;

    private final List<Worker> workers;

    public WorkerOrder(List<Worker> workers) {
        this.workers = new ArrayList<>(workers);
        validate();
    }

    private void validate() {
        validateWorkerCount();
        validateDuplicateWorker();
    }

    private void validateWorkerCount() {
        if (workers.size() < MIN_WORKER_COUNT || workers.size() > MAX_WORKER_COUNT) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 근무자는 %d명 이상 %d명 이하로 입력해주세요.", MIN_WORKER_COUNT, MAX_WORKER_COUNT));
        }
    }

    private void validateDuplicateWorker() {
        if (workers.stream().distinct().count() != workers.size()) {
            throw new IllegalArgumentException("[ERROR] 근무자의 이름은 중복될 수 없습니다.");
        }
    }

    public boolean isSameWorkers(WorkerOrder otherWorkerOrder) {
        return hasSameSize(otherWorkerOrder) && containsAll(otherWorkerOrder);
    }

    private boolean hasSameSize(WorkerOrder otherWorkerOrder) {
        return workers.size() == otherWorkerOrder.workers.size();
    }

    private boolean containsAll(WorkerOrder otherWorkerOrder) {
        return workers.containsAll(otherWorkerOrder.workers);
    }

    public LoopIterator<Worker> getLoopIterator() {
        return new LoopIterator<>(workers);
    }
}
