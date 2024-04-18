package deque;

import jh61b.junit.In;
import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class TNode {
        public T item;
        public TNode prev;
        public TNode next;

        public TNode(T x, TNode prev_node, TNode next_node) {
            item = x;
            next = next_node;
            prev = prev_node;
        }
    }

    private int size;
    private TNode sentinel;


    /** Creates an empty LinkedListDeque */
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        size = 0;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;

    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int wizPos;
        private int position;
        private TNode returnItem;

        public LinkedListIterator() {
            wizPos = 0;
            returnItem = sentinel;
        }


        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {

            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    /** Adds item to the front of the deque. */
    public void addFirst(T x) {
        TNode rest = sentinel.next;
        sentinel.next = new TNode(x, sentinel, rest);
        rest.prev = sentinel.next;
        size += 1;
    }
    /** Adds item to the back of the deque. */
    public void addLast(T x) {

        TNode back = sentinel.prev;
        sentinel.prev = new TNode(x, back, sentinel);
        back.next = sentinel.prev;
        size += 1;
    }

    public T removeFirst() {
        if(size > 0) {
            TNode p = sentinel.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.next.prev = sentinel;
            size -= 1;
            p.next = null;
            p.prev = null;
            return p.item;
        }
        else {
            return null;
        }
    }

    public T removeLast() {
        if(size > 0) {
           TNode p = sentinel.prev;
           sentinel.prev = sentinel.prev.prev;
           sentinel.prev.prev.next = sentinel;
           p.next = null;
           p.prev = null;
           size -= 1;
           return p.item;
        }
        else{
            return  null;
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth */
    public T get(int index) {
        if (size == 0 || index < 0) {
            return null;
        }
        TNode p = sentinel.next;
        int count = 0;
       while (p != sentinel) {
           if (index == count) {
               return p.item;
           }
           count += 1;
           p = p.next;
       }
       return null;
    }
    private T getRecursiveHelper(int index, TNode p){

        if (p == sentinel) {
            return null;
        }
        if (index == 0) {
            return p.item;
        }
        return  getRecursiveHelper(index - 1, p.next);

    }
    public T getRecursive(int index) {

        if (size == 0 || index < 0) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }



    public int size() {
        return size;
    }

    public void printDeque(){

        TNode p;
        p = sentinel.next;
        if (p == null) {
            System.out.println("Empty deque");
        }

        while(p != sentinel) {
            if (p.next == sentinel) {
                System.out.print(p.item);
                System.out.println();
                return;
            }
            System.out.print(p.item + " -> ");
            p = p.next;
        }
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for(int i = 0; i < other.size(); i += 1) {
            T thisItem = this.get(i);
            T otherItem = other.get(i);
            if (!thisItem.equals(otherItem)) {
                return false;
            }
        }
        return true;
    }
}
