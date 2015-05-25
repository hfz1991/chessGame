package controller;

import view.View;
import model.GameManager;

/**
 * A controller class -- controller.
 * @author Fang Zhou He
 * @author Yidan Zhang
 * @author Chao Wang
 */

public class controller 
{
	public static void main(String[] args)
	{
		View view = View.getView();		
		
		GameManager gm = GameManager.getSingleton();
		gm.getBoard().addObserver(view.getBoardPanel());
		view.setVisible(true);
	}
}
