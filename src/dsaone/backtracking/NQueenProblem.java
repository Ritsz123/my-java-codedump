package dsaone.backtracking;

import java.util.ArrayList;

public class NQueenProblem {

    boolean isSafe(int [][] arr, int x,int y,int n){

        int row,col;
        //up
        for (row=0;row<x;row++){
            if (arr[row][y]==1){
                return false;
            }
        }

//        left diagonal
        for (row=x,col=y;row>=0 && col>=0;row--,col--){
            if (arr[row][col]==1){
                return false;
            }
        }

//        right diagonal
        for (row=x,col=y;row>=0&&col<n;row--,col++){
            if (arr[row][col]==1){
                return false;
            }
        }
        return true;
    }

    boolean nQueen(int [][] arr,int x,int n){
        if (x>=n){
            //base case
            for (int i=0;i<n;i++){
                for (int j=0;j<n;j++){
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
            return true;
        }

        for (int col=0;col<n;col++){
            if (isSafe(arr,x,col,n)){
                arr[x][col] = 1;

                //recursive call
                if(nQueen(arr,x+1,n)){
                    return true;
                }

                //backtracking
                arr[x][col]=0;
            }
        }
        return false;
    }

    void nQueenSolve(boolean [][] arr, int col, ArrayList<ArrayList<Integer>> ans){
        int n = arr.length;

        if (col == n){
            ArrayList<Integer> curr = new ArrayList<>();
            for (int i = 0;i<n;i++){
                for (int j = 0;j<n;j++){
                    if (arr[i][j]){
                        curr.add(j+1);
                        break;
                    }
                }
            }
            ans.add(new ArrayList<>(curr));
            return;
        }

        for (int row = 0; row<n; row++){

            if(isSafeHere(arr,row,col)){
                arr[row][col] = true;

                nQueenSolve(arr,col+1,ans);

                arr[row][col] = false;
            }
        }
    }

    private boolean isSafeHere(boolean[][] arr, int row, int col) {
        int tRow = row;
        int tCol = col;

        // left diagonal top
        while(row >= 0 && col >= 0){
            if (arr[row][col]){
                return false;
            }
            row--;
            col--;
        }

        row = tRow;
        col = tCol;
        // only left
        while(col >= 0){
            if (arr[row][col]){
                return false;
            }
            col--;
        }

        col = tCol;
        //left diagonal bottom
        while (row< arr.length && col >= 0){
            if (arr[row][col]){
                return false;
            }
            row++;
            col--;
        }

        return true;
    }
}

class Runner{
    public static void main(String[] args) {
        NQueenProblem nq = new NQueenProblem();
        int n = 4;
        boolean [][] arr = new boolean[n][n];
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        nq.nQueenSolve(arr,0,ans);

        for (int i =0;i<ans.size();i++){
            for (Integer x: ans.get(i)){
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }
}
