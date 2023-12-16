package oncall.controller;

import oncall.domain.EmergencyWorkdaysSchedule;
import oncall.domain.EmergencyWorkerOrder;
import oncall.domain.EmergencyWorkerOrders;
import oncall.domain.date.Dates;
import oncall.dto.WorkMonthDto;
import oncall.service.EmergencyWorkdaysService;
import oncall.view.InputView;
import oncall.view.OutputView;

import java.util.List;

public class EmergencyWorkdaysController {
    private final InputView inputView;
    private final OutputView outputView;
    private final EmergencyWorkdaysService emergencyWorkdaysService;

    public EmergencyWorkdaysController(InputView inputView, OutputView outputView, EmergencyWorkdaysService emergencyWorkdaysService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.emergencyWorkdaysService = emergencyWorkdaysService;
    }

    public void run() {
        Dates workDates = selectWorkDates();
        EmergencyWorkerOrders emergencyWorkerOrders = selectEmergencyWorkerOrders();

        EmergencyWorkdaysSchedule emergencyWorkdaysSchedule =
                emergencyWorkdaysService.schedule(workDates, emergencyWorkerOrders);

        outputView.writeEmergencyWorkdaysSchedule(emergencyWorkdaysSchedule);
    }

    private Dates selectWorkDates() {
        WorkMonthDto workMonthDto = inputView.readWorkMonth();
        return emergencyWorkdaysService.getWorkDates(workMonthDto);
    }

    private EmergencyWorkerOrders selectEmergencyWorkerOrders() {
        EmergencyWorkerOrder emergencyNonHolidayWorkerOrder =
                emergencyWorkdaysService.getEmergencyWorkerOrder(inputView.readNonHolidayEmergencyWorkers());
        EmergencyWorkerOrder emergencyHolidayWorkerOrder =
                emergencyWorkdaysService.getEmergencyWorkerOrder(inputView.readHolidayEmergencyWorkers());

        return new EmergencyWorkerOrders(emergencyNonHolidayWorkerOrder, emergencyHolidayWorkerOrder);
    }
}
