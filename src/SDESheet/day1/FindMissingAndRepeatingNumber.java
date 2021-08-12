package SDESheet.day1;

public class FindMissingAndRepeatingNumber {
    public static void main(String[] args) {
        int[] arr = {1, 3, 3};
        int[] ans = findMissingAndRepeating(arr);
        for(int x: ans) System.out.print(x + " ");
    }

    static int[] findMissingAndRepeating(int [] arr) {
        int n = arr.length;
        int[] count = new int[n + 1];
        for (int i = 0;i<n;i++){
            count[arr[i]]++;
        }
        int repeating = -1;
        int missing = -1;
        for(int i = 1; i <= n;i++){
            if(count[i] > 1){
                repeating = i;
            }else if(count[i] == 0){
                missing = i;
            }
        }
        return new int[] {repeating, missing};
    }
}
