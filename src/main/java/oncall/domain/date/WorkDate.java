package oncall.domain.date;

import oncall.domain.Worker;

public class WorkDate {
    Date date;
    DateType dateType;
    Worker worker;

    public WorkDate(Date date, DateType dateType, Worker worker) {
        this.date = date;
        this.dateType = dateType;
        this.worker = worker;
    }

    public Worker getWorker() {
        return worker;
    }
}
