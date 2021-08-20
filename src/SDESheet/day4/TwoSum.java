package SDESheet.day4;

import java.util.HashMap;

//! brute : find 2 indices such that they sum up to target O(N^2)
public class TwoSum {
    public static void main(String[] args) {
        int[] arr = {3,2,4,5,8};
        int target = 6;

        int[] ans = findTwoNumbersOfSum(arr, target);
        System.out.println(ans[0] + " " + ans[1]);
    }

    static int[] findTwoNumbersOfSum(int[] arr, int target){
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0;i<arr.length;i++){
            if(!map.isEmpty() && map.containsKey(target - arr[i])){
                return new int[] {map.get(target - arr[i]), i};
            }else{
                map.put(arr[i], i);
            }
        }

        return new int[] {-1,-1};
    }
}
