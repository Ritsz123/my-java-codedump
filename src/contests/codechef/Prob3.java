package contests.codechef;

import java.io.*;

class Prob3 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t--!=0){
            long n = Long.parseLong(br.readLine());
            int bits = 0;
            long temp = n;
            while (temp!=0){
                temp = temp/2;
                bits++;
            }
            long a = (long) Math.pow(2,bits-1)-1;
            long b = a^n;
            System.out.println(a*b);
        }
    }
}
