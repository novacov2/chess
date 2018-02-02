package Pieces;

import Game.ChessBoard;
import Game.Player;

public class Rook extends Piece {	
	public Rook(int x, int y, Player player) {
		super(x, y, player, Type.ROOK);
	}

	public Piece deepCopy() {
		return new Rook(this.x, this.y, this.getPlayer());
	}
	
	/*
	 * (non-Javadoc)
	 * @see Pieces.Piece#availableMoves(int, int)
	 */
	public boolean[][] availableMoves(ChessBoard chess_board) {
		return availableMovesRook(chess_board);
	}
	
	
}
