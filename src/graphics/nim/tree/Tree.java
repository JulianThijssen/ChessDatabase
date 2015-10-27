package graphics.nim.tree;

import graphics.nim.Game;

public class Tree {
	public Node root = new Node("X");
	
	public void addGame(Game game) {
		Node rootCopy = root;
		
		for (String move: game.moves) {
			rootCopy = rootCopy.addChild(move);
		}
	}
}
