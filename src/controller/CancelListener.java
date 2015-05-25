package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

/**
 * A controller class -- cancel listener.
 * @author Yidan Zhang
 * @author Chao Wang
 */

public class CancelListener implements ActionListener 
{
	
	private JDialog dialog;
	
	public CancelListener(JDialog dialog)
	{
		this.dialog=dialog;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		dialog.dispose();
	}

}
