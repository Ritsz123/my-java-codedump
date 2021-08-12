package SDESheet.day1;

public class Sort012 {
    public static void main(String[] args) {
        int[] arr = {0, 2, 1, 2, 0};

        sort(arr);

        for(int x: arr){
            System.out.print(x + " ");
        }
    }

    private static void sort(int[] arr) {
        int start = 0;
        int mid = 0;
        int end = arr.length-1;

        while(mid <= end) {
            if(arr[mid] == 0){
                swap(arr, start, mid);
                start++;
                mid++;
            }else if(arr[mid] == 1){
                mid++;
            }else {
                swap(arr, mid, end);
                end--;
            }
        }
    }

    static void swap(int[] arr, int x, int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
