package SDESheet.day3;

public class SearchIn2DMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
            {1,   3,  5,  7},
            {10, 11, 16, 20},
            {23, 30, 34, 50}
        };

        int num = 16;
        System.out.println(searchMatrix(matrix, num));
    }

    public static int searchMatrix(int[][] arr, int num) {
        int n = arr.length;
        int m = arr[0].length;

        for(int i = 0;i<n;i++){
            if(arr[i][0] <= num && arr[i][m-1] >= num){
                if(search(arr[i], num)) return 1;
            }
        }
        return 0;
    }

    private static boolean search(int[] arr, int num){
        int lo = 0;
        int hi = arr.length;

        while(lo <= hi){
            int mid = (lo + hi)/2;
            int curr = arr[mid];
            if(curr == num) return true;
            else if(curr < num){
                lo = mid+1;
            }else{
                hi = mid-1;
            }
        }
        return false;
    }
}
