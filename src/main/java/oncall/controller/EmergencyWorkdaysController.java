package oncall.controller;

import oncall.domain.EmergencyWorkerOrder;
import oncall.domain.date.Dates;
import oncall.dto.WorkMonthDto;
import oncall.service.EmergencyWorkdaysService;
import oncall.view.InputView;
import oncall.view.OutputView;

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
        Dates workDates = emergencyWorkdaysService.getWorkDates(inputView.readWorkMonth());
        EmergencyWorkerOrder emergencyNonHolidayWorkerOrder =
                emergencyWorkdaysService.getEmergencyWorkerOrder(inputView.readNonHolidayEmergencyWorkers());
        EmergencyWorkerOrder emergencyHolidayWorkerOrder =
                emergencyWorkdaysService.getEmergencyWorkerOrder(inputView.readHolidayEmergencyWorkers());
    }
}
