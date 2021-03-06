package graphics.nim.tree;

import java.util.ArrayList;
import java.util.List;

public class Node {
	public Node parent;
	public String move;
	public int count = 1;
	public boolean selected = false;
	
	public List<Node> children = new ArrayList<Node>();

	public Node(String move) {
		this.move = move;
	}
	
	public Node addChild(String move) {
		Node child = new Node(move);
		child.parent = this;
		
		for (Node n: children) {
			if (move.equals(n.move)) {
				n.count++;
				return n;
			}
		}
		
		children.add(child);
		return child;
	}
	
	public Node getChild(String move) {
		for (Node n: children) {
			if (move.equals(n.move)) {
				return n;
			}
		}
		return null;
	}
	
	public Node prev() {
		if (parent == null) {
			return this;
		}

		int index = parent.children.indexOf(this);
		
		try {
			return parent.children.get(index-1);
		} catch (IndexOutOfBoundsException e) {
			return parent.children.get(index);
		}
	}
	
	public Node next() {
		if (parent == null) {
			return this;
		}

		int index = parent.children.indexOf(this);
		
		try {
			return parent.children.get(index+1);
		} catch (IndexOutOfBoundsException e) {
			return parent.children.get(index);
		}
	}
	
	public int getBreadth() {
		int breadth = 0;
		
		if (children.size() > 0) {
			for (Node child: children) {
				breadth += child.getBreadth();
			}
		} else {
			breadth += 32;
		}
		
		return breadth;
	}
	
	@Override
	public String toString() {
		String s = move + "\n";
		for (Node node: children) {
			s += String.format("  %s (%d)\n", node.move, node.count);
		}
		return s;
	}
}
