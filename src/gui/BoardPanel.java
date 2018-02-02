package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Game.ChessBoard;
import Game.Player;
import Pieces.Piece;

public class BoardPanel extends JPanel {
	final Vector<Tile> boardTiles;
	
	private Tile startTile;
	private ChessBoard board;
	
	public BoardPanel(ChessBoard board) {
		super(new GridLayout(8,8));
		this.boardTiles = new Vector<Tile>();
		this.startTile = null;
		this.board = board;
		
		for (int i = 0; i < ChessBoard.SIZE; i++) {
			for (int j = 0; j < ChessBoard.SIZE; j++) {
				Tile tilePanel = new Tile(this, j, i);
				this.boardTiles.addElement(tilePanel);
				super.add(tilePanel); // Add to Jpanel
			}
		}
		super.validate();
	}
	
	public void resetStartTile() {
		startTile = null;
	}
	
	public void setBoard(ChessBoard board) {
		this.board = board;
	}
	
	/**
	 * Desc. After move is done the board should be recreated
	 * 
	 */
	public void recreateBoard() {
		for (int i = 0; i < boardTiles.size(); i++) {
			Tile tile = boardTiles.get(i);
			tile.assignImage(this.board);
			tile.assignTileColor(this.board.getCurrentTurn());
		}
		super.revalidate();
		super.repaint();
	}
	
	private class Tile extends JPanel {
		private final Color lightColor = new Color(245,222,179);
		private final Color darkColor = new Color(139,69,19);
		private final String imagePath = "images/";
		
		private final int x;
		private final int y;
		
		public Tile(BoardPanel boardPanel, int x, int y) {
			super(new GridBagLayout()); // Assign layout
			this.x = x;
			this.y = y;
			assignTileColor(board.getCurrentTurn());
			assignImage(board);
			
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tileSelected(e);
				}
			});
			
			super.validate();
		}
		
		/**
		 * Checks if it was left mouse click and if so update start tile
		 * @param e mouse event
		 */
		private void tileSelected(MouseEvent e){
			if (SwingUtilities.isLeftMouseButton(e)) {
				updateStartTile();
			}
		}
		
		/**
		 * Updates the start tile depending on where user clicked
		 */
		private void updateStartTile() {
			if (startTile == null) { // first click
				Piece selectedPiece = board.getBoard()[this.y][this.x];
				if (selectedPiece != null) {
					startTile = this;
				}
			}
			else { // second click
				boolean legalMove = board.move(startTile.x, startTile.y, this.x, this.y);
				
				conditionalChecking(legalMove);
				startTile = null;
			}
			
		}

		/**
		 * Make move if legal also check for checkmate and stalemate
		 * @param legalMove
		 */
		private void conditionalChecking(boolean legalMove) {			
			if (legalMove) {
				board.flipBoard();
				// Check for check
				boolean check = board.checkForCheck(board.getCurrentTurn());
				
				// Check for checkmate
				boolean checkmate = board.checkAnyPieceCanMove(board.getCurrentTurn());
				
				// check for stalemate
				boolean stalemate = board.checkForStaleMate(board.getCurrentTurn());
				board.flipBoard();
				
				if (check && !checkmate) {
					board.flipBoard();
					recreateBoard();
					// https://stackoverflow.com/questions/8379458/java-gui-101-changing-the-title-of-a-jpanel
					((JFrame) SwingUtilities.getRoot(this)).setTitle("Chess:    CHECK");
				}
				else if (checkmate) {
					Player winner = board.getCurrentTurn().color == Game.Color.BLACK ? board.getWhitePlayer() : board.getBlackPlayer();
					recreateBoard();
					JOptionPane.showMessageDialog(this, winner.color + " WINS!");
					
					Player loser = board.getCurrentTurn();
					
					winner.setScore(winner.getScore()+1);
					
					restartGame();
				}
				else if (stalemate) {
					recreateBoard();
					JOptionPane.showMessageDialog(this, "DRAW");
					
					restartGame();
				}
				else {
					((JFrame) SwingUtilities.getRoot(this)).setTitle("Chess    " + 
				board.getWhitePlayer().getScore() + " - " + board.getBlackPlayer().getScore());
					board.flipBoard();
					recreateBoard();
				}
			}
			else {
				((JFrame) SwingUtilities.getRoot(this)).setTitle("Chess:    ILLEGAL MOVE");
			}
		}

		private void restartGame() {
			Piece[][] standardBoard = board.createStandardChessboard(board.getWhitePlayer(), board.getBlackPlayer());
			ChessBoard startboard = new ChessBoard(standardBoard, board.getWhitePlayer(),
					board.getWhitePlayer(), board.getBlackPlayer());
			
			setBoard(startboard);
			((JFrame) SwingUtilities.getRoot(this)).setTitle("Chess    " + 
					board.getWhitePlayer().getScore() + " - " 
					+ board.getBlackPlayer().getScore());
			recreateBoard();
			resetStartTile();
		}

		/**
		 * desc. assign image to tile
		 * @param board
		 */
		public void assignImage(ChessBoard board) {
			super.removeAll();
			Piece piece = board.getBoard()[this.y][this.x];
			
			if (piece != null) { // Check if piece is on tile
				try {
					String color = piece.getPlayer().color.toString().substring(0,1);
					String type = piece.getType().toString();
					BufferedImage image = ImageIO.read(new File(imagePath + color + type + ".png"));
					super.add(new JLabel(new ImageIcon(image)));
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
		
		/**
		 * desc: assign color to tile
		 */
		private void assignTileColor(Player currentTurn) {
			if (currentTurn.color == Game.Color.WHITE) {
				assignTileColorWhiteTurn();
			}
			else {
				assignTileColorBlackTurn();
			}
			
			
		}

		private void assignTileColorBlackTurn() {
			if (this.y % 2 == 0) {
				super.setBackground(this.x % 2 == 0 ? darkColor : lightColor);
			}
			else {
				super.setBackground(this.x % 2 == 0 ? lightColor : darkColor);
			}
			
		}

		private void assignTileColorWhiteTurn() {
			if (this.y % 2 == 0) {
				super.setBackground(this.x % 2 == 0 ? lightColor : darkColor);
			}
			else {
				super.setBackground(this.x % 2 == 0 ? darkColor : lightColor);
			}
		}
	}
}
