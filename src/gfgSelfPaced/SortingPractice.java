package gfgSelfPaced;

import java.util.*;

class Intervals implements Comparable<Intervals>{
	int start, end;
	Intervals(int a,int b){
		start=a;
		end=b;
	}

	@Override
	public int compareTo(Intervals o1) {
		return this.start-o1.start;
	}
}


public class SortingPractice {

	public static void main(String[] args) {
		
//		int [] a = { 2,5,8,13,15 };
//		int [] b = { 1,3,8,15,18,20,25 };
//		intersectionOfTwoSortedArrays(a, b);
		
//		********
//		int [] a = { 3,8,8 };
//		int [] b = { 2,7,8,8,10,15 };
//		unionOfTwoSortedArray(a,b);
		
//		***********
		
//		int [] arr = { 8,4,2,1 };
//		System.out.println(countInversionsInTheArray(arr));
//		***************
		
//		int [] arr = { 10,4,5,8,11,6,26 };
//		System.out.println(findKthSmallestElementInArray(arr, arr.length, 5));
//		*************
		
//		int [] arr = { 7,3,2,4,9,12,56 };
//		int [] arr = { 3,4,1,9,56,7,9,12 };
//		int m = 5;
//		System.out.println(minimumDifferenceOfMinAndMaxOfMNumbers(arr, m));
		
//		**************
//		int [] arr = { 15,-2,-1,12,17,-3 };
//		sortArrayWithTwoTypesOfElements(arr);
//		for(int x:arr) {
//			System.out.print(x + " ");
//		}
		
//		**************
//		int [] arr = { 0,1,2,1,1,2,0 };
//		sortArrayWithThreeTypesOfElements(arr);
//		for(int x:arr) {
//			System.out.print(x + " ");
//		}
		
//		*************
		
//		int [] a = { 5, 3, 18, 2 };
//		int [] b = { 10,15, 30,7 };
//		int n = a.length;
//		Intervals[] arr = new Intervals[n];
//		for(int i=0;i<n;i++) {
//			arr[i] = new Intervals(a[i],b[i]);
//		}
//		mergeOverlappingIntervals(arr,n);
		
//		**************
		
		int [] arr = { 900,600,700 };
		int [] dept = { 1000,800,730 };
		System.out.println(meetMaximumGuestsInTheParty(arr, dept));
	}
	

	static void mergeWithoutExtraSpace(int[] a,int[] b){
		int n = a.length;
		int m = b.length;

		int i = n-1;
		int j = 0;

		while(i >= 0 && j < m){
			if(a[i] <= b[j]){
				i--;
			}else{
				//swap
				int temp = a[i];
				a[i] = b[j];
				b[j] = temp;
				j++;
			}
		}
		Arrays.sort(a);
		Arrays.sort(b);
	}

	static int meetMaximumGuestsInTheParty(int [] arr,int [] dept) {
		int n = arr.length;
//		Optimal O(n log n) 
		
		Arrays.sort(arr);
		Arrays.sort(dept);
		int count=0;
		int res=0;
		int i=0,j=0;
		while(i<n && j<n) {
			if(arr[i] <= dept[j]) {
				i++;
				count++;
			}else {
				j++;
				count--;
			}
			res = Math.max(res, count);
		}
		return res;
	}
	
	static void mergeOverlappingIntervals(Intervals[] arr,int n) {
//		O(n log n) TC
		//Increasing start time

		Arrays.sort(arr);
		int res=0;
		for(int i=1;i<n;i++) {
			if(arr[res].end>=arr[i].start) {
				//merge the interval
				arr[res].start = Math.min(arr[res].start, arr[i].start);
				arr[res].end = Math.max(arr[res].end,arr[i].end);
			}else {
				res++;
				arr[res]=arr[i];
			}
		}
		
		for(int i=0;i<=res;i++) {
			System.out.println(arr[i].start + " " + arr[i].end);
		}
	}
	
	static void sortArrayWithThreeTypesOfElements(int [] arr) {
//		Dutch flag algorithm
		int lo=0,mid=0,hi=arr.length-1;
		while(mid<=hi) {
			if(arr[mid]==0) {
				swap(arr, mid, lo);
				mid++;
				lo++;
			}else if(arr[mid]==1) {
				mid++;
			}else {
				swap(arr, mid, hi);
				hi--;
			}
		}
	}
	
//	array segregation in 1 traversal & constant space
	static void sortArrayWithTwoTypesOfElements(int [] arr) {
		int i=0,j=arr.length-1;
		while(i<j) {
			if(arr[i]%2==0) i++;

			else {
//				swap i & j
				swap(arr, i, j);
				j--;
			}
		}
//		System.out.println(c);
	}

//	chocolate distribution problem
	static int minimumDifferenceOfMinAndMaxOfMNumbers(int [] arr,int m) {
		if(m>arr.length) return -1;
		Arrays.sort(arr);
		int diff = Integer.MAX_VALUE;
		for(int i=0;(i+m-1)<arr.length;i++) {
			diff = Math.min(diff, arr[i+m-1] - arr[i]);
		}
		return diff;
	}

