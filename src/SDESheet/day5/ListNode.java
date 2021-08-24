package SDESheet.day5;

public class ListNode {
    public int val;
    public ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    ListNode() {
        this.val = 0;
        next = null;
    }

    static ListNode createList(int[] arr){
        ListNode dummy = new ListNode();
        ListNode temp = dummy;
        for (int x: arr){
            temp.next = new ListNode(x);
            temp = temp.next;
        }
        return dummy.next;
    }

    static void printList(ListNode node) {
        while (node != null){
            System.out.print(node.val + " -> ");
            node = node.next;
        }
    }
}
