package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.UndoDialogue;

/**
 * A controller class -- undo dialogue listener.
 * @author Yidan Zhang
 * @author Chao Wang
 */

public class UndoDialogueListener implements ActionListener 
{
	private UndoDialogue undoDia;
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		undoDia = new UndoDialogue();
	}

}
