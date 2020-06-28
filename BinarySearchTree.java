import java.util.Scanner;

public class BinarySearchTree {
	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	public static Node construct(int[] inputs, int low, int high) {
		if (low > high) {
			return null;
		}
		int mid = (low + high) / 2;
		int data = inputs[mid];
		Node lc = construct(inputs, low, mid - 1);
		Node rc = construct(inputs, mid + 1, high);
		Node node = new Node(data, lc, rc);
		return node;
	}

	public static void display(Node node) {
		String str="";
		if(node.left != null) {
			str+=node.left.data;
		}
		else {
			str+=".";
		}
		str+="<-"+node.data+"->";		
		if(node.right != null) {
			str+=node.right.data;
		}
		else {
			str+=".";
		}
		System.out.println(str);
		if(node.left != null) {
			display(node.left);
		}
		if(node.right != null) {
			display(node.right);
		}
	}
	
	public static int size(Node node) {
		if(node == null) {
			return 0;
		}
		int ls = size(node.left);
		int rs = size(node.right);
		return ls+rs+1;
	}
	public static int sum(Node node) {
		if(node == null) {
			return 0;
		}
		int ls = sum(node.left);
		int rs = sum(node.right);
		return ls+rs+node.data;
	}
	public static int max(Node node) {
		if(node == null) {
			return Integer.MIN_VALUE;
		}
		int rm = max(node.right);
		return Math.max(rm, node.data);
	}
	public static int min(Node node) {
		if(node == null) {
			return Integer.MAX_VALUE;
		}
		int lm = min(node.left);
		return Math.min(lm, node.data);
	}
	public static boolean find(Node node,int data) {
		if(node == null) {
			return false;
		}
	    if(node.data == data) {
	    	return true;
	    }
		else if(data<node.data) {
			boolean lb = find(node.left,data);
			if(lb == true) {
				return lb;
			}
		}
		else if(data>node.data) {
			boolean rb = find(node.right,data);
			if(rb == true) {
				return rb;
			}
		}
	    return false;		
	}
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int inputs[] = new int[n];
		for(int i=0;i<n;i++) {
			inputs[i] = scn.nextInt();
		}
		Node root = construct(inputs, 0, inputs.length-1);
		display(root);
		System.out.println(sum(root));
		System.out.println(size(root));
		System.out.println(max(root));
		System.out.println(min(root));
		System.out.println(find(root,99));
		scn.close();
	}
}
