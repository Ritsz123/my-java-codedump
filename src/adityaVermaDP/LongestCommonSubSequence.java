package adityaVermaDP;

public class LongestCommonSubSequence {
    public static void main(String[] args) {
        String s1 = "rit";
        String s2 = "ritesh";

//        System.out.println(longestCommonSubSequenceRecursive(s1,s2,s1.length(),s2.length()));

        int[][] dp = new int[s1.length()+1][s2.length()+1];
        for (int i=0;i<=s1.length();i++){
            for (int j=0;j<=s2.length();j++){
                dp[i][j] = -1;
            }
        }
        System.out.println(lcsMemoized(s1,s2,s1.length(),s2.length(),dp));
    }

    static int longestCommonSubSequenceRecursive(String s1,String s2,int n,int m){
        if(n==0 || m==0) return 0;

        if(s1.charAt(n-1) == s2.charAt(m-1)){
            return 1 + longestCommonSubSequenceRecursive(s1,s2,n-1,m-1);
        }
        return Math.max(longestCommonSubSequenceRecursive(s1,s2,n-1,m), longestCommonSubSequenceRecursive(s1,s2,n,m-1));
    }

    static int lcsMemoized(String s1, String s2, int n, int m, int [][] dp){
        if(n==0 || m==0) return 0;

        if (dp[n][m] != -1) return dp[n][m];

        if (s1.charAt(n-1) == s2.charAt(m-1)){
            dp[n][m] = 1 + lcsMemoized(s1,s2,n-1,m-1,dp);
        }else{
            dp[n][m] = Math.max(lcsMemoized(s1,s2,n-1,m,dp), lcsMemoized(s1,s2,n,m-1,dp));
        }
        return dp[n][m];
    }
}
