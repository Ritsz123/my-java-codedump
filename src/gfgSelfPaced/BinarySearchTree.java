package gfgSelfPaced;

import javafx.util.Pair;
import java.util.*;
import java.util.LinkedList;

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
//        System.out.println(pairSum(root, 9, new HashSet<>()));


        //! vertical sum of BST
//        verticalSumBST(root);

        //! vertical traversal BST
//        verticalTraversalBST(root);

        // ! top view of Binary tree
//        topViewOfBinaryTree(root);

        // ! bottom view of BST
//        bottomViewOfBinaryTree(root);

        //! lowest common ancestor of BST
//        Node ans = lowestCommonAncestorInBST(root, 2,7);
//        System.out.println("lca : " + ans.data);

        //! Kth largest element in BST
//        int kthElement = kthLargestElementInBST(root, 4);
//        System.out.println(kthElement);

        // ! check if there contains a pair with sum k in BST
        //* brute
//        * O(N) space & time
//        traverse inorder the tree to make inorder array(sorted array)
//        then use 2 pointer approach to check if sum is present

        //* efficient
//        !O(h) time & O(h) space
//        HashSet<Integer> hs = new HashSet<>();
//        System.out.println(pairWithGivenSumInBST(root, 10, hs));

        //! find closest element difference in BST
//        int ans = findClosestElementDifference(root, 9);
//        System.out.println(ans);

        //! construct BST from level order traversal
//        ArrayList<Integer> al = new ArrayList<>();
//        int[] levelOrder = {7,4,12,3,6,8,1,5,10};
//        for (int x: levelOrder){
//            al.add(x);
//        }
//        Node tree = constructBSTFromLevelOrderTraversal(al);
//        bst.printTree(tree);

        // ! construct BST from preorder traversal
        // in preorder sequence is -> Root, LEFT TREE, RIGHT TREE

        int[] pre = {40,30,35,80,100};
        Node tree = constructBSTFromPreorderTraversal(pre, 0, pre.length-1);
        bst.printTree(tree);
    }

    static Node constructBSTFromPreorderTraversal(int[] arr, int start, int end){
        if (start <= end) {
            Node root = new Node(arr[start]);
            int mid = start+1;
            while (mid <= end && arr[mid] < arr[start]){
                mid++;
            }

            root.left = constructBSTFromPreorderTraversal(arr, start+1, mid-1);
            root.right = constructBSTFromPreorderTraversal(arr, mid, end);
            return root;
        }
        return null;
    }

    static Node constructBSTFromLevelOrderTraversal(ArrayList<Integer> al) {
        if (al.size() < 1) return null;
        Node root = new Node(al.get(0));
        ArrayList<Integer> a1 = new ArrayList<>();
        ArrayList<Integer> a2 = new ArrayList<>();

        for (int i = 1; i<al.size();i++){
            int x = al.get(i);
            if (x < root.data){
                a1.add(x);
            }else{
                a2.add(x);
            }
        }

        root.left = constructBSTFromLevelOrderTraversal(a1);
        root.right = constructBSTFromLevelOrderTraversal(a2);
        return root;
    }

    static int findClosestElementDifference(Node root, int k){
        if(root == null) return Integer.MAX_VALUE;
        if (root.data == k) return 0;
        if (root.data < k){
            return Math.min(Math.abs(root.data - k), findClosestElementDifference(root.right, k));
        }else{
            return Math.min(Math.abs(root.data - k), findClosestElementDifference(root.left,k));
        }
    }

    static boolean pairWithGivenSumInBST(Node root, int sum, HashSet<Integer> hs){
        if (root == null) return false;

        if (pairWithGivenSumInBST(root.left, sum, hs)) return true;

        if (hs.contains(sum - root.data)){
            return true;
        }else{
            hs.add(root.data);
        }
        return pairWithGivenSumInBST(root.right, sum, hs);
    }

    static Node lowestCommonAncestorInBST(Node root, int n1, int n2){
        if(root == null) return root;

        if(root.data < n1 && root.data < n2){
            return lowestCommonAncestorInBST(root.right, n1, n2);
        }else if (root.data > n1 && root.data > n2){
            return lowestCommonAncestorInBST(root.left, n1, n2);
        }else {
            return root;
        }
    }

    static void bottomViewOfBinaryTree(Node root){
        if (root == null) return;
        Queue<Pair<Node,Integer>> q = new LinkedList<>();
        q.add(new Pair<Node, Integer>(root, 0));
        TreeMap<Integer, Integer> tm = new TreeMap<>();

        while (!q.isEmpty()){
            Pair<Node,Integer> p = q.remove();
            Node curr = p.getKey();
            int hd = p.getValue();

            tm.put(hd, curr.data);

            if (curr.left != null) q.add(new Pair<>(curr.left, hd - 1));
            if (curr.right != null) q.add(new Pair<>(curr.right, hd + 1));
        }

        for (Map.Entry<Integer, Integer> e: tm.entrySet()){
            System.out.print(e.getValue() + " ");
        }
    }

    static void topViewOfBinaryTree(Node root){
        Queue<Pair<Node, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(root, 0));
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        while (!q.isEmpty()){
            Pair<Node, Integer> p = q.remove();
            Node curr = p.getKey();
            int hd = p.getValue();

            if (!tm.containsKey(hd)){
                tm.put(hd, curr.data);
            }

            if (curr.left != null) q.add(new Pair<>(curr.left, hd-1));
            if (curr.right != null) q.add(new Pair<>(curr.right, hd + 1));
        }

        for (Map.Entry<Integer, Integer> e : tm.entrySet()){
            System.out.println(e.getKey() + " : " + e.getValue());
        }
    }

    static void verticalTraversalBST(Node root){
        Queue<Pair<Node, Integer>> q = new LinkedList<>();
        q.add(new Pair<>(root, 0));
        TreeMap<Integer, ArrayList<Integer>> tm = new TreeMap<>();
        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 1;i <= size;i++){
                Pair<Node,Integer> p = q.remove();
                Node curr = p.getKey();
                int hd = p.getValue();

                if (tm.containsKey(hd)){
                    tm.get(hd).add(curr.data);
                }else{
                    ArrayList<Integer> al = new ArrayList<>();
                    al.add(curr.data);
                    tm.put(hd, al);
                }

                if (curr.left != null) {
                    q.add(new Pair<>(curr.left, hd-1));
                }
                if (curr.right != null){
                    q.add(new Pair<>(curr.right, hd + 1));
                }
            }
        }

        for (Map.Entry<Integer, ArrayList<Integer>> e : tm.entrySet()){
            System.out.print(e.getKey() + " : ");
            for (int x: e.getValue()){
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    static int nodeCount = 0, ans = -1;
    static int kthLargestElementInBST(Node root, int k){
        reverseInorder(root, k);
        return ans;
    }

    static void reverseInorder(Node root, int k){
        if (root == null || nodeCount > k) return;
        reverseInorder(root.right, k);

        nodeCount++;
        if (nodeCount == k){
            ans = root.data;
        }

        if(nodeCount < k )
        reverseInorder(root.left, k);
    }

    static void verticalSumBST(Node root) {
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        vSum(root, mp , 0);
        for (Map.Entry<Integer,Integer> e: mp.entrySet()){
            System.out.print(e.getValue() + " ");
        }
        System.out.println();
    }

    static void vSum(Node root, TreeMap<Integer, Integer> tm, int hd) {
        if (root == null) return;
        vSum(root.left, tm, hd-1);

        tm.put(hd, tm.getOrDefault(hd, 0) + root.data);

        vSum(root.right, tm, hd+1);
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
