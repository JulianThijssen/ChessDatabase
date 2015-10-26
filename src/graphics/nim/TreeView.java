package graphics.nim;

import graphics.nim.tree.Node;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class TreeView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public List<Game> games = null;
	public Board board = new Board();
	
	Node root = new Node("root");
	
	public Graphics g = null;
	
	public TreeView() {
		setPreferredSize(new Dimension(400, 400));
		setVisible(true);
	}
	
	public void init() {
		g = getGraphics();
	}
	
	public void loadDatabase(List<Game> games) {
		this.games = games;
		render();
	}
	
	public void render() {
		g.setColor(new Color(0x880088));
		g.fillRect(0, 0, 400, 400);
		
		for (Game game: games) {
			Node prev = root;
			
			for (String move: game.moves) {
				Node cur = new Node(move);
				prev.add(cur);
				prev = cur;
			}
		}
		
		Node r = root;
		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < r.children.size(); y++) {
				Node a = r.children.get(y);
				
				System.out.println(a.move + " " + a.count);
				g.setColor(new Color(0xFFFFFF));
				g.fillOval(x * 32, 32 * y, 32, 32);
				g.setColor(new Color(0x000000));
				g.drawString(a.move, x * 32 + 8, 32*y + 16);
			}
			r = r.children.get(0);
		}
	}
}
