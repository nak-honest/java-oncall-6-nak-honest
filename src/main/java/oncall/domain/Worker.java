package oncall.domain;

import java.util.Objects;

public class Worker {
    private static final int MIN_NAME_LENGTH = 1;
    private static final int MAX_NAME_LENGTH = 5;

    private final String name;

    public Worker(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 이름은 %d자 이상 %d자 이하로 입력해주세요.", MIN_NAME_LENGTH, MAX_NAME_LENGTH));
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Worker worker)) {
            return false;
        }

        return name.equals(worker.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
