package gfgSelfPaced.backtracking;

import java.util.Scanner;

public class RatInAmaze {

    static boolean findPath(int[][] grid, int i, int j, int[][] ans) {
        if(i == grid.length -1 && j== grid.length-1){
            ans[i][j] = 1;
            return true;
        }

        if(isSafe(grid, i,j)){
            ans[i][j] = 1;
            if(findPath(grid, i+1, j, ans)) return true;
            else if(findPath(grid, i, j+1, ans)) return true;
            ans[i][j] = 0;
        }
        return false;
    }

    static boolean isSafe(int[][] grid, int i, int j){
        int n = grid.length;
        if(i < n && i >= 0 && j < n && j >= 0 && grid[i][j] != 0) return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];

        System.out.println("enter matrix..");
        for (int i = 0;i<n;i++){
            for (int j = 0;j<n;j++){
                arr[i][j] = sc.nextInt();
            }
        }

        int[][] ans = new int[n][n];

        boolean path = findPath(arr, 0,0, ans);
        if(!path){
            System.out.println("NO PATH FOUND");
            return;
        }
        for (int i = 0;i<n;i++){
            for (int j = 0;j <n;j++){
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }
}
