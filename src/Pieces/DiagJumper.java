package Pieces;

import Game.ChessBoard;
import Game.Player;

public class DiagJumper extends Piece {

	public DiagJumper(int x, int y, Player player) {
		super(x, y, player, Type.DIAGJUMPER);
	}

	public Piece deepCopy() {
		return new DiagJumper(this.x, this.y, this.getPlayer());
	}
	
	/* 
	 * (non-Javadoc)
	 * @see Pieces.Piece#availableMoves(Game.ChessBoard)
	 */
	public boolean[][] availableMoves(ChessBoard chess_board) {
		boolean[][] moves = new boolean[chess_board.SIZE][chess_board.SIZE];
		Piece[][] board = chess_board.getBoard();	
		// Diag up right
		if (this.y-2 >= 0 && this.x+2 < chess_board.SIZE)
		{
			checkLocation(moves, board[this.y-2][this.x+2], this.x+2, this.y-2);
		}
		
		// Diag up left
		if (this.y-2 >= 0 && this.x-2 >= 0) {
			checkLocation(moves, board[this.y-2][this.x-2], this.x-2, this.y-2);
		}
		
		// Diag down left
		if (this.y+2 < chess_board.SIZE && this.x-2 >= 0) {
			checkLocation(moves, board[this.y+2][this.x-2], this.x-2, this.y+2);
		}
		
		// Diag down right
		if (this.y+2 < chess_board.SIZE && this.x+2 < chess_board.SIZE) {
			checkLocation(moves, board[this.y+2][this.x+2], this.x+2, this.y+2);
		}
		
		return moves;
	}

}
