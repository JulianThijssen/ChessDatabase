package graphics.nim;

import graphics.nim.tree.Node;
import graphics.nim.tree.Tree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JPanel;

public class TreeView extends JPanel implements MouseListener, MouseMotionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	
	private Tree tree = new Tree();
	public List<Game> games = null;
	public Board board = new Board();
	
	int vx = 0;
	int vy = 0;
	
	int px = 0, py = 0;
	float dx = 0, dy = 0;
	
	Node root = new Node("root");
	String[] userMoves = {"e4", "d6", "d4", "Nf6", "Nc3", "g6", "Nf3", "Bg7"};
	
	public Graphics g = null;
	
	public TreeView() {
		setPreferredSize(new Dimension(600, 600));
		setFocusable(true);
		setVisible(true);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
	}
	
	public void init() {
		g = getGraphics();
	}
	
	public void loadDatabase(List<Game> games) {
		this.games = games;
		
		for (Game game: games) {
			tree.addGame(game);
		}
		selectNodes();
		render();
	}
	
	public void selectNodes() {
		Node rootCopy = tree.root;
		rootCopy.selected = true;
		
		for (String userMove: userMoves) {
			rootCopy = rootCopy.getChild(userMove);
			
			if (rootCopy == null) {
				return;
			}
			
			rootCopy.selected = true;
		}
	}
	
//	public void render() {
//		Node rootCopy = tree.root;
//		g.setColor(new Color(0x880088));
//		g.fillRect(0, 0, 600, 600);
//		
//		int i = 0;
//		
//		try {
//		do {
//			boolean found = false;
//			Node c = null;
//			
//			for (int y = 0; y < rootCopy.children.size(); y++) {
//				Node child = rootCopy.children.get(y);
//				
//				renderNode(child, i, y);
//				
//				if (child.selected) {
//					found = true;
//					c = child;
//				}
//			}
//			
//			if (found) {
//				rootCopy = c;
//				i++;
//			} else {
//				break;
//			}
//		} while (true);
//		} catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println("BOOP");
//		}
//	}
//	
	public void render() {
		g.setColor(new Color(0x888888));
		g.fillRect(0, 0, 600, 600);
		render(tree.root, 0, 0);
	}
	
	public void render(Node n, int x, int y) {
		if (n.selected) {
			for (int i = 0; i < n.children.size(); i++) {
				Node child = n.children.get(i);
				
				int cx = x + 64;
				int cy = y + i * 32;
				
				g.drawLine(x-vx+32, y-vy+16, cx - vx+0, cy - vy+16);
				
				render(child, cx, cy);
			}
		}
		
		renderNode(n, x, y);
	}
	
	public void renderNode(Node n, int x, int y) {
		g.setColor(new Color(0xFFFFFF));
		if (n.selected) {g.setColor(new Color(0xFF0000));}
		
		g.fillOval(x - vx, y - vy, 32, 32);
		g.setColor(new Color(0x000000));
		g.drawString(String.format("%s(%d)", n.move, n.count), x + 8 - vx, y + 16 - vy);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println("X: " + x + " Y: " + y);
		requestFocusInWindow();
		//selectNodes();
		//render();
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		dx = (e.getX() - px) / 10f;
		dy = (e.getY() - px) / 10f;
		vx += dx;
		vy += dy;
		px = e.getX();
		py = e.getY();
		render();
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			vy -= 10;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			vy += 10;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			vx -= 10;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			vx += 10;
		}
		render();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
