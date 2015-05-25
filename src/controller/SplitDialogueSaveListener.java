package controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import model.AbstractPiece;
import model.AbstractCompositePiece;
import model.Board;
import model.GameManager;
import view.InfoPanel;
import view.SplitDialogue;
import view.SquarePanel;
import view.View;

/**
 * A controller class -- split save dialogue listener.
 * @author Yidan Zhang
 * @author Chao Wang
 * @author Fang Zhou He
 */

public class SplitDialogueSaveListener implements ActionListener
{

	private SplitDialogue splitDia;
	private ArrayList<Point> pointList;
	private SquarePanel currSquares[][];
	private ArrayList<Point> oldpoint;
	private int index_a;
	private InfoPanel infoPanelN;
	private boolean isSplited=false;
	

	public SplitDialogueSaveListener(SplitDialogue splitDialogue, InfoPanel infoPanel) 
	{
		this.splitDia = splitDialogue;
		this.infoPanelN = infoPanel;
	}

	// Assumption: The SplitDiaglogue will only show for a piece that is movable and not an unmovable piece like barrier
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		Board board = GameManager.getSingleton().getBoard();
		Point point = board.getcurrentSelectedPoint();
		index_a = splitDia.getIndex();
		AbstractPiece pieceSelected = GameManager.getSingleton().getBoard().getPiece(point.y, point.x);
		AbstractCompositePiece ap = ((AbstractCompositePiece)pieceSelected).getPieces().get(index_a);
		splitDia.dispose();
		pointList = new ArrayList<Point>();
		GameManager.getSingleton().getBoard().checkingValidPathPiece(ap, pointList, point.y, point.x);
		
		//get all squares
		View v = View.getView();
		currSquares = v.getBoardPanel().getSquares();
		
		//get all valid move list of the composited piece
		oldpoint = GameManager.getSingleton().getBoard()
				.getValidMoves(point.y, point.x);

		
		for (int i = 0; i < currSquares.length; i++) 
		{
			for (int j = 0; j < currSquares.length; j++) 
			{
				//remove the valid moves of the composited piece
				for (int m = 0; m < oldpoint.size(); m++) 
				{
					if (currSquares[i][j].getGridLocation().equals(
							oldpoint.get(m))) 
					{
						currSquares[j][i].setBorder(BorderFactory
								.createLineBorder(Color.GREEN, 0));
					}
				}
				
				//show the valid moves for the selected piece
				for (int m = 0; m < pointList.size(); m++) 
				{
					if (currSquares[i][j].getGridLocation().equals(
							pointList.get(m))) 
					{
						currSquares[j][i].setBorder(BorderFactory
								.createLineBorder(Color.RED, 3));
					}
				}
			}
		}
		
		isSplited=true;
		infoPanelN.setSplited(isSplited);
		isSplited=false;
	}
	
	public int getIndex() {
		index_a=splitDia.getIndex();
		return index_a;
	}

}
