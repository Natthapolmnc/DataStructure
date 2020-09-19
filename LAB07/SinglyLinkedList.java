public class SinglyLinkedList implements List {

    private static class LinkedNode {

        Object element;
        LinkedNode next;

        LinkedNode(Object e, LinkedNode next) {
            this.element = e;
            this.next = next;
        }
    }
    private LinkedNode header;
    private int size;
    private int modCount = 0;

    public SinglyLinkedList() {
        header = new LinkedNode(null, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isSublist(List list2){
        String tibe1="";
        String tibe2="";
        for (int i=0;i<this.size;i++){
            tibe1+=this.get(i).toString();
        }
        for (int i=0;i<this.size;i++){
            if (list2.contains(this.get(i))){
                tibe2+=this.get(i).toString();
            }
            if (tibe2.length()>list2.size()){
                tibe2="";
                break;
            }
        }
        return tibe1.equals(tibe2);
    }


    public boolean contains(Object e) {
        LinkedNode p = header.next;
        while (p != null && !p.element.equals(e)) {
            p = p.next;
        }
        return p != null;
    }

    public Object get(Object e) {
        LinkedNode p = header.next;
        while (p != null && !p.element.equals(e)) {
            p = p.next;
        }
        return p != null ? p.element:null;
    }

    public Object get(int i) {
        return nodeAt(i).element;
    }

    public void set(int i, Object e) {
        nodeAt(i).element = e;
    }

    public void add(Object e) {
        add(size, e);
    }

    public void add(int i, Object e) {
        assertNonNull(e);
        assertInRange(i, size);
        LinkedNode p = nodeAt(i - 1);
        p.next = new LinkedNode(e, p.next);
        ++size;
        modCount++;
    }

    public void remove(Object e) {
        LinkedNode p = header;
        while (!p.next.element.equals(e) && p.next != null) {
            p = p.next;
        }
        removeAfter(p);
    }

    private void removeAfter(LinkedNode p) {
        if (p != null) {
            p.next = p.next.next;
            --size;
            modCount++;
        }
    }

    public void remove(int i) {
        assertInRange(i, size - 1);
        LinkedNode p = nodeAt(i - 1);
        removeAfter(p);
    }

    private static void assertNonNull(Object e) {
        if (e == null) {
            throw new IllegalArgumentException();
        }
    }

    private static void assertInRange(int i, int max) {
        if (i < 0 || i > max) {
            throw new IllegalArgumentException();
        }
    }

    public String toString(){
        LinkedNode p=this.header.next;
        String result="[";
        while (p!=null){
            result+=p.element.toString();
            if(p.next!=null){
                result+=",";
            }
            p=p.next;
        }
        result+="]";
        return result;
    }

    private LinkedNode nodeAt(int i) {
        LinkedNode p = header;
        for (int j = -1; j < i; j++) {
            p = p.next;
        }
        return p;
    }
    
    public java.util.Iterator iterator() {
        return new SinglyLinkedListItr();
    }

    private class SinglyLinkedListItr implements java.util.Iterator {

        private LinkedNode current = header;
        private LinkedNode previous = null;
        private boolean okToRemove = false;
        private int expectedModCount = modCount;

        public boolean hasNext() {
            return current.next != null;
        }

        public Object next() {
            if (modCount != expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new IllegalStateException();
            }
            previous = current;
            current = current.next;
            okToRemove = true;
            return current.element;
        }

        public void remove() {
            if (modCount != expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }
            removeAfter(previous);
            expectedModCount++;
            okToRemove = false;
            current = previous;
        }


    }

}
