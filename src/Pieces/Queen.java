package Pieces;

import Game.ChessBoard;
import Game.Player;

public class Queen extends Piece {	
	public Queen(int x, int y, Player player) {
		super(x, y, player, Type.QUEEN);
	}
	
	public Piece deepCopy() {
		return new Queen(this.x, this.y, this.getPlayer());
	}

	/*
	 * (non-Javadoc)
	 * @see Pieces.Piece#availableMoves(Game.ChessBoard)
	 */
	public boolean[][] availableMoves(ChessBoard board) {
		boolean[][] rook_moves = availableMovesRook(board);
		boolean[][] bishop_moves = availableMovesBishop(board);
		boolean[][] queen_moves = new boolean[board.SIZE][board.SIZE];
		
		for (int i = 0; i < board.SIZE; i++) {
			for (int j = 0; j < board.SIZE; j++) {
				queen_moves[i][j] = rook_moves[i][j] || bishop_moves[i][j];
			}
		}
		return queen_moves;
	}

}
