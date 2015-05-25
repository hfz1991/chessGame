package model;

import java.util.ArrayList;

public abstract class AbstractCompositePiece extends AbstractPiece implements CompositeInterface {

	protected int point;
	protected int xC;
	protected int yC;
	protected int colour;
	
	private ArrayList<AbstractCompositePiece> pieces = new ArrayList<AbstractCompositePiece>();
	
	public AbstractCompositePiece(int colour,int x, int y) {
		super(x, y);
		this.colour = colour;
		this.pieces.add(this);
	}
	
	public int getColour(){
		return this.colour;
	}
	
	public void addPiece(AbstractCompositePiece piece){
		this.pieces.add(piece);
	}
	
	public void removePiece(AbstractCompositePiece piece){
		this.pieces.remove(piece);
	}
	
	public ArrayList<AbstractCompositePiece> getPieces(){
		return this.pieces;
	}
	
	public int getPieceGroupScore() {
		int score =0;
		for(AbstractCompositePiece p : this.pieces) {
			score += p.getPoint();
		}
		return score;
	}
	
	public int getNumberOfPiecesInGroup() {
		return this.pieces.size();
	}
	
	public void move(int x, int y) {
		this.xC = x;
		this.yC = y;
	}
	
}
