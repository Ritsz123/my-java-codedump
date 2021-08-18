package SDESheet.day2;

//! Rotate the matrix by 90 degree clockwise
//* ex. [[1,2], [3,4]] will become [[3,1][4,2]]
public class RotateMatrix {

    public static void main(String[] args) {
        int[][] mat = {
            { 5, 1, 9, 11 },
            { 2, 4, 8, 10 },
            { 13, 3, 6, 7 },
            { 15, 14, 12, 16 }
        };

        int n= mat.length;

        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

        rotate(mat, n);
        System.out.println("after rotate");

        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void rotate(int[][] mat, int n) {
        //! transpose

        for(int i = 0;i<n;i++){
            for(int j = 0;j<i;j++){
                swap(mat, i, j);
            }
        }

        //! reverse row ---->
        for(int i = 0;i<n;i++){
            int x = 0;
            int y = n-1;
            while(x < y){
                swap(mat, i, x, i, y);
                x++;
                y--;
            }

        }
    }

    private static void swap(int[][] mat, int i, int x, int i1, int y) {
        int t = mat[i][x];
        mat[i][x] = mat[i1][y];
        mat[i1][y] = t;
    }

    private static void swap(int[][] mat, int i, int j) {
        int t = mat[i][j];
        mat[i][j] = mat[j][i];
        mat[j][i] = t;
    }
}
