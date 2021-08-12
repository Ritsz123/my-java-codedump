package DP_RJ;

// ! Minimum falling path sum
public class LC931 {
    static int inf = (int) 1e5;

    public static void main(String[] args) {
        int[][] arr = {
            {2, 1, 3},
            {6, 5, 4},
            {7, 8, 9}
        };

        System.out.println("using recursion");
        findMinimumPathSumRecursive(arr);

        System.out.println("using dp");
        findMinimumPathSumMemoizationDP(arr);
    }

    private static void findMinimumPathSumMemoizationDP(int[][] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0;i<n;i++){
            for (int j = 0;j<n;j++){
                dp[i][j] = inf;
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int j = 0;j<n;j++){
            ans = Math.min(ans, findFallingSumDP(arr,0,j,dp));
        }

        System.out.println(ans);
    }

    private static int findFallingSumDP(int[][] arr, int i, int j, int[][] dp) {
        if (i == arr.length) return 0; // success state
        if (j < 0 || j>= arr.length) return inf; // going out of bound

        if (dp[i][j] != inf){
            return dp[i][j];
        }

        int left = findFallingSumDP(arr, i+1, j-1, dp);
        int mid = findFallingSumDP(arr, i+1,j, dp);
        int right = findFallingSumDP(arr, i+1, j+1,dp);

        return dp[i][j] = Math.min(left, Math.min(mid, right)) + arr[i][j];
    }

    private static void findMinimumPathSumRecursive(int[][] arr) {
        int ans = Integer.MAX_VALUE;
        for(int j = 0; j<arr.length;j++){
            ans = Math.min(ans, findFallingSum(arr, 0, j));
        }
        System.out.println(ans);
    }

    private static int findFallingSum(int[][] arr, int i, int j) {

        if(i == arr.length) return 0;
        if (j < 0 || j >= arr.length) return inf;

        // at every point we move 1 step down

        // move left
        int left = findFallingSum(arr, i+1, j-1);
        // same
        int mid = findFallingSum(arr, i+1, j);
        // move right
        int right = findFallingSum(arr, i+1, j+1);

        return Math.min(left, Math.min(mid, right)) + arr[i][j];
    }
}
