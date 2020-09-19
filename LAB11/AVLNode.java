class AVLNode {
    AVLNode left;
    AVLNode right;
    int element;
    int weight;
    int lh;
    int rh;

    AVLNode(int element, AVLNode left, AVLNode right, int weight, int leftHeight, int rightHeight) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.weight=weight;
        this.lh=leftHeight;
        this.rh=rightHeight;
    }


    /**
     * @param rh the rh to set
     */
    public void setRh(int rh) {
        this.rh = rh;
    }

    /**
     * @param lh the lh to set
     */
    public void setLh(int lh) {
        this.lh = lh;
    }

    /**
     * @return the rh
     */
    public int getRh() {
        return rh;
    }

    /**
     * @return the lh
     */
    public int getLh() {
        return lh;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @return the element
     */
    public int getElement() {
        return element;
    }

    /**
     * @return the left
     */
    public AVLNode getLeft() {
        return left;
    }

    /**
     * @return the right
     */
    public AVLNode getRight() {
        return right;
    }
    /**
     * @param element the element to set
     */
    public void setElement(int element) {
        this.element = element;
    }
    /**
     * @param left the left to set
     */
    public void setLeft(AVLNode left) {
        this.left = left;
    }
    /**
     * @param right the right to set
     */
    public void setRight(AVLNode right) {
        this.right = right;
    }
}