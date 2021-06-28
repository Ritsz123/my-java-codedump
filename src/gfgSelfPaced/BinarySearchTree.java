package gfgSelfPaced;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class BinarySearchTree {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Integer[] arr = {5, 1, 8, null, 2, 7, null};
        /*
                5
            1       8
             2    7
         */
        Node root = bst.createTree(arr);

        //! insert node in bst
//        bst.insertNodeInBST(root, 8);

        //! delete node in bst
//        bst.deleteNodeInBST(root, 5);

        //! floor of BST
//        System.out.println("FLOOR : " + bst.floorOfBST(root, 6));

        //! ceil of BST
//        System.out.println("CEIL : " + bst.ceilOfBST(root, 7));

        //! ceiling on the left side
//        int[] arr1 = { 2, 8, 30, 15, 25, 12 };
//        ceilingOnTheLeftSide(arr1);

        //! Check for bst
//        System.out.println(isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

        //* this solution uses technique of preorder traversal but in constants space..
//        System.out.println(isBst2(root));

        // ! check if there contains 2 nodes whose sum is k

        //* brute
        // find inorder traversal add it in array now find sum from inorder traversal using 2 pointer approach

        // * Efficient
        System.out.println(pairSum(root, 9, new HashSet<>()));


//        bst.printTree(root);
    }

    static boolean pairSum(Node root, int sum, HashSet<Integer> hs) {
        if (root == null) return false;

        if(pairSum(root.left, sum, hs)) return true;

        if (hs.contains(sum - root.data)) return true;
        else hs.add(root.data);

        return pairSum(root.right, sum, hs);
    }

    static boolean isBST(Node root, int min, int max){
        if (root == null) return true;

        return min < root.data
            && root.data < max
            && isBST(root.left, min, root.data)
            && isBST(root.right,root.data, max);
    }

    static int prev = Integer.MIN_VALUE;
    static boolean isBst2(Node root){
        if (root == null) return true;
        if (!isBst2(root.left)) return false;
        if (root.data <= prev) return false;
        prev = root.data;
        return isBst2(root.right);
    }

    static void ceilingOnTheLeftSide(int[] arr) {
        TreeSet<Integer> ts = new TreeSet<>();

        System.out.print("-1 ");
        for (int i =1;i< arr.length;i++){
            Integer c = ts.ceiling(arr[i]);
            if (c != null) {
                System.out.print(c + " ");
            }else{
                System.out.print("-1 ");
            }
            ts.add(arr[i]);
        }
    }

    Node createTree(Integer[] arr){
        int n = arr.length;

        Queue<Node> q = new LinkedList<>();
        Node root = null;
        if (arr[0] != null){
            root = new Node(arr[0]);
            q.add(root);
        }
        int i = 1;
        while (!q.isEmpty()){
            Node parent = q.remove();
            if (i < arr.length) {
                if (arr[i] != null) {
                    parent.left = new Node(arr[i++]);
                    q.add(parent.left);
                } else {
                    parent.left = null;
                    i++;
                }

                if (i < n && arr[i] != null) {
                    parent.right = new Node(arr[i++]);
                    q.add(parent.right);
                } else {
                    parent.right = null;
                    i++;
                }
            }
        }
        return root;
    }

    void printTree(Node root) {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0;i<size;i++){
                Node curr = q.remove();
                if (curr != null) {
                    System.out.print(curr.data + " ");
                    q.add(curr.left);
                    q.add(curr.right);
                }
            }
            System.out.println();
        }
    }

    Node insertNodeInBST(Node root, int val) {
        if (root == null) return new Node(val);
        if(root.data > val){
            root.left = insertNodeInBST(root.left, val);
        }else{
            root.right = insertNodeInBST(root.right, val);
        }
        return root;
    }

    Node deleteNodeInBST(Node root, int val){
        if (root == null) return null;
        if (root.data > val) {
            root.left = deleteNodeInBST(root.left, val);
        }else if (root.data < val){
            root.right = deleteNodeInBST(root.right, val);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            else {
                Node succ = successor(root);
                root.data = succ.data;
                root.right = deleteNodeInBST(root.right, succ.data);
            }
        }
        return root;
    }

    int floorOfBST(Node root, int val){
        if (root == null) return -1;
        int ans = -1;
        while (root != null){
            if (root.data == val){
                return val;
            }else if (root.data > val){
                root = root.left;
            } else {
                ans = root.data;
                root = root.right;
            }
        }
        return ans;
    }

    int ceilOfBST(Node root, int val){
        if(root == null) return -1;
        int ans = Integer.MAX_VALUE;
        while (root != null){
            if (root.data == val){
                return val;
            } else if (root.data < val){
                root = root.right;
            }else{
                ans = root.data;
                root = root.left;
            }
        }
        return ans;

    }

    private Node successor(Node root){
        root = root.right;
        while (root != null && root.left != null){
            root = root.left;
        }
        return root;
    }
}
