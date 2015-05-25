package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.GameManager;

/**
 * A controller class -- split dialogue listener.
 * @author Yidan Zhang
 * @author Chao Wang
 */

public class SaveGameListener implements ActionListener 
{

	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("Save Game");
		
		Point op = new Point(1,0);
		Point tp = new Point(1,1);
		
		//should setPoint in PieceMovementListener each time
		GameManager.getSingleton().getPlayerList().get(0).getMoves().setPoint(op, tp);
		
		try {
			GameManager.getSingleton().getPlayerList().get(0).getMoves().setPoint(op, tp);
			GameManager.getSingleton().getPlayerList().get(0).getMoves().fileHandling();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
