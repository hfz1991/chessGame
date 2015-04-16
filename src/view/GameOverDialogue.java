package view;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import controller.CancelListener;

public class GameOverDialogue extends JDialog {

	private int verticalAxis=400;
	private int horizontalAxis=160;
	
	private JLabel info;
	
	private JButton save;
	
	private Box base;
	private Box infoBox;
	private Box button;
	
	public GameOverDialogue(){
	
		info = new JLabel(" win!");
		
		infoBox = Box.createHorizontalBox();
		infoBox.add(info);
		
		save = new JButton("OK");
		save.addActionListener(new CancelListener(this));
		
		button = Box.createHorizontalBox();
		button.add(save);
		
		base = Box.createVerticalBox();
		base.add(Box.createVerticalStrut(20));
		base.add(infoBox);
		base.add(Box.createVerticalStrut(25));
		base.add(button);
		
		setLayout(new FlowLayout());
		add(base);	
		
		//setup frame
		setTitle("Game Over");
		setSize(verticalAxis, horizontalAxis);
		setResizable(false);
		View v = View.getView();
		setLocationRelativeTo(v);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
	    setVisible(true); 
	}
}
