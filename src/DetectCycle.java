import java.io.*;
import java.util.*;

// This algo is for undirected graph
class DetectCycle {
    private int V;
    private LinkedList<Integer> adj[];

    DetectCycle(int v) {
        V = v;
        adj = new LinkedList[v];

        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    Boolean isCyclicUtil(int v, Boolean visited[], int parent) {

        visited[v] = true;
        Integer i;
        Iterator<Integer> it = adj[v].iterator();

        while (it.hasNext()) {
            i = it.next();

            if (!visited[i]) {
                if (isCyclicUtil(i, visited, v)) {
                    System.out.println(i + " " + v);
                    return true;
                }
            }

            else if (i != parent) {
                return true;
            }
        }
        return false;
    }

    Boolean isCyclic() {

        Boolean visited[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        for (int u = 0; u < V; u++) {

            if (!visited[u]) {

                if (isCyclicUtil(u, visited, -1)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String args[]) throws IOException {

        ArrayList<GraphInfo> graph = new ArrayList<>();
        FileHandler handler = new FileHandler();
        handler.GraphFileReader(graph);

        DetectCycle g1 = new DetectCycle(4039);

        for (int i = 0; i <graph.size() ; i++) {
            g1.addEdge(graph.get(i).source, graph.get(i).destination);
        }

        if (g1.isCyclic()) {
            System.out.println("Graph contains cycle");
        }
        else {
            System.out.println("Graph doesn't contain cycle");
        }
    }
}