package dsaone.backtracking;

import java.util.ArrayList;

public class PartitionArraySumEqual {

	public static void main(String[] args) {
		int array[] = {2, 1, 2, 3, 4, 8};
		int sum=0;
		for(int i=0;i<array.length;i++) {
			sum+=array[i];
		}
		ArrayList<Integer> ans = new ArrayList<>();
		boolean isPossible = sum%2==0 && partition(array, 0, sum/2, ans);
		if(isPossible) {
			for (int integer : ans) {
				System.out.print(integer + " ");
			}
		}else System.out.println("Not Possible");
	}
	
	static boolean partition(int[] arr,int i, int sum, ArrayList<Integer> ans) {
		if(i>=arr.length || sum<0) return false; //remember i>=arr.length
		if(sum==0) return true;
		ans.add(arr[i]);
		boolean leftPossible = partition(arr,i+1,sum-arr[i],ans);
		if(leftPossible) return true;
//		this line makes dsaone.backtracking work
		ans.remove(ans.size()-1);
		return partition(arr, i+1, sum, ans);
	}
}
