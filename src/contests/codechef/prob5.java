package contests.codechef;

import java.io.*;
import java.util.*;

class prob5 {

    public static void main(String[] args)throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t--!=0){
            int n = Integer.parseInt(br.readLine());
            String[] arr = br.readLine().split(" ");
            HashSet<String> hs= new HashSet<>();
            for(int i=0;i<n;i++){
                hs.add(arr[i]);
            }
            int c=0;
            Arrays.sort(arr);

            for(int i=0;i<n-1;i++){
                if(arr[i].charAt(i)==arr[i+1].charAt(i)){
                    continue;
                }
                for(int j=i+1;j<n;j++){
                    char[] a = arr[i].toCharArray();
                    char[] b = arr[j].toCharArray();
                    char temp = a[0];
                    a[0]=b[0];
                    b[0]=temp;
                    String a1 = new String(a);
                    String b1 = new String(b);
                    if(hs.contains(a1)||hs.contains(b1)){

                    }else{
                        c+=2;
                    }
                }
            }
            System.out.println(c);
        }
    }
}
