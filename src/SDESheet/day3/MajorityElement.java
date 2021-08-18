package SDESheet.day3;

//! find the element that appears more than n/2 times in the array
//* brute method simply count the occurances of each element then the one having more than n/2 is ans
//* efficient use moore's voting algorithm to find majority

public class MajorityElement {
    public static void main(String[] args) {
        int[] arr = {2,2,1,1,1,2,2};

        int num = findMajorityElement(arr);
        System.out.println(num);
    }

    private static int findMajorityElement(int[] arr) {
        int count = 0;
        int elem = 0;
        for (int i = 0;i< arr.length;i++){
            if(count == 0) elem = arr[i];
            if (elem == arr[i]) count++;
            else count--;
        }
        return elem;
    }
}
