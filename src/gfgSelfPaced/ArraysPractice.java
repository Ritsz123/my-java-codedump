package gfgSelfPaced;

import java.util.*;
class ArraysPractice {
	// ***************         GFG SELFPACED ARRAY PRACTICE            ***************



	public static void main(String[] args) {
//		int [] arr = new int[] {6,8,4,8,8};
//		int[] arr = {787, 656, 543, 291, 27, 165, 659, 736, 232, 351, 237, 28, 75, 207, 274};
//		int [] arr = {0,0,1,1,0,0,1,1};
//		minGroupFlips(arr);
//		int[] arr = {1,2,1,3,4,3,3};
//		printNbonachiSeries(4, 10);
//		countDistinctElemenmtsInMWindow(arr, 4);
//		prefixSum();
		int[] arr = {10,10,10};
		int num = secondLargestElementInArray(arr);
		System.out.println(num);
	}
	
	
	static void prefixSum() {
		int [] arr = {2,8,3,9,6,5,4};
		//pre calculations
		int [] sums = new int[arr.length];
		sums[0]=arr[0];
		for(int i=1;i<sums.length;i++) {
			sums[i]=sums[i-1]+arr[i];
		}
		System.out.print(getSum(sums,0,2) + " ");
		System.out.print(getSum(sums,1,3) + " ");
		System.out.print(getSum(sums,2,6) + " ");
	}
	static int getSum(int [] prefixArray,int l,int r) {
		if(l!=0) {
			return prefixArray[r]-prefixArray[l-1];
		}
		return prefixArray[r];
	}
	
	
	
	static void countDistinctElemenmtsInMWindow(int [] arr,int m) {
		int [] count = new int[11];
		for(int i=m;i<=arr.length;i++) {
			int c=0;
			for(int j=i-m;j<i;j++) {
				count[arr[j]]++;
			}
			for(int j=0;j<count.length;j++) {
				if(count[j]!=0)c++;
			}
			Arrays.fill(count, 0);
			System.out.print(c + " ");
		}
	}
	
	
	static void printNbonachiSeries(int n,int m) {
		int [] arr = new int[m];
		arr[n-1] = 1;
		int sum=1;
		for(int i=n;i<m;i++) {
			arr[i]=sum;
			sum+=arr[i]-arr[i-n];
		}
		for(int x:arr) {
			System.out.print(x + " ");
		}
	}
	
	static boolean slidingWindowVariation(int [] arr,int k,int target) {
		int n = arr.length;
		int sum=0;
		for(int i=0;i<k;i++) {
			sum+=arr[i];
		}
		if(sum==target) return true;
		for(int i=k;i<n;i++) {
			sum += (arr[i]-arr[i-k]);
			if(sum==target) return true;
		}
		return false;
	}
	
	static int windowSlidingMaxSum(int[] arr,int k) {
		int n = arr.length;
		int sum=0;
		int prev=0;
		for(int i=0;i<k;i++) {
			sum+=arr[i];
		}
		int max = sum;
		for(int i=k;i<n;i++,prev++) {
			sum=sum-arr[prev];
			sum+=arr[i];
			max = Math.max(max, sum);
		}
		return max;
	}
	
	
	static void minGroupFlips(int []arr) {
//		we will always flip the element which is not equal to the first element i.e. arr[0]
//		even if the number of groups of 1's & 0's are same
		int n = arr.length;
		
		for(int i=1;i<n;i++) {
			if(arr[i]!=arr[i-1]) {
				if(arr[i]!=arr[0])
					System.out.print("from " + i);
				else System.out.println(" to " + (i-1));
			}
		}
		if(arr[n-1]!=arr[0]) System.out.println(" to " + (n-1));
	}
	
	
	static int majorityElement(int []arr) {
//		Moore's voting algorithm

		int n = arr.length;
		int count=0;
		int pos=0;
		for(int i=1;i<n;i++) {
			if(count==0) pos=i;
			if(arr[pos]==arr[i]) count++;
			else count--;
		}
		count=0;
		for(int i=0;i<n;i++) {
			if(arr[i]==arr[pos])count++;
		}
		if(count>n/2)return pos;
		else return -1;
	}
	
