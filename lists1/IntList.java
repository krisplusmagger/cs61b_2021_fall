public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Return the size of the list using recursion*/
    public int size() {
        IntList p;
        p = this.rest;
        if (this.rest == null) {
            return 1;
        } else {
            return  1 + p.size();
        }
    }
    /**Return the size of the list using no recursion */
    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while( p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return  totalSize;
    }

    public int get(int i) {
        int index = 1;
        IntList p = this;
        while (index <= i) {
            p = this.rest;
        }
        return  p.first;
    }
    public static void main(String[] args) {
        IntList L = new IntList(15, null);
        L = new IntList(10, L);
        L = new IntList(5, L);

        System.out.println(L.iterativeSize());
        System.out.println(L.size());
    }

}
