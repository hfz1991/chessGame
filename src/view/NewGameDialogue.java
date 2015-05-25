package view;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import controller.CancelListener;
import controller.NewGameSaveDialogueListener;

/**
 * A view class to show the new game dialogue.
 * @author Yidan Zhang
 * @author Chao Wang
 */

public class NewGameDialogue extends JDialog
{
	private JLabel selectNum;
	private JComboBox numOfMove;
	

	private JLabel selectSizeLabel;
	private JComboBox boardSizeBox;
	
	private JButton save;
	private JButton cancel;
	
	private Box base;
	private Box Num;
	private Box button;
	
	private int numMove=40;
	private int startNum=10;
	private int verticalAxis=400;
	private int horizontalAxis=160;

	
	private int minBoardSize = 6;
	private int maxBoardSize = 12;

	public NewGameDialogue(InfoPanel infoPanel)
	{
		
		//select number of moves
		Num = Box.createHorizontalBox();
		selectNum = new JLabel("Select Number of Moves for each Player: ");
		Num.add(selectNum);
		Num.add(Box.createHorizontalStrut(2)); 
		
		numOfMove = new JComboBox();
		
		for(int i = 0; i <= numMove; i++)
		{
			numOfMove.addItem(i+startNum);
		}
		

		Num.add(numOfMove);
		
		this.selectSizeLabel = new JLabel("Select board size");
		this.boardSizeBox = new JComboBox();
		for(int i = this.minBoardSize; i <= this.maxBoardSize; i++) {
			this.boardSizeBox.addItem(i);
		}
		
		Num.add(this.selectSizeLabel);
		Num.add(this.boardSizeBox);
		
		this.setModal(true);
		
		//setup buttons
		save = new JButton("OK");
		save.addActionListener(new NewGameSaveDialogueListener(this,infoPanel));
		cancel = new JButton("Cancel");
		cancel.addActionListener(new CancelListener(this));
		
		button = Box.createHorizontalBox();
		button.add(save);
		button.add(cancel);
		
		base = Box.createVerticalBox();
		base.add(Box.createVerticalStrut(20));
		base.add(Num);
		base.add(Box.createVerticalStrut(25));
		base.add(button);
		
		setLayout(new FlowLayout());
		add(base);		
		
		//setup dialogue
		setTitle("Set Number of Moves");
		setSize(verticalAxis, horizontalAxis);
		setResizable(false);
		View v = View.getView();
		setLocationRelativeTo(v);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
	    setVisible(true); 
	}

	//get number of moves to store it
	public int getNumberOfMoves()
	{
		return Integer.parseInt(this.numOfMove.getSelectedItem().toString());
	}

	
	public int getEnteredBoardSize() {
		return Integer.parseInt(this.boardSizeBox.getSelectedItem().toString());
	}
}
