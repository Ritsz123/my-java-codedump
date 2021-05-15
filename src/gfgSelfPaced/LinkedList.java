package gfgSelfPaced;

import java.util.HashMap;
import java.util.HashSet;

class Node{
    int data;
    Node next;
    Node random;
    Node(int data){
        this.data = data;
    }
    Node(){}
}

public class LinkedList {

    static Node createList(int[] arr){
        Node temp = new Node(),head=temp;
        for (int i = 0;i<arr.length;i++){
            temp.next = new Node(arr[i]);
            temp = temp.next;
        }
        return head.next;
    }

    static void makeLoopInList(Node head,int pos){
        Node temp = head,temp1=head;
        while (temp.next!=null){
            temp = temp.next;
        }
        while (temp1!=null && pos>0){
            temp1 = temp1.next;
            pos--;
        }
        temp.next = temp1;
    }

    public static void main(String[] args) {
        int [] arr = {10,11,12,13,54,66,77};

        Node temp = createList(arr);
        //to add loop in the list
//        makeLoopInList(temp,1);

//        System.out.println("Original list");
//        printList(temp);
//        System.out.println();

//        System.out.println("after reverse of k");
//        temp = reverseLinkedList(head);
//        temp = reverseLinkedListInGroupsOfK(head,3);
//        temp  = reverseLinkedListInGroupsOfKEfficient(temp,3);
//        printList(temp);

//        System.out.println("Linked list has loop: " + detectLoopInALinkedList(temp));
//        System.out.println("after removal of loop");
//        removeLoopFromTheLinkedList(temp);
//        printList(temp);


//        System.out.println("before deleting ");
//        printList(temp);
//        deleteANodeWithTheGivenPointer(temp.next.next);
//        System.out.println("after deleting");
//        printList(temp);

//        System.out.println("before segregation");
//        printList(temp);
//        Node node = segregateEvenOddNodesOfTheLinkedList(temp);
//        System.out.println("After segregation");
//        printList(node);

//        int[] ar = {1,2,3,4};
//        int[] br = {8,9};
//        Node head = createList(ar);
//        Node head2 = createList(br);
//        head2.next.next = head.next.next;
//        printList(head);
//        System.out.println();
//        printList(head2);
//        intersectionPointOfTwoLinkedLists(head,head2);
//        intersectionPointOfTwoLinkedListsEfficient(head,head2);

//        System.out.println("Before swap: ");
//        printList(temp);
//        pairwiseSwapNodesOfTheLinkedList(temp);
//        System.out.println("After swap: ");
//        printList(temp);

//        printList(cloneALinkedListWithRandomPointerEfficient(temp));

//        int[] a = {10,20,30,40};
//        int[] b = {5,15,17,18,35};
//        Node h1 = createList(a);
//        Node h2 = createList(b);
//        printList(mergeTwoSortedLinkedLists(h1,h2));

//        int[] a = {1,2,2,3,4,5,5,5};
//        Node head = createList(a);
//        head = removeDuplicatedFromSortedLinkedList(head);
//        printList(head);

//        int[] a = {5,4,4,8,5};
//        Node h = createList(a);
//        h = removeDuplicateFromNonSortedLinkedList(h);
//        printList(h);

//        int[] a = {1,2,3,4,5,6,7,8,9};
//        Node head = createList(a);
//        int n = 2;
//        System.out.println(printNthNodeFromEndOfLinkedList(head,n));

//        int[] a = {1,2,3,4};
//        Node head = createList(a);
//        int n = 2;
//        System.out.println(printNthNodeFromEndOfLinkedList(head,n));


//        int[] a = {25,14,19,33,10,21,39,90,58,45};
//        Node head = createList(a);
//        makeLoopInList(head,3);
//        System.out.println(detectAndFindTheLengthOfLoopInLinkedList(head));

//        int[] a = {2,4,7,8,9};
//        Node head  = createList(a);
//        head = rotateALinkedListByKPositions(head,5);
//        printList(head);

        int[] a = {4,5};
        int[] b = {3,4,5};
        Node a1 = createList(a);
        Node b1 = createList(b);
        Node ans = addTwoNumbersRepresentedAsALinkedList(a1,b1);
        printList(ans);
    }

