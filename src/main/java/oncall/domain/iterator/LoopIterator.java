package oncall.domain.iterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoopIterator<T> {
    private final List<T> items;

    public LoopIterator(List<T> items) {
        this.items = new ArrayList<>(items);
    }
    public T next() {
        T item = items.get(0);
        Collections.rotate(items, -1);

        return item;
    }
}
