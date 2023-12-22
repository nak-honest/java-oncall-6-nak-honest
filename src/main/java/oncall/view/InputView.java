package oncall.view;

import oncall.dto.WorkMonthDto;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class InputView {
    private static final String INPUT_DELIMITER = ",";
    private static final int WORK_MONTH_SIZE = 2;
    private static final int MONTH_INDEX = 0;
    private static final int START_DAY_OF_WEEK_INDEX = 1;

    private final Supplier<String> reader;
    private final Writer writer;

    public InputView(Supplier<String> reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public WorkMonthDto readWorkMonth() {
        writer.write("비상 근무를 배정할 월과 시작 요일을 입력하세요> ");
        String input = reader.get();
        validateDelimiter(input);
        List<String> workMonth = Arrays.asList(input.split(INPUT_DELIMITER));
        validateWorkMonth(workMonth);

        try {
            return new WorkMonthDto(Integer.parseInt(workMonth.get(MONTH_INDEX)),
                    workMonth.get(START_DAY_OF_WEEK_INDEX));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 월은 숫자여야 합니다.");
        }
    }

    public List<String> readNonHolidayWorkers() {
        writer.write("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String input = reader.get();
        validateDelimiter(input);

        return Arrays.asList(input.split(INPUT_DELIMITER));
    }

    public List<String> readHolidayWorkers() {
        writer.write("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ");
        String input = reader.get();
        validateDelimiter(input);

        return Arrays.asList(input.split(INPUT_DELIMITER));
    }

    private void validateWorkMonth(List<String> workMonthDate) {
        validateWorkMonthSize(workMonthDate);
    }

    private void validateWorkMonthSize(List<String> workMonthDate) {
        if (workMonthDate.size() != WORK_MONTH_SIZE) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 월과 시작 요일은 %s로 구분되어야 합니다.", INPUT_DELIMITER));
        }
    }

    private void validateDelimiter(String input) {
        if (input.startsWith(INPUT_DELIMITER) || input.endsWith(INPUT_DELIMITER)) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 입력은 %s로 시작하거나 끝날 수 없습니다.", INPUT_DELIMITER));
        }
    }
}
