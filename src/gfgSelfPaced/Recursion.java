package gfgSelfPaced;

import java.util.ArrayList;
import java.util.List;

public class Recursion {
    public static void main(String[] args) {

//        TOH(3,'A','B','C');

//        System.out.println(jos(5,3));


//        int[]  arr = {10,5,2,3,6};
//        int sum = 0;
//        subsetSum(arr,sum);

        permutationsOfTheString("ABC");
    }

    static void permutationsOfTheString(String str){
        permute(str.toCharArray(),0);
    }

    static void permute(char[] str,int index){
        if(index >= str.length){
            System.out.print(new String(str) + " ");
            return;
        }
        for (int i=index;i<str.length;i++){
            swap(str,index,i);
            permute(str,index+1);
            swap(str,index,i);
        }
    }
    static void swap(char[] arr,int i,int j){
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    static void subsetSum(int [] arr, int sum){
        List<List<Integer>> ans = new ArrayList<>();
        subsetSum(arr,0,sum,new ArrayList<>(),ans);

        for (List<Integer> l: ans) {
            for (Integer i: l) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println("count : " + ans.size());
    }

    static void subsetSum(int [] arr,int index,int sum,ArrayList<Integer> ds,List<List<Integer>> ans){
        if(sum==0){
            ans.add(new ArrayList<>(ds));
            return;
        }
        if(index >= arr.length){
            return;
        }

        subsetSum(arr,index+1,sum,ds,ans);

        if(sum-arr[index]>=0){
            ds.add(arr[index]);
            subsetSum(arr,index+1,sum-arr[index],ds,ans);
            ds.remove(ds.size()-1);
        }
    }

//    josephus problem
    static int jos(int n,int k){
        if(n==1) return 0;

        return (jos(n-1,k) + k) % n;
    }

//    tower of hannoi
    static void TOH(int n,char source, char med,char dest){
        if(n==1){
            System.out.println("move 1 from " + source + " to " + dest);
            return;
        }
        TOH(n-1,source,dest,med);
        System.out.println("move " + n +" from " + source + " to "+ dest );
        TOH(n-1,med,source,dest);
    }
}
