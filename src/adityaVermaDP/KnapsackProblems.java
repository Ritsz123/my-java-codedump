package adityaVermaDP;

import java.util.ArrayList;

public class KnapsackProblems {
    public static void main(String[] args) {
//        // Input for 0 1 knapsack
//        int[] val = {10,20,80,100,90};
//        int[] wt = {10,5,2,20,10};
//        int W = 25;
//        System.out.println(zeroOneKnapsack(val,wt,W));
//
//        // Input for subsetSumProblem
//        int[] arr = { 1,2,3,5,8,10};
//        int sum = 10;
//        System.out.println(subSetSumProblem(arr,sum));
//
//        // Input for equal sum partition
//        int[] arr1 = { 1,5,11,5 };
//        System.out.println(equalSumPartition(arr1));

//        // Input for count of subsets of given sum
//        int[] arr2 = { 2,3,5,6,8,10 };
//        int sum = 10;
//        System.out.println(countOfSubsetsOfSum(arr2,sum));

//        //minimum subset sum difference
//        int[] arr3 = { 1,2,7,2,2 };
//        System.out.println(minimumSubsetSumDifference(arr3));

//        //number of subset with given difference
//        int[] arr4 = { 1,1,2,3 };
//        int diff = 1;
//        System.out.println(numberOfSubsetsWithGivenDifference(arr4,diff));

//        //Target sum
//        int[] arr5 = {1,1,2,3};
//        int target = 1;
//        System.out.println(targetSum(arr5,target));

//        // unbounded knapsack
//        int[] val = {10,20,80,100,90};
//        int[] wt = {10,5,25,20,10};
//        int W = 20;
//        System.out.println(unboundedKnapsack(val,wt,W));

//        // Rod cutting problem
//        int[] prices = {5,10,15};
//        int[] pieces = {1,2,3};
//        int rod = 5;
//        System.out.println(rodCuttingProblem(prices,pieces,rod));

//        // Coin change problem
        int[] coins = {1,2,5};
        int sum = 5;
        System.out.println(coinChangeProblem1(coins,sum));
    }

    // problems related to 0/1 knapsack

//    maximize the profit (standard knapsack)
    static int zeroOneKnapsack(int []val,int [] wt,int W){
//        initialize
        int[][] dp = new int[val.length+1][W+1];

        int n = val.length;
        for (int i = 0;i<=n;i++){
            dp[i][0] = 0;
        }
        for (int j = 0;j<=W;j++){
            dp[0][j] = 0;
        }

        for (int i = 1;i<=n;i++){
            for(int j = 1;j<=W;j++){
                if (wt[i-1] <= j){
                    dp[i][j] = Math.max(val[i-1] + dp[i-1][j - wt[i-1]], dp[i-1][j]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][W];
    }

//    check if there exist any subset with given sum
    static boolean subSetSumProblem(int[] arr,int sum){
//        init
        int n = arr.length;
        boolean[][] dp = new boolean[n+1][sum+1];

        for (int j = 0;j<=sum;j++){
            dp[0][j] = false;
        }

        for (int i = 0;i<=n;i++){
            dp[i][0] = true;
        }

        for (int i = 1;i<=n;i++){
            for (int j = 1;j<=sum;j++){
                if (arr[i-1] <= j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i-1]];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][sum];
    }

//    find if there exists subset such that sum of elements in subset is equal
//    sum of elements not present in subset
    static boolean equalSumPartition(int[] arr){
        int sum = 0;
        for (int i = 0;i<arr.length;i++){
            sum+=arr[i];
        }
        if (sum%2==0){
            return subSetSumProblem(arr,sum/2);
        }
        return false;
    }

//    count of the subsets whose sum is equal to given sum
    static int countOfSubsetsOfSum(int[] arr,int sum){
        int n = arr.length;
        int[][] dp = new int[n + 1][sum + 1];

        for (int j = 0;j<=sum; j++){
            dp[0][j] = 0;
        }

        for (int i = 0;i<=n;i++){
            dp[i][0] = 1;
        }

        for (int i=1;i<=n;i++){
            for (int j = 1;j<=sum;j++){
                if (arr[i-1] <= j){
                    dp[i][j] = dp[i-1][j] + dp[i-1][j - arr[i-1]];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][sum];
    }

    static int minimumSubsetSumDifference(int[] arr){
        int sum = 0;
        int n = arr.length;
        for (int i=0;i<n;i++){
            sum+=arr[i];
        }

        //code similar to subsetSum
        boolean[][] dp = new boolean[n+1][sum+1];
        for (int j = 0;j<=sum;j++){
            dp[0][j] = false;
        }
        for (int i = 0;i<=n;i++){
            dp[i][0] = true;
        }

        for (int i = 1;i<=n;i++){
            for (int j = 1;j<=sum;j++){
                if (arr[i-1] <= j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-arr[i-1]];
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

//        actual new code for this problem
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0;i<(sum+2)/2;i++){
            if (dp[n][i]) al.add(i);
        }

        int mn = Integer.MAX_VALUE;
        for (int i = 0;i<al.size();i++){
            mn = Math.min(mn,sum-2 * al.get(i));
        }
        return mn;
    }

    static int numberOfSubsetsWithGivenDifference(int [] arr, int diff){
        int sum = 0;
        int n = arr.length;
        for (int i =0;i<n;i++) sum+=arr[i];

        int s1 = (diff + sum)/2;

        return countOfSubsetsOfSum(arr,s1);
    }

    static int targetSum(int[] arr,int target){
        return numberOfSubsetsWithGivenDifference(arr,target);
    }


    // problems related to unbounded knapsack
    static int unboundedKnapsack(int [] val,int[] wt, int W){
        int n = val.length;
        int[][] dp = new int[n+1][W+1];
        for (int i =0;i<=n;i++){
            dp[i][0] = 0;
        }
        for (int j = 0;j<=W;j++){
            dp[0][j] = 0;
        }

        for (int i=1;i<=n;i++){
            for (int j = 1;j<=W;j++){
                if (wt[i-1] <= j){
                    dp[i][j] = Math.max(dp[i-1][j], val[i-1] + dp[i][j - wt[i-1]]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][W];
    }

    static int rodCuttingProblem(int[] prices,int[] pieces,int rodLen){
        int n = prices.length;
        int[][] dp = new int[n+1][rodLen+1];

        for(int j=0;j<=rodLen;j++){
            dp[0][j] = 0;
        }
        for(int i=0;i<=n;i++){
            dp[i][0] = 0;
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=rodLen;j++){
                if(pieces[i-1]<=j){
                    dp[i][j] = Math.max(dp[i-1][j],prices[i-1] + dp[i][j-pieces[i-1]]);
                }else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][rodLen];
    }

//    number of ways to form the given sum from coins
    static int coinChangeProblem1(int[] coins,int sum){
        int n = coins.length;
        int[][] dp = new int[n+1][sum+1];

        for(int j = 1;j<=sum;j++){
            dp[0][j] = 0;
        }
        for(int i = 0;i<=n;i++){
            dp[i][0] = 1;
        }

        for(int i = 1;i<=n;i++){
            for(int j = 1;j<=sum;j++){
                if(coins[i-1]<=j){
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                }else dp[i][j] = dp[i-1][j];
            }
        }
        return dp[n][sum];
    }
}
