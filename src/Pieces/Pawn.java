package Pieces;

import Game.ChessBoard;
import Game.Player;

public class Pawn extends Piece {	
	public Pawn(int x, int y, Player player) {
		super(x, y, player, Type.PAWN);
	}

	public Piece deepCopy() {
		return new Pawn(this.x, this.y, this.getPlayer());
	}
	
	/*
	 * (non-Javadoc)
	 * @see Pieces.Piece#availableMoves(Game.ChessBoard)
	 */
	public boolean[][] availableMoves(ChessBoard chess_board) {
		boolean[][] moves = new boolean[chess_board.SIZE][chess_board.SIZE];
		Piece[][] board = chess_board.getBoard();
		
		//Check if pawn can move up
		if (this.y-1 >= 0 && board[this.y-1][this.x] == null)
			moves[this.y-1][this.x] = true;
			
		// Check if pawn hasn't moved yet and if no pieces are in the way 
		// then can move 2 spots up
		if (this.y == 6 && board[this.y-1][this.x] == null 
				&& board[this.y-2][this.x] == null) {
			moves[this.y-2][this.x] = true;
		}
		pawnCapture(chess_board, moves);
		
		return moves;
	}
}
