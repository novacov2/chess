package Pieces;

import Game.ChessBoard;
import Game.Player;

public class Knight extends Piece {

	public Knight(int x, int y, Player player) {
		super(x, y, player, Type.KNIGHT);
	}

	public Piece deepCopy() {
		return new Knight(this.x, this.y, this.getPlayer());
	}
	
	/*
	 * (non-Javadoc)
	 * @see Pieces.Piece#availableMoves(Game.ChessBoard)
	 */
	public boolean[][] availableMoves(ChessBoard chess_board) {
		return availableMovesKnight(chess_board);
	}

}
