package oncall.controller;

import oncall.domain.WorkdaysSchedule;
import oncall.domain.WorkerOrder;
import oncall.domain.WorkerOrders;
import oncall.domain.date.Dates;
import oncall.dto.WorkMonthDto;
import oncall.service.EmergencyWorkdaysService;
import oncall.util.ExceptionRetryHandler;
import oncall.view.InputView;
import oncall.view.OutputView;

public class EmergencyWorkdaysController {
    private final InputView inputView;
    private final OutputView outputView;
    private final EmergencyWorkdaysService emergencyWorkdaysService;

    public EmergencyWorkdaysController(InputView inputView, OutputView outputView,
                                       EmergencyWorkdaysService emergencyWorkdaysService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.emergencyWorkdaysService = emergencyWorkdaysService;
    }

    public void run() {
        Dates workDates = ExceptionRetryHandler.retryUntilValid(this::selectWorkDates);
        WorkerOrders workerOrders = ExceptionRetryHandler.retryUntilValid(this::selectEmergencyWorkerOrders);

        WorkdaysSchedule workdaysSchedule = emergencyWorkdaysService.schedule(workDates, workerOrders);

        outputView.writeWorkdaysSchedule(workdaysSchedule);
    }

    private Dates selectWorkDates() {
        WorkMonthDto workMonthDto = inputView.readWorkMonth();
        return emergencyWorkdaysService.getWorkDates(workMonthDto);
    }

    private WorkerOrders selectEmergencyWorkerOrders() {
        WorkerOrder emergencyNonHolidayWorkerOrder =
                emergencyWorkdaysService.getWorkerOrder(inputView.readNonHolidayWorkers());
        WorkerOrder emergencyHolidayWorkerOrder =
                emergencyWorkdaysService.getWorkerOrder(inputView.readHolidayWorkers());

        return new WorkerOrders(emergencyNonHolidayWorkerOrder, emergencyHolidayWorkerOrder);
    }
}
