
package view;

import java.awt.*;
import javax.swing.*;

/**
 * A view class to show overall view.
 * @author Yidan Zhang
 * @author Chao Wang
 */

public class View extends JFrame
{ 

	private BoardPanel board;
	private InfoPanel info;
	
	private static View singletonInstanceView = null; 
	
	public BoardPanel getBoardPanel() 
	{
		return this.board;
	}

	public void setBoard(BoardPanel board) 
	{
		this.board = board;
	}

	public InfoPanel getInfo() 
	{
		return this.info;
	}

	public void setInfo(InfoPanel info) 
	{
		this.info = info;
	}

	public View() 
	{ 
		this.board=new BoardPanel(); 
		this.info=new InfoPanel(this);
		this.add(board,BorderLayout.WEST);
		this.add(info,BorderLayout.EAST);
	    
		this.setTitle("Chess");
		this.setSize(840, 640);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.setVisible(true);
	} 

	
	public static View getView()
	{
		if(View.singletonInstanceView == null )
		{
			View.singletonInstanceView = new View();
		}
		return singletonInstanceView;
	}
}