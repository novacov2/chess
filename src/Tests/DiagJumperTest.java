package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.ChessBoard;
import Game.Color;
import Game.Player;
import Pieces.DiagJumper;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;

public class DiagJumperTest extends PiecesTest {

	@Test
	public void DiagJumperCanCaptureAndCantCaptureOwnPiece() {
		Piece[][] test_board = {
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, new Pawn(2,2,new Player(Color.WHITE)), null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,new DiagJumper(4,4,new Player(Color.WHITE)), null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, new Pawn(2,6,new Player(Color.BLACK)), null,null, null, null, null},
				{null, null, null, null,null, null, null, null}
		};
		
		ChessBoard chessboard = new ChessBoard(test_board, new Player(Color.WHITE),
				new Player(Color.WHITE), new Player(Color.BLACK));
		boolean[][] diagJumperMoves = test_board[4][4].availableMoves(chessboard);
				
		boolean[][] expectedMoves = {
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, true, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, true, false, false, false, true, false},
				{false, false, false, false, false, false, false, false}
		};
		
		checkMoves(diagJumperMoves, expectedMoves);
	}


}
