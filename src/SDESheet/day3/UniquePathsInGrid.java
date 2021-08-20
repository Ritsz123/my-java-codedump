package SDESheet.day3;

//! use recursion to find all the paths that reaches the end
// start from point (n,m) use base case i==m-1 && j==n-1 return 1 and count the paths
// at each point there are 2 possibilities move right or move down.

//! Better USE DP for the same recursive code

public class UniquePathsInGrid {

    public static void main(String[] args) {

        //! Brute O(2 ^ N)
        int x = 3;
        int y = 7;
        System.out.println("Naive recursion: ");
        System.out.println(uniquePathRecursive(0,0, x, y));

        //! better sol with DP tc : O(N * M) sc:O(N * M)
        int[][] dp = new int[x][y];
        for (int i = 0;i<x;i++){
            for(int j = 0;j<y;j++){
                dp[i][j] = -1;
            }
        }
        System.out.println("Using DP: ");
        System.out.println(uniquePathRecursiveDP(0,0, x,y,dp));
    }

    static int uniquePathRecursive(int i, int j, int x, int y) {
        if(i == x-1 && j == y-1) return 1;

        if(i >= x || j >= y) return 0;

        int right = uniquePathRecursive(i, j+1, x,y);
        int bottom = uniquePathRecursive(i+1, j, x,y);

        return right + bottom;
    }

    static int uniquePathRecursiveDP(int i, int j, int x, int y, int[][] dp) {
        if(i == x-1 && j == y-1) return 1;

        if(i >= x || j >= y) return 0;

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int right = uniquePathRecursiveDP(i, j+1, x,y, dp);
        int bottom = uniquePathRecursiveDP(i+1, j, x,y, dp);

        return dp[i][j] = right + bottom;
    }
}
