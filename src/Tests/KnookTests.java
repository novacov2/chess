package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.ChessBoard;
import Game.Color;
import Game.Player;
import Pieces.Knight;
import Pieces.Knook;
import Pieces.Pawn;
import Pieces.Piece;

public class KnookTests extends PiecesTest {

	@Test
	public void KnookCanCaptureAndCantCaptureOwnPiece() {
		Piece[][] test_board = {
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,new Knook(4,4,new Player(Color.WHITE)), null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, new Pawn(3,6,new Player(Color.BLACK)),null, new Knight(5,6,new Player(Color.WHITE)), null, null},
				{null, null, null, null,null, null, null, null}
		};
		
		ChessBoard chessboard = new ChessBoard(test_board, new Player(Color.WHITE),
				new Player(Color.WHITE), new Player(Color.BLACK));
		boolean[][] knookMoves = test_board[4][4].availableMoves(chessboard);
				
		boolean[][] expectedMoves = {
				{false, false, false, false, true, false, false, false},
				{false, false, false, false, true, false, false, false},
				{false, false, false, true, true, true, false, false},
				{false, false, true, false, true, false, true, false},
				{true, true, true, true, false, true, true, true},
				{false, false, true, false, true, false, true, false},
				{false, false, false, true, true, false, false, false},
				{false, false, false, false, true, false, false, false}
		};
		
		checkMoves(knookMoves, expectedMoves);
	}

}
