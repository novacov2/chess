package Game;

import java.util.Scanner;

import Pieces.Bishop;
import Pieces.King;
import Pieces.Knight;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import gui.GUI;

public class Game {

	public static void main(String[] args) {
		ChessBoard standardBoard = new ChessBoard(BoardType.STANDARD);
		GUI table = new GUI(standardBoard);
	}
	
/* For Testing Purposes
	private static void printBoard(Piece[][] chessboard) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (chessboard[i][j] == null) {
					System.out.print("N ");
				}
				else{ 
					System.out.print((chessboard[i][j].getPlayer().color == Color.BLACK ? "B" : "W" )+ " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	*/
}
