package contests.codechef;

import java.io.*;

class prob1 {
    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int h = Integer.parseInt(line[1]);
        int x = Integer.parseInt(line[2]);

        int[] arr = new int[n];
        line = br.readLine().split(" ");
        for (int i=0;i<n;i++){
            arr[i] = Integer.parseInt(line[i]);
        }
        boolean poss = false;
        for (int i=0;i<n;i++){
            if ((arr[i] + x)>=h){
                poss = true;
                break;
            }
        }
        System.out.println(poss?"YES":"NO");
    }
}
