class Heap {
    int[] elementData;

    Heap(int[] elementData) {
        this.elementData = elementData;
        for (int i = (elementData.length) / 2; i > 0; i--) {
            reHeap(i);
        }
    }

    public void reHeap(int root) {
        if (root * 2 + 1 <= this.elementData.length-1) {
            if (this.elementData[root] < this.elementData[root * 2 + 1]&& this.elementData[root*2+1]>this.elementData[root*2]) {
                int temp = this.elementData[root * 2 + 1];
                this.elementData[root * 2 + 1] = this.elementData[root];
                this.elementData[root] = temp;
                //System.out.println("kaii1");
                if (((root * 2 + 1) * 2) <= this.elementData.length) {
                    reHeap(root * 2 + 1);
                }

            } else if (this.elementData[root] < this.elementData[root * 2]&& this.elementData[root*2]>this.elementData[root*2+1]) {
                int temp = this.elementData[root * 2];
                this.elementData[root * 2] = this.elementData[root];
                this.elementData[root] = temp;
                //System.out.println("kaii2");
                if (((root * 2) * 2) <= this.elementData.length) {
                    reHeap(root * 2);
                }
            }
        }
        if (root * 2 <= this.elementData.length-1) {
            if (this.elementData[root] < this.elementData[root * 2]) {
                int temp = this.elementData[root * 2];
                this.elementData[root * 2] = this.elementData[root];
                this.elementData[root] = temp;
                //System.out.println("kaii3");
                if (((root * 2) * 2) < this.elementData.length) {
                    reHeap(root * 2);
                }
            }
        }
    }

    void printHeap() {
        for (int i : elementData) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        int[] test = {0, 20, 13, 25, 5, 6, 50, 2, 16, 9, 1 };
        Heap testHeap = new Heap(test);
        testHeap.printHeap();

    }
}