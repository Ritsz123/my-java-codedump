package contests;

import java.io.*;
public class CFC {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t--!=0){
            String[] line = br.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            int [] initial = new int[n];
            line= br.readLine().split(" ");
            for(int i=0;i<n;i++){
                initial[i] = Integer.parseInt(line[i]);
            }
            line = br.readLine().split(" ");
            int [] required = new int[n];
            for(int i=0;i<n;i++){
                required[i] = Integer.parseInt(line[i]);
            }
            int [] painters = new int[m];
            line = br.readLine().split(" ");
            for(int i=0;i<m;i++){
                painters[i] = Integer.parseInt(line[i]);
            }

            int [] difference = new int[n];
            int diffCount = 0;
            for(int i=0;i<n;i++){
                if(initial[i]!= required[i]){
                    difference[i] = required[i];
                    diffCount++;
                }else{
                    required[i] = -1;
                }
            }
            boolean isPoss = true;
            if(diffCount>m){
                isPoss = false;
            }else{

            }
        }
    }
}
