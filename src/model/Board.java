package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

/**
 * A class representing the model of the chess game board.
 * @author Fang Zhou He
 * @author Michael Kowalenko
 */
public class Board extends Observable {

	private PieceGroup[][] squareArray;
	private Point currentSelectedPoint;
	
	public Board() {
		currentSelectedPoint = new Point();
	}
	
	public void setCurrentSelectedPoint(int x, int y){
		currentSelectedPoint.x=x;
		currentSelectedPoint.y=y;
	}
	
	public Point getcurrentSelectedPoint(){
		return currentSelectedPoint;
	}
	
	/**
	 * @pre a Piece must exist in the point fromP
	 * @post a PieceGroup or single piece is moved, taking any items in the new position
	 * @return score change to the moving piece's player
	 */
	public int movePieces(Point fromP, Point toP) {
		int scoreChange = 0;
		// move pieces logic here

		int fromX = fromP.x;
		int fromY = fromP.y;
		int toX = toP.x;
		int toY = toP.y;

		PieceGroup fromPG = squareArray[fromY][fromX];
		PieceGroup toPG = squareArray[toP.y][toP.x];
		//Click Blank Square
		if(squareArray[toY][toX] == null)
		{
			try{
				if (fromPG.getPieces() != null) {

					AbstractPiece piece = fromPG.getPieces().get(0);
					if (!(piece instanceof Barrier)) {
						MovablePiece movableP = (MovablePiece) piece;

						movableP.move(toX, toY);

						squareArray[fromY][fromX] = null;
						squareArray[piece.getyC()][piece.getxC()] = fromPG;
					}

				}
				}
				catch(Exception e){
					System.out.println("Cannot choose empty square");
				}
		}
		else if(toPG.getPieces().get(0) instanceof Barrier){
			squareArray[fromP.y][fromP.x] = null;
			squareArray[toP.y][toP.x] = fromPG;
	
			scoreChange = toPG.getPieceGroupScore();

		}
		//Click Piece
		else{
			//Same team, Merge 
			if(fromPG.getPieces().get(0).getColour() == toPG.getPieces().get(0).getColour())
			{
				//Check is the same type
				boolean sameTypeFlag = false;
				for(int i=0 ; i < fromPG.getPieces().size(); i ++){
					for(int j=0 ; j< toPG.getPieces().size() ; j++){
						if(fromPG.getPieces().get(i).getClass() == toPG.getPieces().get(j).getClass()){
							sameTypeFlag = true;
						}
					}
				}
				
				//check size is less and equal to 3 and not same type
				if(fromPG.getPieces().size() < 3 && sameTypeFlag == false){
					
					for(int i=0 ; i < fromPG.getPieces().size(); i ++){
						toPG.getPieces().add(fromPG.getPieces().get(i));
					}
					squareArray[fromP.y][fromP.x] = null;
				}
				
			}
			//Not in the same team, Take Piece//
			else
			{
				squareArray[fromP.y][fromP.x] = null;
				squareArray[toP.y][toP.x] = fromPG;
				
				scoreChange = toPG.getPieceGroupScore();
			}
			
		}
	
		this.setChanged();
		this.notifyObservers();
		return scoreChange;
	}

	/**
	 * @pre A GameManager and Board object exist
	 * @post The Board object contains a set of pieces in default positions
	 */
	public void initialisePieces() {
		this.squareArray = new PieceGroup[6][6];


		squareArray[0][0] = new PieceGroup(new Rock(GameManager.BLACK_PLAYER, 0, 0 ));
		squareArray[0][1] = new PieceGroup(new Bishop(GameManager.BLACK_PLAYER, 0, 1));
		squareArray[0][2] = new PieceGroup(new Knight(GameManager.BLACK_PLAYER, 0, 2));
		squareArray[0][3] = new PieceGroup(new Knight(GameManager.BLACK_PLAYER, 0, 3));
		squareArray[0][4] = new PieceGroup(new Bishop(GameManager.BLACK_PLAYER, 0, 4));
		squareArray[0][5] = new PieceGroup(new Rock(GameManager.BLACK_PLAYER, 0, 5));

		squareArray[2][0] = new PieceGroup(new Barrier(2, 0));
		squareArray[2][1] = new PieceGroup(new Barrier(2, 1));
		squareArray[2][2] = new PieceGroup(new Barrier(2, 2));
		squareArray[2][3] = new PieceGroup(new Barrier(2, 3));
		squareArray[2][4] = new PieceGroup(new Barrier(2, 4));
		squareArray[2][5] = new PieceGroup(new Barrier(2, 5));


		squareArray[3][0] = new PieceGroup(new Barrier(3, 0));
		squareArray[3][1] = new PieceGroup(new Barrier(3, 1));
		squareArray[3][2] = new PieceGroup(new Barrier(3, 2));
		squareArray[3][3] = new PieceGroup(new Barrier(3, 3));
		squareArray[3][4] = new PieceGroup(new Barrier(3, 4));
		squareArray[3][5] = new PieceGroup(new Barrier(3, 5));

		squareArray[5][0] = new PieceGroup(new Rock(GameManager.WHITE_PLAYER, 5, 0));
		squareArray[5][1] = new PieceGroup(new Bishop(GameManager.WHITE_PLAYER, 5, 1));
		squareArray[5][2] = new PieceGroup(new Knight(GameManager.WHITE_PLAYER, 5, 2));
		squareArray[5][3] = new PieceGroup(new Knight(GameManager.WHITE_PLAYER, 5, 3));
		squareArray[5][4] = new PieceGroup(new Bishop(GameManager.WHITE_PLAYER, 5, 4));
		squareArray[5][5] = new PieceGroup(new Rock(GameManager.WHITE_PLAYER, 5, 5));


		this.setChanged();
		this.notifyObservers();
	}

