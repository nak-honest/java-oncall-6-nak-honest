package oncall;

import camp.nextstep.edu.missionutils.Console;
import oncall.controller.EmergencyWorkdaysController;
import oncall.domain.WorkdaysScheduler;
import oncall.service.EmergencyWorkdaysService;
import oncall.view.InputView;
import oncall.view.OutputView;
import oncall.view.Writer;

public class Application {
    public static void main(String[] args) {
        Writer writer = new Writer(System.out::print, System.out::println);
        InputView inputView = new InputView(Console::readLine, writer);
        OutputView outputView = new OutputView(writer);
        WorkdaysScheduler workdaysScheduler = new WorkdaysScheduler();
        EmergencyWorkdaysService emergencyWorkdaysService = new EmergencyWorkdaysService(workdaysScheduler);

        EmergencyWorkdaysController controller =
                new EmergencyWorkdaysController(inputView, outputView, emergencyWorkdaysService);
        controller.run();
    }
}
