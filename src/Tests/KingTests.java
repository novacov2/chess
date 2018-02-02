package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.ChessBoard;
import Game.Color;
import Game.Player;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Pawn;
import Pieces.Piece;

public class KingTests extends PiecesTest {

	@Test
	public void KingCorrectAvailableMoves() {

		Piece[][] test_board = {
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, new Pawn(3,4,new Player(Color.WHITE)),null, null, null, null},
				{null, null, null, new King(3,5,new Player(Color.BLACK)),null, null, null, null},
				{null, null, null, new Pawn(3,6,new Player(Color.BLACK)),null, null, null, null},
				{null, null, null, null,null, null, null, null}
		};
		
		ChessBoard chessboard = new ChessBoard(test_board, new Player(Color.BLACK),
				new Player(Color.WHITE), new Player(Color.BLACK));
		
		boolean[][] kingMoves = test_board[5][3].availableMoves(chessboard);
				
		boolean[][] expectedMoves = {
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, true, true, true, false, false, false},
				{false, false, true, false, true, false, false, false},
				{false, false, true, false, true, false, false, false},
				{false, false, false, false, false, false, false, false}
		};
		
		checkMoves(kingMoves, expectedMoves);
	}
	
	

}
