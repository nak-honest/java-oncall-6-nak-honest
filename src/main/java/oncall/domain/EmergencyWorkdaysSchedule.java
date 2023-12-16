package oncall.domain;

import oncall.domain.date.Date;
import oncall.domain.date.WorkDate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmergencyWorkdaysSchedule {
    private final List<WorkDate> workDates;

    public EmergencyWorkdaysSchedule(List<WorkDate> workDates) {
        this.workDates = new ArrayList<>(workDates);
    }
}
