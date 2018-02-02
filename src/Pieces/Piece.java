package Pieces;

import Game.ChessBoard;
import Game.Player;

public abstract class Piece {
	int x, y;
	private Player player; 
	Type type;
	
	public Piece() {}
	
	/*
	 * @param x the x location of piece
	 * @param y the y location of piece 
	 */
	public Piece(int x, int y, Player player, Type type) {
		this.x = x;
		this.y = y;
		this.setPlayer(player);
		this.type = type;
	}
	
	/*
	 * Desc: Set new x and y for piece
	 */
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public Type getType() {
		return this.type;
	}
	
	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/*
	 * Desc: If location is null marks location as true and moves on to the 
	 * next location if not an available location it breaks for loop
	 * returns: true if piece can move to location and false if it can not
	 * 
	 * @param moves 2d boolean array what moves the piece can go too
	 * @param piece piece at location x and y
	 * @param locx checking location x
	 * @param locy checking location y
	 * 
	 */
	public boolean checkLocation(boolean[][] moves, Piece piece, int locx, int locy) {
		if (piece == null) {
			moves[locy][locx] = true;
			return true;
		}
		else if (piece.getPlayer().color == this.getPlayer().color) {
			/*
			 * piece is your own piece and can not jump over it
			 * so break
			 */
			return false;
		}
		else {
			/*
			 * piece is opponents piece so we can capture it
			 * then break
			 */
			moves[locy][locx] = true;
			return false;
		}
	}
	
	public boolean[][] availableMovesBishop(ChessBoard chess_board){
		boolean[][] moves = new boolean[chess_board.SIZE][chess_board.SIZE];
		Piece[][] board = chess_board.getBoard();
		
		
		checkDiagUpRight(moves, board, chess_board.SIZE);
		checkDiagDownRight(moves, board, chess_board.SIZE);
		checkDiagUpLeft(moves, board, chess_board.SIZE);
		checkDiagDownLeft(moves, board, chess_board.SIZE);
		
		return moves;
	}
	
	public void pawnCapture(ChessBoard chess_board, boolean[][] moves) {
		Piece[][] board = chess_board.getBoard();
		//Check if pawn can capture right
		if (this.y-1 >= 0 
				&& this.x+1 < chess_board.SIZE
				&& board[this.y-1][this.x+1] != null 
				&& board[this.y-1][this.x+1].getPlayer().color != this.getPlayer().color)
			moves[this.y-1][this.x+1] = true;
		
		//Check if pawn can capture left
		if (this.y-1 >= 0
				&& this.x-1 >= 0
				&& board[this.y-1][this.x-1] != null 
				&& board[this.y-1][this.x-1].getPlayer().color != this.getPlayer().color)
			moves[this.y-1][this.x-1] = true;
	}
	
	/*
	 * Checks how far up and right a bishop can go
	 */
	private void checkDiagUpRight(boolean[][] moves, Piece[][] board, int size){
		for (int dx = 1, dy = 1; this.x + dx < size && this.y - dy >= 0; dx++, dy++) {
			Piece piecexy = board[this.y-dy][this.x+dx];
			if (!checkLocation(moves, piecexy, this.x+dx, this.y-dy))
				break;
		}
	}
	
	/*
	 * Checks how far down and right a bishop can go
	 */
	private void checkDiagDownRight(boolean[][] moves, Piece[][] board, int size){
		for (int dx = 1, dy = 1; this.x + dx < size && this.y + dy < size; dx++, dy++) {
			Piece piecexy = board[this.y+dy][this.x+dx];
			if (!checkLocation(moves, piecexy, this.x+dx, this.y+dy))
				break;
		}
	}
	
	/*
	 * Checks how far up and left a bishop can go
	 */
	private void checkDiagUpLeft(boolean[][] moves, Piece[][] board, int size){
		for (int dx = 1, dy = 1; this.x - dx >= 0 && this.y - dy >= 0; dx++, dy++) {
			Piece piecexy = board[this.y-dy][this.x-dx];
			if (!checkLocation(moves, piecexy, this.x-dx, this.y-dy))
				break;
		}
	}
	
