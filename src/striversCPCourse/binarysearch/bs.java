package striversCPCourse.binarysearch;

public class bs {
/*
    given an array and k
    need to divide all elements of array by any number
    and find the smallest such number such that sum should be less than or equal to k
*/

    public static void main(String[] args) {
        int[] arr = { 1, 4, 5, 8 ,2, 11, 23, 13 };
        int k = 10;

        System.out.println(solve(arr, k));
    }

    static int solve(int[] arr, int k){
        int max = arr[0];
        for (int i = 1; i< arr.length;i++){
            max = Math.max(arr[i], max);
        }

        int lo = 1;
        int hi = max+1; // max + 1 to handle case k = 0

        int ans = -1;
        while (lo <= hi){
            int mid = (lo + hi)/2;
            int sum = 0;
            for (int i = 0;i <arr.length;i++){
                sum += arr[i] / mid;
            }
            if (sum <= k){
                ans = mid;
                hi = mid-1;

            }else{
                lo = mid+1;
            }
        }
        return ans;
    }
}