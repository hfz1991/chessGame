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

import model.GameManager;
import model.PieceGroup;
import view.BoardPanel;
import view.SquarePanel;
import view.View;

public class PieceMovementListener implements MouseListener {

	private SquarePanel parentSquarePanel;

	private ArrayList<Point> point;

	private int currX;
	private int currY;
	private int oldX;
	private int oldY;

	private SquarePanel currSquares[][];

	public static SquarePanel selectedPieceSquarePanel = null;

	public PieceMovementListener(SquarePanel psp) {
		this.parentSquarePanel = psp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		
		// TODO Auto-generated method stub
		if (this.parentSquarePanel.getCurrentPieceGroup() != null) {
			PieceMovementListener.selectedPieceSquarePanel = this.parentSquarePanel;

			// need to check if the piece is movable
			// piece!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// show border if player click a square
			parentSquarePanel.setBorder(BorderFactory.createLineBorder(
					Color.BLUE, 3));
			currX = this.parentSquarePanel.getGridLocation().x;
			currY = this.parentSquarePanel.getGridLocation().y;
			
			View v = View.getView();
			currSquares = v.getBoardPanel().getSquares();
			
			point = GameManager.getSingleton().getBoard()
					.getValidMoves(currY, currX);


			for (int i = 0; i < currSquares.length; i++) {
				for (int j = 0; j < currSquares.length; j++) {
					for (int m = 0; m < point.size(); m++) {
						if (currSquares[i][j].getGridLocation().equals(
								point.get(m))) {
							currSquares[j][i].setBorder(BorderFactory
									.createLineBorder(Color.GREEN, 3));
						}
					}
				}
			}
		} else {
			if (PieceMovementListener.selectedPieceSquarePanel != null) {

				// move from
				Point from = selectedPieceSquarePanel.getGridLocation();
				// move to
				Point to = this.parentSquarePanel.getGridLocation();

				GameManager.getSingleton().getBoard().movePieces(from, to);

				// change back to no border -- show selected piece
				selectedPieceSquarePanel.setBorder(BorderFactory
						.createLineBorder(Color.BLUE, 0));

				// change back to no border -- show valid path

				/*
				 * for(int i=0; i<panelList.size();i++){
				 * panelList.get(i).setBorder
				 * (BorderFactory.createLineBorder(Color.GREEN, 0));
				 * System.out.println("change color"); }
				 */
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
