package Tests;

import org.junit.Test;

import Game.ChessBoard;
import Game.Color;
import Game.Player;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Rook;

public class RookTests extends PiecesTest{

	/*
	 * Tests that available moves stops when able to capture a piece
	 */
	@Test
	public void ValidMovesOpponentPiecesInWay() {
		Piece[][] test_board = {
				{null, null, null, null,null, null, null, null},
				{new Pawn(0,1,new Player(Color.BLACK)), null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{new Rook(0,7,new Player(Color.WHITE)), null, null, null,null, null, null, null}
		};
		
		ChessBoard chessboard = new ChessBoard(test_board, new Player(Color.WHITE),
				new Player(Color.WHITE), new Player(Color.BLACK));
		boolean[][] rookMoves = test_board[7][0].availableMoves(chessboard);
				
		boolean[][] expectedMoves = {
				{false, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false},
				{true, false, false, false, false, false, false, false},
				{false, true, true, true, true, true, true, true}
		};
		
		checkMoves(rookMoves, expectedMoves);
	}
	
	
	/*
	 * Tests that available moves stops when able to capture a piece
	 */
	@Test
	public void ValidMovesOwnPiecesInWay() {
		Piece[][] test_board = {
				{null, null, null, null,null, null, null, null},
				{null, null, null, new Pawn(3,1,new Player(Color.WHITE)),null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, new Rook(3,4,new Player(Color.WHITE)),null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null}
		};
		
		ChessBoard chessboard = new ChessBoard(test_board, new Player(Color.WHITE),
				new Player(Color.WHITE), new Player(Color.BLACK));
		boolean[][] rookMoves = test_board[4][3].availableMoves(chessboard);
				
		boolean[][] expectedMoves = {
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, true, false, false, false, false},
				{false, false, false, true, false, false, false, false},
				{true, true, true, false, true, true, true, true},
				{false, false, false, true, false, false, false, false},
				{false, false, false, true, false, false, false, false},
				{false, false, false, true, false, false, false, false}
		};
		
		checkMoves(rookMoves, expectedMoves);
	}

}
