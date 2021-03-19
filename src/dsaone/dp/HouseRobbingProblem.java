package dsaone.dp;

import java.util.Scanner;

public class HouseRobbingProblem {


    static int maxProfitByRobbingDP(int i,int [] gold,int [] dp){
        if(i==0) return gold[0];
        if(i==1) return Math.max(gold[0],gold[1]);

        if(dp[i]!=-1){
            return dp[i];
        }
        int max = Math.max(maxProfitByRobbingDP(i-2,gold,dp) + gold[i],maxProfitByRobbingDP(i-1,gold,dp));
        dp[i] = max;
        return max;
    }

    static int maxProfitByRobbing(int i,int [] gold){
        if(i==0){
            return gold[0];
        }
        if(i==1) return  Math.max(gold[0],gold[1]);

        return Math.max(maxProfitByRobbing(i-2,gold)+gold[i],maxProfitByRobbing(i-1,gold));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] gold = new int[n];
        for(int i=0;i<n;i++){
            gold[i] = sc.nextInt();
        }

        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i]=-1;
        }

//        int ans = maxProfitByRobbing(n-1,gold);
        int ans = maxProfitByRobbingDP(n-1,gold,dp);
        System.out.println(ans);
    }

}
