import java.io.*;
import java.util.*;

class Traversal {
    private int V;
    private LinkedList<Integer> adj[];

    Traversal(int v) {
        V = v;
        adj = new LinkedList[v];

        for (int i=0; i<v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    void addEdge(int v,int w) {
        adj[v].add(w);
    }

    void checkBFS(int s) {

        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();

        visited[s]=true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s+" ");

            Iterator<Integer> i = adj[s].listIterator();

            while (i.hasNext())  {
                int n = i.next();

                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    void DFSUtil(int v, boolean visited[]) {

        visited[v] = true;
        System.out.print(v + " ");

        Iterator<Integer> i = adj[v].listIterator();

        while (i.hasNext()) {
            int n = i.next();

            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    void checkDFS(int v) {

        boolean visited[] = new boolean[V];
        DFSUtil(v, visited);
    }

    public static void main(String args[]) throws IOException {

        ArrayList<GraphInfo> graph = new ArrayList<>();
        FileHandler handler = new FileHandler();
        handler.GraphFileReader(graph);
        int startingVertex = 2;

        Traversal traversal = new Traversal(4039);

        for (int i = 0; i <graph.size() ; i++) {
            traversal.addEdge(graph.get(i).source, graph.get(i).destination);
        }

        handler.writeToFile();

        System.out.println("Following is Breadth First Traversal (starting from vertex " + startingVertex + ")");
        traversal.checkBFS(startingVertex);

        System.out.println("\n\nFollowing is Depth First Traversal (starting from vertex " + startingVertex + ")");
        traversal.checkDFS(startingVertex);

        handler.writeToConsole();

        System.out.println("Following is Breadth First Traversal (starting from vertex " + startingVertex + ")");
        traversal.checkBFS(startingVertex);

        System.out.println("\n\nFollowing is Depth First Traversal (starting from vertex " + startingVertex + ")");
        traversal.checkDFS(startingVertex);
    }
}