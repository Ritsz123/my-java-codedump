package gfgSelfPaced;


import java.util.HashMap;
import java.util.HashSet;

public class HashingPractice {

	public static void main(String[] args) {
//		int [] arr1 = { 20,15,20,15,30,30,5 };
//		int [] arr2 = { 30,5,30,8 };
		
//		System.out.println(countNumberOfUniqueIntersectionsInTheArrays(arr1, arr2));
		
//		System.out.println(countOfUniqueElementsInUnionOfTwoArrays(arr1, arr2));
		
//		int [] arr = { 11,5,6 };
//		System.out.println(pairWithGivenSumInTheArray(arr, 10));
		
//		int [] arr = { 1,4,13,-3,-10,5 };
//		System.out.println(subArrayWithSumZero(arr));
		
//		int [] arr = { 5,8,6,13,3,-1 };
//		System.out.println(subArrayWithGivenSum(arr, 19));
		
//		int [] arr = { 8,3,1,5,-6,6,2,2 }; 
//		System.out.println(longestSubarrayWithGivenSum(arr, 4));
		
//		int [] arr = { 1,0,1,1,1,0,0 };
//		System.out.println(longestSubArrayWithEqualZerosAndOnes(arr));
		
//		int [] a = { 0,1,0,0,0,0 };
//		int [] b = { 1,0,1,0,0,1 };
//		System.out.println(longestCommonSpanWithSameSumInTwoBinaryArrays(a, b));
		
//		int [] arr = { 5,4,3,2, 10,11,12,13,14,15 };
//		System.out.println(longestConsecutiveSubSequence(arr));
		
//		int[] arr = { 10,20,10,10,30,40 };
//		countDistictElementInEachWindowOfSizeK(arr, 4);

		int[] arr = {10,2,-2,-20,10};
		System.out.println(countOfSubArraysWithGivenSum(arr,-10));
	}
	
