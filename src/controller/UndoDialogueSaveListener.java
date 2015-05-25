package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.UndoDialogue;

/**
 * A controller class -- undo save dialogue listener.
 * @author Yidan Zhang
 * @author Chao Wang
 */

public class UndoDialogueSaveListener implements ActionListener 
{

	private UndoDialogue undoDia;
	
	public UndoDialogueSaveListener(UndoDialogue undoDialogue) 
	{
		// TODO Auto-generated constructor stub
		undoDia=undoDialogue;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub	
		undoDia.dispose();
	}

}
