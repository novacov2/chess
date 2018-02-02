package Pieces;

import Game.ChessBoard;
import Game.Player;

public class Knook extends Piece {

	public Knook(int x, int y, Player player) {
		super(x, y, player, Type.KNOOK);
	}

	public Piece deepCopy() {
		return new Knook(this.x, this.y, this.getPlayer());
	}
	
	/*
	 * (non-Javadoc)
	 * @see Pieces.Piece#availableMoves(Game.ChessBoard)
	 */
	public boolean[][] availableMoves(ChessBoard board) {
		boolean[][] rookMoves = availableMovesRook(board);
		boolean[][] knightMoves = availableMovesKnight(board);
		boolean[][] knookMoves = new boolean[board.SIZE][board.SIZE];
		
		for (int i = 0; i < board.SIZE; i++) {
			for (int j = 0; j < board.SIZE; j++) {
				knookMoves[i][j] = rookMoves[i][j] || knightMoves[i][j];
			}
		}
		
		return knookMoves;
	}

}