    // ! GFG quest
    static Node addTwoNumbersRepresentedAsALinkedList(Node a,Node b){
        if (a==null) return b;
        if (b==null) return a;

        Node dummy = new Node();

        a = reverseLinkedList(a);
        b = reverseLinkedList(b);

        Node c = dummy;
        int carry = 0;
        while(a!=null || b!=null || carry!=0){
            int sum = 0;
            if (a != null){
                sum += a.data;
                a = a.next;
            }
            if (b != null){
                sum += b.data;
                b = b.next;
            }
            sum += carry;
            carry = sum/10;
            sum = sum % 10;
            c.next = new Node(sum);
            c=c.next;
        }
        return reverseLinkedList(dummy.next);
    }

    static Node rotateALinkedListByKPositions(Node head,int k){
        if(head == null) return null;
        Node curr = head,prev = null;
        while (curr!=null && k-->0){
            prev = curr;
            curr = curr.next;
        }
        if (curr == null) return head;
        prev.next = null;
        Node newHead = curr;
        while (curr.next!=null){
            curr = curr.next;
        }
        curr.next = head;
        return newHead;
    }

    static int detectAndFindTheLengthOfLoopInLinkedList(Node head){
        Node fast = head,slow = head;
        if (fast ==null || fast.next ==null) return 0;
        do{
            fast = fast.next.next;
            slow = slow.next;
        } while(fast !=null && fast.next != null && fast != slow);

        if (fast == slow){
            // * loop found
          fast = head;
          while (fast != slow){
              fast = fast.next;
              slow = slow.next;
          }

          int count = 1;

          while (fast.next != slow){
              fast = fast.next;
              count++;
          }
          return count;
        } else {
            return 0;
        }
    }

    static int printNthNodeFromEndOfLinkedList(Node head,int n) {
     // !  Multi traversal

//        Node temp = head;
//        int count = 0;
//        while(temp!=null){
//            count++;
//            temp = temp.next;
//        }
//        temp = head;
//        int x = 0;
//        while(x != count-n){
//            x++;
//            temp = temp.next;
//        }

//        return temp.data;

        // ! single traverse

        Node n1 = head, n2 = head;
        while(n-- > 0) {
            n1 = n1.next;
        }

        while(n1!=null){
            n1 = n1.next;
            n2 = n2.next;
        }
        return n2 == null ? -1 : n2.data;
    }

    static Node removeDuplicateFromNonSortedLinkedList(Node head){
        if(head == null) return head;
        Node curr = head;
        HashSet<Integer> hs = new HashSet<>();
        hs.add(curr.data);
        while(curr.next!=null){
            if(hs.contains(curr.next.data)){
                curr.next = curr.next.next;
            }else{
                hs.add(curr.next.data);
                curr = curr.next;
            }
        }
        return head;
    }

    static Node removeDuplicatedFromSortedLinkedList(Node head){
        if(head == null) return head;
        Node curr = head;
        while(curr.next!=null){
            if(curr.data == curr.next.data){
                curr.next = curr.next.next;
            }else{
                curr = curr.next;
            }
        }
        return head;
    }

    static Node mergeTwoSortedLinkedLists(Node a,Node b){
        if (a==null) return b;
        if (b==null) return a;

        Node head,tail;
        if (a.data <= b.data){
            head = a;
            tail = a;
            a = a.next;
        }else{
            head = b;
            tail = b;
            b = b.next;
        }

        while (a!=null && b!=null){
            if (a.data <= b.data){
                tail.next = a;
                tail = tail.next;
                a = a.next;
            }else{
                tail.next = b;
                tail = tail.next;
                b = b.next;
            }
        }

        if(a==null) tail.next = b;
        else tail.next = a;
        return head;
    }

    static Node cloneALinkedListWithRandomPointerEfficient(Node head){
        for (Node curr = head;curr!=null;){
            Node next = curr.next;
            curr.next = new Node(curr.data); //assigning data to new node
            curr.next.next = next;      //assigning next to new node
            curr = next;
        }

        for (Node curr = head; curr!=null;curr = curr.next.next){
            curr.next.random = curr.random!=null ? curr.random.next : null;
        }

        Node head2 = new Node(); //dummy
        Node temp = head2;

        for (Node curr = head;curr!=null;){
            Node t = curr.next;
            curr.next = curr.next.next;
            curr = curr.next;
            temp.next = t;
            temp = temp.next;
        }
        return head2.next;
    }

