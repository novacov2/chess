package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Game.ChessBoard;
import Game.Player;
import Pieces.Piece;

public class GUI {
	private final JFrame gameFrame;
	private final static Dimension DIMENSION = new Dimension(800,800);
	private BoardPanel boardPanel;
	private ChessBoard board;
	
	/**
	 * Desc. Sets up board and creates BoardPanel with tiles
	 * @param board
	 */
	public GUI(ChessBoard board) {
		this.board = board;
		this.gameFrame = new JFrame("Chess    0 - 0");
		this.gameFrame.setLayout(new BorderLayout());
		// Set JFrame size
		gameFrame.setSize(DIMENSION);
		
		// Create menu bar
		populateMenuBar();
		
		boardPanel = new BoardPanel(board);
        gameFrame.add(boardPanel, BorderLayout.CENTER);
		gameFrame.setVisible(true);
	}
			 
	/**
	 * 
	 * sets chess board to new board
	 * @param board
	 */
	public void setBoard(ChessBoard board) {
		this.board = board;
	}
	
	/**
	 * Desc. Populates menu bar to allow user to undo 
	 */
	private void populateMenuBar() {
		JMenuBar tableMenuBar = new JMenuBar();
		tableMenuBar.add(CreateEditMenu());
		this.gameFrame.setJMenuBar(tableMenuBar);
	}
	
	/**
	 * 
	 * @return JMenu Object that will add an edit to menu bar
	 */
	private JMenu CreateEditMenu() {
		// Create edit menu
		final JMenu editMenu = new JMenu("Edit");
		
		JMenuItem undo = new JMenuItem("Undo");
		addUndoListener(undo);
		
		JMenuItem customGame = new JMenuItem("Custom Game");
		addCustomGameListener(customGame);
		
		JMenuItem standardGame = new JMenuItem("Standard Game");
		addStandardGameListener(standardGame);
		
		JMenuItem forfietGame = new JMenuItem("Forfiet");
		addForfietGameListener(forfietGame);
		
		editMenu.add(undo);
		editMenu.add(customGame);
		editMenu.add(standardGame);
		editMenu.add(forfietGame);
		return editMenu;
	}

	/**
	 * Allow current player to forfiet
	 * @param forfietGame
	 */
	private void addForfietGameListener(JMenuItem forfietGame) {
		forfietGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player loser = board.getCurrentTurn();
				
				Player winner = loser == board.getBlackPlayer() ? board.getWhitePlayer() : board.getBlackPlayer();
				winner.setScore(winner.getScore()+1);
				
				Piece[][] standardBoard = board.createStandardChessboard(board.getWhitePlayer(), board.getBlackPlayer());
				ChessBoard startboard = new ChessBoard(standardBoard, board.getWhitePlayer(),
						board.getWhitePlayer(), board.getBlackPlayer());
				
				setBoard(startboard);
				boardPanel.setBoard(startboard);
				gameFrame.setTitle("Chess    " + 
						board.getWhitePlayer().getScore() + " - " 
						+ board.getBlackPlayer().getScore());
				boardPanel.recreateBoard();
				boardPanel.resetStartTile();
			}
			
		});	
		
	}

	/**
	 * Allow user to start new game as Standard
	 * @param standardGame
	 */
	private void addStandardGameListener(JMenuItem standardGame) {
		standardGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChessBoard standardBoard = new ChessBoard(Game.BoardType.STANDARD);
				setBoard(standardBoard);
				
				boardPanel.setBoard(standardBoard);
				boardPanel.recreateBoard();
				boardPanel.resetStartTile();
			}
			
		});	
		
	}

	/**
	 * Allows user to create custom game
	 * @param customGame
	 */
	private void addCustomGameListener(JMenuItem customGame) {
		customGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChessBoard customBoard = new ChessBoard(Game.BoardType.CUSTOM);
				setBoard(customBoard);
				
				boardPanel.setBoard(customBoard);
				boardPanel.recreateBoard();
				boardPanel.resetStartTile();
				
			}
			
		});	
	}

	/**
	 * Allows user to undo
	 * @param undo
	 */
	private void addUndoListener(JMenuItem undo) {
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChessBoard oldBoard;
				
				// Check if user can go back to his last turn
				if (board.getHistory().size() > 1) {
					board.getHistory().pop();
					oldBoard = (ChessBoard) board.getHistory().pop();
					setBoard(oldBoard);
					
					boardPanel.setBoard(oldBoard);
					boardPanel.recreateBoard();
					boardPanel.resetStartTile();
				}
			}
		});
	}
}
