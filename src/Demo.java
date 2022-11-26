import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
public class Demo
    {
        public static void main(String [] args) throws IOException {
            UndirectedWeightedGraph g = new UndirectedWeightedGraph();
            ArrayList<GraphInfo> info = new ArrayList<>();
            FileHandler handler = new FileHandler();
            handler.GraphFileReader(info);
            ArrayList<Integer> number = new ArrayList<>();
            number=GetUniqueElements(info);
            for (int i = 0; i <number.size() ; i++) {
                g.insertVertex(Integer.toString(number.get(i)));
            }
            for (int i = 0; i <number.size() ; i++) {
                System.out.println((number.get(i).toString()));
            }
            for (int i = 0; i <info.size() ; i++) {
                g.insertEdge(Integer.toString(info.get(i).source),Integer.toString(info.get(i).destination),info.get(i).weight);
            }
            g.Prims();
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











