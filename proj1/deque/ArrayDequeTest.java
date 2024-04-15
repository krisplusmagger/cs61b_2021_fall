package deque;

import org.junit.Test;
import static org.junit.Assert.*;


public class ArrayDequeTest {

    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String>  aad1 = new ArrayDeque<String>();
        assertTrue("A newly initialized ArrayDeque should be empty", aad1.isEmpty());

        aad1.addFirst("front");
        assertEquals(1, aad1.size());
        assertFalse("aad1 should now contain 1 item", aad1.isEmpty());

        aad1.addLast("middle");
        assertEquals(2, aad1.size());
        assertFalse("aad1 should now contain 2 item", aad1.isEmpty());

        aad1.addLast("last");
        assertEquals(3, aad1.size());
        assertFalse("aad1 should now contain 3 item", aad1.isEmpty());

        System.out.println("Printing out deque: ");
        aad1.printDeque();
    }
    @Test
    public void addRemoveTest(){
        ArrayDeque<Integer> aad1 = new ArrayDeque<Integer>();
        assertTrue("aad1 should be empty upon initialization", aad1.isEmpty());

        aad1.addFirst(19);
        assertFalse("aad1 should contain 1 item", aad1.isEmpty());

        aad1.removeFirst();
        assertTrue("aad1 should be empty after removal", aad1.isEmpty());
    }
    @Test
    public void removeEmptyTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> aad1 = new ArrayDeque<Integer>();
        aad1.addFirst(3);

        aad1.removeLast();
        aad1.removeFirst();
        aad1.removeLast();
        aad1.removeFirst();

        int size = aad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }
    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {


        ArrayDeque<String>  aad1 = new ArrayDeque<String>();
        ArrayDeque<Double>  aad2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> aad3 = new ArrayDeque<Boolean>();

        aad1.addFirst("string");
        aad2.addFirst(3.14159);
        aad3.addFirst(true);

        String s = aad1.removeFirst();
        double d = aad2.removeFirst();
        boolean b = aad3.removeFirst();

    }
    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

//        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }


    }

    @Test
    public void testaddsizeempty() {
        ArrayDeque<String> dq = new ArrayDeque<>();
        assertEquals(true, dq.isEmpty());

        dq.addFirst("first");
        assertEquals(1, dq.size());

        dq.addLast("last");
        assertEquals(2, dq.size());

        dq.printDeque();

        String first = dq.removeFirst();
        assertEquals("first", first);

        String last = dq.removeLast();
        assertEquals("last", last);

        assertEquals(0, dq.size());
    }

//    public ArrayDeque<Integer> create(int[] array) {
//        ArrayDeque<Integer> dq = new ArrayDeque<>();
//        for (int x : array) {
//            dq.addLast(x);
//        }
//        return dq;
//    }
//    @Test

//    public void testgrowshrink() {
//        ArrayDeque<Integer> dq = new ArrayDeque<>();
//        for (int i = 0; i < 16; i++) {
//            dq.addLast(i);
//        }
//        for (int i = -16; i < 0; i++) {
//            dq.addFirst(i);
//        }
//        for (int i = -1; i >= 16; i--) {
//            assertEquals(i, dq.get(i));
//        }
//        for (int i = 0; i < 30; i++) {
//            dq.removeFirst();
//        }
//        assertEquals(2, dq.size());
//        dq.printDeque();
//    }
//    public void main(String[] args) {
//        testgrowshrink();
//    }
}