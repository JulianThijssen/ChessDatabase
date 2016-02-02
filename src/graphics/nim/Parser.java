package graphics.nim;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
	public static List<Game> loadFile(String filepath) throws FileNotFoundException, IOException {
		File file = new File(filepath);
		BufferedReader in = new BufferedReader(new FileReader(file));
		
		List<Game> games = new ArrayList<Game>();
		Game game = null;
		String line = null;
		boolean moves = false;
		
		while ((line = in.readLine()) != null) {
			if (line.startsWith("[Event")) {
				game = new Game();
			}
			if (line.startsWith("[White ")) {
				String[] tokens = line.split("\"");
				game.white = tokens[1];
			}
			if (line.startsWith("[Black ")) {
				String[] tokens = line.split("\"");
				game.black = tokens[1];
			}
			if (line.startsWith("[Result ")) {
				String[] tokens = line.split("\"");
				game.whiteWon = "1-0".equals(tokens[1]) ? true : false;
			}
			if (line.startsWith("[WhiteElo ")) {
				String[] tokens = line.split("\"");
				try {
					game.whiteElo = Integer.parseInt(tokens[1]);
				} catch (NumberFormatException e) {
					game.whiteElo = 0;
				}
			}
			if (line.startsWith("[BlackElo ")) {
				String[] tokens = line.split("\"");
				
				try {
					game.blackElo = Integer.parseInt(tokens[1]);
				} catch (NumberFormatException e) {
					game.blackElo = 0;
				}
			}
			if (line.startsWith("[TimeControl ")) {
				String[] tokens = line.split("\"");
				game.timeControl = tokens[1];
			}
			if (line.startsWith("[Termination")) {
				if (line.contains("abandoned") || line.contains("Abandoned")) {
					game.termination = Termination.ABANDONED;
				}
			}
			if (game.termination != Termination.ABANDONED) {
				if (line.startsWith("1.")) {
					moves = true;
				}
				if (moves) {
					List<String> lineMoves = extractMoves(line);
					
					for (String move: lineMoves) {
						game.moves.add(move);
						
						if ("1-0".equals(move) || "0-1".equals(move) || "1/2-1/2".equals(move)) {
							moves = false;
							games.add(game);
						}
					}
				}
			}
		}
		
		in.close();
		
		for (Game g: games) {
			//System.out.println(g.white + " " + g.black + " " + g.moves.get(0));
		}
		
		return games;
	}

	public static List<String> extractMoves(String line) {
		line = line.replaceAll("\\d+\\.", "");
		String[] tokens = line.split(" ");
		List<String> moves = new ArrayList<String>();
		
		for (String token: tokens) {
			if (token.isEmpty() || token.length() < 2) {
				continue;
			}

			String[] parts = token.split("\\.");
			if (parts.length > 1) {
				moves.add(parts[1]);
			} else {
				moves.add(parts[0]);
			}
		}
		
		return moves;
	}
	
	public static void main(String[] args) {
		try {
			loadFile("lichess_Nimthora_2016-02-02.pgn");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
