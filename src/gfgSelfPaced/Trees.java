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

    int height(Node root){
        if(root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);

        return Math.min(left, right) + 1;
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

    Node head = null, prev_node = null;
    void treeTODLl(Node root){
        if(root == null) return;
        treeTODLl(root.left);
        if (head == null){
            head = root;
            prev_node = root;
        } else {
            prev_node.right = root;
            root.left = prev_node;
            prev = prev_node.right;
        }
        treeTODLl(root.right);
    }

//   ! Construct a tree ..
//   ! either inorder preorder or inorder Postorder is required to construct tree

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

//    ! Left view of a tree
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

//    ! Right view of tree
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

//    ! sprial order traversal of tree
    ArrayList<Integer> sprialTraversalOfTree(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        boolean flag = true;
        while (!q.isEmpty()){
            int len = q.size();
            int[] temp = new int[len];
            int x = 0;
            for (int i = 0;i<len;i++){
                Node curr = q.remove();
                temp[x++] = curr.data;
                if (curr.left !=null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
            if (flag) {
                reverseArray(temp);
            }

            for(int e: temp){
                ans.add(e);
            }
            flag = !flag;
        }
        return ans;
    }

    private void reverseArray(int[] temp) {
        int i=  0;
        int j = temp.length-1;
        while (i<j){
            int t= temp[i];
            temp[i] = temp[j];
            temp[j] = t;
            i++;
            j--;
        }
    }

    // ! maxdiameter is the maximum distance between 2 leaf nodes
//    * Brute solution
    int maxDiameterOfTree(Node root) {
        if (root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);

        int currHeight = left + right + 1;
        int max = Math.max(maxDiameterOfTree(root.left), maxDiameterOfTree(root.right));
        max = Math.max(max, currHeight);
        return max;
    }

//    the idea is to simply modify the height finding function

    int maxDiameter = 0;
    int maxDiameterEfficient(Node root){
        if(root == null) return 0;

        int left = maxDiameterEfficient(root.left);
        int right = maxDiameterEfficient(root.right);

        // Code change in height fn
        maxDiameter = Math.max(maxDiameter, left + right + 1);
        //
        return Math.max(left, right) + 1;
    }

    // ! lowest Common ancestor
    //* lowest means nearest...

    // *Brute
    // find the path to n1
    // find path to n2
    // check for commons in these paths the last common is ans.

    // * efficient
    Node lcs(Node root, int n1, int n2) {
        if (root == null || root.data == n1 || root.data == n2){
            return root;
        }

        Node left = lcs(root.left, n1,n2);
        Node right = lcs(root.right, n1,n2);

        //not found in any part
        if (left == null && right == null) return null;
        else if (left == null) return right; //left is null right is not;
        else if (right == null) return left; // right is null left is not;
        else return root; // n1 is in left && n2 is in right or opposite then nearest connection is the current node.
    }

    int countNodesInCompleteBinaryTree(Node root) {
        if (root == null) return 0;

        Node curr = root;
        int lh = 0;
        int rh = 0;
        while (curr != null) {
            lh++;
            curr = curr.left;
        }
        curr = root;
        while (curr != null){
            rh++;
            curr = curr.right;
        }

        if (lh == rh){
            return (int) Math.pow(2, lh) - 1;
        }
        return countNodesInCompleteBinaryTree(root.left) + countNodesInCompleteBinaryTree(root.right) + 1;
    }

    boolean checkIfSubtree(Node n1, Node n2){
        if(n1 == null){
            return false;
        }else if(isSame(n1,n2)){
            return true;
        }else{
            return checkIfSubtree(n1.left,n2) || checkIfSubtree(n1.right, n2);
        }
    }

    boolean isSame(Node n1, Node n2){
        if(n1 == null || n2 == null){
            return n1 == n2;
        }else if(n1.data == n2.data){
            return isSame(n1.left, n2.left) && isSame(n2.right, n2.right);
        }else {
            return false;
        }
    }

//    ! Maximum path sum from any node to any node in tree
    int maxSum;
    int maxPathSumFromAnyNodeToAnyNode(Node root){
        maxSum = Integer.MIN_VALUE;
        maxPath(root);
        return maxSum;
    }

    int maxPath(Node root) {
        if(root == null) return 0;

        int left = Math.max(0, maxPath(root.left)); // to avoid -ve values
        int right = Math.max(0, maxPath(root.right));

        maxSum = Math.max(maxSum, left + right + root.data); //* maximum for left-> node-> right

        return Math.max(left, right) + root.data;
    }

    Node treeFromLinkedList(Node llist){
        if (llist == null) return null;
        Queue<Node> q = new LinkedList<>();
        Node root = new Node(llist.data);
        q.add(root);
        llist = llist.left;
        while (llist != null){
            Node curr = q.remove();
            curr.left = new Node(llist.data);
            llist = llist.left;
            q.add(curr.left);
            if (llist != null){
                curr.right = new Node(llist.data);;
                llist = llist.left;
                q.add(curr.right);
            }
        }
        return root;
    }
}
