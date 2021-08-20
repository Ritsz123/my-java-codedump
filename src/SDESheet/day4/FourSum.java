package SDESheet.day4;

import java.util.ArrayList;
import java.util.Arrays;

// Given an array nums of n integers, return an array of all the unique quadruplets such that its sum = target
//! use O(N ^ 4) brute solution.
//! use O(N * N * N) sol. for last N use 2 ptr approach remember to sort the array first.
public class FourSum {
    public static void main(String[] args) {
        int[] arr = {1,0,-1,0,-2,2};
        int target = 0;

        ArrayList<ArrayList<Integer>> ans = findFourSum(arr, target);

        for (ArrayList<Integer> a : ans){
            for (int x: a){
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    static ArrayList<ArrayList<Integer>> findFourSum(int[] arr, int target){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int n = arr.length;
        Arrays.sort(arr);

        for (int i = 0;i<n;i++){
            for (int j = i+1;j<n;j++){
                int x = j+1;
                int y = n-1;
                int sum = arr[i] + arr[j];
                while (x < y){
                    int s = arr[x] + arr[y];
                    if (sum + s == target){
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(arr[i]);
                        temp.add(arr[j]);
                        temp.add(arr[x]);
                        temp.add(arr[y]);
                        ans.add(new ArrayList<>(temp));

                        //to skip the repetation of 3rd num
                        while(temp.get(2) == arr[x]){
                            x++;
                        }

                        //to skip repetation of 4th num
                        while (temp.get(3) == arr[y]) {
                            y--;
                        }
                    }else
                    if(sum + s < target){
                        x++;
                    }else {
                        y--;
                    }
                }
                if (j + 1 < n && arr[j] == arr[j+1]){
                    j++;
                }
            }
            if(i+1 < n && arr[i] == arr[i+1]){
                i++;
            }
        }
        return ans;
    }
}
