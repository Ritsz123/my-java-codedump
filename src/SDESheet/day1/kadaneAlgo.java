package SDESheet.day1;

public class kadaneAlgo {
    public static void main(String[] args) {
        int[] arr = {-1,-2,-3,-4};
        kadane(arr);
    }

    private static void kadane(int[] arr) {
        int max = arr[0];
        int sum = 0;
        for (int i = 0;i<arr.length;i++){
            sum += arr[i];
            max = Math.max(sum, max);
            if (sum < 0) sum = 0;
        }
        System.out.println(max);
    }
}