	static int maxSumCircularSubArray(int [] arr) {
//		The idea is to find maximum of normal subarray sum & maximum of circular subArray sum then return Max of both;
//		to find maximum of circular subarraysum we can use modified kadane algo to find minimum subarray sum & subtract this 
//		from the total sum of the array
		int n= arr.length;
		int sum=0;
		int max = arr[0];
//		kadane algo to find max sum
		for(int i=0;i<n;i++) {
			sum+=arr[i];
			if(sum>max)max = sum;
			if(sum<0)sum=0;
		}
		int min=arr[0];
		sum=0;
		int total=0;

//		modified kadane algo to find min sum
		for(int i=0;i<n;i++) {
			total+=arr[i];
			sum+=arr[i];
			if(sum<min) min = sum;
			if(sum>0)sum=0;
		}
		max = Math.max(max, total-min);
		return max;
	}
	
	
	
	static int maxLenEvenOddSubArray(int [] arr) {
		int n = arr.length;
		int count=1;
		int max=1;
		for(int i=1;i<n;i++) {
			if((arr[i-1]%2==1 && arr[i]%2==0) || (arr[i-1]%2==0 && arr[i]%2==1)) {
				count++;
			}else count=1;
			if(count>max)max=count;
		}
		return max;
	}
	
	
	static int maxSumSubArray(int[] arr) {
//		Kadane algorithm

		int n = arr.length;
		int max=arr[0];
		int sum=0;
		for(int i=0;i<n;i++) {
			sum+=arr[i];
			if(sum>max) max = sum;
			if(sum<0) sum=0;
		}
		return max;
	}
	
	
	
	static int consecutiveOnes(int [] arr) {
		int n = arr.length;
		int count=0;
		int max=0;
		for(int i=0;i<n;i++) {
			if(arr[i]==1) {
				count++;
			}else count=0;
			if(count>max)max=count;
		}
		return max;
	}
	
	
	static int maxWater(int []arr) {
		int n = arr.length;
		int water=0;
		int[] leftMax = new int[n];
		int[] rightMax = new int[n];
		leftMax[0]=arr[0];
		rightMax[n-1] = arr[n-1];
		for(int i=1;i<n;i++) {
			leftMax[i]=Math.max(leftMax[i-1], arr[i]);
		}
		for(int i=n-2;i>=0;i--) {
			rightMax[i]=Math.max(rightMax[i+1], arr[i]);
		}
		for(int i=1;i<n-1;i++) {
			water+= Math.min(leftMax[i], rightMax[i]) - arr[i];
		}
		return water;
	}
	
	
	static int maxProfit(int [] arr) {
		//stocks buy & sell
		int profit=0;
		for(int i=0;i<arr.length-1;i++) {
			if(arr[i+1]>arr[i]) {
				profit+= (arr[i+1]-arr[i]);
			}
		}
		return profit;
	}

	static void removeDuplicateFromSortedArray(int [] arr){
		int res = 1;
		for (int i=1;i<arr.length;i++){
			if (arr[i]!=arr[res]){
				arr[res] = arr[i];
				res++;
			}
		}
	}

	static int secondLargestElementInArray(int[] arr){
		int n = arr.length;
		int res = -1, largest = 0;

		for (int i=1;i<n;i++){
			if(arr[i]>arr[largest]){
				res = largest;
				largest = i;
			}else if(arr[i]!=arr[largest]){
				if (res == -1 || arr[i] > arr[res]){
					res = i;
				}
			}
		}
		return res != -1 ? arr[res] : -1;
	}

	static int binarySearch(int [] arr,int left,int right,int target) {
		if(left<=right) {
			int mid = (left + right)/2;
			if(arr[mid]==target) return mid;
			if(target<arr[mid]) {
				return binarySearch(arr,left,mid-1,target);
			}else {
				return binarySearch(arr,mid+1,right,target);
			}
		}
		return -1;
	}
}
