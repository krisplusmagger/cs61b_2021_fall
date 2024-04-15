package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        if(isEmpty()) {
            return null;
        }
        T maxItem = this.get(0);
        int i = 0;
        while(i < this.size()) {
            if (comparator.compare(this.get(i), maxItem) > 0) {
                maxItem = this.get(i);
            }
            i += 1;
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if(isEmpty()) {
            return null;
        }
        T maxItem = this.get(0);
        int i = 0;
        while(i < this.size()) {
            if (c.compare(this.get(i), maxItem) > 0) {
                maxItem = this.get(i);
            }
            i += 1;
        }
        return maxItem;
    }

}
