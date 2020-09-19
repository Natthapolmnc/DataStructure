import java.lang.IllegalArgumentException;

class SelfOrganizedList {
    int size;
    int modCount;
    LinkedNode header;
    LinkedNode tail;

    class LinkedNode {
        String element;
        LinkedNode prev;
        LinkedNode next;
        int modCnt = 0;

        LinkedNode(String e, LinkedNode prev, LinkedNode next) {
            modCnt = 0;
            this.element = e;
            this.prev = prev;
            this.next = next;
        }
    }

    SelfOrganizedList() {
        this.modCount = 0;
        this.size = 0;
        this.header = new LinkedNode("Header", null, null);
        this.tail = new LinkedNode("tail", header, null);
        this.header.next=tail;
    }

    public void assertNonNull(Object e) {
        if (e == null)
            throw new IllegalArgumentException();
    }

    private LinkedNode nodeOf(String e) {
        LinkedNode p = header.next;
        while (!p.element.equals(e) && p != tail) {
            p = p.next;
        }
        return p;
    }

    private LinkedNode nodeAt(int i) {
        LinkedNode p = header;
        for (int j = -1; j < i; j++)
            p = p.next;
        return p;
    }

    private static void assertInRange(int i, int max) {
        if (i < 0 || i > max)
            throw new IllegalArgumentException();
    }

    public boolean contains(String e) {
        LinkedNode node = nodeOf(e);
        if (node == tail)
            return false;
        node.modCnt++;
        while (node.prev.modCnt<=node.modCnt &&node!=header.next){
            int nodeModCnt=node.modCnt;
            String nodeElement=node.element;
            node.modCnt=node.prev.modCnt;
            node.element=node.prev.element;
            node.prev.modCnt=nodeModCnt;
            node.prev.element=nodeElement;
            node=node.prev;
        }
        return true;
    }

    public void reverse() {
        LinkedNode temp = null; 
        LinkedNode current = header; 
  
        while (current != null) { 
            temp = current.prev; 
            current.prev = current.next; 
            current.next = temp; 
            current = current.prev; 
        }
        temp=this.header;
        this.header=this.tail;
        this.tail=temp;
    }

    public void add(String e) {
        add(size, e);
    }

    public void add(int i, String e) {
        assertInRange(i, size);
        LinkedNode p = nodeAt(i);
        addBefore(p, e);
    }

    private void addBefore(LinkedNode p, String e) {
        assertNonNull(e);
        LinkedNode newNode = new LinkedNode(e, p.prev, p); // Steps 1 and 2
        p.prev.next = newNode; // Step 3
        p.prev = newNode; // Step 4
        size++;
        modCount++;
        // or simply code
        // p.prev = p.prev.next = new LinkedNode(e, p.prev, p);
        // p.prev p.prev.next new LinkedNode(e, p.prev, p);
    }

    void printElement() {
        LinkedNode p = this.header.next;
        while (p!= tail) {
            System.out.print(p.element + "[" + p.modCnt + "] ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SelfOrganizedList l = new SelfOrganizedList();
        l.add("A");
        l.add("B");
        l.add("C");
        l.add("D");
        l.add("E");
        l.add("F");
        l.printElement();
        l.reverse();
        l.contains("C");
        l.printElement();
        l.contains("D");
        l.printElement();
        l.contains("C");
        l.contains("C");
        l.contains("A");
        l.contains("A");
        l.contains("D");
        l.printElement();
    }
}