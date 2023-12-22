package oncall.domain;

import oncall.domain.date.WorkDate;

import java.util.*;

public class WorkdaysSchedule {
    private final List<WorkDate> workDates;

    public WorkdaysSchedule(List<WorkDate> workDates) {
        this.workDates = new ArrayList<>(workDates);
    }

    public List<WorkDate> getWorkDates() {
        return Collections.unmodifiableList(workDates);
    }
}
