/* Name: Khursheed Alam Khan					Bellman Ford Algorithm Diameter
 * 20i-0496
 * SE-R
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

// Time Complexity Big-O(V*E)

public class DiameterByBellmanford 
{


	// Graph class 
	static class BellGraph 
	{
	    private int vertices;
	    private PriorityQueue<Edge>[] adj;
	 
	    public int getVertices() 
	    {
	        return vertices;
	    }
	 
	    public BellGraph(int vertices) 
	    {
	        this.vertices = vertices;
	        // initialize adjacency 
	        adj = new PriorityQueue[vertices];
	        for (int i = 0; i < vertices; i++) 
	        {
	            adj[i] = new PriorityQueue<Edge>();
	        }
	    }
	 
	    public void addEdge(int source, int dest, int weight) 
	    {
	        adj[source].add(new Edge(source, dest, weight));
	    }
	 
	    public void addEdge(Edge e) 
	    {
	        adj[e.getSource()].add(e);
	    }
	 
	    public void removeEdge(int i, int j) 
	    {
	        Iterator<Edge> it = adj[i].iterator();
	        Edge other = new Edge(i, j, 0);
	        while (it.hasNext()) 
	        {
	            if (it.next().equals(other)) 
	            {
	                it.remove();
	                return;
	            }
	        }
	    }
	 
	    public boolean hasEdge(Edge edge) 
	    {
	        return adj[edge.getSource()].contains(edge);
	    }
	 
	    public PriorityQueue<Edge> neighbours(int vertex) 
	    {
	        return adj[vertex];
	    }
	}
	
	//------------------------------------------------------------------------------
	
	//Edge Class
	static class Edge implements Comparable
	{
	    private int source;
	    private int dest;
	    private int weight;
	 
	    public Edge(int startPoint, int endPoint, int weight) 
	    {
	        this.source = startPoint;
	        this.dest = endPoint;
	        this.weight = weight;
	    }
	 
	    public int getSource() 
	    {
	        return source;
	    }
	 
	    public int getDest() 
	    {
	        return dest;
	    }
	 
	    public int getWeight() 
	    {
	        return weight;
	    }
	 
	    public boolean equals(Edge neighbor) 
	    {
	        if (this.source == neighbor.source) 
	        {
	            if (this.dest == neighbor.dest) 
	            {
	                return true;
	            }
	        }
	        return false;
	    }
	    
	    public int compareTo(Object n) 
	    {
	        Edge neighbor = (Edge) n;
	        return Integer.compare(this.weight, neighbor.weight);
	    }	 
	}
	
	//----------------------------------------------------------------
	
	// BellmanFord Class
	static void bellmanAlgo(BellGraph g, int startVertex) 
	{
        // for storing distances
        float[] distances = new float[g.getVertices()];
        // for storing predecessors
        int[] predecessors = new int[g.getVertices()];
 
        // initialize arrays
        for (int i = 0; i < distances.length; i++) 
        {
            distances[i] = Integer.MAX_VALUE;
            predecessors[i] = -1;	// initializing all with no predecessors
        }
        distances[startVertex] = 0;
 
        // relax all edges v - 1 times repeatedly

            for (int i = 0; i < g.getVertices() - 1; i++) 
            {
                Iterator<Edge> it = g.neighbours(i).iterator();
                while (it.hasNext()) 
                {
                    Edge e = it.next(); // edge (u, v)
                    // Relaxation occurs here
                    // if distance(u) + w(u, v) < distance(v) then distance(v) = distance(u) + w(u, v)
                    // predecessor(v) = u
                    if (distances[e.getSource()] + e.getWeight() < distances[e.getDest()]) 
                    {
                        distances[e.getDest()] = distances[e.getSource()] + e.getWeight();
                        predecessors[e.getDest()] = e.getSource();
                    }
                }
            }
 
        // One More time Iteration to check for any negative weights
        // check for negative weight circles and if exists then Return
        for (int i = 0; i < g.getVertices() - 1; i++) 
        {
            Iterator<Edge> it = g.neighbours(i).iterator();
            while (it.hasNext()) 
            {
                Edge e = it.next(); // edge (u, v)
                // if distance(u) + w(u, v) < distance(v) then graph contains negative-weight circle
                if (distances[e.getSource()] + e.getWeight() < distances[e.getDest()]) 
                {
                    System.out.println("The Graph has negative-weight circle");
                    return;
                }
            }
        }
 
        // Outputs the shortest paths from the single source
        System.out.println("Vertex\tDistance\tPredecessor");
        for (int i = 0; i < g.getVertices(); i++) 
        {
            System.out.println(i + "\t" + distances[i] + "\t\t" + predecessors[i]);
        }
        
        int maxNode = 0;
        float maxDistance = 0;
        int maxPre = 0;
        
        for (int i = 0; i < g.getVertices(); i++) 
        {
            if(distances[i] > maxDistance && predecessors[i] != -1)
            {
            	maxDistance = distances[i];
            	maxNode = i;
            	maxPre = predecessors[i];
            }
        }	
        
        System.out.println();
        
        System.out.println("The Diameter of the Graph is: "+(int)maxDistance);
        System.out.println("From source: "+startVertex);
        System.out.println("To max node: "+maxNode);
        System.out.println("With max Predecessor: "+maxPre);
        System.out.println();
 
    }
	
	public static void main(String[] args) throws IOException 
	{
		 ArrayList<GraphInfo> fbGraph = new ArrayList<>();
         FileHandler handler = new FileHandler();
         handler.GraphFileReader(fbGraph);
         
         int source = 2873;
         
         BellGraph bg = new BellGraph(4039);
         
         for (int i = 0; i <fbGraph.size() ; i++) 
         {
             bg.addEdge(fbGraph.get(i).source, fbGraph.get(i).destination, fbGraph.get(i).weight);
         }
         
         long initiate = System.currentTimeMillis();
         
         handler.writeFileDiameterByBellman();
         
         bellmanAlgo(bg, source);
         
         long end = initiate - System.currentTimeMillis();
         long executionTime = end;
         long finalTime = Math.abs(executionTime);
         
         handler.writeToConsole();
         
         bellmanAlgo(bg, source);
       
        System.out.println("\n\nExecution time: " + finalTime + " milli-seconds");
         
        PrintWriter write = new PrintWriter("Execution Time by Bellman.txt");
        write.println("Execution time: " + finalTime + " milli-seconds");   
        write.close(); 

	}

}
