package gfgSelfPaced;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SortingTechniques {

	public static void main(String[] args) {
		int [] arr =  { 4,3,2,10,12,1,5,6 };
		
//		insertionsort(arr);
//		System.out.println("Insertion sort:");
		
//		bubbleSort(arr);
//		System.out.println("Bubble Sort");
		
//		selectionSort(arr);
//		System.out.println("Selection Sort");
		
//		quickSort(arr,0,arr.length-1);
//		System.out.println("Quick Sort");
		
//		mergeSort(arr, 0, arr.length-1);
//		System.out.println("merge Sort");
		
		countingSort(arr);
		System.out.println("Counting Sort");
		
		for(int x:arr) {
			System.out.print(x + " ");
		}
	}

	static void countingSort(int [] arr) {
		HashMap<Integer,Integer> map = new HashMap<>();
		for(int i=0;i<arr.length;i++) {
			if(!map.isEmpty() && map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i]+1));
			}else {
				map.put(arr[i], 1);
			}
		}
		int k=0;
		for(Map.Entry<Integer,Integer> e:map.entrySet()) {
			int time = e.getValue();
			int num = e.getKey();
			for(int i=0 ;i<time;i++) {
				arr[k++]=num;
			}
		}
		
	}
	
	static void mergeSort(int [] arr,int l,int r) {
		if(l < r) {
			int mid = (l+r)/2;
			mergeSort(arr, l, mid);
			mergeSort(arr, mid+1, r);
			merge(arr,l,mid,r);
		}
	}
	static void merge(int [] arr,int l,int mid,int r) {
		int [] left = Arrays.copyOfRange(arr, l, mid+1);
		int [] right = Arrays.copyOfRange(arr, mid+1, r+1);
		int i=0,j=0,k=l;
		while(i<left.length && j<right.length) {
			if(left[i]<=right[j]) {
				arr[k++] = left[i++];
			}else {
				arr[k++] = right[j++];
			}
		}
		while(i<left.length) {
			arr[k++]=left[i++];
		}
		while(j<right.length) {
			arr[k++]=right[j++];
		}
	}
	
	
	static void quickSort(int [] arr,int l,int r) {
		if(l<=r) {
			int p = partition(arr,l,r);
			quickSort(arr, l, p-1);
			quickSort(arr, p+1, r);
		}
	}
	
	static int partition(int [] arr,int l,int r) {
		int pivot = arr[r];
		int i=l-1;
		for(int j=l;j<r;j++) {
			if(arr[j]<pivot) {
				//increment i & swap arr[i], arr[j]
				i++;
				int t = arr[j];
				arr[j]=arr[i];
				arr[i]=t;
			}
		}
//		swap pivot with i++
		i++;
		int t = arr[i];
		arr[i]=pivot;
		arr[r]=t;
		return i;
	}
	
	static void selectionSort(int [] arr) {
		int n = arr.length;
		for(int i=0;i<n;i++) {
			int min=i;
			for(int j=i+1;j<n;j++) {
				if(arr[j]<=arr[min]) min=j;
				
				//swap arr[min] & arr[i]
				int t = arr[min];
				arr[min] = arr[i];
				arr[i] = t;
			}
		}
	}
	
	static void bubbleSort(int [] arr) {
		int n = arr.length;
		for(int i=0;i<n-1;i++) {
			for(int j=0;j<n-1;j++) {
				if(arr[j]>arr[j+1]) {
//					swap
					int t = arr[j];
					arr[j]=arr[j+1];
					arr[j+1]=t;
				}
			}
		}
	}
	
	static void insertionsort(int [] arr) {
		int n = arr.length;
		for(int i=1;i<n;i++) {
			int key = arr[i];
			int j = i-1;
			while(j >= 0 && arr[j]>key) {
				arr[j+1]=arr[j];
				j--;
			}
			arr[j+1]=key;
		}
	}
}