	private void checkDiagDownLeft(boolean[][] moves, Piece[][] board, int size){
		for (int dx = 1, dy = 1; this.x - dx >= 0 && this.y + dy < size; dx++, dy++) {
			Piece piecexy = board[this.y+dy][this.x-dx];
			if (!checkLocation(moves, piecexy, this.x-dx, this.y+dy))
				break;
		}
	}
	
	
	public boolean[][] availableMovesRook(ChessBoard chess_board){
		boolean[][] moves = new boolean[chess_board.SIZE][chess_board.SIZE];
		Piece[][] board = chess_board.getBoard();

		checkUp(moves, board, chess_board.SIZE);
		checkDown(moves, board, chess_board.SIZE);
		checkRight(moves, board, chess_board.SIZE);
		checkLeft(moves, board, chess_board.SIZE);
		
		return moves;
		
	}
	
	public boolean[][] availableMovesKnight(ChessBoard chess_board){
		boolean[][] moves = new boolean[chess_board.SIZE][chess_board.SIZE];
		Piece[][] board = chess_board.getBoard();
		
		// Check Up 2 right 1
		checkLocationHelper(chess_board, moves, this.x+1, this.y-2);
		
		// Check right 2 up 1
		checkLocationHelper(chess_board, moves, this.x+2, this.y-1);
		
		// Check down 1 right 2
		checkLocationHelper(chess_board, moves, this.x+2, this.y+1);
		
		// Check down 2 right 1
		checkLocationHelper(chess_board, moves, this.x+1, this.y+2);
		
		// Check down 2 left 1
		checkLocationHelper(chess_board, moves, this.x-1, this.y+2);
		
		// Check down 1 left 2
		checkLocationHelper(chess_board, moves, this.x-2, this.y+1);
		
		// Check up 1 left 2
		checkLocationHelper(chess_board, moves, this.x-2, this.y-1);
		
		// Check up 2 left 1
		checkLocationHelper(chess_board, moves, this.x-1, this.y-2);
		
		return moves;
	}
	
	/*
	 * Checks hows far the rook can move up
	 */
	private void checkUp(boolean[][] moves, Piece[][] board, int size){
		for(int i = 1; i + this.y < size; i++) {
			Piece piecexy = board[this.y+i][this.x];
			if (!checkLocation(moves, piecexy, this.x, this.y+i))
				break;
		}
	}
	
	/*
	 * Checks how far the rook can move right
	 */
	private void checkRight(boolean[][] moves, Piece[][] board, int size){
		for (int i = 1; i + this.x < size; i++) {
			Piece piecexy = board[this.y][this.x + i];
			if (!checkLocation(moves, piecexy, this.x+i, this.y))
				break;
		}
	}
	
	/*
	 * Checks how far the rook can move left
	 */
	private void checkLeft(boolean[][] moves, Piece[][] board, int size){
		for (int i = 1; this.x - i >= 0; i++) {
			Piece piecexy = board[this.y][this.x-i];
			if (!checkLocation(moves, piecexy, this.x-i, this.y))
				break;
		}
	}
	
	/*
	 * Checks how far the rook can move down
	 */
	private void checkDown(boolean[][] moves, Piece[][] board, int size){
		for (int i = 1; this.y - i >= 0; i++) {
			Piece piecexy = board[this.y-i][this.x];
			if (!checkLocation(moves, piecexy, this.x, this.y-i))
				break;
		}
	}
	
	protected void checkLocationHelper(ChessBoard chess_board, boolean[][] moves, int x, int y) {
		Piece[][] board = chess_board.getBoard();
		if (x >= 0 && x < chess_board.SIZE && y >= 0 && y < chess_board.SIZE) {
			checkLocation(moves, board[y][x], x, y);
		}
	}
	
	/*
	 * Checks if the piece can go to the x and y position
	 * @param x the final x position
	 * @param y the final y position
	 * return: chessboard of availableMoves
	 */
	public abstract boolean[][] availableMoves(ChessBoard board);
	
	public abstract Piece deepCopy();
}
