package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.GameManager;
import model.Player;

import view.InfoPanel;
import view.NewGameDialogue;
import view.View;

/**
 * A controller class -- new game save dialogue listener.
 * @author Yidan Zhang
 * @author Chao Wang
 */

public class NewGameSaveDialogueListener implements ActionListener
{
	
	private NewGameDialogue parentView;
	private InfoPanel infoPanel;
	
	public NewGameSaveDialogueListener(NewGameDialogue parentView, InfoPanel infoPanel) 
	{
		this.parentView = parentView;
		this.infoPanel=infoPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		GameManager.getSingleton().setTotalOfMoves(parentView.getNumberOfMoves());

		GameManager.getSingleton().getBoard().setSizeOfBoard(parentView.getEnteredBoardSize());

		GameManager.getSingleton().newGame();
		
		this.parentView.dispose(); 
		this.infoPanel.getGoBack().setEnabled(true);
		this.infoPanel.getSplit().setEnabled(true);
		this.infoPanel.getSave().setEnabled(true);
		
		View v = View.getView();
		
		// Add the InfoPanel to observe the Players
		for(Player player : GameManager.getSingleton().getPlayerList()) 
		{
			player.addObserver(v.getInfo());
		}
		
		v.getInfo().setTimer(); 
		
	}
}
