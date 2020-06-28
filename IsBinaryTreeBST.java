import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class IsBinaryTreeBST {
	public static class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	public static class Pair {
		Node node;
		int state;

		Pair(Node node, int state) {
			this.node = node;
			this.state = state;
		}
	}

	public static Node construct(Integer[] arr) {
		Node root = new Node(arr[0], null, null);
		Pair rtp = new Pair(root, 1);

		Stack<Pair> st = new Stack<>();
		st.push(rtp);

		int idx = 0;
		while (st.size() > 0) {
			Pair top = st.peek();
			if (top.state == 1) {
				idx++;
				if (arr[idx] != null) {
					top.node.left = new Node(arr[idx], null, null);
					Pair lp = new Pair(top.node.left, 1);
					st.push(lp);
				} else {
					top.node.left = null;
				}

				top.state++;
			} else if (top.state == 2) {
				idx++;
				if (arr[idx] != null) {
					top.node.right = new Node(arr[idx], null, null);
					Pair rp = new Pair(top.node.right, 1);
					st.push(rp);
				} else {
					top.node.right = null;
				}

				top.state++;
			} else {
				st.pop();
			}
		}

		return root;
	}

	public static void display(Node node) {
		if (node == null) {
			return;
		}

		String str = "";
		str += node.left == null ? "." : node.left.data + "";
		str += " <- " + node.data + " -> ";
		str += node.right == null ? "." : node.right.data + "";
		System.out.println(str);

		display(node.left);
		display(node.right);
	}

	public static int height(Node node) {
		if (node == null) {
			return -1;
		}

		int lh = height(node.left);
		int rh = height(node.right);

		int th = Math.max(lh, rh) + 1;
		return th;
	}

	static boolean isBST;

	public static void isBST(Node node) {
		if(node.left != null && node.left.data<node.data) {
//			System.out.println(node.data+":");
			lMax = Integer.MIN_VALUE;
			lmax(node.left);
			if(lMax<node.data) {
				isBST = true;
			}
			else {
				isBST = false;
				return;
			}
//			System.out.println(isBST);
			isBST(node.left);
		}
		if(node.right != null && node.right.data>node.data) {
//			System.out.println(node.data+":");
			rMin = Integer.MAX_VALUE;
			rmin(node.right);
			if(rMin>node.data) {
				isBST = true;
			}
			else {
				isBST = false;
				return;
			}
//			System.out.println(isBST);
			isBST(node.right);	
		}
	}

	static int lMax;

	public static void lmax(Node node) {
		if (node == null) {
			return;
		}
		if (node.data > lMax) {
			lMax = node.data;
		}
//		System.out.println(node.data+":"+lMax);
		lmax(node.left);
		lmax(node.right);
	}

	static int rMin;

	public static void rmin(Node node) {
		if (node == null) {
			return;
		}
		if (node.data < rMin) {
			rMin = node.data;
		}
//		System.out.println(node.data+":"+rMin);
		rmin(node.left);
		rmin(node.right);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[n];
		String[] values = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			if (values[i].equals("n") == false) {
				arr[i] = Integer.parseInt(values[i]);
			} else {
				arr[i] = null;
			}
		}

		Node root = construct(arr);
		isBST = false;
		isBST(root);
		System.out.println(isBST);
		
		
	}
}
