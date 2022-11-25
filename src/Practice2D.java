public class Practice2D {

    public static void main(String[] args) {
        int [][] arr = new int[4000][4000];
        for (int i = 0; i <2000 ; i++) {
            for (int j = 0; j <4000 ; j++) {
              arr[i][j]=1;
                System.out.println(arr[i][j]);
            }
        }
    }
}
