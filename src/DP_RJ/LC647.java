package DP_RJ;

// ! count of palindromic substrings

public class LC647 {
    public static void main(String[] args) {
        String str = "ababc";

        countOfPalindrome(str);
    }

    private static void countOfPalindrome(String str) {
        int n = str.length();
        boolean [][] dp = new boolean[n][n];
        boolean [][] vis = new boolean[n][n];

        int count = 0;
        for (int i = 0;i<n;i++){
            for (int j = i; j<n;j++){
                if (isPalindrome(str, i, j, dp, vis)){
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    private static boolean isPalindrome(String str, int i, int j, boolean[][] dp, boolean[][] vis) {
        if (i > j) return true;

        if (vis[i][j]) return dp[i][j];

        vis[i][j] = true;
        if (str.charAt(i) == str.charAt(j)){
            return dp[i][j] = isPalindrome(str, i+1, j-1, dp, vis);
        }else{
            return dp[i][j] = false;
        }
    }
}
