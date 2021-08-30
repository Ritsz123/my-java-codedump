package SDESheet.day6;

import SDESheet.day5.ListNode;

public class DetectCycleInLL {
    public static void main(String[] args) {
        int[] arr = {3,2,0,-4};
        int pos = 1;
        ListNode head = ListNode.createList(arr);
        makeLoopInList(head, pos);
        boolean hasCycle = detectCycle(head);
        System.out.println("HAS CYCLE " + hasCycle);
    }

    static boolean detectCycle(ListNode head){
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) return true;
        }
        return false;
    }

    static void makeLoopInList(ListNode head, int pos){
        ListNode temp = head,temp1=head;
        while (temp.next!=null){
            temp = temp.next;
        }
        while (temp1!=null && pos>0){
            temp1 = temp1.next;
            pos--;
        }
        temp.next = temp1;
    }
}
