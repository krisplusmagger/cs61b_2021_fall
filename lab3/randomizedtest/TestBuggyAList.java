package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> al1 = new AListNoResizing<Integer>();
        BuggyAList<Integer> al2 = new BuggyAList<Integer>();

        for(int i = 0; i < 4; i++) {
            al1.addLast(i);
            al2.addLast(i);
        }
        for(int i = 0; i < 4; i++) {
            assertEquals("they are not equal", al1.removeLast(), al2.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<Integer>();
        BuggyAList<Integer> L2 = new BuggyAList<Integer>();

        int N = 5000;
        for(int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 4);
            if(operationNumber == 0 ) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1 && L.size() != 0 && L2.size() != 0) {
//                int size = L.size();
                int item_a = L.removeLast();
                int item_b = L2.removeLast();
                assertEquals("getLast not equal error:", item_a, item_b);
//                System.out.println("size: " + size);
            } else if (operationNumber == 2 && L.size() != 0 && L2.size() != 0) {
                int p = L.getLast();
                int p_2 = L2.getLast();
                assertEquals("removeLast not equal error:", p, p_2);
            } else{
                int size1 = L.size();
                int size2 = L2.size();
                assertEquals("sizes are not equal", size1, size2);
            }
        }
    }
}
