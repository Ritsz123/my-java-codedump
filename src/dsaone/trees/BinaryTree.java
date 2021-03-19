package dsaone.trees;
import java.util.*;
class Node{
	Node left,right;
	int data;
	Node(int data){
		this.data=data;
	}
}

public class BinaryTree {
	static Scanner sc;
	public static void main(String[] args) {
		sc=new Scanner(System.in);
		Node root = createTree();
		
//		inOrder(root);
//		System.out.println();
//		preOrder(root);
//		System.out.println();
//		postOrder(root);
//		System.out.println();

		BFS(root);
	}
	
	static Node createTree() {
		Node newNode=null;
		int data;
		System.out.println("Enter data");
		data = sc.nextInt();
		if(data==-1) return null; //no more nodes present
		
		newNode = new Node(data);
		System.out.println("Enter value left to " + data);
		newNode.left = createTree();
		System.out.println("Enter value Right to "+ data);
		newNode.right = createTree();
		return newNode;
	}

	static boolean checkForBST(Node root){
		return checkForBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}

	static boolean checkForBST(Node root,int min,int max){
		if (root==null) return true;

		return (root.data > min &&
				root.data < max &&
				checkForBST(root.left,min,root.data) &&
				checkForBST(root.right,root.data,max)
			);
	}

	static void printKthLevelOfTree(Node root,int k){
		if(root==null) return;
		if(k==0) System.out.println(root.data);

		printKthLevelOfTree(root.left,k-1);
		printKthLevelOfTree(root.right,k-1);
	}

	static int heightOfTree(Node root){
		if (root ==null) return 0;
		int lh = heightOfTree(root.left);
		int rh = heightOfTree(root.right);
		return Math.max(lh,rh) + 1;
	}

	static void inOrder(Node root) {
		//LNR
		if(root==null) return;
		inOrder(root.left);
		System.out.print(root.data + " ");
		inOrder(root.right);
	}
	
	static void preOrder(Node root) {
		//NLR
		if(root==null) return;
		System.out.print(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	}
	
	static void postOrder(Node root) {
		//LRN
		if(root==null) return;
		postOrder(root.left);
		postOrder(root.right);
		System.out.println(root.data + " ");
	}

	static void BFS(Node root){
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()){
			Node curr = q.remove();
			System.out.print(curr.data + " ");
			if (curr.left!=null){
				q.add(curr.left);
			}
			if (curr.right!=null){
				q.add(curr.right);
			}
		}
	}
}

