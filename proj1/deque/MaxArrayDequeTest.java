package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayDequeTest  {

    @Test
    public void testMaxWithComparator() {
        //创建比较器，用于元素长度的比较
        Comparator<String> lengthComparator = new Comparator<String>() {
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        };

        MaxArrayDeque<String> deque1 = new MaxArrayDeque<>(lengthComparator);

        // 添加一些字符串到 deque 中
        deque1.addLast("apple");
        deque1.addLast("banana");
        deque1.addLast("pear");

        // 测试 max() 方法是否按照比较器指定的顺序返回最大元素
        assertEquals("banana", deque1.max());

        // 测试 removeMax() 方法是否按照比较器指定的顺序移除最大元素
        assertEquals("pear", deque1.removeLast());
        assertEquals("banana", deque1.removeLast());
        assertEquals("apple", deque1.removeLast());
    }

}
