package deque;

import org.antlr.v4.runtime.misc.ObjectEqualityComparator;

public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    private int front_p;
    private int back_p;
    private int length;


    /** Create an empty list*/
    public ArrayDeque() {
        items = (T[]) new Object[8];
        length = 8;
        size = 0;
        back_p = 4;
        front_p = 4;
    }
    private int minusOne(int index) {
        if(index == 0) {
            return length - 1;
        }
        return index - 1;
    }
    private int plusOne(int index, int module) {
        // 10 % 15 = 10
        index %= module;
        if(index == module - 1) {
            return 0;
        }
        return index + 1;
    }
    private void grow() {
        T[] newArray = (T[]) new Object[length * 2];
        int ptr1 = front_p;
        int ptr2 = length;
        while(ptr1 != back_p) {

            newArray[ptr2] = items[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length * 2);
        }
        newArray[ptr2] = items[ptr1];
        front_p = length;
        back_p = ptr2;
        items = newArray;
        length *= 2;

    }
    private void shrink() {
        T[] newArray = (T[]) new Object[length / 2];
        int ptr1 = front_p;
        int ptr2 = length / 4;
        while(ptr1 != back_p) {
            newArray[ptr2] = items[ptr1];
            ptr1 = plusOne(ptr1, length);
            ptr2 = plusOne(ptr2, length / 2);
        }
        newArray[ptr2] = items[ptr1];
        front_p = length / 4;
        back_p = ptr2;
        items = newArray;
        length /= 2;

    }


    public void addFirst(T x) {
        //如果size满了
        if (size == length - 1) {
            grow();
        }
        //for speical case if size == 0
        if(size == 0) {
            items[front_p] = x;
//            front_p = minusOne(front_p);
            size += 1;
            return;
        }
        front_p = minusOne(front_p);
        items[front_p] = x;
        size += 1;
    }
    /** Inserts X into the back of the list */
    public void addLast(T x) {
        if(size == length - 1) {
            grow();
        }
        //for speical case if size == 0
        if(size == 0) {
            items[back_p] = x;
//            back_p = plusOne(back_p, length);
            size += 1;
            return;
        }
        back_p = plusOne(back_p, length);
        items[back_p] = x;
        size += 1;
    }

    public T removeFirst() {
        if(length >= 16 && length /size >= 4) {
            shrink();
        }
        if(size == 0) {
            return null;
        }

        T item = items[front_p];
        front_p = plusOne(front_p, length);
        size -= 1;
        return item;
    }

    /** Remove the element of the back*/
    public T removeLast() {

        if(length >= 16 && length / size >= 4) {
            shrink();
        }
        if(size == 0) {
            return null;
        }

        T item = items[back_p];
        back_p = minusOne(back_p);
        size -= 1;
        return item;
    }
    public T get(int index) {
        if(index >= size) {
            return null;
        }
        int ptr = front_p;
        for(int i = 0; i < index; i++) {
            ptr = plusOne(ptr, length);
        }
        return items[ptr];
    }
    public void printDeque() {
        int ptr = front_p;
        while(ptr != back_p) {
            System.out.print(items[ptr] + " ");
            ptr = plusOne(ptr, length);
        }
        System.out.println(" ");
    }
    public int size(){
        return size;
    }
//    public boolean isEmpty(){
//        return size == 0;
//    }

}
