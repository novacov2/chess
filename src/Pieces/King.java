package Pieces;

import Game.ChessBoard;
import Game.Player;

public class King extends Piece {	
	public King(int x, int y, Player player) {
		super(x, y, player, Type.KING);
	}

	public Piece deepCopy() {
		return new King(this.x, this.y, this.getPlayer());
	}
	
	/*
	 * (non-Javadoc)
	 * @see Pieces.Piece#availableMoves(Game.ChessBoard)
	 */
	public boolean[][] availableMoves(ChessBoard chess_board) {
		boolean[][] moves = new boolean[chess_board.SIZE][chess_board.SIZE];
		Piece[][] board = chess_board.getBoard();
		
		/*
		 * @TODO: Make sure king can't move into check?
		 */
		
		//Check if king can move up
		checkLocationHelper(chess_board, moves, this.x, this.y-1);
		
		//Check if king can move down
		checkLocationHelper(chess_board, moves, this.x, this.y+1);
		
		//Check if king can move right
		checkLocationHelper(chess_board, moves, this.x+1, this.y);
		
		//Check if can can move left
		checkLocationHelper(chess_board, moves, this.x-1, this.y);
		
		// Check Diag up right
		checkLocationHelper(chess_board, moves, this.x+1, this.y-1);
		
		// Check Diag up left
		checkLocationHelper(chess_board, moves, this.x-1, this.y-1);
		
		// Check Diag down left
		checkLocationHelper(chess_board, moves, this.x-1, this.y+1);
		
		// Check Diag down right
		checkLocationHelper(chess_board, moves, this.x+1, this.y+1);
		
		return moves;
	}
}
