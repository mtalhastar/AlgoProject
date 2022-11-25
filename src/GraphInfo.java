public class GraphInfo {

     int source;
     int destination;
     int weight;

     GraphInfo(int source,int destination,int weight){
         this.source=source;
         this.destination=destination;
         this.weight=weight;
     }


    GraphInfo(int source,int destination){
        this.source=source;
        this.destination=destination;
    }

    GraphInfo(){
        this.source=source;
        this.destination=destination;
    }


    public int getDestination() {
        return destination;
    }

    public int getSource() {
        return source;
    }

    public int getWeight() {
        return weight;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
