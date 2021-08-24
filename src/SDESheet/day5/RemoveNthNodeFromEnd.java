package SDESheet.day5;

public class RemoveNthNodeFromEnd {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        ListNode head = ListNode.createList(arr);
//        ListNode ans = removeNthNode(head, 3);
        ListNode ans = removeNthNodeOneTraversal(head, 8);
        ListNode.printList(ans);
    }

    //! two traversal
    private static ListNode removeNthNode(ListNode head, int n) {
        int len = 0;
        ListNode temp = head;
        while (temp.next != null){
            temp = temp.next;
            len++;
        }
        int rem = len - n;
        temp = head;
        while (rem-- > 0){
            temp= temp.next;
        }
        temp.next = temp.next.next;
        return head;
    }

    //! single traversal
    static ListNode removeNthNodeOneTraversal(ListNode node, int n){
        ListNode dummy = new ListNode(0);
        dummy.next = node;
        ListNode fast = dummy;
        ListNode slow = dummy;
        for (int i = 1;i<=n;i++){
            fast = fast.next;
        }
        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
