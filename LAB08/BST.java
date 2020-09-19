
class BST {
    private BTNode root;

    BST() {
        this.root = null;
    }

    /**
     * @return the root
     */
    private BTNode getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    private void setRoot(BTNode root) {
        this.root = root;
    }

    public boolean contain(int element) {
        return getNode(element) != null;
    }

    public void add(int[] newData) {
        for (int i : newData) {
            add(i);
        }
    }

    public void removeRoot(int element) {
        BTNode pp = root;
        BTNode p = root;
        while (p.getRight().getRight() != null && p.getRight().getLeft() != null && p.getLeft().getRight() != null
                && p.getLeft().getLeft() != null) {
            pp = p;
            if (p.getElement() < element) {
                p = p.getRight();
            } else if (p.getElement() > element) {
                p = p.getLeft();
            }
        }
        if (p.getLeft() != null) {
            BTNode g = p.getLeft();
            BTNode gg = g;
            while (g.getRight() != null) {
                gg = g;
                g = g.getRight();
            }
            if (g.element==gg.element){
                p.setElement(g.element);
                p.setLeft(null);
                return;
            }
            p.element = g.element;
            gg.setRight(null);
        } else if (p.getRight() != null) {
            BTNode g = p.getRight();
            BTNode gg = g;
            while (g.getLeft() != null) {
                gg = g;
                g = g.getLeft();
            }
            if (gg.element==g.element){
                p.element=g.element;
                p.setRight(null);
                return;
            }
            p.element = g.element;
            gg.setLeft(null);
        }
    }

    public void add(int newElement) {
        if (this.root == null) {
            this.root = new BTNode(newElement, null, null);
        }
        if (this.root != null) {
            BTNode p = root;
            while (p.getLeft() != null && p.getRight() != null) {
                if (p.getElement() == newElement) {
                    return;
                } else if (p.getElement() < newElement) {
                    p = p.getRight();
                } else if (p.getElement() > newElement) {
                    p = p.getLeft();
                }
            }
            if (newElement > p.getElement()) {
                p.setRight(new BTNode(newElement, null, null));
            } else if (newElement < p.getElement()) {
                p.setLeft(new BTNode(newElement, null, null));
            }
        }
    }

    public BTNode getNode(int element) {
        BTNode p = this.root;
        while (p.getLeft() != null && p.getRight() != null) {
            if (p.getElement() == element) {
                return p;
            } else if (p.getElement() < element) {
                p = p.getRight();
            } else if (p.getElement() > element) {
                p = p.getLeft();
            }
        }
        return null;
    }

    public void remove(int element) {
        BTNode p = this.root;
        BTNode pp = root;
        while (p != null) {
            pp = p;
            if (p.getElement() == element) {
                break;
            } else if (p.getElement() < element) {
                p = p.getRight();
            } else if (p.getElement() > element) {
                p = p.getLeft();
            }
        }
        if (p == null) {
            System.out.println("invalid input");
            return;
        } else if (p.getRight() != null) {
            BTNode rr = p.getRight();
            BTNode r = p.getRight();
            while (r.getLeft() != null) {
                rr = r;
                r = r.getLeft();
            }
            if (r.equals(rr)) {
                p.element = r.element;
                p.right = null;
                return;
            }
            p.element = r.element;
            rr.left = null;
        } else if (p.getLeft() != null) {
            BTNode ll = p.getLeft();
            BTNode l = p.getLeft();
            while (l.getRight() != null) {
                ll = l;
                l = l.getRight();
            }
            if (l.equals(ll)) {
                p.element = l.element;
                p.left = null;
                return;
            }
            p.element = l.element;
            ll.right = null;
        } else {
            p = null;
        }

    }

    public int getMax() {
        BTNode p = root;
        while (p.getRight() != null) {
            p = p.getRight();
        }
        return p.getElement();
    }

    public int getMin() {
        BTNode p = root;
        while (p.getLeft() != null) {
            p = p.getLeft();
        }
        return p.getElement();
    }

    public int nnodes() {
        return numNode(this.root);
    }

    public int numNode(BTNode n) {
        if (n == null) {
            return 0;
        } else {
            return 1 + numNode(n.getLeft()) + numNode(n.getRight());
        }
    }

    public void printTree() {
        printInorder(this.root);
    }

    public void printInorder(BTNode p) {
        if (p != null) {
            printInorder(p.getLeft());
            System.out.print(p.getElement() + ",");
            printInorder(p.getRight());
        }
    }

}