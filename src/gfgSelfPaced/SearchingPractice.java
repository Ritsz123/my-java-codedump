package gfgSelfPaced;

import java.util.*;

public class SearchingPractice {

	public static void main(String[] args) {
//		int [] arr = { 5,5,5,5,10,15,15,20,20,20,20 };
//		int [] arr = { 0,0,0,1,1,1,1,1,1 };
//		System.out.println(countOccuranceOfOneinSortedBinaryArray(arr));
//		System.out.println(findSquareRootFloor(16));
//		int [] arr = { 10,20,40,60,5,8 };
//		System.out.println(findPeakElementInTheArray(arr,arr.length));
//		int [] arr = { 3,5,9,2,8,10,11 };
//		int [] ans= findPairInArrayWithSumXInUnsortedArray(arr, 17);
		
//		int[] arr = { 2,4,8,9,11,12,20,30 };
//		int[] ans = findPairInArrayWithSumXInSortedArray(arr, 23);
//		for(int x:ans) System.out.print( x + " ");
//		boolean ans = findIfTheArrayContainsTripletWithSumX(arr, 32);
//		System.out.println(ans);
//		int [] arr = { 8,7,6,8,6,6,6,6 };
//		System.out.println(findIndexOfMajorityElement(arr));

//		int[] bigArray = new int[100000];
//		for (int i = 0;i<bigArray.length;i++){
//			bigArray[i] = i * 10;
//		}
//
//		System.out.println(searchInInfiniteSizedSortedArray(bigArray,82520));
	}
	
	static int findIndexOfMajorityElement(int []arr) {
		int  n = arr.length;
		int index=-1;
		int count=0;
		for(int i=0;i<n;i++) {
			if(count==0) index = i;
			if(arr[i]==arr[index]) count++;
			else count--;
		}
		count=0;
		for(int i=0;i<n;i++) {
			if(arr[i]==arr[index]) count++;
		}
		return count>n/2? index:-1;
	}
	
	static boolean findIfTheArrayContainsTripletWithSumX(int [] arr,int x) {
//		(n * n) time && O(1) space approach (best for triplets)
		
		int n = arr.length;
		Arrays.sort(arr);
		for(int i=0;i<n;i++) {
			int temp = x-arr[i];
			if(checkPairWithSumXinArray(arr, temp,i+1,n)) {
				return true;
			}
		}
		return false;
	}

	static boolean checkPairWithSumXinArray(int [] arr,int x,int start,int end) {
		int i=start,j = end-1;
		while(i<j) {
			int sum = arr[i]+arr[j];
			if(sum==x) return true;
			else if(sum>x) j--;
			else i++;
		}
		return false;
	}
	
	static int[] findPairInArrayWithSumXInSortedArray(int []arr,int x) {
		
//		O(N) time & O(1) space
		
		int i=0,j=arr.length-1;
		while(i<j) {
			int sum = arr[i] + arr[j];
			if(sum==x) return new int[] { arr[i], arr[j] };
			else if(sum>x) {
				j--;
			}else {
				i++;
			}
		}
		return new int[] { -1, -1 };
	}

	static int[] findPairInArrayWithSumXInUnsortedArray(int[] arr,int x) {
		int n = arr.length;
//		O(N * N)
//		for(int i=0;i<n;i++) {
//			int temp = x-arr[i];
//			for(int j=i+1;j<n;j++) {
//				if(arr[j]==temp) return new int[] { arr[i],arr[j] };
//			}
//		}
//		return new int[] {-1,-1};
//		************************************
//		O(N) time  & O(N)space (for unsorted array)
		HashMap<Integer,Integer> map = new HashMap<>();
		for(int i=0;i<n;i++) {
			int temp = x-arr[i];
			if(!map.isEmpty() && map.containsKey(temp)) {
				return new int[] { arr[i], temp };
 			}else {
 				map.put(arr[i], i);
 			}
		}
		return new int[] { -1,-1 };
	}
	
