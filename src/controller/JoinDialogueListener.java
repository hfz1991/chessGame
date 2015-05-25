package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.JoinDialogue;

/**
 * A controller class -- join dialogue listener.
 * @author Yidan Zhang
 * @author Chao Wang
 */

public class JoinDialogueListener implements ActionListener
{

	private JoinDialogue joinDia;
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		joinDia= new JoinDialogue();
		
	}
	
}
