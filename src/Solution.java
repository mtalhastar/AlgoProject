import java.io.*;
import java.util.*;
import java.util.ArrayList;
class Edge {
    private int fromVertex;
    private int toVertex;
    private int weight;

    public Edge(int fromVertex, int toVertex, int weight) {
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
        this.weight = weight;
    }

    public int getFromVertex() {
        return fromVertex;
    }

    public int getToVertex() {
        return toVertex;
    }

    public int getWeight() {
        return weight;
    }
}

class MinHeap {
    private ArrayList<Edge> heap;

    public MinHeap() {
        heap = new ArrayList<Edge>();
    }

    public boolean empty() {
        return (heap.size() == 0);
    }

    public void push(Edge e) {
        heap.add(e);
        if (heap.size() == 1) {
            return;
        }

        // Heapify_up
        int index = heap.size() - 1;
        int parentIndex = (index - 1) / 2;

        while (parentIndex > 0) {
            if (heap.get(parentIndex).getWeight() <= heap.get(index).getWeight()) {
                break;
            }

            Edge temp = heap.get(parentIndex);
            heap.set(parentIndex, heap.get(index));
            heap.set(index, temp);

            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }

        if (heap.get(parentIndex).getWeight() > heap.get(index).getWeight()) {
            Edge temp = heap.get(parentIndex);
            heap.set(parentIndex, heap.get(index));
            heap.set(index, temp);
        }
    }

    public Edge pop() {
        if (empty()) {
            return null;
        }

        Edge minWeightEdge = heap.get(0);

        // Heapify_down
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        if (heap.size() != 0) {
            int index = 0, leftIndex = 1, rightIndex = 2;
            while (rightIndex < heap.size()) {
                if ((heap.get(index).getWeight() <= heap.get(leftIndex).getWeight()) && (heap.get(index).getWeight() <= heap.get(rightIndex).getWeight())) {
                    break;
                }

                if (heap.get(leftIndex).getWeight() <= heap.get(rightIndex).getWeight()) {
                    Edge temp = heap.get(index);
                    heap.set(index, heap.get(leftIndex));
                    heap.set(leftIndex, temp);
                    index = leftIndex;
                }
                else {
                    Edge temp = heap.get(index);
                    heap.set(index, heap.get(rightIndex));
                    heap.set(rightIndex, temp);
                    index = rightIndex;
                }

                leftIndex = (2 * index) + 1;
                rightIndex = (2 * index) + 2;
            }

            if (leftIndex < heap.size()) {
                if (heap.get(index).getWeight() > heap.get(leftIndex).getWeight()) {
                    Edge temp = heap.get(index);
                    heap.set(index, heap.get(leftIndex));
                    heap.set(leftIndex, temp);
                }
            }
        }

        return minWeightEdge;
    }
}

class Solution {
    private static void prim(ArrayList<ArrayList<Edge>> graph) {
        if (graph == null) {
            return;
        }

        int numVertices = graph.size();
        if (numVertices < 1) {
            return;
        }

        ArrayList<ArrayList<Edge>> tree = new ArrayList<ArrayList<Edge>>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            tree.add(i, new ArrayList<Edge>());
        }

        boolean[] visited = new boolean[numVertices];
        long totalMSTWeight = 0;
        MinHeap edges = new MinHeap();

        // Visit vertex 0
        visited[0] = true;
        for (Edge e : graph.get(0)) {
            edges.push(e);
            //System.out.println("Heap has " + e.getWeight());
        }

        while (!edges.empty()) {
            Edge leastEdge = edges.pop();
            if (visited[leastEdge.getToVertex()] == false) {
                visited[leastEdge.getToVertex()] = true;
                totalMSTWeight += leastEdge.getWeight();
                tree.get(leastEdge.getFromVertex()).add(leastEdge);

                for (Edge e : graph.get(leastEdge.getToVertex())) {
                    if (visited[e.getToVertex()] == false) {
                        edges.push(e);
                        //System.out.println("Heap has " + e.getWeight());
                    }
                }
            }
        }


        int sum=0;
        for (int i = 0; i < tree.size(); i++) {
            for (Edge e : tree.get(i)) {
                System.out.println((e.getFromVertex() ) + " " + (e.getToVertex() ) +  " weight: " + e.getWeight());
                sum=sum+e.getWeight();
            }
        }
        System.out.println(sum);

        return;
    }

    public static void main(String[] args) throws IOException {
           ArrayList<GraphInfo> info = new ArrayList<>();
           FileHandler handler= new FileHandler();
           handler.GraphFileReader(info);
            ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
            for (int i = 0; i < 4032; i++) {
                graph.add(i, new ArrayList<Edge>());
            }
            int j=0;
            int edges =4032;
            while (edges > 0) {
               int v1= info.get(j).source;
               int v2= info.get(j).destination;
               int weight = info.get(j).weight;
                //Populate edge list
                graph.get(v1).add(new Edge(v1, v2, weight));
                graph.get(v2).add(new Edge(v2, v1, weight));
                j++;
//            	if (input.hasNext()) {
//            		input.nextLine();
//            	}
//            	else {
//            		break;
//            	}
                edges--;
            }
            prim(graph);


    }
}