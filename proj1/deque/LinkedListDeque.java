package deque;

import jh61b.junit.In;

public class LinkedListDeque<T> implements Deque<T> {

    public class TNode {
        public T item;
        public TNode prev;
        public TNode next;

        public TNode(T x, TNode prev_node, TNode next_node) {
            item = x;
            next = next_node;
            prev = prev_node;
        }
    }

//    private IntNode first;
    private int size;
    private TNode sentinel_F;
    private TNode sentinel_B;

    public LinkedListDeque(T x) {

        sentinel_F = new TNode(null, null, null);
        sentinel_B = new TNode(null, null, null);
        sentinel_F.next = new TNode(x, sentinel_F, sentinel_B);
        size = 1;
        sentinel_B.prev = sentinel_F.next;
    }
    public LinkedListDeque() {
        sentinel_F = new TNode(null, null, null);
        size = 0;
        sentinel_B = new TNode(null, null, null);
        sentinel_F.next = sentinel_B;
        sentinel_B.prev = sentinel_F;
    }


    public void addFirst(T x) {
        sentinel_F.next = new TNode(x, sentinel_F, sentinel_F.next);
        size += 1;
        if(size == 1) {
            sentinel_B.prev = sentinel_F.next;
        }
    }
    public T getFirst() {
        return sentinel_F.next.item;
    }
    public T removeLast() {
        if(size > 0) {
            T p = sentinel_B.prev.item;
            sentinel_B.prev = sentinel_B.prev.prev;
            sentinel_B.prev.next = sentinel_B;
            size -= 1;
            return p;
        }
        else{
            return  null;
        }
    }
    public T removeFirst() {
        if(size > 0) {
            T p = sentinel_F.next.item;
            sentinel_F.next = sentinel_F.next.next;
            sentinel_F.next.prev = sentinel_F;
            size -= 1;
            return p;
        }
        else {
            return null;
        }
    }
    public T get(int index) {
        TNode p = sentinel_F;
        int t = 0;
        while(t < index) {
            p = sentinel_F.next;
            t += 1;
        }
        return p.next.item;
    }
    public T getRecursiveHelper(int index, TNode p){

        if (index == 0) {
            return p.item;
        } else{
            return  getRecursiveHelper(index - 1, p.next);
        }
    }
    public T getRecursive(int index) {
            return getRecursiveHelper(index, sentinel_F.next);
    }

    public void addLast(T x) {
        size += 1;

        sentinel_B.prev.next = new TNode(x, sentinel_B.prev, sentinel_B);
        sentinel_B.prev = sentinel_B.prev.next;
//
    }

    public int size() {
        return size;
    }
//    public boolean isEmpty() {
//        return  size == 0;
//    }
    public void printDeque(){

        TNode p;
        p = sentinel_F;
        while(p.next.item != null) {

            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println(" ");
    }
}
