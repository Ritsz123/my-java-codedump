package SDESheet.day3;

//! elements having count greater than n/3
//* find counts manually
//* use hashmap to find count
//* use moore's voting algo.
    // approach observe that if majority element is present then there can be at max 2 diff majority elements
    // because suppose total elements = 8 then 8/3 = 2 so we need one element with freq atleast (2 + 1) hence there can only be 2 such elements
    // because 3 + 3 = 6 and the other can be freq 2
    //* then confirm the answer by counting them

public class MajorityElement2 {
    public static void main(String[] args) {
        int[] arr = {3,2,3,3,2,1,2};
        int ans = findMajorityElement(arr);
        System.out.println(ans);
    }

    static int findMajorityElement(int[] arr) {
        int e1 = -1;
        int e2 = -1;
        int c1 = 0;
        int c2 = 0;

        for (int i = 0;i<arr.length;i++){
            int curr = arr[i];
            if(e1 == curr) c1++;
            else if (e2 == curr) c2++;
            else if(e1 == -1){
                e1 = curr;
                c1 = 1;
            }else if(e2 == -1){
                e2 = curr;
                c2 = 1;
            }else{
                c1--;
                c2--;
            }
        }
        //at this point we have 2 possibilities of majority element
        // we then confirm them by counting

        c1 = 0;
        c2 = 0;
        for(int i = 0;i<arr.length;i++){
            if(arr[i] == e1){
                c1++;
            }else if(arr[i] == e2){
                c2++;
            }
        }

        if(c1 > arr.length/3) return e1;
        else if(c2 > arr.length/3) return e2;
        return -1;
    }
}
