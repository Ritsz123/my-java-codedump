package SDESheet.day4;

//! Naive solution is to sort the array then find the max length of consecutive sequences
//* Optimal : add all the elements in the hashset
//* now for every element check if that element - 1 exists in hashset if so then skip that element
//* else we found the smallest element of sequence therefore now while elem + i exists increment i & count find the max of these counts
//! basically the approach is to start from minimal number of sequence

//Note that every element must be unique

import java.util.HashSet;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] arr = {36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42};

        int len = longestSequenceLength(arr);
        System.out.println(len);
    }

    private static int longestSequenceLength(int[] arr) {
        HashSet<Integer> hs = new HashSet<>();
        for (int x: arr) {
            hs.add(x);
        }

        int max = 0;
        for (int x: arr) {
            if (!hs.contains(x-1)){
                int count = 1;
                while (hs.contains(x + count)){
                    count++;
                }
                max = Math.max(count, max);
            }
        }
        return max;

    }
}
