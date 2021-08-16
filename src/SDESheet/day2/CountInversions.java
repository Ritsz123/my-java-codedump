package SDESheet.day2;

public class CountInversions {
    //! find two elements a[i] and a[j] such that a[i] > a[j] and i < j.

    //* method 1 brute force. for every pair check for the condition
    //* Method 2 use merge sort with little change

    public static void main(String[] args) {
        int[] arr = { 2, 4, 1, 3, 5 };
        int[] temp = new int[arr.length];

        int count = mergeSort(arr, temp, 0, arr.length-1);
        System.out.println(count);
    }

    static int mergeSort(int[] arr, int[] temp, int lo, int hi) {
        int count = 0;
        if(lo < hi){
            int mid = (lo + hi)/2;

            count += mergeSort(arr, temp, lo, mid);
            count += mergeSort(arr, temp, mid+1, hi);

            count += merge(arr, temp, lo, mid+1, hi);
        }
        return count;
    }

    private static int merge(int[] arr, int[] temp, int lo, int mid, int hi) {
        int i = lo;
        int j = mid;
        int k = lo;
        int count = 0;

//        i is less than end of first part
        while (i <= mid-1 && j <= hi){
            if(arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];

                // the required condition element ie
                // i is less than j and arr[i] > arr[j]
                // if arr[j] is less than arr[i]
                // then arr[j] is less than all elements from i to mid hence add them
                count += (mid - i);
            }
        }

        while (i <= mid-1){
            temp[k++] = arr[i++];
        }

        while (j <= hi){
            temp[k++] = arr[j++];
        }

        for(int x = lo;x<=hi;x++){
            arr[x] = temp[x];
        }
        return count;
    }
}
