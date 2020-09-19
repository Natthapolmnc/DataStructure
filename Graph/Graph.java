import java.lang.reflect.Array;
import java.util.ArrayList;


class Graph {
    private int size;
    private int maxSize;
    private Vertex[] vertexList;
    private int[][] matrixAdj;

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @param vertexList the vertexList to set
     */
    public void setVertexList(Vertex[] vertexList) {
        this.vertexList = vertexList;
    }

    /**
     * @param matrixAdj the matrixAdj to set
     */
    public void setMatrixAdj(int[][] matrixAdj) {
        this.matrixAdj = matrixAdj;
    }

    /**
     * @return the matrixAdj
     */
    public int[][] getMatrixAdj() {
        return matrixAdj;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @return the vertexList
     */
    public Vertex[] getVertexList() {
        return vertexList;
    }

    Graph(int maxSize) {
        this.maxSize = maxSize;
        vertexList = new Vertex[maxSize];
        matrixAdj = new int[maxSize][maxSize];
        size = 0;
    }

    Graph() {
        this.maxSize = 10;
        vertexList = new Vertex[10];
        matrixAdj = new int[10][10];
        size = 0;
    }

    public void add(String point) {
        if (size + 1 >= maxSize) {
            int tempSize = 0;
            Vertex[] tempVer = new Vertex[maxSize * 2];
            int[][] tempMax = new int[maxSize * 2][maxSize * 2];
            for (Vertex i : vertexList) {
                tempVer[tempSize++] = i;
            }
            for (int i = 0; i <= maxSize; i++) {
                for (int j = 0; i <= maxSize; j++) {
                    tempMax[i][j] = matrixAdj[i][j];
                }
            }
            this.matrixAdj = tempMax;
            this.vertexList = tempVer;
        }
        vertexList[size++] = new Vertex(point);
    }

    public void add(Vertex point) {
        if (size + 1 >= maxSize) {
            int tempSize = 0;
            Vertex[] tempVer = new Vertex[maxSize * 2];
            int[][] tempMax = new int[maxSize * 2][maxSize * 2];
            for (Vertex i : vertexList) {
                tempVer[tempSize++] = i;
            }
            for (int i = 0; i <= maxSize; i++) {
                for (int j = 0; i <= maxSize; j++) {
                    tempMax[i][j] = matrixAdj[i][j];
                }
            }
            this.matrixAdj = tempMax;
            this.vertexList = tempVer;
        }

        vertexList[size++] = point;
    }

    public void addEdge(Vertex from, Vertex to) {
        int v1 = vertexIndex(from);
        int v2 = vertexIndex(to);
        if (v1 == -1 || v2 == -1)
            return;
        matrixAdj[v1][v2] = 1;
        matrixAdj[v2][v1] = 1;
    }

    public int vertexIndex(Vertex p) {
        for (int o = 0; o < size; o++) {
            if (p.equals(vertexList[o])) {
                return o;
            }
        }
        return -1;
    }


    public int vertexIndex(String p) {
        for (int o = 0; o < size; o++) {
            if (p.equals(vertexList[o].getLabels())) {
                return o;
            }
        }
        return -1;
    }

    void remove(String v) {
        int i = vertexIndex(v);
        for (int a = i; a <= size; a++) {
            vertexList[a] = vertexList[a + 1];
        }
        for (int j = 0; j < size + 1; j++) {
            if (j >= i) {
                for (int k = 0; k < size + 1; k++) {
                    if (k >= i) {
                        matrixAdj[j][k] = matrixAdj[j + 1][k + 1];
                    }
                }
            }
        }
        size--;

    }

    
    void remove(Vertex v) {
        int i = vertexIndex(v);
        for (int a = i; a <= size; a++) {
            vertexList[a] = vertexList[a + 1];
        }
        for (int j = 0; j < size + 1; j++) {
            if (j >= i) {
                for (int k = 0; k < size + 1; k++) {
                    if (k >= i) {
                        matrixAdj[j][k] = matrixAdj[j + 1][k + 1];
                    }
                }
            }
        }
        size--;

    }

    void removeEdge(Vertex start, Vertex end) {
        int v1 = vertexIndex(start);
        int v2 = vertexIndex(end);
        matrixAdj[v1][v2] = 0;
        matrixAdj[v2][v1] = 0;
    }

    public void showAdjacencyMatrix() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(matrixAdj[i][j] + " ");
            System.out.println();
        }
    }

    


    public void breadthFirstSearch(String target) {
        Queue q = new Queue(maxSize);
        boolean found = false;
        q.enqueue(vertexList[0]);
        if (vertexIndex(target)==-1){
            System.out.println("Not found");
            return;
        }
        do {
            Vertex progtagonist = q.dequeue();
            ArrayList<Integer> tempArr = getAdjVertex(progtagonist);
            System.out.print(progtagonist.getLabels()+" ");
            if (progtagonist.getLabels().equals(target)) {
                found = true;
            }
            if (found == false && !progtagonist.getVisit() ) {
                progtagonist.setVisit(true);
                if (tempArr!=null) {
                    for (int k : tempArr) {
                        q.enqueue(vertexList[k]);
                    }
                }
            }
        } while (!q.isEmpty() && !found);
        System.out.println();
    }

    private ArrayList<Integer> getAdjVertex(Vertex protagonistVertex) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        int index = vertexIndex(protagonistVertex);
        for (int i = 0; i < size; i++) {
            if (matrixAdj[index][i] != 0 && !vertexList[i].getVisit()) {
                temp.add(i);
            }
        }
        return temp;
    }
}