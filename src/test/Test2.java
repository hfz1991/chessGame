package test;

import java.awt.Point;
import java.io.IOException;

import model.GameManager;
import model.Moves;

public class Test2 {
	public static void main(String[] args) throws IOException {

		GameManager gm = GameManager.getSingleton();
		gm.getBoard().initialisePieces();
		Moves moves = gm.getPlayerList().get(0).getMoves();
		Point op = new Point(1,0);
		Point tp = new Point(1,1);
		moves.setPoint(op, tp);
		moves.fileHandling();
	}
}
