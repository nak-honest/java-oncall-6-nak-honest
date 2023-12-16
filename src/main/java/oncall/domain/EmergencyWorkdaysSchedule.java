package oncall.domain;

import oncall.domain.date.Date;
import oncall.domain.date.WorkDate;

import java.util.*;

public class EmergencyWorkdaysSchedule {
    private final List<WorkDate> workDates;

    public EmergencyWorkdaysSchedule(List<WorkDate> workDates) {
        this.workDates = new ArrayList<>(workDates);
    }

    public List<WorkDate> getWorkDates() {
        return Collections.unmodifiableList(workDates);
    }
}
