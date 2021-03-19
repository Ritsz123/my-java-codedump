package dsaone.dp;

import java.util.Arrays;

public class MinCoins {
	public static void main(String[] args) {
		int n = 18;
		int[] coins = {7,5,1};
		int [] dp = new int[n+1];
		Arrays.fill(dp, -1);
		dp[0]=0;
		int ans = countMin(n, coins,dp);
		System.out.println(ans);
	}
	
	static int countMin(int n,int[] coins,int[] dp) {
		if(n==0) return 0;
		int ans = Integer.MAX_VALUE;
		for(int i=0;i<coins.length;i++) {
			if(n-coins[i]>=0) {
				int subAns;
				if(dp[n-coins[i]]!=-1) {
					subAns = dp[n-coins[i]];
				}else {
					subAns = countMin(n-coins[i],coins,dp);
				}
				if(subAns!=Integer.MAX_VALUE && subAns+1 < ans) ans = subAns+1;
			}
		}
		dp[n]=ans;
		return ans;
	}
}
