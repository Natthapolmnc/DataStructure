class Main {
    public static void main(String[] args) {
        SplayTree tree = new SplayTree();
        tree.add(2);
        tree.printTree();
        tree.add(10);
        tree.printTree();
        tree.add(1);
        tree.printTree();
        tree.add(5);
        tree.printTree();
        tree.add(7);
        tree.printTree();
        tree.add(15);
        tree.printTree();
        tree.add(10);
        tree.printTree();
        tree.splay(5);      
        tree.splay(8);
        tree.printTree();
        tree.remove(15);
        tree.remove(20);
        tree.printTree();
    }
}