package contests.codechef;

import java.io.*;

class Prob2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t--!=0){
            char[] arr = br.readLine().toCharArray();
            int count = 0;
            boolean grp = false;
            for (int i=0;i<arr.length;i++){
                if (arr[i]=='1' && !grp){
                    grp = true;
                    count++;
                }
                if (arr[i]=='0'){
                    grp = false;
                }
            }
            System.out.println(count);
        }
    }
}
