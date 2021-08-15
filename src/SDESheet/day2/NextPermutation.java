package SDESheet.day2;

public class NextPermutation {

    // find first 'i' starting from end such that arr[i] < arr[i+1] -> mark as index1
    // then find first index starting from end such that arr[j] > arr[index1] -> mark as index2
    // swap index1 & index2
    // reverse all from that index1 + 1 -> end

    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,5,6,8,7};
        nextPermutation(arr);

        for (int x: arr){
            System.out.print(x + " ");
        }
    }

    static void nextPermutation(int[] arr){
        int n = arr.length;
        int index1 = -1;
        int index2 = -1;

        for(int i = n-2; i>=0;i--){
            if(arr[i] < arr[i+1]){
                index1 = i;
                break;
            }
        }

        if(index1 != -1){
            for(int i = n-1;i>=0;i--) {
                if(arr[i] > arr[index1]){
                    index2 = i;
                    break;
                }
            }
        }

        if(index1 != -1 && index2 != -1){
            //swap
            int t = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = t;
        }

        reverse(arr, index1 + 1);
    }

    static void reverse(int[] arr, int i){
        int x = i;
        int y = arr.length-1;
        while (x < y){
            int temp = arr[x];
            arr[x] = arr[y];
            arr[y] = temp;
            x++;
            y--;
        }
    }
}
