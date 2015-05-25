package test;

import java.awt.Point;
import java.util.ArrayList;

import model.*;

/**
 * Test class
 * @author Fang Zhou He
 * 
 */
public class mainTest {
	public static void main(String[] args){
		
		GameManager gm = GameManager.getSingleton();
		gm.getBoard().initialisePieces();
		AbstractPiece[][] square = gm.getBoard().getSquareArray();

		
		System.out.println("==============================================");
		//Testing getPiece() in Board
		System.out.println("Testing getPiece() case 1...");
		AbstractPiece pg = gm.getBoard().getPiece(0, 0);
		AbstractPiece ap = ((AbstractCompositePiece)pg);
		if(!(ap instanceof Rock)){
			System.out.println("Error!");
			System.out.println(ap);
		}
		else{
			System.out.println("OK");
		}
		
		
		System.out.println("Testing getPiece() case 2...");
		try{
			AbstractPiece pg1 = gm.getBoard().getPiece(0, 1);
			AbstractPiece ap1 = pg1;
			if(!(ap1 instanceof Bishop)){
				System.out.println("Error!");
				System.out.println(ap1);
			}
			else{
				System.out.println("OK");
			}
		}
		catch(Exception e){
			System.out.println("Error!NULL Pointer exception");
		}
		
		System.out.println("Testing getPiece() case 3...");
		try{
			AbstractPiece pg1 = gm.getBoard().getPiece(5, 3);
			AbstractPiece ap1 = pg1;
			if(!(ap1 instanceof Knight)){
				System.out.println("Error!");
				System.out.println(ap1);
			}
			else{
				System.out.println("OK");
			}
		}
		catch(Exception e){
			System.out.println("Error!NULL Pointer exception");
		}
		//Finish Testing getPiece() in Board
		
		
		//Testing movePiece() in Board
		System.out.println("==============================================");
		System.out.println("Testing movePiece() case 1...(MERGE)");
		System.out.println("\nSize of Square(5,0) (Rock) is :" + ((AbstractCompositePiece)square[5][0]).getPieces().size());
		System.out.println("Size of Square(5,1) (Bishop) is :" + ((AbstractCompositePiece)square[5][1]).getPieces().size());

		System.out.println("\nMoving Rock(5,0) to merge Bishop(5,1)...");
		Point launchPoint = new Point(0,5);
		Point targetPoint = new Point(1,5);
		gm.getBoard().movePieces(launchPoint, targetPoint);
		System.out.println("\nSize of Square(5,0) (Rock) is :");
		try{
			System.out.print(((AbstractCompositePiece)square[5][0]).getPieces().size());
		}
		catch(Exception e)
		{
			System.out.println("NULL...OK");
		}
		System.out.println("Size of Square(5,1) (Bishop) is :" + ((AbstractCompositePiece)square[5][1]).getPieces().size());
		System.out.println("\nMoving Knight(5,3) to merge Bishop(5,1)...");
		Point KnightPoint = new Point(3,5);
		gm.getBoard().movePieces(KnightPoint, targetPoint);
		System.out.println("Size of Square(5,1) (Bishop) is :" + ((AbstractCompositePiece)square[5][1]).getPieces().size());

		System.out.println("\nMoving One More Piece(5,4) to merge Bishop(5,1)...");
		Point ExtraP = new Point(4,5);
		gm.getBoard().movePieces(ExtraP, targetPoint);
		System.out.println("Size of Square(5,1) is :" + ((AbstractCompositePiece)square[5][1]).getPieces().size());
		System.out.println("Size of Square(5,4) is :" + ((AbstractCompositePiece)square[5][4]).getPieces().size());
		System.out.println("#1 Piece in Square(5,1) is :" + ((AbstractCompositePiece)square[5][1]).getPieces().get(0).getName());
		System.out.println("#2 Piece in Square(5,1) is :" + ((AbstractCompositePiece)square[5][1]).getPieces().get(1).getName());
		System.out.println("#3 Piece in Square(5,1) is :" + ((AbstractCompositePiece)square[5][1]).getPieces().get(2).getName());

		//Testing checkValidPathPiece for splitPiece() in order to show the particular piece
		System.out.println("==============================================");
		System.out.println("Testing checkValidPathPiece for (Rock) case ...");
		int AssumedIndex = 1; 
		ArrayList<Point> list = new ArrayList<Point>();
		AbstractPiece thisRock = ((AbstractCompositePiece)gm.getBoard().getPiece(5, 1)).getPieces().get(AssumedIndex);
		gm.getBoard().checkingValidPathPiece(thisRock, list, 5, 1);
		for(int i = 0 ; i < list.size() ; i++){
			System.out.println("Point x: " + list.get(i).x + " Point y: " + list.get(i).y);
		}
		//Testing splitPiece() in Board
		System.out.println("==============================================");
		System.out.println("Testing splitPiece() case 1...(SPLIT)");
		Point fromPoint = new Point(1,5);
		Point toPoint = new Point (0,5);
		AbstractCompositePiece myRock = ((AbstractCompositePiece)gm.getBoard().getPiece(5, 1)).getPieces().get(1);
		System.out.println("\nSplit Rock(5,1) to Square(5,0)...");
		gm.getBoard().splitPiece(fromPoint, toPoint, myRock);
		System.out.println("\nSize of Square(5,0) is :" + ((AbstractCompositePiece)square[5][0]).getPieces().size());
		System.out.println("Size of Square(5,1) is :" + ((AbstractCompositePiece)square[5][1]).getPieces().size());
		System.out.println("#1 Piece in Square(5,1) is :" + ((AbstractCompositePiece)square[5][1]).getPieces().get(0).getName());
		System.out.println("#2 Piece in Square(5,1) is :" + ((AbstractCompositePiece)square[5][1]).getPieces().get(1).getName());
//		
//		//Testing take barrier in splitPiece()
		System.out.println("======================");
		System.out.println("Testing splitPiece() case 2...(SPLIT)");
		AbstractCompositePiece myBishop = ((AbstractCompositePiece)gm.getBoard().getPiece(5, 1)).getPieces().get(0);
		System.out.println("\nSplit Bishop(5,1) to Barrier(3,3)...");
		Point newToPoint = new Point (3,3);
		int score = gm.getBoard().splitPiece(fromPoint, newToPoint, myBishop);
		System.out.println("\nSize of Square(5,1) is :" + ((AbstractCompositePiece)square[5][1]).getPieces().size());
		System.out.println("#1 Piece in Square(5,1) is :" + ((AbstractCompositePiece)square[5][1]).getPieces().get(0).getName());
		System.out.println("Size of Barrier(3,3) is :" + ((AbstractCompositePiece)square[3][3]).getPieces().size());
		System.out.println("#1 Piece in Square(3,3) is :" + ((AbstractCompositePiece)square[3][3]).getPieces().get(0).getName());
		System.out.println("Got " + score + " point!");
//
//		//Testing addPiece() in PieceGroup
		System.out.println("==============================================");
		System.out.println("Testing addPiece() case 1...");
		AbstractPiece[][] pg2 = gm.getBoard().getSquareArray();
		System.out.println("Adding piece into squareArray[0][0]...");
		((AbstractCompositePiece)pg2[0][0]).addPiece(new Rock(GameManager.BLACK_PLAYER,0,0));
		System.out.println("Size of PieceGroup[0][0] is " +((AbstractCompositePiece)pg2[0][0]).getPieces().size());
		System.out.println("Adding piece into squareArray[0][0]...");
		((AbstractCompositePiece)pg2[0][0]).addPiece(new Bishop(GameManager.BLACK_PLAYER,0,0));
		System.out.println("Size of PieceGroup[0][0] is " +((AbstractCompositePiece)pg2[0][0]).getPieces().size());

	}
}
