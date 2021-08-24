package SDESheet.day5;

//! node that if the number is not in reverse order reverse it i.e list of 123 should be 3->2->1
public class AddTwoNumbersRepresentedAsLinkedList {
    public static void main(String[] args) {
        //num 349
        int[] arr = {2,4,9};
        //num 654
        int[] brr = {5,6,4};

        ListNode a = ListNode.createList(arr);
        ListNode b = ListNode.createList(brr);

        ListNode sum = addToNos(a,b);
        ListNode.printList(sum);
    }

    private static ListNode addToNos(ListNode a, ListNode b) {
        ListNode dummy = new ListNode();
        ListNode temp = dummy;
        int carry = 0;
        while (a != null || b != null){
            int n1 = a == null ? 0 : a.val;
            int n2 = b == null ? 0 : b.val;
            int sum = n1 + n2 + carry;

            carry = sum / 10;
            sum = sum % 10;
            temp.next = new ListNode(sum);
            temp = temp.next;
            if (a != null) a = a.next;
            if (b != null) b = b.next;
        }

        // * edge case
        if (carry != 0){
            temp.next = new ListNode(carry);
        }
        
        return dummy.next;
    }
}
