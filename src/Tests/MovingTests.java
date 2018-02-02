package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Game.ChessBoard;
import Game.Color;
import Game.Player;
import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;

public class MovingTests extends PiecesTest {

	@Test
	public void PieceCantMoveOutOfBounds() {
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
		
		ChessBoard chessboard = new ChessBoard(test_board, new Player(Color.WHITE),
				new Player(Color.WHITE), new Player(Color.BLACK));
		assertEquals(false, chessboard.move(4, 4, 8, 8));
	}
	
	@Test
	public void PieceCapturesAndBoardUpdates() {
		Piece[][] test_board = {
				{null, null, null, new King(3,0,new Player(Color.BLACK)),null, null, null, null},
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
		assertEquals(true, chessboard.move(3, 5, 4, 4));
		
		Piece[][] expectedBoard = {
				{null, null, null,new King(3,0,new Player(Color.BLACK)),null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,new Pawn(4,4,new Player(Color.BLACK)), null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null}
		};
		
		checkBoard(expectedBoard, chessboard.getBoard());
	}
	
	@Test
	public void CantMoveIntoCheck() {
		Piece[][] test_board = {
				{null, null, null, new King(3,0,new Player(Color.BLACK)),null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,new Bishop(4,4,new Player(Color.WHITE)), null, null, null},
				{null, null, null, new Pawn(3,5,new Player(Color.BLACK)),null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, new Queen(3,7,new Player(Color.WHITE)),null, null, null, null}
		};
		
		ChessBoard chessboard = new ChessBoard(test_board, new Player(Color.BLACK),
				new Player(Color.WHITE), new Player(Color.BLACK));
		
		// Shoudn't be able to make move because queen puts king in check
		assertEquals(false, chessboard.move(3, 5, 4, 4));
		
		// Board should be exact same
		Piece[][] expectedBoard = {
				{null, null, null, new King(3,0,new Player(Color.BLACK)),null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,new Bishop(4,4,new Player(Color.WHITE)), null, null, null},
				{null, null, null, new Pawn(3,5,new Player(Color.BLACK)),null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, new Queen(3,7,new Player(Color.WHITE)),null, null, null, null}
		};
		
		checkBoard(expectedBoard, chessboard.getBoard());
	}
	
	/*
	 * Desc. Check if board flips correctly
	 */
	@Test
	public void FlipBoard() {
		Player whitePlayer = new Player(Color.WHITE);
		Player blackPlayer = new Player(Color.BLACK);
		
		Piece[][] test_board = {				
				{new Rook(0,0,blackPlayer), new Knight(1,0,blackPlayer), new Bishop(2,0,blackPlayer), new Queen(3,0,blackPlayer),new King(4,0,blackPlayer), new Bishop(5,0,blackPlayer), new Knight(6,0,blackPlayer), new Rook(7,0,blackPlayer)},
				{new Pawn(0,1,blackPlayer), new Pawn(1,1,blackPlayer), new Pawn(2,1,blackPlayer), new Pawn(3,1,blackPlayer),new Pawn(4,1,blackPlayer), new Pawn(5,1,blackPlayer), new Pawn(6,1,blackPlayer), new Pawn(7,1,blackPlayer)},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{new Pawn(0,6,blackPlayer), new Pawn(1,6,blackPlayer), new Pawn(2,6,blackPlayer), new Pawn(3,6,blackPlayer),new Pawn(4,6,blackPlayer), new Pawn(5,6,blackPlayer), new Pawn(6,6,blackPlayer), new Pawn(7,6,blackPlayer)},
				{new Rook(0,7,blackPlayer), new Knight(1,7,blackPlayer), new Bishop(2,7,blackPlayer), new Queen(3,7,blackPlayer),new King(4,7,blackPlayer), new Bishop(5,7,blackPlayer), new Knight(6,7,blackPlayer), new Rook(7,7,blackPlayer)}
		};
		
		ChessBoard chessboard = new ChessBoard(test_board, null,
				new Player(Color.WHITE), new Player(Color.BLACK));
		
		// Shoud be able to do this move and get king into checkmate
		chessboard.flipBoard();
		
		Piece[][] expectedBoard = {
				{new Rook(0,0,blackPlayer), new Knight(1,0,blackPlayer), new Bishop(2,0,blackPlayer), new Queen(3,0,blackPlayer),new King(4,0,blackPlayer), new Bishop(5,0,blackPlayer), new Knight(6,0,blackPlayer), new Rook(7,0,blackPlayer)},
				{new Pawn(0,1,blackPlayer), new Pawn(1,1,blackPlayer), new Pawn(2,1,blackPlayer), new Pawn(3,1,blackPlayer),new Pawn(4,1,blackPlayer), new Pawn(5,1,blackPlayer), new Pawn(6,1,blackPlayer), new Pawn(7,1,blackPlayer)},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{new Pawn(0,6,blackPlayer), new Pawn(1,6,blackPlayer), new Pawn(2,6,blackPlayer), new Pawn(3,6,blackPlayer),new Pawn(4,6,blackPlayer), new Pawn(5,6,blackPlayer), new Pawn(6,6,blackPlayer), new Pawn(7,6,blackPlayer)},
				{new Rook(0,7,blackPlayer), new Knight(1,7,blackPlayer), new Bishop(2,7,blackPlayer), new Queen(3,7,blackPlayer),new King(4,7,blackPlayer), new Bishop(5,7,blackPlayer), new Knight(6,7,blackPlayer), new Rook(7,7,blackPlayer)}
		};
		
		checkBoard(expectedBoard, chessboard.getBoard());
	}
	
	
	
	private void checkBoard(Piece[][] expectedBoard, Piece[][] outputBoard) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (expectedBoard[i][j] != null) {
					assertEquals(expectedBoard[i][j].getPlayer().color, outputBoard[i][j].getPlayer().color);
					assertEquals(expectedBoard[i][j].getType(), outputBoard[i][j].getType());
					
					// Check if position of piece updates
					assertEquals(expectedBoard[i][j].getX(), outputBoard[i][j].getX());
					assertEquals(expectedBoard[i][j].getY(), outputBoard[i][j].getY());
				}
				else {
					assertEquals(expectedBoard[i][j], outputBoard[i][j]);
				}
			}
		}
	}

}
