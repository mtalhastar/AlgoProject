// Java program to find degree of a vertex.

import java.io.IOException;
import java.util.ArrayList;

class FindDegree
{
    //Structure of Graph
    static class Graph
    {
        // vertices and edges
        int v, e;
        int[][] dir;

        //Graph Constructor
        Graph(int v, int e) {
            this.v = v;
            this.e = e;
            dir = new int[v][];
            for (int i = 0; i < v; i++)
                dir[i] = new int[v];
        }
    }
    static Graph createGraph(int v, int e) throws IOException {
        Graph G = new Graph(v, e);

	/* 0-----1
		| \ |
		| \ |
		| \ |
		2-----3 */
        ArrayList<GraphInfo>  graphInfos = new ArrayList<>();
        //direction from 0
        ArrayList<GraphInfo> info = new ArrayList<>();
        FileHandler handler = new FileHandler();
        handler.GraphFileReader(info);
        for (int i = 0; i <info.size() ; i++) {
         int src=   info.get(i).source;
         int dest=  info.get(i).destination;
            G.dir[src][dest] = 1;
        }

        return G;
    }

    static int findDegree(Graph G, int ver)
    {
        int degree = 0;
        for (int i = 0; i < G.v; i++) {
            if (G.dir[ver][i] == 1)
                degree++;
        }

        // below line is to account for self loop in graph
        // check sum of degrees in graph theorem
        if(G.dir[ver][ver] == 1) degree++;
        return degree;
    }

    // Driver code
    public static void main(String[] args) throws IOException {
        int vertices = 4039;
        int edges = 88234;

        // Creating a Graph
        Graph G = createGraph(vertices, edges);
        ArrayList<GraphInfo> info = new ArrayList<>();
        FileHandler handler = new FileHandler();
        handler.GraphFileReader(info);
        ArrayList<Integer> number = new ArrayList<>();
        number=GetUniqueElements(info);
        int degree=0;
        long start = System.nanoTime();
        for (int i = 0; i <number.size() ; i++) {
          //  System.out.println("Degree of Vertex "+number.get(i)+" "+findDegree(G, number.get(i))+"\n");
            degree = degree +findDegree(G, number.get(i));
        }
        long end = System.nanoTime();

        double execution = end - start;
        execution = execution / 1000000000;
        System.out.println("The average degree of graph is "+degree/number.size());
        System.out.println("Execution Time: " + execution + " in seconds");


    }
    public static ArrayList<Integer>  GetUniqueElements(ArrayList<GraphInfo> graphInfos){
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i <graphInfos.size() ; i++) {
            if(!integers.contains(graphInfos.get(i).source)){
                integers.add(graphInfos.get(i).source);
            }
        }
        return integers;
    }
}
