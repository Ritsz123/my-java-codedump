package dsaone.dp;


public class KnapSackProblem {

//	0/1 knapsack

	public static void main(String[] args) {
//		W => Knapsack capacity

		int w = 10;
		int[] weights = {1,3,4,6};
		int n = weights.length;
		int[] price = {20,30,10,50};
		int ans = solve(n,w,weights,price);
//		int ans = bruteKnap(0,weights,price,w,0);
		System.out.println(ans);
	}

//	DP solution (efficient)
	static int solve(int n,int w,int [] weights,int [] price) {
		int [][] dp = new int[n+1][w+1];
		
		for(int i=0;i<w;i++) {
			dp[0][i]=0;
		}
		for(int i=0;i<n;i++) {
			dp[i][0]=0;
		}
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=w;j++) {
				dp[i][j]=knap(i,j,weights,price,dp);
			}
		}
		return dp[dp.length-1][dp[0].length-1];
	}

	static int knap(int i,int j, int [] weights,int [] price,int [][] dp) {
		int withoutInclude = dp[i-1][j];
		int withInclude = 0;
		int currentWeight = weights[i-1]; 
		if(currentWeight <= j) {
			withInclude = price[i-1] + dp[i-1][j-currentWeight];
		}
		return Math.max(withoutInclude, withInclude);
	}

//	BruteForce
	static int bruteKnap(int i,int [] weights,int[] prices,int capacity,int currentProfit){
		if (i >= weights.length || capacity <= 0 ){
			return currentProfit;
		}
		int take = 0;
		if (capacity-weights[i]>=0){
			take = bruteKnap(i+1,weights,prices,capacity-weights[i],currentProfit+prices[i]);
		}

		int notTake = bruteKnap(i+1,weights,prices,capacity,currentProfit);
		return Math.max(take,notTake);
	}
}