	static void countDistictElementInEachWindowOfSizeK(int [] arr,int k){
		
		HashMap<Integer,Integer> map = new HashMap<>();
		
		for(int i=0;i<k;i++) {
			if(map.containsKey(arr[i])) {
				map.put(arr[i],map.get(arr[i])+1);
			}else {
				map.put(arr[i], 1);
			}
		}
		
		System.out.print(map.size() + " ");
		
		for(int i=k;i<arr.length;i++) {	
			if(map.get(arr[i-k])==1) {
				map.remove(arr[i-k]);
			}else {
				map.put(arr[i-k], map.get(arr[i-k])-1);
			}
			
			if(map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i])+1);
			}else {
				map.put(arr[i], 1);
			}
			System.out.print(map.size()+" ");
		}
	}

	static int longestConsecutiveSubSequence(int [] arr) {
//		Naive approach
		
//		Arrays.sort(arr);
//		int maxCount = 1;
//		int count=1;
//		for(int i=1;i<arr.length;i++) {
//			if(arr[i]==arr[i-1]+1) {
//				count++;
//			}else {
//				maxCount = Math.max(maxCount, count);
//				count=1;
//			}
//		}
//		maxCount = Math.max(maxCount, count);
//		return maxCount;
		
//		Efficient
		HashSet<Integer> hs = new HashSet<>();
		for(int i=0;i<arr.length;i++) {
			hs.add(arr[i]);
		}
		int ans = 1;
		
		for(int i=0;i<arr.length;i++) {
			int num = arr[i];
			int count = 1;
			if(!hs.contains(num-1)) {
				while(hs.contains(num+1)) {
					count++;
					num++;
				}
				ans = Math.max(ans, count);
			}
		}
		return ans;
	}

	static int longestCommonSpanWithSameSumInTwoBinaryArrays(int [] arr1,int[] arr2) {
//		Naive approach
		
//		int count=0;
//		for(int i=0;i<arr1.length;i++) {
//			int sum1=0,sum2=0;
//			for(int j=i;j<arr1.length;j++) {
//				sum1+=arr1[j];
//				sum2+=arr2[j];
//				if(sum1==sum2) {
//					count = Math.max(count, j-i+1);
//				}
//			}
//		}
//		return count;
		
//		Efficient solution
		
		
		int [] arr = new int[arr1.length];
		for(int i=0;i<arr1.length;i++) {
			arr[i]=arr1[i]-arr2[i];
		}
		
		HashMap<Integer,Integer> map = new HashMap<>();
		int presum=0;
		int count=0;
		for(int i=0;i<arr.length;i++) {
			presum+=arr[i];
			if(!map.containsKey(presum)) {
				map.put(presum, i);
			}
			else {
				count = Math.max(count, i-map.get(presum));
			}
		}
		return count;
	}

	static int countOfSubArraysWithEqualNumberOfZerosAnsOnes(int[] arr){
		// consider 0 as -1 and solve according to zero sum subarray problem
		for (int i=0;i<arr.length;i++){
			if (arr[i] == 0) arr[i] = -1;
		}

		HashMap<Integer,Integer> hm = new HashMap<>();
		int ps = 0;
		int count = 0;
		for (int i=0;i<arr.length;i++){
			ps+=arr[i];
			if (ps == 0) count++;
			if (hm.containsKey(ps)){
				count += hm.get(ps);
			}
			hm.put(ps,hm.getOrDefault(ps,0) + 1);
		}
		return count;
	}
	
	static int longestSubarrayWithGivenSum(int [] arr,int sum) {
//		
//		Naive approach;
		
		
//		int count=0;
//		for(int i=0;i<arr.length;i++) {
//			int temp=0;
//			for(int j=i;j<arr.length;j++) {
//				temp+=arr[j];
//				if(temp==sum) {
//					count = Math.max(count, j-i+1);
//				}
//			}
//		}
//		return count;
		
//		optimised
		HashMap<Integer,Integer> map = new HashMap<>();
		int count=0;
		int preSum=0;
		for(int i=0;i<arr.length;i++) {
			preSum+=arr[i];
			
			if(preSum==sum) {
				count = i+1;
			}
			if(!map.containsKey(preSum)) {
				map.put(preSum, i);
			}
			if(map.containsKey(preSum-sum)) {
				count = Math.max(count, i-map.get(preSum-sum));
			}
		}
		return count;
	}

	static int countOfSubArraysWithGivenSum(int[] arr,int sum){
		int count = 0;
		HashMap<Integer,Integer> hm= new HashMap<>();
		int ps = 0;
		for (int i=0;i<arr.length;i++){
			ps+=arr[i];
			if (ps==sum) count++;
			if (hm.containsKey(ps-sum)){
				count+=hm.get(ps-sum);
			}
			hm.put(ps,hm.getOrDefault(ps,0) + 1);
		}
		return count;
	}
	
	static boolean subArrayWithGivenSum(int [] arr, int k) {
		int preSum=0;
		HashSet<Integer> hs = new HashSet<>();
		for(int i=0;i<arr.length;i++) {
			preSum+=arr[i];
			if(preSum==k) return true;
			
			if(hs.contains(preSum-k)) {
				return true;
			}else {
				hs.add(preSum);
			}
		}
		return false;
	}

	static boolean subArrayWithSumZero(int [] arr) {
		HashSet<Integer> hs = new HashSet<>();
		int pre_sum=0;
		for(int i=0;i<arr.length;i++) {
			pre_sum+=arr[i];
			if(hs.contains(pre_sum)) {
				return true;
			} 
			if(pre_sum==0) return true;
			hs.add(pre_sum);
		}
		return false;
	}
	
	
	static boolean pairWithGivenSumInTheArray(int [] arr,int sum) {
		HashSet<Integer> hs = new HashSet<>();
		 
		for(int i=0;i<arr.length;i++) {
			int t = sum-arr[i];
			if(!hs.isEmpty() && hs.contains(t)) {
				return true;
			}else {
				hs.add(arr[i]);
			}
		}
		return false;
	}
	
	static int countOfUniqueElementsInUnionOfTwoArrays(int [] arr1,int [] arr2) {
		HashSet<Integer> hs = new HashSet<>();
		for(int i=0;i<arr1.length;i++) {
			hs.add(arr1[i]);
		}
		for(int i=0;i<arr2.length;i++) {
			hs.add(arr2[i]);
		}
		return hs.size();
	}
	
	static int countNumberOfUniqueIntersectionsInTheArrays(int [] arr1,int [] arr2) {
		HashSet<Integer> set = new HashSet<>();
		for(int i=0;i<arr1.length;i++) {
			set.add(arr1[i]);
		}
		int count=0;
		for(int i=0;i<arr2.length;i++) {
			if(set.contains(arr2[i])) {
				count++;
				set.remove(arr2[i]);
			}
		}
		return count;
	}
}