	static int searchElementInSortedRotatedArray(int [] arr,int num) {
		int lo=0,hi=arr.length-1;
		while(lo<=hi) {
			int mid = (lo + hi)/2;
			if(arr[mid]==num) return mid;
			if(arr[lo]<arr[mid]) {
//				left arraySorted
				if(num>=arr[lo] && num < arr[mid]) {
					hi = mid-1;
				}else {
					lo = mid+1;
				}
			}else {
//				right sorted
				if(num>arr[mid] && num<=arr[hi]) {
					lo = mid+1;
				}else {
					hi = mid-1;
				}
			}
		}
		return -1;
	}
	
	static int findPeakElementInTheArray(int [] arr,int n) {
		
		
//	 ********	Naive approach..   ******* 
//		if(n==1) return 0;
//		if(arr[0]>arr[1]) return 1;
//		if(arr[n-1]>arr[n-2]) return n-1;
//		for(int i=1;i<n-1;i++) {
//			if(arr[i]>=arr[i-1] && arr[i]>=arr[i+1]) return i;
//		}
//		return -1;
		
		int lo = 0,hi=n-1;
		while(lo<=hi) {
			int mid = (lo+hi)/2;
			if((mid==0 || arr[mid]>arr[mid-1]) && (mid==n-1 || arr[mid]>arr[mid+1])) return mid;
			if(mid>0 && arr[mid-1]>arr[mid]){
				hi = mid-1;
			}else lo = mid+1;
		}
		return -1;
	}

	//solution should be log n
	static int searchInInfiniteSizedSortedArray(int[] arr,int num){
		if(arr[0] == num) return 0;
		int i = 1;
		while (arr[i] < num){
			i = i*2;
			if(arr[i] == num) return i;
		}
		return binarySearch(arr,i/2+1,i-1,num);
	}
	
	static int findSquareRootFloor(int num) {
//		Brute Approach  TC: O(sqrt(num))
//		
//		int i=1;
//		while(i * i <= num)
//			i++;
//		return i-1;
		
//		Optimal TC: (log num) 
		int lo = 1,hi=num,ans = 1;
		while(lo<=hi) {
			int mid = (lo + hi)/2;
			if(mid * mid>num) {
				hi = mid-1;
			}else {
				ans = mid;
				lo = mid+1;
			}
		}
		return ans;
	}
	
	static int countOccuranceOfOneinSortedBinaryArray(int [] arr) {
	
		int n = arr.length;
		int start = findFirstIndexOfNumber(arr, 1);
		if(start==-1) return 0;
		else return n-start;
	}

	// ********* SOLUTION SHOULD BE STRICTLY O(LOG N) IN WORST CASE ************* \\
	static int countOccurancesOfNumberInSortedArray(int []arr,int num) {
		int start = findFirstIndexOfNumber(arr, num);
		int end = findLastIndexOfNumber(arr, num);
		if(start==-1) return 0;
		else return end-start+1;
	}

	static int findLastIndexOfNumber(int [] arr,int num) {
		int lo=0;
		int hi = arr.length-1;
		while(lo<=hi) {
			int mid = (lo + hi)/2;
			if(arr[mid]==num) {
				if(mid==arr.length-1 || arr[mid]!=arr[mid+1]) {
					return mid;
				}else lo = mid+1;
			}else if(arr[mid]>num) {
				hi = mid-1;
			}else if(arr[mid]<num) {
				lo = mid+1;
			}
		}
		return -1;
	}
	
	static int findFirstIndexOfNumber(int [] arr,int num) {
		int lo = 0;
		int hi = arr.length-1;
		while(lo<=hi) {
			int mid = (lo+hi)/2;
			if(arr[mid]==num) {
				if(mid==0 || arr[mid]!=arr[mid-1]) return mid;
				else hi = mid-1;
			}else
			if(arr[mid]>num) {
				hi = mid-1;
			}else
			if(arr[mid]<num) {
				lo = mid+1;
			}
		}
		return -1;
	}

	static int binarySearch(int [] arr,int start,int end,int num) {
		if(start<=end) {
			int mid = (start+end)/2;
			if(arr[mid]==num) {
				if(mid==0 || arr[mid]!=arr[mid-1])
					return mid;
				else return binarySearch(arr, start, mid-1, num);
			}
			if(arr[mid]>num) {
				return binarySearch(arr,start,mid-1,num);
			}else return binarySearch(arr, mid+1, end, num);
		}
		return -1;
	}
}
