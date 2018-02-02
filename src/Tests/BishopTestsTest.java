package Tests;

import org.junit.Test;

import Game.ChessBoard;
import Game.Color;
import Game.Player;
import Pieces.Bishop;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Rook;

public class BishopTestsTest extends PiecesTest{

	@Test
	public void ValidMovesOpponentPiecesInWay() {
		Piece[][] test_board = {
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, new Pawn(2,5,new Player(Color.BLACK)), null,null, null, null, null},
				{null, new Bishop(1,6,new Player(Color.WHITE)), null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null}
		};
		
		ChessBoard chessboard = new ChessBoard(test_board, new Player(Color.WHITE),
				new Player(Color.WHITE), new Player(Color.BLACK));
		boolean[][] bishopMoves = test_board[6][1].availableMoves(chessboard);
				
		boolean[][] expectedMoves = {
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{true, false, true, false, false, false, false, false},
				{false, false, false, false, false, false, false, false},
				{true, false, true, false, false, false, false, false}
		};
		
		checkMoves(bishopMoves, expectedMoves);
	}

}
