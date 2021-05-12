package gfgSelfPaced;

import java.util.Arrays;
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

//		String str = "dvdf";
//		System.out.println(longestSubstringWithDistinctCharacters(str));

//		System.out.println(checkIfTheStringIsSubSequenceOfOther("GEEKSFORGEEKS","GRGES"));

//		System.out.println(leftMostRepeatingElement("maax"));

//		System.out.println(leftMostNonRepeatingElement("maaxm"));

//		patternMatchingNaive("aaaaaaaaa","aaaa");
//		patternMatchingNaiveOptimised("ritesh","sh");

//		rabinKarpAlgorithmForPatternMatching("ritesh","sh");

//		kmpPatternMatching("ababcababaad","ababa");

		System.out.println(smallestWindowOfStringContainingAllCharactersOfOther("badeaebcaae","aabc"));
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

	static String smallestWindowOfStringContainingAllCharactersOfOther(String s1,String s2){
		int n = s1.length();
		int m = s2.length();

		HashMap<Character,Integer> map = new HashMap<>();
		int start = 0;
		int count = 0;

		int minLength = Integer.MAX_VALUE;
		int minStart = 0;

		for (int i = 0;i<m;i++){
			map.put(s2.charAt(i),map.getOrDefault(s2.charAt(i),0)+1);
		}

		for (int end = 0; end<n; end++){
			char currentChar = s1.charAt(end);

			if (map.containsKey(currentChar)){
				map.put(currentChar,map.get(currentChar) - 1);
				if (map.get(currentChar) >= 0){
					count++;
				}
			}

			while (count == m) {
				if (end - start + 1 < minLength){
					minLength = end-start+1;
					minStart = start;
				}

				char charAtStart = s1.charAt(start);
				start++;

				if (map.containsKey(charAtStart)){
					if(map.get(charAtStart) == 0){
						count--;
					}
					map.put(charAtStart, map.get(charAtStart) +1 );
				}
			}
		}

		return s1.substring(minStart,minStart + minLength);
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

	//anagram means every character from s2 should be present in s1 with same frequency
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

	static boolean checkIfTheStringIsSubSequenceOfOther(String s1,String s2){
		int i = 0,j=0;
		while (i<s1.length()){
			if (s1.charAt(i) == s2.charAt(j)){
				j++;
			}
			i++;
		}
		if (j == s2.length()) return true;
		return false;
	}

	static int leftMostRepeatingElement(String str){
		int[] count = new int[257];
		int ans = -1;
		for (int i = str.length()-1;i>=0;i--){
			count[str.charAt(i)]++;
			if (count[str.charAt(i)]>1) ans = i;
		}
		return ans;
	}

	static int leftMostNonRepeatingElement(String s){
		int[] count = new int[257];
		Arrays.fill(count, -1);
		for (int i = 0;i<s.length();i++){
			if (count[s.charAt(i)] == -1){
				count[s.charAt(i)] = i;
			}else{
				count[s.charAt(i)] = -2;
			}
		}

		int ans = Integer.MAX_VALUE;
		for (int i = 0;i<count.length;i++){
			if (count[i] >= 0)
			ans = Math.min(ans,count[i]);
		}
		return ans;
	}

	static void patternMatchingNaive(String str,String pat){
		for (int i = 0;i <= str.length() - pat.length();i++){
			boolean flag = true;
			for (int j = 0;j<pat.length();j++){
				if (str.charAt(i + j) != pat.charAt(j)){
					flag = false;
					break;
				}
			}
			if (flag) System.out.print(i + " ");
		}
	}

//	only works if the characters are non repeating
	static void patternMatchingNaiveOptimised(String str, String pat){
		int n = str.length();
		int m = pat.length();

		for (int i = 0;i <= n-m;){
			int j = 0;
			for (j=0;j<m;j++){
				if (pat.charAt(j) != str.charAt(i +j)){
					break;
				}
			}
			if (j==m) System.out.print(i + " ");
			if (j==0){
				i++;
			}else{
				i = i+j;
			}
		}
	}

	static void rabinKarpAlgorithmForPatternMatching(String str,String pat){
		int n = str.length();
		int m = pat.length();

		int patternHash = 0;
		int currHash = 0;

		for(int i = 0;i<m;i++){
			patternHash += pat.charAt(i);
			currHash += str.charAt(i);
		}

		for (int i = m;i<n;i++){
//			System.out.println("pattern " + patternHash + " curr " + currHash);
			if (patternHash == currHash){
				boolean match = true;
				for (int j = 0;j < m;j++){
					if (pat.charAt(j) != str.charAt(i-m + j)){
						match = false;
						break;
					}
				}
				if (match) System.out.print(i-m + " ");
			}
			currHash = currHash - str.charAt(i-m) + str.charAt(i);
		}
		if (currHash == patternHash) System.out.println(n-m);
	}

	static void fillLps(String pat,int[] lps){
		int len = 0;
		lps[0] = 0;
		int i = 1;
		while(i < lps.length){
			if(pat.charAt(i) == pat.charAt(len)){
				lps[i] = len+1;
				len++;
				i++;
			} else {
				if (len == 0){
					lps[i] = 0;
					i++;
				} else {
					len = lps[len-1];
				}
			}
		}
//		for (int x:lps){
//			System.out.print(x + " ");
//		}
	}

	static void kmpPatternMatching(String str,String pat){
		int n = str.length();
		int m = pat.length();
		int[] lps = new int[m];
		fillLps(pat,lps);

		int i = 0, j=0;
		while(i < n){
			if (str.charAt(i) == pat.charAt(j)){
				i++;
				j++;
			}
			if (j == m){
				System.out.print(i-m + " ");
				j = lps[j-1];
			}else if(i < n && str.charAt(i) != pat.charAt(j)){
				if (j==0){
					i++;
				}else{
					j = lps[j-1];
				}
			}
		}
	}
}