	static int findKthSmallestElementInArray(int [] arr,int n,int k) {
//		use lambardo partition technique (same as quick sort)
		int l = 0,r=n-1;
		while(l<=r) {
			int p = partition(arr,l,r);
			if(p==k-1) return arr[p];
			else if(p > k-1) {
				r=p-1;
			}else l=p+1;
		}
		return -1;
	}
	
	static int partition(int []arr,int l,int r) {
		int pivot = arr[r];
		int i=l-1;
		for(int j=l;j<r;j++) {
			if(arr[j]<pivot) {
				
				//swap i++ & j
				i++;
				swap(arr, i, j);
			}
		}
//		swap i++ & pivot and return position of pivot i.e i++'s
		i++;
		swap(arr,i,r);
		return i;
	}
	
	static int countInversionsInTheArray(int [] arr) {
//		a pair arr[i],arr[j] is said to be inversion of i<j and arr[i]>arr[j]
//		Naive approach

//		int count=0;
//		for(int i=0;i<arr.length;i++) {
//			for(int j=i+1;j<arr.length;j++) {
//				if(arr[i]>arr[j]) count++;
//			}
//		}
//		return count;
		return mergeSort(arr,0,arr.length-1);
	}
	static int mergeSort(int[] arr,int l,int r) {
		int count= 0;
		if(l<r) {
			int m = (l + r)/2;
			count += mergeSort(arr,l,m);
			count += mergeSort(arr,m+1,r);
			count += countAndMerge(arr,l,m,r);
		}
		return count;
	}
	static int countAndMerge(int[] arr,int l, int m,int r) {
		int count=0;
		int [] left = Arrays.copyOfRange(arr, l, m+1);
		int [] right = Arrays.copyOfRange(arr, m+1, r+1);
//		create to seperate array to store left and right part
		int i=0,j=0,k=l;
//		i & j are zero because we are using two different arrays
		while(i<left.length && j<right.length) {
			if(left[i]<=right[j]) {
				arr[k++] = left[i++];
//				no use of us. as we need to count only if left[i]>right[j];
			}else {
//				this is what we need...
//				now here as right[j] is smaller than left[i] then typically all the elements in left[] are greater than right[j] and we have to count them i.e. mid+1 - l+i
//				because in merge sort we have two sorted arrays only.
				
				count+= ( m+1 ) - ( l+i ); // MOST IMPORTANT
				arr[k++] = left[i++];
			}
		}
		while(i<left.length) {
			arr[k++]=left[i++];
		}
		while(j<right.length) {
			arr[k++]=left[j++];
		}
		return count;
	}
	
	static void unionOfTwoSortedArray(int []a ,int [] b) {
//		print unique elements only
		int i=0,j=0;
		int m = a.length;
		int n = b.length;
		while(i<m && j<n) {
			int p;
//			to avoid duplicate elements in same array
			if(i>0 && a[i]==a[i-1]) {
				i++;
				continue;
			}
			if(j>0 && b[j]==b[j-1]) {
				j++;
				continue;
			}
			if(a[i]<b[j]) {
				p=a[i];
				i++;
			}else if(b[j]<a[i]) {
				p=b[j];
				j++;
			}else {
				p=a[i];
				i++;j++;
			}
			System.out.print(p + " ");
		}
		while(i<m) {
			if(i==0 || a[i]!=a[i-1]) {
				System.out.print(a[i] + " ");
				i++;
			}
		}
		while(j<n) {
			if(j==0 || b[j]!=b[j-1]) {
				System.out.print(b[j] + " ");
				j++;
			}
		}
	}
	
	static void intersectionOfTwoSortedArrays(int [] a,int [] b) {
//		print unique elements only
		
		int i=0,j=0;
		while(i<a.length && j<b.length) {
			if(i>0 && a[i]==a[i-1]) {
				i++; continue;
			}
			if(a[i]<b[j]) {
				i++;
			}else if(a[i]>b[j]) {
				j++;
			}else {
				System.out.print(a[i] + " ");
				i++;j++;
			}
		}
		System.out.println();
	}
	
	static void swap(int [] arr,int i,int j) {
		int t = arr[i];
		arr[i]=arr[j];
		arr[j]=t;
	}
}
