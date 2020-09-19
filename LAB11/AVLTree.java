import java.util.Stack;

class AVLTree {
    private AVLNode root;

    AVLTree() {
        this.root = null;
    }

    public boolean contain(int element) {
        return getNode(element) != null;
    }

    public void addAVL(int[] newData) {
        for (int i : newData) {
            addAVL(i);
        }
    }

    public void addAVL(int newElement) {
        AVLNode p = root;
        AVLNode pp = root;
        Stack<AVLNode> s = new Stack<AVLNode>();
        if (this.root == null) {
            this.root = new AVLNode(newElement, null, null, 0, 0, 0);
            pp = root;
        } else {
            while (p != null) {
                pp = p;
                s.push(pp);
                if (p.getElement() == newElement) {
                    return;
                } else if (p.getElement() < newElement) {
                    p = p.getRight();
                } else if (p.getElement() > newElement) {
                    p = p.getLeft();
                }
            }
            if (newElement > pp.getElement()) {
                pp.setRight(new AVLNode(newElement, null, null, 0, 0, 0));
            } else if (newElement < pp.getElement()) {
                pp.setLeft(new AVLNode(newElement, null, null, 0, 0, 0));
            }
        }
        updateWeight(pp);
        while (!s.isEmpty()) {
            AVLNode thePop = s.pop();
            updateWeight(thePop);
            thePop = rotate(pp, thePop, s);
            pp = thePop;
        }
    }

    private void updateWeight(AVLNode node) {
        node.setLh(node.getLeft() == null ? 0 : Math.max(node.getLeft().getLh() + 1, node.getLeft().getRh() + 1));
        node.setRh(node.getRight() == null ? 0 : Math.max(node.getRight().getLh() + 1, node.getRight().getRh() + 1));
        node.setWeight(node.getLh() - node.getRh());
    }

    private AVLNode rotate(AVLNode childNode, AVLNode node, Stack<AVLNode> s) {
        updateWeight(childNode);
        if (Math.abs(childNode.getWeight()) > 1) {
            if (childNode.getWeight() > 0)
                if (node.getWeight() >= 0)
                    childNode = singleRightRotate(childNode, node);
                else
                    childNode = doubleRightRotate(childNode, node);
            else if (node.getWeight() <= 0)
                childNode = singleLeftRotate(childNode, node);
            else
                childNode = doubleLeftRotate(childNode, node);
            if (!s.isEmpty()) {
                AVLNode pre = s.peek();
                if ((Integer) childNode.getElement() < (Integer) pre.getElement())
                    pre.setLeft(childNode);
                else
                    pre.setRight(childNode);
            } else
                root = childNode;
        }
        return childNode;
    }

    private AVLNode singleRightRotate(AVLNode childNode, AVLNode node) {
        node.setLeft(childNode.getRight());
        childNode.setRight(node);
        updateWeight(childNode);
        updateWeight(node);
        return childNode;
    }

    private AVLNode singleLeftRotate(AVLNode childNode, AVLNode node) {
        node.setRight(childNode.getLeft());
        childNode.setLeft(node);
        updateWeight(childNode);
        updateWeight(node);
        return childNode;
    }

    private AVLNode doubleRightRotate(AVLNode childNode, AVLNode node) {
        AVLNode newRoot = childNode.getRight();
        node.setRight(newRoot.getLeft());
        childNode.setLeft(newRoot.getRight());
        newRoot.setRight(childNode);
        newRoot.setLeft(node);
        updateWeight(childNode);
        updateWeight(node);
        updateWeight(newRoot);
        return newRoot;
    }

    private AVLNode doubleLeftRotate(AVLNode childNode, AVLNode node) {
        AVLNode newRoot = childNode.getLeft();
        node.setLeft(newRoot.getRight());
        childNode.setRight(newRoot.getLeft());
        newRoot.setLeft(childNode);
        newRoot.setRight(node);
        updateWeight(childNode);
        updateWeight(node);
        updateWeight(newRoot);
        return newRoot;
    }

    public void removeAVL(int elementData) {
        AVLNode childNode = root;
        AVLNode node = root;
        Stack<AVLNode> dataKeep = new Stack<AVLNode>();
        while (childNode != null && childNode.getElement() != elementData) {
            node = childNode;
            if (childNode.getElement() < elementData) {
                childNode = childNode.getRight();
            } else if (childNode.getElement() > elementData) {
                childNode = childNode.getLeft();
            }
        }
        if (childNode == null) {
            return;
        }
        if ((childNode.getLeft() == null) && (childNode.getRight() == null)) {
            if (childNode == root) {
                root = null;
            } else if (node.getLeft() == childNode) {
                node.setLeft(null);
            } else {
                node.setRight(null);
            }
        } else if ((childNode.getLeft() != null) && (childNode.getRight() != null)) {
            node = childNode;
            AVLNode afterChildNode = childNode.getRight();
            dataKeep.push(afterChildNode);
            while (afterChildNode.getLeft() != null) {
                node = afterChildNode;
                dataKeep.push(afterChildNode);
                afterChildNode = afterChildNode.getLeft();
            }
            childNode.setElement(afterChildNode.getElement());
            if (node == childNode) {
                node.setRight(afterChildNode.getRight());
            } else {
                node.setLeft(afterChildNode.getRight());
            }
        } else if (childNode.getLeft() != null) {
            if (childNode == root) {
                root = childNode.getLeft();
            } else if (node.getLeft() == childNode) {
                node.setLeft(childNode.getLeft());
            } else {
                node.setRight(childNode.getLeft());
            }
        } else if (childNode == root) {
            root = childNode.getRight();
        } else if (node.getRight() == childNode) {
            node.setRight((childNode.getRight()));
        } else {
            node.setLeft(childNode.getRight());
        }
        if (!dataKeep.isEmpty()) {
            dataKeep.pop();
        }
        while (!dataKeep.isEmpty()) {
            childNode = dataKeep.pop();
            updateWeight(childNode);
            if (Math.abs(childNode.getWeight()) > 1) {
                if (childNode.getLh() < childNode.getRh()) {
                    node = childNode.getRight();
                } else {
                    node = childNode.getLeft();
                }
                rotate(childNode, node, dataKeep);
            }
        }
    }

    public AVLNode getNode(int element) {
        AVLNode p = this.root;
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

    public int getMax() {
        AVLNode p = root;
        while (p.getRight() != null) {
            p = p.getRight();
        }
        return p.getElement();
    }

    public int getMin() {
        AVLNode p = root;
        while (p.getLeft() != null) {
            p = p.getLeft();
        }
        return p.getElement();
    }

    public int nnodes() {
        return numNode(this.root);
    }

    public int numNode(AVLNode n) {
        if (n == null) {
            return 0;
        } else {
            return 1 + numNode(n.getLeft()) + numNode(n.getRight());
        }
    }

    public void printTree() {
        printInorder(this.root);
        System.out.println();
    }

    public void printInorder(AVLNode p) {
        if (p != null) {
            printInorder(p.getLeft());
            System.out.print(p.getElement() + ",");
            printInorder(p.getRight());
        }
    }

    public static void main(String[] args) {
        AVLTree newTree = new AVLTree();
        newTree.addAVL(new int[] { 5, 7, 10, 12, 13, 15, 25, 28 });
        newTree.printTree();
        newTree.removeAVL(12);
        newTree.printTree();
        newTree.removeAVL(15);
        newTree.printTree();
    }

}