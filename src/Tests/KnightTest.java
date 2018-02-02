package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.ChessBoard;
import Game.Color;
import Game.Player;
import Pieces.Bishop;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Knight;

public class KnightTest extends PiecesTest {

	@Test
	public void KnightCanCaptureAndCantCaptureOwnPiece() {
		Piece[][] test_board = {
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,new Knight(4,4,new Player(Color.WHITE)), null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, new Pawn(3,6,new Player(Color.BLACK)),null, new Knight(5,6,new Player(Color.WHITE)), null, null},
				{null, null, null, null,null, null, null, null}
		};
		
		ChessBoard chessboard = new ChessBoard(test_board, new Player(Color.WHITE),
				new Player(Color.WHITE), new Player(Color.BLACK));
		boolean[][] knightMoves = test_board[4][4].availableMoves(chessboard);
				
		boolean[][] expectedMoves = {
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, true, false, true, false, false},
				{false, false, true, false, false, false, true, false},
				{false, false, false, false, false, false, false, false},
				{false, false, true, false, false, false, true, false},
				{false, false, false, true, false, false, false, false},
				{false, false, false, false, false, false, false, false}
		};
		
		checkMoves(knightMoves, expectedMoves);
	}
}
