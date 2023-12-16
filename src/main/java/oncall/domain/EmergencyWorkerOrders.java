package oncall.domain;

public class EmergencyWorkerOrders {
    private final EmergencyWorkerOrder nonHolidayEmergencyWorkerOrder;
    private final EmergencyWorkerOrder holidayEmergencyWorkerOrder;

    public EmergencyWorkerOrders(EmergencyWorkerOrder nonHolidayEmergencyWorkerOrder,
                                 EmergencyWorkerOrder holidayEmergencyWorkerOrder) {
        validateSameWorker(nonHolidayEmergencyWorkerOrder, holidayEmergencyWorkerOrder);

        this.nonHolidayEmergencyWorkerOrder = nonHolidayEmergencyWorkerOrder;
        this.holidayEmergencyWorkerOrder = holidayEmergencyWorkerOrder;
    }

    private void validateSameWorker(EmergencyWorkerOrder nonHolidayEmergencyWorkerOrder,
                                    EmergencyWorkerOrder holidayEmergencyWorkerOrder) {
        if (!nonHolidayEmergencyWorkerOrder.hasSameWorker(holidayEmergencyWorkerOrder)) {
            throw new IllegalArgumentException("[ERROR] 평일 근무자와 휴일 근무자는 같아야 합니다.");
        }
    }

    public EmergencyWorkerOrder getNonHolidayEmergencyWorkerOrder() {
        return nonHolidayEmergencyWorkerOrder;
    }

    public EmergencyWorkerOrder getHolidayEmergencyWorkerOrder() {
        return holidayEmergencyWorkerOrder;
    }
}
