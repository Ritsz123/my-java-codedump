package adityaVermaDP;

public class LongestCommonSubSequence {
    public static void main(String[] args) {
        String s1 = "sietmr";
        String s2 = "ritesh";

//        System.out.println(longestCommonSubSequenceRecursive(s1,s2,s1.length(),s2.length()));

//        int[][] dp = new int[s1.length()+1][s2.length()+1];
//        for (int i=0;i<=s1.length();i++){
//            for (int j=0;j<=s2.length();j++){
//                dp[i][j] = -1;
//            }
//        }
//        System.out.println(lcsMemoized(s1,s2,s1.length(),s2.length(),dp));

//        System.out.println(lcsTD(s1,s2,s1.length(),s2.length()));

//        System.out.println(longestCommonSubstring(s1,s2,s1.length(),s2.length()));

//        System.out.println(printLongestCommonSubsequence(s1,s2));

//        System.out.println(shortestCommonSuperSequence(s1,s2));

//        System.out.println(minimumNumberOfInsertionAndDeletionRequiredToMakeAasB("heap","pea"));

//        System.out.println(longestPalindromicSubSequence("abgdcbca"));

        System.out.println(minimumNumberOfDeletionsToMakeTheStringPalindrome("abgdcbca"));
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

    static  int lcsTD(String s1,String s2,int n,int m){
        int[][] dp = new int[n+1][m+1];
        //initialization
        for (int i=0;i<=n;i++){
            for (int j=0;j<=m;j++){
                if (i==0 || j==0){
                    dp[i][j] = 0;
                }
            }
        }
        for (int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }

    static int longestCommonSubstring(String s1,String s2,int n,int m){
        int[][] dp = new int[n+1][m+1];
        for (int i=0;i<=n;i++){
            for (int j=0;j<=m;j++){
                if (i==0 || j==0){
                    dp[i][j] = 0;
                }
            }
        }
        int mx = 0;
        for (int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = 0;
                }
                mx = Math.max(mx,dp[i][j]);
            }
        }
        return mx;
    }

    static String printLongestCommonSubsequence(String s1,String s2){
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n+1][m+1];

        for (int i = 1;i <= n;i++){
            for(int j = 1;j <= m; j++){
                if (s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        String str = "";
        int i = n,j=m;
        while(i>0 && j>0){
            if (s1.charAt(i-1) == s2.charAt(j-1)){
                str+=s1.charAt(i-1);
                i--;
                j--;
            }else{
                if(dp[i-1][j]>dp[i][j-1]){
                    i--;
                }else{
                    j--;
                }
            }
        }
        char[] ar = str.toCharArray();
        i=0;j=ar.length-1;
        while (i<j){
            char t = ar[i];
            ar[i] = ar[j];
            ar[j] = t;
            i++;
            j--;
        }
        return new String(ar);
    }

    static int shortestCommonSuperSequence(String s1,String s2){
        int n = s1.length();
        int m = s2.length();
        return n + m - lcsTD(s1,s2,n,m);
    }

    static int minimumNumberOfInsertionAndDeletionRequiredToMakeAasB(String s1, String s2){
        int lcs = lcsTD(s1,s2,s1.length(),s2.length());
        return s1.length()-lcs + s2.length()-lcs;
    }

    static int longestPalindromicSubSequence(String str) {
        char[] ar = str.toCharArray();
        int i = 0,j=ar.length-1;
        while(i<j){
            char t = ar[i];
            ar[i] = ar[j];
            ar[j] = t;
            i++;
            j--;
        }
        int lps = lcsTD(str,new String(ar),str.length(),str.length());
        return lps;
    }

    static int minimumNumberOfDeletionsToMakeTheStringPalindrome(String str) {
        return str.length() - longestPalindromicSubSequence(str);
    }
}
