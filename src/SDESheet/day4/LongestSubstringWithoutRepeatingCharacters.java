package SDESheet.day4;

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        String str = "dvdf";
        System.out.println(lengthOfLongestSubstring(str));
    }

    static int lengthOfLongestSubstring(String s){
        char[] arr = s.toCharArray();
        if(arr.length < 1) return 0;
        int left = 0;
        int right = 0;
        int max = 1;
        HashSet<Character> hs = new HashSet<>();
        while(right < arr.length){
            if (hs.contains(arr[right])){
                hs.remove(arr[left]);
                left++;
            }else{
                hs.add(arr[right]);
                right++;
            }
            max = Math.max(max, hs.size());
        }
        return max;
    }
}
