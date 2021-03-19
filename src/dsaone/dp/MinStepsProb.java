package dsaone.dp;

import java.util.Arrays;

// the problem is basically to find the minimum number of steps required to make the number 0 (ZERO)
// at each step we can perform any one operation
// divide n by 2
// divide n by 3
// subtract n by 1

public class MinStepsProb {

    // Bottom Up approach
    static int minStepsBU(int n){
        int [] dp = new int[n+1];
        Arrays.fill(dp,-1);

        dp[2]=1;
        dp[3]=1;

        int sub1,div2,div3;

        for(int i=4;i<=n;i++){
            sub1 = div2 = div3 = Integer.MAX_VALUE;
            if(i%3==0){
                div3 = dp[i/3];
            }
            if(i%2==0){
                div2=dp[i/2];
            }
            sub1 = dp[i-1];
            dp[i] = 1 + Math.min(sub1,Math.min(div2,div3));
        }
        return dp[n];
    }

    // Top Down approach
    static int minStepsTD(int n,int[] dp){
        if(n==2||n==3||n==1) return 1;

        if(dp[n]!=Integer.MAX_VALUE){
            return dp[n];
        }
        int div3,div2,sub1;
        sub1 = div2 = div3 = Integer.MAX_VALUE;

        if(n%3==0){
            div3 = minStepsTD(n/3,dp);
        }
        if(n%2==0){
            div2 = minStepsTD(n/2,dp);
        }
        sub1 = minStepsTD(n-1,dp);

        dp[n] = 1 + Math.min(sub1,Math.min(div2,div3));
        return dp[n];
    }

    public static void main(String[] args) {

        int n = 10;
        int [] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        System.out.println("Using Top Down DP");
        System.out.println(minStepsTD(n,dp));

        System.out.println("Using Bottom Up DP");
        System.out.println(minStepsBU(10));
    }
}
