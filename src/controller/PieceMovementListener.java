package controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import model.AbstractCompositePiece;
import model.AbstractPiece;
import model.Barrier;
import model.CompositeInterface;
import model.GameManager;
import model.Player;
import view.GameOverDialogue;
import view.SplitDialogue;
import view.SquarePanel;
import view.View;

/**
 * A controller class -- piece movement listener.
 * @author Yidan Zhang
 * @author Chao Wang
 * @author Fang Zhou He
 * @author Michael Kowalenko
 */

public class PieceMovementListener implements MouseListener
{

	private SquarePanel parentSquarePanel;

	private ArrayList<Point> point;
	private ArrayList<Point> pointA;
	private ArrayList<Point> pointList;
	
	private int currX;
	private int currY;
	private int oldX;
	private int oldY;
	private int size;
	
	private boolean isSplited=false;

	private SquarePanel currSquares[][];
	private SquarePanel oldSquares[][];

	private AbstractPiece newSquareArray;

	private static SquarePanel prevSP = null;
	private static ArrayList<SquarePanel> prevSPList = null;
	
	private AbstractPiece ap=null;

	public static SquarePanel selectedPieceSquarePanel = null;

	public PieceMovementListener(SquarePanel psp) 
	{
		this.parentSquarePanel = psp;
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{

		if (prevSPList == null) {
			prevSPList = new ArrayList<SquarePanel>();
		} else {
			for (int c = 0; c < prevSPList.size(); c++) 
			{
				prevSPList.get(c).setBorder(
						BorderFactory.createLineBorder(Color.GREEN, 0));
			}
		}
		
		//Set coordinate of currentSelectedSquare
		GameManager.getSingleton().getBoard().setCurrentSelectedPoint(this.parentSquarePanel.getGridLocation().x,this.parentSquarePanel.getGridLocation().y);
		
		if ((this.parentSquarePanel.getCurrentPieceGroup() != null) && (PieceMovementListener.selectedPieceSquarePanel == null)) 
		{
			// A barrier cannot be moved
			if(this.parentSquarePanel.getCurrentPieceGroup().containsUnmovablePiece()) 
			{
				return;	
			}
			AbstractCompositePiece currentPieceGroup = (AbstractCompositePiece)this.parentSquarePanel.getCurrentPieceGroup();
			
			int currentTurnColour = GameManager.getSingleton().getCurrentPlayerTurnColour();
			if(currentPieceGroup.getColour() != currentTurnColour) 
			{
				return;
			}
			
			PieceMovementListener.selectedPieceSquarePanel = this.parentSquarePanel;

			if (prevSP != null) 
			{
				prevSP.setBorder(BorderFactory.createLineBorder(Color.BLUE, 0));
			}

			prevSP = parentSquarePanel;

			currX = this.parentSquarePanel.getGridLocation().x;
			currY = this.parentSquarePanel.getGridLocation().y;

			newSquareArray = GameManager.getSingleton().getBoard()
					.getPiece(currY, currX);

			//only show border for movable piece
			AbstractPiece piece = ((AbstractCompositePiece)newSquareArray).getPieces().get(0);
			if (piece instanceof CompositeInterface) 
			{
				parentSquarePanel.setBorder(BorderFactory.createLineBorder(
						Color.BLUE, 3));
			}

			View v = View.getView();
			currSquares = v.getBoardPanel().getSquares();

			point = GameManager.getSingleton().getBoard()
					.getValidMoves(currY, currX);

				for (int i = 0; i < currSquares.length; i++) 
				{
					for (int j = 0; j < currSquares.length; j++) 
					{
						for (int m = 0; m < point.size(); m++) 
						{
							if (currSquares[i][j].getGridLocation().equals(
									point.get(m))) 
							{
								currSquares[j][i].setBorder(BorderFactory
										.createLineBorder(Color.GREEN, 3));

								prevSPList.add(currSquares[j][i]);

							}
						}
					}
				}
		} else 
		{
			if (PieceMovementListener.selectedPieceSquarePanel != null) 
			{	
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

				int numberOfPiecesMoved = 0;
				int scoreChange = 0;
				AbstractCompositePiece oldPieces = (AbstractCompositePiece)(GameManager.getSingleton().getBoard().getPiece(oldY, oldX));
				size=oldPieces.getPieces().size();

				for (int x = 0; x < pointA.size(); x++) 
				{
					if (to.x == pointA.get(x).y && to.y == pointA.get(x).x) 
					{
						int index = View.getView().getInfo().getIndexN();
						
						isSplited = View.getView().getInfo().getIsSplited();
						View.getView().getInfo().setSplited(false);
						
						if(isSplited==false){
							scoreChange += GameManager.getSingleton().getBoard().movePieces(from, to);
						}else if(isSplited==true){
							ap=null;
							AbstractCompositePiece acp = (AbstractCompositePiece)GameManager.getSingleton().getBoard().getPiece(oldY, oldX);
							ap = acp.getPieces().get(index);
							pointList = new ArrayList<Point>();
							GameManager.getSingleton().getBoard().checkingValidPathPiece(ap, pointList, oldY, oldX);
							for (int y = 0; y < pointList.size(); y++) 
							{
								if (to.x == pointList.get(y).y && to.y == pointList.get(y).x) 
								{

									GameManager.getSingleton().getBoard().splitPiece(from, to, (AbstractCompositePiece) ap);
								}
							}
							
						}else{
							System.out.println("error!");
						}
						numberOfPiecesMoved++;
					}
				}
				
				if (numberOfPiecesMoved > 0) 
				{
					GameManager.getSingleton().addScoreToCurrentPlayer(scoreChange);
					GameManager.getSingleton().increaseMoveNumber();
					GameManager.getSingleton().nextPlayersTurn();
				}
				
				// change back to no border -- show selected piece
				selectedPieceSquarePanel.setBorder(BorderFactory
						.createLineBorder(Color.BLUE, 0));

				v.getInfo().setTimer();
				PieceMovementListener.selectedPieceSquarePanel = null;
				
				Player winner = GameManager.getSingleton().getWinningPlayerDueToElimination();
				if(winner != null) 
				{
					// Game over because a player's pieces were eliminated
					new GameOverDialogue(winner);
				} else if (GameManager.getSingleton().bothPlayersHadMaxTurns()) 
				{
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
