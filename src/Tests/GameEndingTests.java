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

public class GameEndingTests {

	@Test
	public void CheckMoveIntoCheckMate() {
		Piece[][] test_board = {
				{new Rook(0,0,new Player(Color.BLACK)), new Knight(1,0,new Player(Color.BLACK)), new Bishop(2,0,new Player(Color.BLACK)), new Queen(3,0,new Player(Color.BLACK)),new King(4,0,new Player(Color.BLACK)), new Bishop(5,0,new Player(Color.BLACK)), new Knight(6,0,new Player(Color.BLACK)), new Rook(7,0,new Player(Color.BLACK))},
				{new Pawn(0,1,new Player(Color.BLACK)), new Pawn(1,1,new Player(Color.BLACK)), new Pawn(2,1,new Player(Color.BLACK)), new Pawn(3,1,new Player(Color.BLACK)),new Pawn(4,1,new Player(Color.BLACK)), null, null, new Pawn(7,1,new Player(Color.BLACK))},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{new Pawn(0,6,new Player(Color.WHITE)), new Pawn(1,6,new Player(Color.WHITE)), new Pawn(2,6,new Player(Color.WHITE)), new Pawn(3,6,new Player(Color.WHITE)),null, new Pawn(5,6,new Player(Color.WHITE)), new Pawn(6,6,new Player(Color.WHITE)), new Pawn(7,6,new Player(Color.WHITE))},
				{new Rook(0,7,new Player(Color.WHITE)), new Knight(1,7,new Player(Color.WHITE)), new Bishop(2,7,new Player(Color.WHITE)), new Queen(3,7,new Player(Color.WHITE)),new King(4,7,new Player(Color.WHITE)), new Bishop(5,7,new Player(Color.WHITE)), new Knight(6,7,new Player(Color.WHITE)), new Rook(7,7,new Player(Color.WHITE))}
		};
		
		ChessBoard chessboard = new ChessBoard(test_board, new Player(Color.WHITE),
				new Player(Color.WHITE), new Player(Color.BLACK));
		
		// Shoud be able to do this move and get king into checkmate
		assertEquals(true, chessboard.move(3, 7, 7, 3));
		assertEquals(true, chessboard.checkForCheck(new Player(Color.BLACK)));
		assertEquals(true,chessboard.checkAnyPieceCanMove(new Player(Color.BLACK)));
	}
	
	@Test
	public void Stalemate() {
		Piece[][] test_board = {
				{new King(0,0,new Player(Color.BLACK)), null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, new Queen(1,5,new Player(Color.BLACK)), null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{new King(0,7,new Player(Color.WHITE)), null, null, null,null, null, null, null}
		};
		
		ChessBoard chessboard = new ChessBoard(test_board, null,
				new Player(Color.WHITE), new Player(Color.BLACK));
		
		assertEquals(true,chessboard.checkForStaleMate(new Player(Color.WHITE)));
	}
	
	@Test
	public void NoStalemate() {
		Piece[][] test_board = {
				{new King(0,0,new Player(Color.BLACK)), null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, new Queen(2,5,new Player(Color.BLACK)), null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{new King(0,7,new Player(Color.WHITE)), null, null, null,null, null, null, null}
		};
		
		ChessBoard chessboard = new ChessBoard(test_board, null,
				new Player(Color.WHITE), new Player(Color.BLACK));
		
		assertEquals(false,chessboard.checkForStaleMate(new Player(Color.WHITE)));
	}
	
	@Test
	public void StalemateTwoKings() {
		Piece[][] test_board = {
				{new King(0,0,new Player(Color.BLACK)), null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{new King(0,7,new Player(Color.WHITE)), null, null, null,null, null, null, null}
		};
		
		ChessBoard chessboard = new ChessBoard(test_board, null,
				new Player(Color.WHITE), new Player(Color.BLACK));
		
		assertEquals(true,chessboard.checkForStaleMate(new Player(Color.WHITE)));
	}
	
	public void printMoves(boolean[][] moves) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print((moves[i][j] ? "T" : "F" )+ " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
}
