package SDESheet.day2;

public class StockBuyAndSell {
    public static void main(String[] args) {
        int[] arr = {100,180,260,310,40,535,695};

        //! maximum profit only 1 buy n sell
        int max = stockBuyAndSell(arr);
        System.out.println(max);

        System.out.println(multipleBuyAndSell(arr));
    }

    private static int multipleBuyAndSell(int[] arr) {
        int profit = 0;
        for (int i = 1;i<arr.length;i++){
            if(arr[i] > arr[i-1]){
                profit += arr[i] - arr[i-1];
            }
        }
        return profit;
    }

    private static int stockBuyAndSell(int[] arr) {
        int n = arr.length;
        int[] right = new int[n];
        right[n-1] = arr[n-1];

        for (int i = n-2;i >= 0;i--){
            right[i] = Math.max(arr[i], right[i+1]);
        }

        int max = 0;
        for (int i = 0;i<n;i++){
            max = Math.max(max, right[i] - arr[i]);
        }

        return max;
    }
}