	public PieceGroup[][] getSquareArray() {
		return squareArray;
	}

	/**
	 * Get a list of points that a PieceGroup can move to.
	 * @pre The game board exists with one of more pieces in a provided position
	 * @param int currentX - x of selected PieceGroup (0 - )
	 * @param int currentY - y of selected PieceGroup (0 - )
	 * @return
	 */
	public ArrayList<Point> getValidMoves(int currentX, int currentY){
		
		PieceGroup pg = this.squareArray[currentX][currentY];
		ArrayList<Point> validArray = new ArrayList<Point>();
		if (pg.getPieces() != null) {

			AbstractPiece piece = pg.getPieces().get(0);
			//Check if the piece is movable piece
			checkingValidPathPiece(piece,validArray,currentX,currentY);
			
			try{
				AbstractPiece piece1 = pg.getPieces().get(1);
				if(piece1 != null){
					checkingValidPathPiece(piece1,validArray,currentX,currentY);
				}
			
				AbstractPiece piece2 = pg.getPieces().get(2);
				if(piece2 != null){
					checkingValidPathPiece(piece2,validArray,currentX,currentY);
				}
			}
			catch(Exception e3){
			}
		}
		//Finish piece checking
	

		return validArray;
	}
	
	/**
	 * Helper method for getValidMoves
	 * @param piece
	 * @param validArray
	 * @param x
	 * @param y
	 * @return ArrayList<Point> valid points
	 */
	public ArrayList<Point> checkingValidPathPiece(AbstractPiece piece, ArrayList<Point> validArray, int x, int y){
		
		if (!(piece instanceof Barrier)) {
			
			boolean reachToPieceFlag = false;
			
			//Bishop
			if(piece instanceof Bishop){
				//Right Bottom
				for(int i=1;x+i<6;i++){
					if(y+i<6){
						if(this.squareArray[x+i][y+i] == null && reachToPieceFlag == false){
							Point p = new Point((x+i),(y+i));
							validArray.add(p);
						}
						else if(this.squareArray[x+i][y+i] != null && reachToPieceFlag == false){
							reachToPieceFlag = true;
							Point p = new Point((x+i),(y+i));
							validArray.add(p);
						}
						
					}
				}
				
				//Left Bottom
				reachToPieceFlag = false;
				for(int i=1;x+i<6;i++){
					if(y-i>=0){
						if(this.squareArray[x+i][y-i] == null && reachToPieceFlag == false){
							Point p = new Point((x+i),(y-i));
							validArray.add(p);
						}
						else if(this.squareArray[x+i][y-i] != null && reachToPieceFlag == false){
							reachToPieceFlag = true;
							Point p = new Point((x+i),(y-i));
							validArray.add(p);
						}
						
					}
				}
				
				//Right Up
				reachToPieceFlag = false;
				for(int i=1;x-i>=0;i++){
					if(y+i<6){
						if(this.squareArray[x-i][y+i] == null && reachToPieceFlag == false){
							Point p = new Point((x-i),(y+i));
							validArray.add(p);
						}
						else if(this.squareArray[x-i][y+i] != null && reachToPieceFlag == false){
							reachToPieceFlag = true;
							Point p = new Point((x-i),(y+i));
							validArray.add(p);
						}
						
					}
				}
				
				//Left Up
				reachToPieceFlag = false;
				for(int i=1;x-i>=0;i++){
					if(y-i>=0){
						if(this.squareArray[x-i][y-i] == null && reachToPieceFlag == false){
							Point p = new Point((x-i),(y-i));
							validArray.add(p);
						}
						else if(this.squareArray[x-i][y-i] != null && reachToPieceFlag == false){
							reachToPieceFlag = true;
							Point p = new Point((x-i),(y-i));
							validArray.add(p);
						}
						
					}
				}
			}
			//Finish Bishop
			
			//Rock
			reachToPieceFlag = false;
			if(piece instanceof Rock){
				// Up
				for (int i = 1; x - i >= 0; i++) {
					if(this.squareArray[x-i][y] == null && reachToPieceFlag == false){
						Point p = new Point((x - i), (y));
						validArray.add(p);
					}
					else if(this.squareArray[x-i][y] != null && reachToPieceFlag == false){
						reachToPieceFlag = true;
						Point p = new Point((x-i),(y));
						validArray.add(p);
					}
					
				}
				
				reachToPieceFlag = false;
				// Down
				for (int i = 1; x + i < 6; i++) {
					if(this.squareArray[x+i][y] == null && reachToPieceFlag == false){
						Point p = new Point((x + i), (y));
						validArray.add(p);
					}
					else if(this.squareArray[x+i][y] != null && reachToPieceFlag == false){
						reachToPieceFlag = true;
						Point p = new Point((x+i),(y));
						validArray.add(p);
					}
					
				
				}

				reachToPieceFlag = false;
				 //Right
				for (int i = 1; y + i < 6; i++) {
					if (this.squareArray[x][y+i] == null && reachToPieceFlag == false) {
						Point p = new Point((x), (y+i));
						validArray.add(p);
					} else if (this.squareArray[x][y+i] != null && reachToPieceFlag == false) {
						reachToPieceFlag = true;
						Point p = new Point((x), (y+i));
						validArray.add(p);
					}
				}

				reachToPieceFlag = false;
				 //Left
				 for(int i=1;y-i>=0;i++){
					if (this.squareArray[x][y-i] == null && reachToPieceFlag == false) {
						Point p = new Point((x), (y-i));
						validArray.add(p);
					} else if (this.squareArray[x][y-i] != null && reachToPieceFlag == false) {
						reachToPieceFlag = true;
						Point p = new Point((x), (y-i));
						validArray.add(p);
					}
					 
				 }
			}
			//Finish Rock
			
			//Knight
			if(piece instanceof Knight){
				// Right Bottom Horizontal 
				if (x + 1 < 6) {
					if (y + 2 < 6) {
						Point p = new Point((x + 1), (y + 2));
						validArray.add(p);
					}
				}

				// Right Bottom Vertical
				if (x + 2 < 6) {
					if (y + 1 < 6) {
						Point p = new Point((x + 2), (y + 1));
						validArray.add(p);
					}
				}	
						
				// Left Bottom Horizontal
				if ( x + 1 < 6) {
					if (y - 2 >= 0) {
						Point p = new Point((x + 1), (y - 2));
						validArray.add(p);
					}
				}

				// Left Bottom Vertical
				if (x + 2 < 6) {
					if (y - 1 >= 0) {
						Point p = new Point((x + 2), (y - 1));
						validArray.add(p);
					}
				}
				
				// Right Up Horizontal
				if (x - 1 >= 0) {
					if (y + 2 < 6) {
						Point p = new Point((x - 1), (y + 2));
						validArray.add(p);
					}
				}

				// Right Up Vertical
				if (x - 2 >= 0) {
					if (y + 1 < 6) {
						Point p = new Point((x - 2), (y + 1));
						validArray.add(p);
					}
				}
				
				// Left Up Horizontal
				if (x - 1 >= 0) {
					if (y - 2 >= 0) {
						Point p = new Point((x - 1), (y - 2));
						validArray.add(p);
					}
				}
				
				// Left Up Vertical
				if (x - 2 >= 0) {
					if (y - 1 >= 0) {
						Point p = new Point((x - 2), (y - 1));
						validArray.add(p);
					}
				}
			}
			//Finish Knight
		}
		//Finish First piece checking
		
		return validArray;
	}
	
