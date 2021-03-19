package contests.codechef;

import java.io.*;
import java.util.Arrays;

class Prob4{

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t--!=0){
            int n = Integer.parseInt(br.readLine());
            String[] line = br.readLine().split(" ");
            int[] arr = new int[n];
            for (int i=0;i<n;i++){
                arr[i] = Integer.parseInt(line[i]);
            }
            Arrays.sort(arr);
            int start = arr[0];
            long count=0;
            boolean poss = false;;
            for (int i=0;i<n;i++){
                if(arr[i]>i+1){
                    poss = true;
                    break;
                }else{
                    long req = (i+1) + start-1;
                    count += req - arr[i];
                }

            }
            System.out.println((count%2==0 || poss)? "Second": "First");
        }
    }
}

