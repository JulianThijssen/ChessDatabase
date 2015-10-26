package graphics.nim.tree;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public String move;
	public int count = 1;
	
	public List<Node> children = new ArrayList<Node>();
	
	public Node(String move) {
		this.move = move;
	}
	
	public void add(Node node) {
		for (Node child: children) {
			if (child.move.equals(node.move)) {
				child.count++;
				return;
			}
		}
		children.add(node);
	}
}
