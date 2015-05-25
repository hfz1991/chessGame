package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.AbstractPiece;
import model.AbstractCompositePiece;
import model.CompositeInterface;
import model.GameManager;
import view.BoardPanel;
import view.InfoPanel;
import view.SplitDialogue;

/**
 * A controller class -- split dialogue listener.
 * @author Yidan Zhang
 * @author Chao Wang
 * @author Fang Zhou He
 */

public class SplitDialogueListener implements ActionListener 
{
	private SplitDialogue splitDia;
	private InfoPanel infoPanelN;
	


	public SplitDialogueListener(InfoPanel infoPanel) {
		this.infoPanelN = infoPanel;
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Point point = GameManager.getSingleton().getBoard().getcurrentSelectedPoint();
		AbstractPiece selectedPiece = GameManager.getSingleton().getBoard().getPiece(point.y, point.x);
		if (!(selectedPiece instanceof CompositeInterface)) {
			return;	// Do not move unmovable pieces like barrier
		}
		int size = ((AbstractCompositePiece)selectedPiece).getPieces().size();
		String pieceName[] = new String[size];
		for(int i=0; i < size ; i++){
			pieceName[i]=((AbstractCompositePiece)selectedPiece).getPieces().get(i).getName();
		}
		splitDia = new SplitDialogue(pieceName,infoPanelN);
	}

}
