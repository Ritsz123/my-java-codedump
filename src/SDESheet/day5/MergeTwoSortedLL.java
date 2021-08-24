package SDESheet.day5;

public class MergeTwoSortedLL {
    public static void main(String[] args) {
        int[] arr = {1,5,5,7,10,20};
        int [] brr = {0,2,5,10,15,50};
        ListNode a = ListNode.createList(arr);
        ListNode b = ListNode.createList(brr);

        ListNode merged = mergeTwoSortedList(a, b);

        ListNode.printList(merged);
    }

    static ListNode mergeTwoSortedList(ListNode a, ListNode b){
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        while (a != null && b != null){
            if (a.val <= b.val){
                temp.next = new ListNode(a.val);
                a = a.next;
            }else{
                temp.next = new ListNode(b.val);
                b = b.next;
            }
            temp = temp.next;
        }

        if (a != null){
            temp.next = a;
        }
        if (b != null){
            temp.next = b;
        }

        return dummy.next;
    }
}
