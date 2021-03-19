package contests.codechef;

import java.io.*;
class Prob6 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t--!=0){
            String[] line = br.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            long k = Long.parseLong(line[1]);
            line = br.readLine().split(" ");
            long [] arr = new long[n];
            for(int i=0;i<n;i++){
                arr[i]=Long.parseLong(line[i]);
            }
            boolean isPoss = true;
            for(int i=0;i<n;i++){
                if(arr[i]%k!=0){
                    boolean done = false;
                    for(int j=0;j<n;j++){
                        if(((arr[i]+arr[j])&k)==0){
                            done = true;
                            break;
                        }
                    }
                    if(!done){
                        isPoss = false;
                        break;
                    }
                }
            }
            if (isPoss){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }
}


