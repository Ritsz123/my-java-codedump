package SDESheet.day2;


// brute : O(m * n) space
//    create a seperate matrix traverse it now if i[i][j] is 0 in old matrix
//    mark the complete row and col as 0 in new matrix

// efficient O(m + n)
// for marking rows and cols use extra col[] and row[] now if a[i][j] ==0 then row[i] = 0 and col[j] = 0;
// during filling if row[i] || col[j] == 0 then mark a[i][j] = 0

public class SetMatrixZeros {

    //* if arr[i][j] == 0 then set the complete row i = 0 and complete column j = 0;
    public static void main(String[] args) {
        int[][] matrix = {
            { 0, 1, 2, 0 },
            { 3, 4, 5, 2 },
            { 1, 3, 1, 5 },
        };

        setMatrixZero(matrix);

        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix[0].length;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void setMatrixZero(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        boolean flag = false;

        for(int i = 0;i<n;i++) {
            //to resolve conflict of position a[0][0] we will use it only for col
            if(arr[i][0] == 0) {
                flag = true;
            }

            for(int j = 1;j<m;j++) {
                if(arr[i][j] == 0) {
                    arr[0][j] = 0;
                    arr[i][0] = 0;
                }
            }
        }

        for(int i = 1;i<n;i++){
            for(int j = 1;j<m;j++){
                if(arr[i][0] == 0 || arr[0][j] == 0){
                    arr[i][j] = 0;
                }
            }
        }

        // filling first row (depend upon (0,0))
        if(arr[0][0] == 0){
            for(int j = 0;j<m;j++){
                arr[0][j] = 0;
            }
        }

        //filling first col (depend on flag)
        if(flag){
            for(int i = 0;i<n;i++){
                arr[i][0] = 0;
            }
        }
    }
}
