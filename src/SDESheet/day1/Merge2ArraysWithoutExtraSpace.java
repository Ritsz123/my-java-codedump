package SDESheet.day1;

import java.util.Arrays;

public class Merge2ArraysWithoutExtraSpace {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {0, 2, 6, 8, 9};

        merge(arr1, arr2);

        for (int x: arr1){
            System.out.print(x + " ");
        }
        System.out.println();
        for (int x: arr2){
            System.out.print(x + " ");
        }
    }

    private static void merge(int[] arr1, int[] arr2) {
        int i = arr1.length-1;
        int j = 0;

        while(i >= 0 && j < arr2.length){
            if (arr1[i] > arr2[j]){
                swap(arr1, arr2, i, j);
                i--;
            } else {
                j++;
            }
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }

    static void swap(int[] arr1, int[] arr2, int i, int j){
        int temp = arr1[i];
        arr1[i] = arr2[j];
        arr2[j] = temp;
    }
}
