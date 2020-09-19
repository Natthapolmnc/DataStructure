class SplayTree {
    private SplayNode root;
    static private SplayNode header = new SplayNode();

    public void add(int elementData) {
        if (root == null) {
            root = new SplayNode(elementData, null, null, 0, 0, 0);
            return;
        }
        splay(elementData);
        if (root.getElement() == elementData) {
            return;
        }
        SplayNode addNew = new SplayNode(elementData, null, null, 0, 0, 0);
        if (root.getElement() > elementData) {
            addNew.setLeft(root.getLeft());
            addNew.setRight(root);
            root.setLeft(null);
            root = addNew;
        } else if (root.getElement() < elementData) {
            addNew.setRight(root.getRight());
            addNew.setLeft(root);
            root.setRight(null);
            root = addNew;
        }
    }

    public void remove(int elementData) {
        if (root == null) {
            return;
        }
        splay(elementData);
        if (root.getLeft() == null) {
            root = root.getRight();
        } else {
            SplayNode oldRight = root.getRight();
            root = root.getLeft();
            splay(elementData);
            root.setRight(oldRight);
        }

    }


    public void splay(int elementData) {
        SplayNode pointer = root;
        SplayNode child;
        SplayNode r = this.header;
        SplayNode l = this.header;
        header.setLeft(null);
        header.setRight(null);
        for (;;) {
            if (pointer.getElement() > elementData) {
                if (pointer.getLeft() == null)
                    break;
                else if (pointer.getLeft().getElement() > elementData) {
                    child = pointer.getLeft();
                    pointer.setLeft(child.getRight());
                    child.setRight(pointer);
                    pointer = child;
                    if (pointer.getLeft() == null)
                        break;
                }
                r.setLeft(pointer);
                r = pointer;
                pointer = pointer.getLeft();
            } else if (pointer.getElement() == elementData) {
                break;
            } else {
                if (pointer.getRight() == null)
                    break;
                else if (pointer.getRight().getElement() < elementData) {
                    child = pointer.getRight();
                    pointer.setRight(child.getLeft());
                    child.setLeft(pointer);
                    pointer = child;
                    if (pointer.getRight() == null) {
                        break;
                    }
                }
                l.setRight(pointer);
                l = pointer;
                pointer = pointer.getRight();

            }
        }
        l.setRight(pointer.getLeft());
        r.setLeft(pointer.getRight());
        pointer.setLeft(header.getRight());
        pointer.setRight(header.getLeft());
        root = pointer;
    }

    public void printTree() {
        printPreorder(this.root);
        System.out.println();
    }

    public void printPreorder(SplayNode p) {
        if (p != null) {
            System.out.print(p.getElement() + ",");
            printPreorder(p.getLeft());
            printPreorder(p.getRight());
        }
    }

}