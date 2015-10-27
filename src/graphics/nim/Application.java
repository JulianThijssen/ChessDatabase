package graphics.nim;

import graphics.nim.tree.Tree;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

public class Application {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setTitle("Chess database");
		frame.setResizable(false);
		frame.setFocusable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ChessScreen screen = new ChessScreen();
		TreeView treeView = new TreeView();
		//frame.add(screen);
		frame.add(treeView);
		frame.pack();
		frame.setVisible(true);
		
		screen.init();
		treeView.init();
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Game> games;
		try {
			games = Parser.loadFile("Database.pgn");
			
			//screen.loadGame(games.get(0));
			treeView.loadDatabase(games);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
