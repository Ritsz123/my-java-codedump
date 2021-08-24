package SDESheet.day5;

public class ReverseLinkedList {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        ListNode head = ListNode.createList(arr);
        ListNode reversed = reverseLinkedList(head);
        ListNode.printList(reversed);
    }

    static ListNode reverseLinkedList(ListNode node) {
        ListNode prev = null;
        while (node != null){
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }
}
