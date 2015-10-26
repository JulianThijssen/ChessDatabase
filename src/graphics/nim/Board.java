package graphics.nim;

public class Board {
	public Piece[][] pieces = new Piece[8][8];
	
	public Board() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				pieces[x][y] = null;
			}
		}
		
		// Place pawns
		for (int x = 0; x < 8; x++) {
			pieces[x][1] = new Piece(PieceColor.WHITE, Type.PAWN);
			pieces[x][6] = new Piece(PieceColor.BLACK, Type.PAWN);
		}
		
		// Place rooks
		pieces[0][0] = new Piece(PieceColor.WHITE, Type.ROOK);
		pieces[7][0] = new Piece(PieceColor.WHITE, Type.ROOK);
		pieces[0][7] = new Piece(PieceColor.BLACK, Type.ROOK);
		pieces[7][7] = new Piece(PieceColor.BLACK, Type.ROOK);
		
		// Place knights
		pieces[1][0] = new Piece(PieceColor.WHITE, Type.KNIGHT);
		pieces[6][0] = new Piece(PieceColor.WHITE, Type.KNIGHT);
		pieces[1][7] = new Piece(PieceColor.BLACK, Type.KNIGHT);
		pieces[6][7] = new Piece(PieceColor.BLACK, Type.KNIGHT);
		
		// Place bishops
		pieces[2][0] = new Piece(PieceColor.WHITE, Type.BISHOP);
		pieces[5][0] = new Piece(PieceColor.WHITE, Type.BISHOP);
		pieces[2][7] = new Piece(PieceColor.BLACK, Type.BISHOP);
		pieces[5][7] = new Piece(PieceColor.BLACK, Type.BISHOP);
		
		// Place queens
		pieces[3][0] = new Piece(PieceColor.WHITE, Type.QUEEN);
		pieces[3][7] = new Piece(PieceColor.BLACK, Type.QUEEN);
		
		// Place kings
		pieces[4][0] = new Piece(PieceColor.WHITE, Type.KING);
		pieces[4][7] = new Piece(PieceColor.BLACK, Type.KING);
	}
	
	public void move(boolean white, String move) {
		//System.out.println(move);
		if (Character.isLowerCase(move.charAt(0))) {
			// Pawn move
			if (move.length() == 2) {
				int file = getFile(move.charAt(0));
				int rank = Character.getNumericValue(move.charAt(1)) - 1;

				try {
					if (white) {
						Piece p1 = pieces[file][rank-1];
						if (p1 != null && p1.type == Type.PAWN) {
							pieces[file][rank] = p1;
							pieces[file][rank-1] = null;
							return;
						}
						Piece p2 = pieces[file][rank-2];
						if (p2 != null && p2.type == Type.PAWN) {
							pieces[file][rank] = p2;
							pieces[file][rank-2] = null;
							return;
						}
					}
					if (!white) {
						Piece p1 = pieces[file][rank+1];
						if (p1 != null && p1.type == Type.PAWN) {
							pieces[file][rank] = p1;
							pieces[file][rank+1] = null;
							return;
						}
						Piece p2 = pieces[file][rank+2];
						if (p2 != null && p2.type == Type.PAWN) {
							pieces[file][rank] = p2;
							pieces[file][rank+2] = null;
							return;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			}
		}
		if (Character.isUpperCase(move.charAt(0))) {
			if (move.startsWith("B")) {
				
			}
		}
	}
	
	public int getFile(char c) {
		switch (c) {
		case 'a': return 0;
		case 'b': return 1;
		case 'c': return 2;
		case 'd': return 3;
		case 'e': return 4;
		case 'f': return 5;
		case 'g': return 6;
		case 'h': return 7;
		}
		return -1;
	}
}
