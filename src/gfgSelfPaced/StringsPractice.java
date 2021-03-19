package gfgSelfPaced;

import java.util.HashMap;
import java.util.HashSet;

public class StringsPractice {
	static int[] factdp = new int[256];

	public static void main(String[] args) {
		
//		String s = "abcabcabc";
//		String p = "abc";
//		System.out.println(countNumberOfPatternsInTheString(s, p));

//		String s1 = "BABA";
//		String s2 = "ABAB";
//
//		System.out.println(checkForRotation(s1,s2));

//		String s1 = "FROG";
//		String s2 = "RGOF";
//		System.out.println(isAnagram(s1,s2));

//		String s1 = "geeksfrg";
//		String s2 = "frog";
//		System.out.println(stringContainsAnagram(s1,s2));

//		String str = "STRING";
//		long start = System.currentTimeMillis();
//		System.out.println(lexicographicRankOfTheString(str,0,str.length()));
//		long end = System.currentTimeMillis();
//		System.out.println("code executed in " + (end-start) + " ms");

		String str = "dvdf";
		System.out.println(longestSubstringWithDistinctCharacters(str));
	}

	static int longestSubstringWithDistinctCharacters(String str){
		char[] arr = str.toCharArray();
		if(arr.length<1) return 0;
		HashSet<Character> hs = new HashSet<>();
		int max=0;
		int right=0;
		int left=0;
		int count=1;
		while(right<arr.length){
			if(hs.contains(arr[right])){
				hs.remove(arr[left]);
				left++;
			}else{
				hs.add(arr[right]);
				right++;
			}
			count = right-left;
			max = Math.max(max,count);
		}
		return max;
	}


	static int lexicographicRankOfTheString(String str,int index,int n){
		if (index==str.length()){
			return 1;
		}
		int count=0;
		for (int i=index+1;i<str.length();i++){
			if (str.charAt(i)<str.charAt(index)){
				count++;
			}
		}
//		System.out.println("count "+count);
		return count * fact(n-1) + lexicographicRankOfTheString(str,index+1,n-1);
	}

//	find factorial using dynamic programming
	static int fact(int n){
		if (n<=1) return 1;
		if (factdp[n]!=0) return factdp[n];

		factdp[n] = fact(n-1) * n;
		return factdp[n];
	}

	static boolean stringContainsAnagram(String s1,String s2){
		int[] CT = new int[256];
		int[] CP = new int[256];

		for (int i=0;i<s2.length();i++){
			CT[s1.charAt(i)]++;
			CP[s2.charAt(i)]++;
		}

		for (int i=s2.length();i<s1.length();i++){
			if (areBothArraysSame(CT,CP)) return true;

			CT[s1.charAt(i)]++;
			CT[s1.charAt(i-s2.length())]--;

		}
		return areBothArraysSame(CT,CP);
	}

	static boolean areBothArraysSame(int []a, int []b){
		if (a.length!=b.length) return false;
		for (int i=0;i<a.length;i++){
			if (a[i]!=b[i]) return false;
		}
		return true;
	}

	static boolean isAnagram(String s1,String s2){
		if (s1.length()!=s2.length()) return false;

		int [] count = new int[256];
		for (int i=0;i<s1.length();i++){
			count[s1.charAt(i)]++;
			count[s2.charAt(i)]--;
		}
		for (int i=0;i<count.length;i++){
			if (count[i]!=0){
				return false;
			}
		}
		return true;
	}

	static boolean checkForRotation(String s1,String s2){
		if(s1.length()!=s2.length()) return false;
		return (s1 + s1).contains(s2);
	}

	static int countNumberOfPatternsInTheString(String str,String pat) {	
//		int count=0;
//		for(int i=0;i<str.length() - pat.length() + 1;i++) {
//			for(int j=i,k=0;k<pat.length();j++,k++) {
//				if(str.charAt(j)!=pat.charAt(k)) {
//					break;
//				}else {
//					if(k == pat.length()-1) {
//						count++;
//						System.out.print(i + " ");
//					}
//				}
//			}
//		}
//		System.out.println();
//		return count;
		
		int count=0;
		int b = 0,c = pat.length();
		if(c==0) return 0;
		while(c<=str.length()) {
			if(str.substring(b,c).equals(pat)) {
				System.out.print(b + " ");
				count++;
			}
			b++;
			c++;
		}
		System.out.println();
		return count;
	}
}
