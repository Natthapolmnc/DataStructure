public class MyNode {
    private Vertex data;
    private MyNode next;

    public MyNode(Vertex obj, MyNode n) {
        data = obj;
        next = n;
    }

    public void setNext(MyNode n) {
        next = n;
    }

    public void setData(Vertex d) {
        data = d;
    }

    public MyNode getNext() {
        return next;
    }

    public Vertex getData() {
        return data;
    }
}