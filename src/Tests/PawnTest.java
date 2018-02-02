package Tests;

import org.junit.Test;

import Game.ChessBoard;
import Game.Color;
import Game.Player;
import Pieces.Bishop;
import Pieces.Pawn;
import Pieces.Piece;

public class PawnTest extends PiecesTest {

	@Test
	public void PawnCanMoveUpOrCapture() {
		Piece[][] test_board = {
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,new Bishop(4,4,new Player(Color.WHITE)), null, null, null},
				{null, null, null, new Pawn(3,5,new Player(Color.BLACK)),null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null}
		};
		
		ChessBoard chessboard = new ChessBoard(test_board, new Player(Color.BLACK),
				new Player(Color.WHITE), new Player(Color.BLACK));
		boolean[][] pawnMoves = test_board[5][3].availableMoves(chessboard);
				
		boolean[][] expectedMoves = {
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, true, true, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false}
		};
		
		checkMoves(pawnMoves, expectedMoves);
	}

}
