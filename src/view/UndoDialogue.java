package view;

import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import controller.CancelListener;
import controller.UndoDialogueSaveListener;

/**
 * A view class to show the undo dialogue.
 * @author Yidan Zhang
 * @author Chao Wang
 */

public class UndoDialogue  extends JDialog
{

	private int verticalAxis=400;
	private int horizontalAxis=160;
	
	private JLabel info;
	private JButton save;
	private JButton cancel;
	
	private Box base;
	private Box infoBox;
	private Box button;
	
	public UndoDialogue()
	{
	
		info = new JLabel("Are you sure to Undo last step?");
		
		infoBox = Box.createHorizontalBox();
		infoBox.add(info);
		
		save = new JButton("OK");
		save.addActionListener(new UndoDialogueSaveListener(this));
		cancel = new JButton("Cancel");
		cancel.addActionListener(new CancelListener(this));
		
		button = Box.createHorizontalBox();
		button.add(save);
		button.add(cancel);
		
		base = Box.createVerticalBox();
		base.add(Box.createVerticalStrut(20));
		base.add(infoBox);
		base.add(Box.createVerticalStrut(25));
		base.add(button);
		
		setLayout(new FlowLayout());
		add(base);	
		
		//setup dialogue
		setTitle("Undo Move");
		setSize(verticalAxis, horizontalAxis);
		setResizable(false);
		View v = View.getView();
		setLocationRelativeTo(v);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
	    setVisible(true); 
	}
}
