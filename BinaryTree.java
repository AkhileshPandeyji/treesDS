import java.util.Stack;

public class BinaryTree {
	static class Node{
		int data;
		Node left;
		Node right;		
		Node(int data){
			this.data = data;
			this.left = null;
			this.right = null;
		}
		Node(int data,Node lchild,Node rchild){
			this.data = data;
			this.left = lchild;
			this.right = rchild;
		}
	}
	static class Pair{
		Node node;
		int state;
		Pair(Node n,int s){
			node = n;
			state = s;
		}
	}
	public static Node construct(Integer[] input) {
		Stack<Pair> st = new Stack<>();
		Node root = new Node(input[0]);
		st.push(new Pair(root,1));
		int idx = 1;
		while(st.size()>0) {
			Pair top = st.peek();
			if(top.state == 1) {
				Integer val = input[idx];
				if(val != null) {
					Node n = new Node(val);
					top.node.left = n;
					st.push(new Pair(n,1));
				}
				else {
					top.node.left = null;
				}
				top.state++;
				idx++;
			}
			else if(top.state == 2) {
				Integer val = input[idx];
				if(val!=null) {
					Node n = new Node(val);
					top.node.right = n;
					st.push(new Pair(n,1));
				}
				else {
					top.node.right = null;
				}
				top.state++;
				idx++;
			}
			else {
				st.pop();
			}
		}
		return root;
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
	public static void main(String args[]) {
		Integer[] input = {10,20,40,null,null,50,60,null,null,null,30,null,70,80,null,null,90,null,null};
		Node root = construct(input);
		System.out.println(root.data);
		display(root);
	}
}
