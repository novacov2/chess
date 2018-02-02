package Pieces;

import Game.ChessBoard;
import Game.Player;

public class Bishop extends Piece {
	public Bishop(int x, int y, Player player) {
		super(x, y, player, Type.BISHOP);
	}

	
	public Piece deepCopy() {
		return new Bishop(this.x, this.y, this.getPlayer());
	}
	
	/*
	 * (non-Javadoc)
	 * @see Pieces.Piece#availableMoves(int, int)
	 */
	public boolean[][] availableMoves(ChessBoard chess_board) {
		return availableMovesBishop(chess_board);
	}
	
	

	
}
