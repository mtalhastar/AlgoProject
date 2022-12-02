/* Name: Khursheed Alam Khan					Dijkstra Algorithm
 * 20i-0496
 * SE-R
 */
import java.awt.RenderingHints.Key;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
import java.lang.Math;

// TIME COMPLEXITY: E * log(E) since insertion of PQ is log(n), and searching is (n).. Therefore n * log(n) = Big-O(nlog(n)) || E{logE) where E is no. of edges iterated

class Dijkstra {
    
     static class DjikPair	// class pair to know the distance to a vertex 
     {
        int vertex;
        int distance;
        public DjikPair(int vertex, int distance)
        {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
     
  // putting the reachable node from source + the distance to that node in hash map
     static class DjikGraph
     {
        int vertices;
        HashMap<Integer,ArrayList<DjikPair>>adjList;	// initializing adjacency list representation 
        public DjikGraph(int vertices)
        {
            this.vertices = vertices;
            adjList = new HashMap();
        }
        
        public void addEdge(int source,int destination,int distance)	// Adding edges to our graph
        {
            if(!adjList.containsKey(source))
            {
             adjList.put(source,new ArrayList<DjikPair>());   
            }
            adjList.get(source).add(new DjikPair(destination,distance));
        }
        
        public int dijkstraAlgorithm(int source) throws FileNotFoundException
        {
            HashMap<Integer,Integer>visitedDistance = new HashMap();	// A visited Distance Hash Map to store what nodes have been visited with their shortest distances
        
            PriorityQueue<DjikPair> pq = new PriorityQueue<DjikPair>((x,y) -> x.distance - y.distance); // creating priority-queue according to Minimum-Heap Distance
            pq.add(new DjikPair(source,0));	// add the source vertex to the PQ with distance set to 0
            int result = Integer.MIN_VALUE;	// result is what to display, setting it to MIN to let Priority queue sort in minimum heap manner with shortest distance placed before longest distance 
            
            while(pq.size()!=0)	// making sure while pa is not empty
            {
                DjikPair pair = pq.remove();	// polling the source vertex from priority queue

                if(!visitedDistance.containsKey(pair.vertex))	// if visited distance map does not contain the current vertex, the code continues
                {
	                visitedDistance.put(pair.vertex, pair.distance);	// placing the polled vertex + distance to the distance map
	                //result = Math.max(result,pair.distance);
	                int current = pair.vertex;	// then placing the pair vertex to current 
	                if(adjList.containsKey(current))	// condition to check if node polled is available in adjList	
	                {
		                for(DjikPair x:adjList.get(current))	// then we begin to check its neighbors
		                {
		                    if(!visitedDistance.containsKey(x.vertex))	// if neighbor node is not in visitedDistance, we place it in the priority queue
		                    {
		                        pq.add(new DjikPair(x.vertex,pair.distance + x.distance));	// the neighbor vertex and previous node distance + neighbor distance saved in priority queue
//		                        FileHandler.writeFile();
		                    }
		                }
	                }
                }	// else it goes back to while loop
            }
            System.out.println(visitedDistance);
            
            System.out.println("");
            
            return (visitedDistance.size()!=vertices)?-1:result;	// final result obtained from source to destination with shortest distance
        }
    }
     
     public static void main(String args[]) throws IOException 
     {
    	 ArrayList<GraphInfo> fbGraph = new ArrayList<>();
         FileHandler handler = new FileHandler();
         handler.GraphFileReader(fbGraph);
         
         int source = 2873;
         
         DjikGraph dg = new DjikGraph(4039);
         
         for (int i = 0; i <fbGraph.size() ; i++) 
         {
             dg.addEdge(fbGraph.get(i).source, fbGraph.get(i).destination, fbGraph.get(i).weight);
         }
         
         long initiate = System.currentTimeMillis();
         
         handler.writeFileDjikstra();
         
         dg.dijkstraAlgorithm(source);
         
         long end = initiate - System.currentTimeMillis();
         long executionTime = end;
         long finalTime = Math.abs(executionTime);
         
         handler.writeToConsole();
         
         dg.dijkstraAlgorithm(source);
       
         System.out.println("\n\nExecution time: " + finalTime + " milli-seconds");
         
         PrintWriter write = new PrintWriter("Execution Time by Dijkstra.txt");
         write.println("Execution time: " + finalTime + " milli-seconds");   
         write.close(); 
     }
}