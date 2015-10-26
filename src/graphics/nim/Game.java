package graphics.nim;
import java.util.ArrayList;
import java.util.List;


public class Game {
	public String white = "NoName";
	public String black = "NoName";
	
	public boolean whiteWon = false;
	
	public int whiteElo = 0;
	public int blackElo = 0;
	public String timeControl = "Unknown";
	public Termination termination = Termination.UNKNOWN;
	
	public List<String> moves = new ArrayList<String>();
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Game between %s (%d) and %s (%d)\n", white, whiteElo, black, blackElo));
		if (whiteWon) {
			sb.append("Winner: " + white + "\n");
		} else {
			sb.append("Winner: " + black + "\n");
		}
		
		for (String move: moves) {
			sb.append(move + "\n");
		}
		
		return sb.toString();
	}
}
