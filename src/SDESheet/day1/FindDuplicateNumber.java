package SDESheet.day1;

public class FindDuplicateNumber {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 2, 2};
        findDuplicate(arr);
    }

    static void findDuplicate(int[] arr){
        int slow = arr[0];
        int fast = arr[0];

        do{
            slow = arr[slow];
            fast = arr[arr[fast]];
        }while(slow != fast);

        fast = arr[0];
        while (fast != slow){
            slow = arr[slow];
            fast = arr[fast];
        }
        System.out.println(fast);
    }
}
