package contests.hackerrank;

import java.io.*;
import java.math.*;

public class Solution {

    public static void main(String[] args) throws  Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t--!=0){
            String [] line = br.readLine().split(" ");
            long start = Long.parseLong(line[0]);
            long end = Long.parseLong(line[1]);

            int ans = (int) Math.floor(Math.sqrt(end)) -(int) Math.ceil(Math.sqrt(start)) + 1;

            System.out.println(ans);
        }
    }

    static boolean appendAndDelete(String s, String t, int k) {
        int i=0;
        boolean poss = false;
        if (s.length()+t.length()<=k){
            return true;
        }
        for (;i<s.length() && i<t.length();i++){
            if (s.charAt(i)!=t.charAt(i)){
                break;
            }
        }
        int minReq = (s.length()-i) + (t.length()-i);
        return k >= minReq && (k - minReq) % 2 == 0;
    }

    static void extraLongFactorials(int n) {
        BigInteger bigInteger = BigInteger.ONE;
        while (n!=1){
            bigInteger = bigInteger.multiply(BigInteger.valueOf(n));
            n--;
        }
        System.out.println(bigInteger);
    }

    static int findDigits(int n) {
        int temp = n;
        int count=0;
        while (n!=0){
            int num = n%10;
            n=n/10;
            if (num!=0 && temp%num==0) count++;
        }
        return count;
    }

    static int jumpingOnClouds(int[] c, int k) {
        int count=0;
        int n = c.length;
        int i=0;
        do {
            i = (i+k)%n;
            count++;
            if(c[i]==1){
                count+=2;
            }
        }
        while (i!=0);
        return 100-count;
    }

    static int[] permutationEquation(int[] p) {
        int[] ans = new int[p.length-1];
        for(int i=1;i<p.length;i++){
            int pos=0;
            for(int j=1;j<p.length;j++){
                if(p[j]==i){
                    pos = j;
//                    System.out.println("pos found at " + pos);
                    break;
                }
            }
            for(int j=1;j<p.length;j++){
                if(p[j]==pos){
                    pos = j;
//                    System.out.println("found pos 2nd time" + pos);
                    break;
                }
            }
            ans[i-1] = pos;
        }
        return ans;
    }

    static int[] circularArrayRotation(int[] a, int k, int[] queries) {

        k = k%a.length;
        int [] ans = new int[queries.length];
        int [] temp = new int[k];
        int n = a.length;

        for(int i=0;i<k;i++){
            temp[i] = a[n-k+i];
        }

        for(int i=n-1;i>=k;i--){
            a[i] = a[i-k];
        }

        for(int i=0;i<k;i++) {
            a[i] = temp[i];
        }

        for(int i=0;i<ans.length;i++){
            ans[i] = a[queries[i]];
        }
        return ans;
    }

    static int gcd(int a,int b){
        if(a==0){
            return b;
        }
        return gcd(b%a,a);
    }

    static int lcm(int a,int b){
        return (a/gcd(a,b))*b;
    }
}
