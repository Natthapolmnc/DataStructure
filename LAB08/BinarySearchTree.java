class BinarySearchTree {
    public static void main(String[] args) {
        BST tree = new BST();
        tree.add(9);
        tree.add(2);
        tree.add(12);
        tree.removeRoot(12);
        tree.printTree();
    }
}