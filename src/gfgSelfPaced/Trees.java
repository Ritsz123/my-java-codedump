package gfgSelfPaced;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
    Node constructTreeUsingInorderAndPreorder(int[] inorder, int[] preorder, int start,int end) {
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

        curr.left = constructTreeUsingInorderAndPreorder(inorder, preorder, start, currIndex-1);

        curr.right = constructTreeUsingInorderAndPreorder(inorder, preorder, currIndex +1 , end);

        return curr;
    }

    ArrayList<Integer> leftViewOfTree (Node node) {
        ArrayList<Integer> ans = new ArrayList<>();

        if(node == null) return ans;

        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()){
            int qLen = q.size();
            for (int i = 0;i< qLen;i++){
                Node curr = q.remove();

                if (i == 0){
                    ans.add(curr.data);
                }

                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
        }
        return ans;
    }

    ArrayList<Integer> rightViewOfTree(Node node){
        ArrayList<Integer> ans = new ArrayList<>();

        if (node == null) return ans;

        Queue<Node> q = new LinkedList<>();
        q.add(node);

        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 1;i <= size;i++){
                Node curr = q.remove();
                if (i == size){
                    ans.add(curr.data);
                }
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
        }
        return ans;
    }
}
