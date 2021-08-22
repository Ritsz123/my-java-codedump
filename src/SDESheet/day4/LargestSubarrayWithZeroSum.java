package SDESheet.day4;

//! The approach is to keep prefix sum and its index at each point.
//! if the prefix sum is 0 then ans is max (ans, i - map[presum])
//! else add it to map with its index

import java.util.HashMap;

public class LargestSubarrayWithZeroSum {
    public static void main(String[] args) {
        int[] arr = {0,0,-2,2,-8,1,7,10,23};
        System.out.println(largestLengthOfSubarrayOfLengthZero(arr));
    }

    private static int largestLengthOfSubarrayOfLengthZero(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        int sum = 0;
        for (int i = 0;i<arr.length;i++){
            sum += arr[i];
            //* to handle the case when input will be [0 0 0]
            if (sum == 0) {
                ans = i+1;
            }
            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            }else{
                map.put(sum , i);
            }
        }
        return ans;
    }
}
