import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class TravelAndChange {
	static int height = 0;
    static int maximum = Integer.MIN_VALUE;
    static int minimum = Integer.MAX_VALUE;
    static int size = 0;
    static int total = 0;

    private static class Node {
        int data;
        ArrayList < Node > children = new ArrayList < > ();
    }

    public static void display(Node node) {
        String str = node.data + " -> ";
        for (Node child: node.children) {
            str += child.data + ", ";
        }
        str += ".";
        System.out.println(str);

        for (Node child: node.children) {
            display(child);
        }
    }

    public static Node construct(int[] arr) {
        Node root = null;

        Stack < Node > st = new Stack < > ();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                st.pop();
            } else {
                Node t = new Node();
                t.data = arr[i];

                if (st.size() > 0) {
                    st.peek().children.add(t);
                } else {
                    root = t;
                }

                st.push(t);
            }
        }
        return root;
    }

    public static void multisolver(Node node, int depth) {

        size++;
        height = Math.max(height, depth);
        maximum = Math.max(maximum, node.data);
        minimum = Math.min(minimum, node.data);
        total = total + node.data;

        for (Node child: node.children) {
            multisolver(child, depth + 1);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }

        Node root = construct(arr);
        multisolver(root, 0);
        System.out.println("Maximum:" + maximum);
        System.out.println("Minimum:" + minimum);
        System.out.println("Size:" + size);
        System.out.println("Total:" + total);
        System.out.println("Heigth:" + height);
    }
}
