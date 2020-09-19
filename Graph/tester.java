class tester{
    public static void main(String[] args) {
        Graph g4 = new Graph();
        Vertex v1 = new Vertex("v1");
        g4.add(v1);
        Vertex v2 = new Vertex("v2");
        g4.add(v2);
        Vertex v3 = new Vertex("v3");
        g4.add(v3);
        Vertex v4 = new Vertex("v4");
        g4.add(v4);
        Vertex v5 = new Vertex("v5");
        g4.add(v5);
        g4.remove(v1);
        g4.remove(v2);
        g4.addEdge(v1, v2);
        g4.addEdge(v2, v3);
        g4.addEdge(v3, v4);
        g4.addEdge(v4, v5);
        g4.addEdge(v1, v1);
        System.out.println(g4.vertexIndex(v1));
        g4.showAdjacencyMatrix();
        g4.breadthFirstSearch("v3");
    }
}