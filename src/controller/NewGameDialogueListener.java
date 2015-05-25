package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.InfoPanel;
import view.NewGameDialogue;

/**
 * A controller class -- new game dialogue listener.
 * @author Yidan Zhang
 * @author Chao Wang
 */

public class NewGameDialogueListener implements ActionListener 
{
	private NewGameDialogue newGameDia;
	private InfoPanel infoPanel;

	public NewGameDialogueListener(InfoPanel infoPanel) 
	{
		// TODO Auto-generated constructor stub
		this.infoPanel=infoPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		newGameDia = new NewGameDialogue(infoPanel);
	}
}
