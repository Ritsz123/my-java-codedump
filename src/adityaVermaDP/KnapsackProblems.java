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

        // Input for count of subsets of given sum
//        int[] arr2 = { 2,3,5,6,8,10 };
//        int sum = 10;
//        System.out.println(countOfSubsetsOfSum(arr2,sum));

        int[] arr3 = { 1,2,7,2,2 };
        System.out.println(minimumSubsetSumDifference(arr3));
    }

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
}
