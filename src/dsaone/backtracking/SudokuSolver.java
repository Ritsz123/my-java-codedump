package dsaone.backtracking;

public class SudokuSolver {

    int[][] sudoku;
    int n;

    SudokuSolver(int[][] board){
        this.sudoku = board;
        this.n = board.length;
    }

    public boolean solve(){
//        System.out.println("solving...");
        int row=-1,col=-1;
        boolean filled = true;

        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if (sudoku[i][j]==0){
                    row = i;
                    col = j;
                    filled = false;
                    break;
                }
            }
            if (!filled){
                break;
            }
        }

        if(filled) return true;

        for (int num=1;num<=n;num++){
//            System.out.println("checking...");
            if (isSafe(row,col,num)){

                sudoku[row][col] = num;
                if (solve()){
                    return true;
                }else{
                    // backtracking
                    sudoku[row][col] = 0;
                }

            }
        }
        return false;
    }

    boolean isSafe(int row,int col,int number){
//        check in column
        for (int i=0;i<n;i++){
            if (sudoku[row][i]==number){
                return false;
            }
        }

//        check in row
        for (int i=0;i<n;i++){
            if (sudoku[i][col]==number){
                return false;
            }
        }

        return true;
    }

    void print(){
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                System.out.print(sudoku[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
                { 7,0,9,0,0,2,6,8,0 },
                { 0,0,2,0,5,0,7,0,4 },
                { 0,0,0,0,0,0,2,0,0 },
                { 1,9,0,0,0,7,0,6,0 },
                { 8,6,7,1,9,5,0,4,0 },
                { 5,0,4,0,0,0,0,9,0 },
                { 4,3,5,7,8,0,0,2,0 },
                { 0,0,6,4,0,0,0,0,1 },
                { 9,8,0,5,0,6,0,0,3 }
        };
        SudokuSolver solver = new SudokuSolver(board);
        long startTime = System.currentTimeMillis();
        solver.solve();
        long endTime = System.currentTimeMillis();
        solver.print();

        System.out.println();
        System.out.println("Code executed in " + (endTime-startTime) + " ms");
    }
}
