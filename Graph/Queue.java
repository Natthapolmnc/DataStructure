public class Queue {
    private int size;
    private int max;
    private MyNode front, rear;

    public Queue(int m) {
        max = m;
        front = rear = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == max;
    }

    public void enqueue(Vertex obj) {
        if (isFull()) {
            System.out.println("Queue is full. Can't Add " + obj + "!");
            return;
        } else {
            MyNode tmp = new MyNode(obj, null);
            if (isEmpty()) { // Add the first node
                front = rear = tmp;
            } else { // Add another node
                rear.setNext(tmp);
                rear = tmp;
            }
            size++;
        }
    }

    public Vertex dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is Empty.");
            return null;
        } else {
            Vertex obj = front.getData();
            if (front == rear) // Remove last node
                front = rear = null;
            else // Remove another node
                front = front.getNext();
            size--;
            return obj;
        }
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Nothing in queue");
        } else {
            System.out.println("Print Queue:");
            MyNode start = front;
            while (start != null) {
                System.out.println(start.getData());
                start = start.getNext();
            }
        }
    }
}