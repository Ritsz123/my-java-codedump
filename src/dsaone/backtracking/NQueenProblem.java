package dsaone.backtracking;

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
}

class Runner{
    public static void main(String[] args) {
        NQueenProblem nq = new NQueenProblem();
        int n = 4;
        int [][] arr = new int[n][n];
        boolean ans = nq.nQueen(arr,0,n);
        System.out.println(ans);
    }
}
