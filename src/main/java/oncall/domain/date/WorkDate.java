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

    public int getMonth() {
        return date.getMonth().getValue();
    }

    public int getDay() {
        return date.getDay();
    }

    public String getDayOfWeek() {
        return date.getDayOfWeek();
    }

    public String getWorkerName() {
        return worker.getName();
    }

    public boolean isDateType(DateType dateType) {
        return this.dateType.equals(dateType);
    }

    public boolean isDayType(DayType dayType) {
        return date.isDayType(dayType);
    }
}
