import java.io.IOException;
import java.util.ArrayList;

public class Application {
    public static void main(String[] args) throws IOException {
        Prim prim = new Prim();
        ArrayList<GraphInfo> graph = new ArrayList<>();
        FileHandler handler = new FileHandler();
        handler.GraphFileReader(graph);
        ArrayList<Integer> integers ;
        integers =GetUniqueElements(graph);
        ArrayList<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i <integers.size(); i++) {
            vertices.add(new Vertex(integers.get(i).toString()));
        }
        System.out.println(vertices.size());
        for (int i = 0; i <graph.size() ; i++) {
            prim.addVertex(findVertex(Integer.toString(graph.get(i).source),vertices), findVertex(Integer.toString(graph.get(i).destination),vertices), new Edge(graph.get(i).weight));
            prim.addVertex(findVertex(Integer.toString(graph.get(i).destination),vertices),findVertex(Integer.toString(graph.get(i).source),vertices), new Edge(graph.get(i).weight));
        }
        prim.printAllGraph();
        prim.scanPrimAlgorithm();
        System.out.println( "-------------\n");
        prim.printMst();
    }
    public static ArrayList<Integer> GetUniqueElements(ArrayList<GraphInfo> graphInfos){
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i <graphInfos.size() ; i++) {
            if(!integers.contains(graphInfos.get(i).source)){
                integers.add(graphInfos.get(i).source);
            }
        }
        for (int i = 0; i <graphInfos.size() ; i++) {
            if(!integers.contains(graphInfos.get(i).destination)){
                integers.add(graphInfos.get(i).destination);
            }
        }
        return integers;
    }
    public static Vertex findVertex(String name , ArrayList<Vertex> vertexlist){
        Vertex vertex = new Vertex();
        for (int i = 0; i <vertexlist.size() ; i++) {
            if(vertexlist.get(i).getName().equalsIgnoreCase(name)){
              vertex=vertexlist.get(i);
            }
        }
        return vertex;
    }
}
