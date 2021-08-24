package SDESheet.day5;

public class FindMiddleOfLL {
    public static void main(String[] args) {

        int[] arr = {1,2,3,4,5,6,7};
        ListNode node = ListNode.createList(arr);

        System.out.println(findMiddleOfLL(node));
    }
    static int findMiddleOfLL(ListNode node){
        ListNode fast = node;
        ListNode slow = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.val;
    }
}
