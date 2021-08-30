package SDESheet.day6;

import SDESheet.day5.ListNode;

public class FindIntersectionPointInLL {
    public static void main(String[] args) {
        int[] ar = {1,2,3,4};
        int[] br = {8,9};
        ListNode head1 = ListNode.createList(ar);
        ListNode head2 = ListNode.createList(br);
        head2.next.next = head1.next.next;

        ListNode node = findIntersection(head1,head2);
        if(node == null) System.out.println("No intersection ");
        else System.out.println("Intersection @ " + node.val);
    }

    static ListNode findIntersection(ListNode h1, ListNode h2){
        ListNode n1 = h1;
        ListNode n2 = h2;

        while (n1 != n2) {
            if(n1 == null){
                n1 = h2;
            }else{
                n1 = n1.next;
            }
            if(n2 == null){
                n2 = h1;
            }else{
                n2 = n2.next;
            }
        }
        return n1;
    }
}
