package controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.AbstractPiece;
import model.Barrier;
import model.GameManager;
import model.PieceGroup;
import model.Player;
import view.BoardPanel;
import view.GameOverDialogue;
import view.SquarePanel;
import view.View;

public class PieceMovementListener implements MouseListener {

	private SquarePanel parentSquarePanel;

	private ArrayList<Point> point;
	private ArrayList<Point> pointA;

	private int currX;
	private int currY;
	private int oldX;
	private int oldY;

	private SquarePanel currSquares[][];
	private SquarePanel oldSquares[][];

	private PieceGroup newSquareArray;

	private static SquarePanel prevSP = null;
	private static ArrayList<SquarePanel> prevSPList = null;

	public static SquarePanel selectedPieceSquarePanel = null;

	public PieceMovementListener(SquarePanel psp) {
		this.parentSquarePanel = psp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (prevSPList == null) {
			prevSPList = new ArrayList<SquarePanel>();
		} else {
			for (int c = 0; c < prevSPList.size(); c++) {
				prevSPList.get(c).setBorder(
						BorderFactory.createLineBorder(Color.GREEN, 0));
			}
		}

		// TODO Auto-generated method stub
		if ((this.parentSquarePanel.getCurrentPieceGroup() != null) && (PieceMovementListener.selectedPieceSquarePanel == null)) {
			
			// Michael change 1
			if(this.parentSquarePanel.getCurrentPieceGroup().containsUnmovablePiece()) {
				return;	// A barrier cannot be moved!
			}
			// Michael change 2
			int currentTurnColour = GameManager.getSingleton().getCurrentPlayerTurnColour();
			if(this.parentSquarePanel.getCurrentPieceGroup().getPieceGroupColour() != currentTurnColour) {
				System.out.println(this.parentSquarePanel.getCurrentPieceGroup().getPieceGroupColour());
				System.out.println(currentTurnColour);
				return;
			}
			
			PieceMovementListener.selectedPieceSquarePanel = this.parentSquarePanel;

			if (prevSP != null) {
				prevSP.setBorder(BorderFactory.createLineBorder(Color.BLUE, 0));
			}

			prevSP = parentSquarePanel;

			currX = this.parentSquarePanel.getGridLocation().x;
			currY = this.parentSquarePanel.getGridLocation().y;

			newSquareArray = GameManager.getSingleton().getBoard()
					.getPiece(currY, currX);

			//only show border for movable piece
			AbstractPiece piece = newSquareArray.getPieces().get(0);
			if (!(piece instanceof Barrier)) {
				parentSquarePanel.setBorder(BorderFactory.createLineBorder(
						Color.BLUE, 3));
			}

			View v = View.getView();
			currSquares = v.getBoardPanel().getSquares();

			point = GameManager.getSingleton().getBoard()
					.getValidMoves(currY, currX);

			// Remove after demo
			try {
				for (int i = 0; i < currSquares.length; i++) {
					for (int j = 0; j < currSquares.length; j++) {
						for (int m = 0; m < point.size(); m++) {
							if (currSquares[i][j].getGridLocation().equals(
									point.get(m))) {
								currSquares[j][i].setBorder(BorderFactory
										.createLineBorder(Color.GREEN, 3));

								prevSPList.add(currSquares[j][i]);

							}
						}

					}
				}
			} catch (Exception e1) {
				System.out.println("Cannot move barrier");
			}

		} else {
			if (PieceMovementListener.selectedPieceSquarePanel != null) {

				// move from
				Point from = selectedPieceSquarePanel.getGridLocation();
				// move to
				Point to = this.parentSquarePanel.getGridLocation();

				oldX = from.x;
				oldY = from.y;
				View v = View.getView();
				oldSquares = v.getBoardPanel().getSquares();
				pointA = GameManager.getSingleton().getBoard()
						.getValidMoves(oldY, oldX);

				// Michael change 3a
				int numberOfPiecesMoved = 0;
				int scoreChange = 0;
				
				for (int x = 0; x < pointA.size(); x++) {
					if (to.x == pointA.get(x).y && to.y == pointA.get(x).x) {
						scoreChange += GameManager.getSingleton().getBoard().movePieces(from, to);
						numberOfPiecesMoved++;
					}
				}
				//System.out.println(scoreChange);
				
				// Michael change 3
				if (numberOfPiecesMoved > 0) {
					GameManager.getSingleton().addScoreToCurrentPlayer(scoreChange);
					GameManager.getSingleton().increaseMoveNumber();
					GameManager.getSingleton().nextPlayersTurn();
				}
				

				// change back to no border -- show selected piece
				selectedPieceSquarePanel.setBorder(BorderFactory
						.createLineBorder(Color.BLUE, 0));

				v.getInfo().setTimer();
				PieceMovementListener.selectedPieceSquarePanel = null;
				
				// Michael Change 4
				Player winner = GameManager.getSingleton().getWinningPlayerDueToElimination();
				if(winner != null) {
					// Game over because a player's pieces were eliminated
					new GameOverDialogue(winner);
				} else if (GameManager.getSingleton().bothPlayersHadMaxTurns()) {
					// Game over because turns are all done
					winner = GameManager.getSingleton().getPlayerWithMaxScore();
					new GameOverDialogue(winner);
				}
			
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
