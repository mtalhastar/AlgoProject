import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class FileHandler {

 // Import the IOException class to handle errors
public static void GraphFileReader(ArrayList<GraphInfo> graphInfos) throws IOException {

    FileReader fr=new FileReader("src/facebook_combined.txt");
    BufferedReader br=new BufferedReader(fr);
    StringTokenizer myTokens;
    String line;
    while ((line = br.readLine())!= null) {

        myTokens = new StringTokenizer(line, " ");
        while (myTokens.hasMoreTokens()){
         int source=   Integer.parseInt(myTokens.nextToken());
         int destination=Integer.parseInt(myTokens.nextToken());
         int weight = Integer.parseInt(myTokens.nextToken());
         graphInfos.add(new GraphInfo(source,destination,weight));
        }
    }
    br.close();
    fr.close();
}


public static void GraphWeightWriter (ArrayList<GraphInfo> graphInfos,int bound) {
          Random random = new Random();
            try {
                FileWriter myWriter = new FileWriter("src/facebook_combined.txt");
                for (int i = 0; i < graphInfos.size() ; i++) {
                    myWriter.write(graphInfos.get(i).source+" "+graphInfos.get(i).destination+" "+random.nextInt(bound)+"\n");
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
 public static void main(String[] args) throws IOException {
    ArrayList <GraphInfo> graphInfos  = new ArrayList<>();
    GraphFileReader(graphInfos);
     for (int i = 0; i <graphInfos.size() ; i++) {
         System.out.println(graphInfos.get(i).source+" "+graphInfos.get(i).destination+" "+graphInfos.get(i).weight+"\n");
     }
    //pass your array into this function with txt file path , this function returns array filled with info inside txt
     // as source , destination and weight
    //copy your absolute file paths here and only use GraphFileReader Function in your classes.

 }


}


