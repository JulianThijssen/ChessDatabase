package graphics.nim;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class ChessScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public Game game = null;
	public Board board = new Board();
	
	public Graphics g = null;
	
	public ChessScreen() {
		setPreferredSize(new Dimension(400, 400));
		setVisible(true);
	}
	
	public void init() {
		g = getGraphics();
	}
	
	public void loadGame(Game game) {
		this.game = game;
		render();
		board.move(true, game.moves.get(0));
		render();
		board.move(false, game.moves.get(1));
		render();
		board.move(true, game.moves.get(2));
		render();
	}
	
	public void render() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (((x + y) % 2) == 0) {
					g.setColor(new Color(0xDDDDBB));
					g.fillRect(x * 50, y * 50, 50, 50);
				}
				if (((x + y) % 2) == 1) {
					g.setColor(new Color(0xDDBB00));
					g.fillRect(x * 50, y * 50, 50, 50);
				}
			}
		}
		
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Piece piece = board.pieces[x][y];
				
				if (piece != null) {
					int iy = 7 - y;
					
					if (piece.color == PieceColor.WHITE) {
						g.setColor(new Color(0xFFFFFF));
						g.fillOval(x * 50, iy * 50, 50, 50);
					}
					if (piece.color == PieceColor.BLACK) {
						g.setColor(new Color(0x000000));
						g.fillOval(x * 50, iy * 50, 50, 50);
					}
				}
			}
		}
	}
}
