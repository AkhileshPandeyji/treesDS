import java.util.ArrayList;
import java.util.Stack;

public class TreeConstruct {
public static class Node{
	int data;
	ArrayList<Node> children = new ArrayList<>();
}
public static Node treeConstruct(int[] inputs) {
	Stack<Node> st = new Stack<>();
	Node root = new Node();
	root.data = inputs[0];
	st.push(root);
	
	for(int i=1;i<inputs.length;i++) {
		if(inputs[i] == -1) {
			st.pop();
		}
		else {
			Node child = new Node();
			child.data = inputs[i];
			
			Node parent = st.peek();
			parent.children.add(child);
			st.push(child);
		}
	}
	return root;
}

public static void display(Node root) {
	System.out.print(root.data+"->");
	for(int i=0;i<root.children.size();i++) {
		Node children = root.children.get(i);
		System.out.print(children.data+" ");
	}
	System.out.println(".");
	for(int i=0;i<root.children.size();i++) {
		Node children = root.children.get(i);
		display(children);
	}
}

public static int size(Node node) {
	int totSize=0;
	for(int i=0;i<node.children.size();i++) {
		Node child = node.children.get(i);
		totSize+=size(child);
	}
	return totSize+1;
}

public static int max(Node node) {
    int mx = node.data;
    for(int i=0;i<node.children.size();i++) {
    	Node child = node.children.get(i);
    	mx = Math.max(mx,max(child));
    }
    return mx;
}

public static int height(Node node) {
	int mxHeight = 0;
    for(int i=0;i<node.children.size();i++) {
    	Node child = node.children.get(i);
    	mxHeight = Math.max(mxHeight,height(child)+1);
    	
    }
    return mxHeight;
  }
public static void traversals(Node node){
	
	System.out.println("Node Pre "+node.data);
    for(int i=0;i<node.children.size();i++) {
    	Node child = node.children.get(i);
    	System.out.println("Edge Pre "+node.data+"--"+child.data);
    	traversals(child);
    	System.out.println("Edge Post "+node.data+"--"+child.data);
    }
    System.out.println("Node Post "+node.data);
  }
public static void main(String args[]) {
	int[] inputs = { 10 , 20 , 50 , -1 , 60, -1 ,-1, 30,70,-1,-1,40,100,-1,-1,-1};
	Node root = treeConstruct(inputs);
	display(root);
	System.out.println(size(root));
	System.out.println(max(root));
	System.out.println(height(root));
	traversals(root);
}
}