    static Node cloneALinkedListWithRandomPointer(Node node){
        HashMap<Node,Node> map = new HashMap<>();
        Node temp = node;

        while (temp!=null){
            map.put(temp,new Node(temp.data));
            temp = temp.next;
        }
        temp = node;
        while (temp!=null){
            Node clone = map.get(temp);
            clone.next = map.get(temp.next);
            clone.random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(node);
    }

    static Node pairwiseSwapNodesOfTheLinkedListWithoutSwapData(Node head){
        if (head == null || head.next == null){
            return null;
        }
        Node current = head.next.next;
        Node prev = head;
        head = head.next;
        head.next = prev;

        while (current !=null && current.next!=null){
            prev.next = current.next;
            prev = current;
            Node next = current.next.next;
            current.next.next = current.next;
            current = next;
        }
        prev.next=current;
        return head;
    }

//    by swapping data
    static void pairwiseSwapNodesOfTheLinkedList(Node node){
        while (node!=null && node.next!=null){
//            swap node & node.next data
            int temp = node.data;
            node.data = node.next.data;
            node.next.data = temp;
            node = node.next.next;
        }
    }


    static void intersectionPointOfTwoLinkedListsEfficient(Node node1,Node node2){
        int count1=0,count2=0;
        Node temp = node1;
        while (temp!=null){
            temp = temp.next;
            count1++;
        }
        temp = node2;
        while (temp!=null){
            temp = temp.next;
            count2++;
        }
        int diff = Math.abs(count1-count2);
        if (count1>count2){
            while (diff>0 && node1!=null){
                node1 = node1.next;
                diff--;
            }
        }else{
            while (diff>0 && node2!=null){
                node2 = node2.next;
                diff--;
            }
        }
        while (node1!=null && node2!=null){
            if (node1==node2){
                System.out.println("Point of intersection via Efficient " + node1.data);
                break;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
    }

    //using hashset
    static void intersectionPointOfTwoLinkedLists(Node node1,Node node2){
        HashSet<Node> hs = new HashSet<>();
        for (Node c = node1;c!=null;c=c.next){
            hs.add(c);
        }
        for (Node c = node2;c!=null;c=c.next){
            if (hs.contains(c)){
                System.out.println("Intersection point " + c.data);
                break;
            }
        }
    }

    static Node segregateEvenOddNodesOfTheLinkedList(Node head){

        Node dummy = new Node();
        Node dummy1 = new Node();
        Node even=dummy;
        Node odd=dummy1;
        Node evenHead = even,oddHead = odd;

        for (;head!=null;head=head.next){
            if (head.data%2==0){
                even.next = head;
                even = even.next;
            }else{
                odd.next = head;
                odd = odd.next;
            }
        }
        even.next = oddHead.next;
        odd.next = null;
        return evenHead.next;
    }

    static void deleteANodeWithTheGivenPointer(Node node){
//        node is the node to be deleted
//        node is never the last node
        node.data = node.next.data;
        node.next = node.next.next;
    }

    static void removeLoopFromTheLinkedList(Node head){
        Node fast = head,slow = head;
        boolean hasLoop = false;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;

            if (slow==fast){
                hasLoop = true;
                break;
            }
        }
        if (!hasLoop) return;

        slow = head;
        while (slow.next != fast.next){
            slow = slow.next;
            fast = fast.next;
        }
        fast.next = null;
    }

    static boolean detectLoopInALinkedList(Node head){
        Node fast = head,slow=head;
        while (fast!=null && fast.next!=null){
            slow = slow.next;
            fast=fast.next.next;

            if (slow==fast){
                return true;
            }
        }
        return false;
    }

    static Node reverseLinkedListInGroupsOfKEfficient(Node head,int k){
        Node current = head, prevFirst = null;
        boolean isFirstPass = true;
        while (current!=null){
            Node first = current,prev=null;
            int count=0;
            while (current!=null && count<k){
                Node next = current.next;
                current.next = prev;

                prev = current;
                current = next;
                count++;
            }
            if (isFirstPass){
                head = prev;
                isFirstPass = false;
            }else{
                prevFirst.next = prev;
            }
            prevFirst = first;
        }
        return head;
    }

//    ! recursive solution
    static Node reverseLinkedListInGroupsOfK(Node head,int k){
        Node prev=null,next=null,current = head;
        int c=0;
        while (current!=null && c<k){
            next = current.next;
            current.next = prev;

            prev=current;
            current = next;

            c++;
        }
        if (next!=null){
            head.next = reverseLinkedListInGroupsOfK(next,k);
        }
        return prev;
    }

    static Node reverseLinkedList(Node head){
        Node prev=null,next=null,current=head;
        while (current!=null){
            //reversing logic
            next = current.next;
            current.next = prev;

            //move forward
            prev = current;
            current = next;
        }
        return prev;
    }

    static void printList(Node temp){
        while (temp!=null){
            System.out.print(temp.data + " ");
            temp=temp.next;
        }
    }
}
