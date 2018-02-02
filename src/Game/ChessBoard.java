/**
 * 
 */
package Game;

import java.util.Vector;
import java.util.*;

import Pieces.Bishop;
import Pieces.DiagJumper;
import Pieces.King;
import Pieces.Knight;
import Pieces.Knook;
import Pieces.Pawn;
import Pieces.Piece;
import Pieces.Queen;
import Pieces.Rook;
import Pieces.Type;


/**
 * @author paulnovacovici
 *
 */
public class ChessBoard {	
	// Size of board
	public final static int SIZE = 8;
	
	// History
	private static Stack<ChessBoard> history;
	
	// board of abstract type piece
	private Piece[][] board;
	
	// Current Players Turn
	private Player currentTurn;
	
	// Players in game
	private Player blackPlayer;
	private Player whitePlayer;
	
	Vector<Piece> black = new Vector<Piece>();
	
	Vector<Piece> white = new Vector<Piece>();
	
	
	public ChessBoard() {}
	
	public ChessBoard(BoardType type) {
		this.blackPlayer = new Player(Color.BLACK);
		this.whitePlayer = new Player(Color.WHITE);
		this.currentTurn = whitePlayer;
		this.board = new Piece[SIZE][SIZE];
		this.history = new Stack();
		if (type == BoardType.STANDARD) {
			copyBoard(createStandardChessboard(this.whitePlayer, this.blackPlayer));
		}
		else {
			copyBoard(createCustomChessBoard(this.whitePlayer, this.blackPlayer));
		}
	}


	/**
	 * create copy of ChessBoard object
	 * @param copy
	 */
	public ChessBoard(ChessBoard copy) {
		this.board = new Piece[SIZE][SIZE];
		copyBoard(copy.getBoard());
		this.currentTurn = new Player(copy.currentTurn);
		this.blackPlayer = new Player(copy.blackPlayer);
		this.whitePlayer = new Player(copy.whitePlayer);
	}
		
	/**
	 * @param board 2d piece array used for chess board initialization 
	 */
	public ChessBoard(Piece[][] board, Player currentTurn, 
			Player whitePlayer, Player blackPlayer) {
		this.history = new Stack();
		this.currentTurn = currentTurn;
		this.board = new Piece[SIZE][SIZE];
		this.blackPlayer = blackPlayer;
		this.whitePlayer = whitePlayer;
		copyBoard(board);
	}
	