	/**
	 * @pre A PieceGroup with multiple pieces exists.
	 * @post The multiple pieces in the PieceGroup are separated.
	 */
	public void splitPiece(Point fromP, Point toP, AbstractPiece piece){
		PieceGroup fromPG = squareArray[fromP.y][fromP.x];
		for(int i=0 ; i < fromPG.getPieces().size(); i++){
			if(piece.getClass() == fromPG.getPieces().get(i).getClass()){
				if(squareArray[toP.y][toP.x] == null){
					squareArray[toP.y][toP.x] = new PieceGroup(piece);
				}
				else{
					squareArray[toP.y][toP.x].getPieces().add(piece);
				}
				fromPG.removePiece(piece);
			}
		}
		this.setChanged();
		this.notifyObservers();
		
	}
	
	public int getNumberOfPlayerPieces(int playerNumber) {
		int pieceCounter = 0;
		for(int i = 0; i < this.squareArray.length; i++) {
			for(int j = 0; j < this.squareArray[i].length; j++) {
				if(squareArray[i][j] != null) {
					PieceGroup pg = squareArray[i][j];
					if(pg.getPieceGroupColour() == playerNumber) {
						pieceCounter += pg.getNumberOfPiecesInGroup();
					}
				}
			}
		}
		return pieceCounter;
	}
	
	public PieceGroup getPiece(int x, int y){
		for(int i=0;i<6;i++){
			for(int j=0;j<6;j++){
				if(x==i && y==j) {
					return squareArray[x][y];
				}
			}
		}
		return null;
	}
	

}
