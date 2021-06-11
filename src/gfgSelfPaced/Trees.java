package gfgSelfPaced;

public class Trees {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    // ! convert tree to doubly linked list in place.

    Node prev = null;
    Node treeToDoublyLinkedList(Node root) {
        if (root == null) return null;

        Node head = treeToDoublyLinkedList(root.left);

        if (prev == null){
            head = root;
        }else{
            root.left = prev;
            prev.right = root;
        }

        prev = root;

        treeToDoublyLinkedList(root.right);
        return head;
    }

    int preIndex = 0;
    Node constructTree(int[] inorder, int[] preorder, int start,int end) {
        if (start > end) return null;

        Node curr = new Node(preorder[preIndex]);

        // search for index of current element in inorder so that left elements form left tree
        // and right elements forms right tree.
        int currIndex = -1;
        for (int i = start;i<=end;i++){
            if(inorder[i] == curr.data){
                currIndex = i;
                break;
            }
        }

        curr.left = constructTree(inorder, preorder, start, currIndex-1);

        curr.right = constructTree(inorder, preorder, currIndex +1 , end);

        return curr;
    }
}
