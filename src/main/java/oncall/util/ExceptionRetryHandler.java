package oncall.util;

import java.util.function.Function;
import java.util.function.Supplier;

public class ExceptionRetryHandler {
    private ExceptionRetryHandler() {}

    public static <R> R retryUntilValid(Supplier<R> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + System.lineSeparator());
            }
        }
    }
}
