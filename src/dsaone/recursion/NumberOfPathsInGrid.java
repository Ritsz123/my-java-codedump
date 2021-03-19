package dsaone.recursion;

import java.util.Arrays;
import java.util.Scanner;

public class NumberOfPathsInGrid {
	
	static int path(int a,int b) {
		if(a==1 || b==1) return 1;
		return path(a,b-1) + path(a-1,b);
	}
	
	static int findPathsDP(int a,int b) {
		int [][] dp = new int[a][b];
		for(int i=0;i<a;i++) {
			Arrays.fill(dp[i], -1);
		}
		return count(a-1,b-1,dp);
	}

	static int count(int a,int b,int[][] dp) {
		if(a==0 || b==0) return 1;
		int c;
		if(dp[a][b]==-1) {
			c = count(a-1,b,dp) + count(a,b-1,dp);
			dp[a][b]=c;
		}else {
			c = dp[a][b];
		}
		return c;
	}
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Rows and Columns value:");

		int m = sc.nextInt();
		int n = sc.nextInt();

		System.out.println("Number of paths in matrix using:");

		System.out.println("1. simple dsaone.recursion :");
		System.out.println(path(m,n));
		System.out.println("2. Dynamic programming :");
		System.out.println(findPathsDP(m,n));
	}
}