	/**
	 * create a deep copy of the board
	 * @param board
	 */
	private void copyBoard(Piece[][] board){
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (board[i][j] != null) {
					this.board[i][j] = board[i][j].deepCopy();
				}
				
				if (board[i][j] == null) {
					continue;
				}
				else if(this.board[i][j].getPlayer().color == Color.BLACK) {
					this.black.addElement(this.board[i][j]);
				}
				else {
					this.white.addElement(this.board[i][j]);
				}
			}
		}
	}
	
	public Stack getHistory() {
		return this.history;
	}
	
	/**
	 * Gets the player of whose turn it is
	 * @return
	 */
	public Player getCurrentTurn() {
		return this.currentTurn;
	}
	
	/**
	 * Used when undoing moves
	 * @param player new current turn
	 */
	public void setCurrentTurn(Player player) {
		this.currentTurn = player;
	}
	
	/**
	 * Creates standard chess board 
	 * @return ChessBoard object for a standard chess game
	 */
	public Piece[][] createStandardChessboard(Player whitePlayer, Player blackPlayer) {		
		Piece[][] start_board = {
				{new Rook(0,0,blackPlayer), new Knight(1,0,blackPlayer), new Bishop(2,0,blackPlayer), new Queen(3,0,blackPlayer),new King(4,0,blackPlayer), new Bishop(5,0,blackPlayer), new Knight(6,0,blackPlayer), new Rook(7,0,blackPlayer)},
				{new Pawn(0,1,blackPlayer), new Pawn(1,1,blackPlayer), new Pawn(2,1,blackPlayer), new Pawn(3,1,blackPlayer),new Pawn(4,1,blackPlayer), new Pawn(5,1,blackPlayer), new Pawn(6,1,blackPlayer), new Pawn(7,1,blackPlayer)},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{new Pawn(0,6,whitePlayer), new Pawn(1,6,whitePlayer), new Pawn(2,6,whitePlayer), new Pawn(3,6,whitePlayer),new Pawn(4,6,whitePlayer), new Pawn(5,6,whitePlayer), new Pawn(6,6,whitePlayer), new Pawn(7,6,whitePlayer)},
				{new Rook(0,7,whitePlayer), new Knight(1,7,whitePlayer), new Bishop(2,7,whitePlayer), new Queen(3,7,whitePlayer),new King(4,7,whitePlayer), new Bishop(5,7,whitePlayer), new Knight(6,7,whitePlayer), new Rook(7,7,whitePlayer)}
		};
		return start_board;
	}
	
	private Piece[][] createCustomChessBoard(Player whitePlayer2, Player blackPlayer2) {
		Piece[][] start_board = {
				{new Rook(0,0,blackPlayer), new Knight(1,0,blackPlayer), new DiagJumper(2,0,blackPlayer), new Queen(3,0,blackPlayer),new King(4,0,blackPlayer), new Bishop(5,0,blackPlayer), new Knook(6,0,blackPlayer), new Rook(7,0,blackPlayer)},
				{new Pawn(0,1,blackPlayer), new Pawn(1,1,blackPlayer), new Pawn(2,1,blackPlayer), new Pawn(3,1,blackPlayer),new Pawn(4,1,blackPlayer), new Pawn(5,1,blackPlayer), new Pawn(6,1,blackPlayer), new Pawn(7,1,blackPlayer)},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{null, null, null, null,null, null, null, null},
				{new Pawn(0,6,whitePlayer), new Pawn(1,6,whitePlayer), new Pawn(2,6,whitePlayer), new Pawn(3,6,whitePlayer),new Pawn(4,6,whitePlayer), new Pawn(5,6,whitePlayer), new Pawn(6,6,whitePlayer), new Pawn(7,6,whitePlayer)},
				{new Rook(0,7,whitePlayer), new Knight(1,7,whitePlayer), new DiagJumper(2,7,whitePlayer), new Queen(3,7,whitePlayer),new King(4,7,whitePlayer), new Bishop(5,7,whitePlayer), new Knook(6,7,whitePlayer), new Rook(7,7,whitePlayer)}
		};
		return start_board;
	}
	
	/**
	 * Set white player for game
	 * @param player the white player
	 */
	private void setWhitePlayer(Player player) {
		this.whitePlayer = player;
	}
	
	/**
	 * Set black player for game
	 * @param player the black player
	 */
	private void setBlackPlayer(Player player) {
		this.blackPlayer = player;
	}
	
	/*
	 * returns current setup of board
	 */
	public Piece[][] getBoard(){
		return this.board;
	}
	
	/*
	 * Flips board so opponent can move
	 */
	public void flipBoard() {
		for (int i = 0; i < this.SIZE/2; i++) {
			for (int j = 0; j < this.SIZE; j++) {
				swap(j, this.SIZE - 1 - i, j, i);
			}
		}
	}
	
	/**
	 * @parm x1 y1 pair to swap with second pair
	 * @parm x2 y2 pair to swap with first pair
	 */
	private void swap(int x1, int y1, int x2, int y2) {
		Piece temp = this.board[y1][x1];
		this.board[y1][x1] = this.board[y2][x2];
		
		if (this.board[y1][x1] != null) {
			this.board[y1][x1].setXY(x1, y1);
		}
		
		this.board[y2][x2] = temp;
		
		if (this.board[y2][x2] != null) {
			this.board[y2][x2].setXY(x2, y2);
		}
	}
	
	/**
	 * @param startX integer resembles x position of piece to be moved
	 * @param startY integer resembles y position of piece to be moved
	 * @param finalX integer resembles x position of piece to be moved
	 * @param finalY integer resembles y position of piece to be moved
	 */
	public boolean move(int startX, int startY, int finalX, int finalY) {
		if (checkInBounds(startX, startY, finalX, finalY)) {
			// Invalid selection
			return false;
		}
		
		Piece piece = this.board[startY][startX];
		
		if (piece == null || piece.getPlayer().color != this.currentTurn.color) {
			// Did not select a valid piece
			return false;
		}
		
		boolean[][] moves = piece.availableMoves(this);
		
		if (moves[finalY][finalX]) { // Available move
			Piece opponentPiece = board[finalY][finalX];
			// Save state before move
			history.push(new ChessBoard(this));
			
			this.board[finalY][finalX] = this.board[startY][startX];
			piece.setXY(finalX, finalY);
			this.board[startY][startX] = null;
			
			// Check if we need to remove piece from vector
			if (opponentPiece != null) {
				killPiece(opponentPiece);
			}
						
			boolean check = checkForCheck(this.currentTurn);
			
			if (check) { 
				/*
				 * player moved and king is in check still
				 * so we must move piece back			 
				 */
				resetBack(startX, startY, finalX, finalY, piece, opponentPiece);
				return false;
			}
			
			this.currentTurn = this.currentTurn.color == this.whitePlayer.color ? this.blackPlayer : this.whitePlayer;
			return true;
		}
		else { // Else do nothing, don't move piece.
			return false;	
		}
	}

	/**
	 * Move board back to original state
	 * @param startX
	 * @param startY
	 * @param finalX
	 * @param finalY
	 * @param piece
	 * @param opponentPiece
	 */
	private void resetBack(int startX, int startY, int finalX, int finalY, Piece piece, Piece opponentPiece) {
		history.pop();
		insertPiece(opponentPiece);
		this.board[startY][startX] = this.board[finalY][finalX];
		piece.setXY(startX,startY);
		this.board[finalY][finalX] = opponentPiece;
	}

	/**
	 * Insert piece back into alive vector
	 * @param opponentPiece
	 */
	private void insertPiece(Piece opponentPiece) {
		if (opponentPiece == null) {
			return;
		}
		if (opponentPiece.getPlayer().color == Color.WHITE) {
			white.addElement(opponentPiece);
		}
		else {
			black.addElement(opponentPiece);
		}
	}

	/**
	 * Desc. Check if move is in bounds
	 * @param startX
	 * @param startY
	 * @param finalX
	 * @param finalY
	 * @return
	 */
	private boolean checkInBounds(int startX, int startY, int finalX, int finalY) {
		return startX < 0 || startX >= this.SIZE || startY < 0 || startY >= this.SIZE
				|| finalX < 0 || finalX >= this.SIZE || finalY < 0 || finalY >= this.SIZE;
	}
	
	public Player getBlackPlayer() {
		return blackPlayer;
	}

	public Player getWhitePlayer() {
		return whitePlayer;
	}

	/**
	 * Takes piece out of corresponding vector of alive pieces
	 * @param piece
	 */
	private void killPiece(Piece piece) {
		if (piece.getPlayer().color == Color.BLACK) {
			black.removeElement(piece);
		}
		else {
			white.removeElement(piece);
		}
		
	}

	/**
	 * desc: Checks for if player is in check
	 * @param player type of player we are checking for check
	 * 
	 * return: True if in check false otherwise
	 */
	public boolean checkForCheck(Player player) {
		Piece king;
		boolean[][] moves;
		if (player.color == Color.WHITE) {
			king = getKing(white);
			moves = getAllOpponentsMoves(black);
		}
		else { // Color is black
			king = getKing(black);
			moves = getAllOpponentsMoves(white);
			
		}
		
		if (moves[king.getY()][king.getX()]) {
			return true;
		}
		return false;
	}
	
	/** 
	 * Desc: Check if any piece can move so king isn't vulnerable
	 * 
	 * @param playerInCheck is the player currently in check
	 */
	public boolean checkAnyPieceCanMove(Player playerInCheck) {
		if (playerInCheck.color == Color.WHITE) {
			return checkAllPieces(white, playerInCheck);
		}
		else {
			return checkAllPieces(black, playerInCheck);
		}
	}
	
	/**
	 * Desc. Checks if player is in stalemate
	 * @param player is the player we are checking is in stalemate
	 */
	public boolean checkForStaleMate(Player player) {
		// Both colors only have king left	
		if (this.black.size() == 1 && this.white.size() == 1) {
			return true;
		}
		else {
			return checkAnyPieceCanMove(player);
		}
	}

	/**
	 * @param pieces vector of pieces that we can check to move to get out of check
	 * @player type of player we are checking is still in check
	 */
	private boolean checkAllPieces(Vector<Piece> pieces, Player player) {
		for (int i = 0; i < pieces.size(); i++) {
			Piece piece = pieces.elementAt(i);
			boolean[][] moves = piece.availableMoves(this);		
			
			if (canGetOutOfCheck(moves, piece)) {
				return false;
			}
		}
		return true;		
	}

	/**
	 * Desc: move piece to all available locations and check if king is in
	 * check still
	 */
	private boolean canGetOutOfCheck(boolean[][] moves, Piece piece) {
		for (int i = 0; i < this.SIZE; i++) {
			for (int j = 0; j < this.SIZE; j++) {
				if(checkMove(moves, piece, i, j)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Check if move will get piece out of check
	 * @param moves
	 * @param piece
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean checkMove(boolean[][] moves, Piece piece, int i, int j) {
		if (moves[i][j]) {
			// Moving piece 
			this.board[piece.getY()][piece.getX()] = null;
			
			Piece savePiece = this.board[i][j]; // Piece at i and j
			int saveX = piece.getX();
			int saveY = piece.getY();
			
			this.board[i][j] = piece;
			piece.setXY(j, i);
			
			if (savePiece != null) {
				killPiece(savePiece);
			}
			
			boolean check = checkForCheck(piece.getPlayer());
			
			// return pieces to original states
			setBack(i, j, savePiece, saveX, saveY);
			if (!check) {
				// There is a viable move to get out of check
				return true;
			}
		}
		return false;
	}
	
	

	/**
	 * Move piece back to x and y and place the saved piece on x and y
	 * @param y 
	 * @param x
	 * @param savePiece
	 * @param saveX
	 * @param saveY
	 */
	private void setBack(int y, int x, Piece savePiece, int saveX, int saveY) {
		this.board[saveY][saveX] = this.board[y][x];
		this.board[saveY][saveX].setXY(saveX, saveY);
		this.board[y][x] = savePiece;
		insertPiece(savePiece);
	}

	private boolean[][] getAllOpponentsMoves(Vector<Piece> pieces) {
		boolean[][] moves = new boolean[this.SIZE][this.SIZE];
		
		for (int i = 0; i < pieces.size(); i++) {
			Piece piece = pieces.elementAt(i);
			
			if (piece.getType() != Type.PAWN
					&& piece.getType() != Type.KING) { // Pawns can only kill diag right and diag left
				boolean[][] pieceMoves = piece.availableMoves(this);
				
				combineMoves(moves, pieceMoves);
			}
			else if (piece.getType() == Type.PAWN) {
				piece.pawnCapture(this, moves);
			}
		}
		return moves;
	}

	private void combineMoves(boolean[][] moves, boolean[][] pieceMoves) {
		for (int i = 0; i < this.SIZE; i++) {
			for (int j = 0; j < this.SIZE; j++) {
				moves[i][j] = moves[i][j] || pieceMoves[i][j];
			}
		}
		
	}

	private Piece getKing(Vector<Piece> vec) {
		for (int i = 0; i < vec.size(); i++) {
			if (vec.get(i).getType() == Type.KING) {
				return vec.get(i);
			}
		}
		return null; // ERROR king should be on board
	}

	
	/* FOR DEBUGING
	 * desc: prints out T if piece is in tile or F if no piece
	 * @param board chess board
	 *
	private void printBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print((this.board[i][j] == null ? "F" : "T" )+ " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/* FOR DEBUGING
	 * desc: prints all moves available
	 *
	public void printMoves(boolean[][] moves) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print((moves[i][j] ? "T" : "F" )+ " ");
			}
			System.out.println();
		}
		System.out.println();
	}*/
	
}
