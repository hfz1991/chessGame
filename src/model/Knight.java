package model;

/**
 * Knight chess piece.
 * @author Fang Zhou He
 * @author Michael Kowalenko
 */
public class Knight extends AbstractCompositePiece {
		

	public Knight(int colour, int x, int y) {
		super(colour, x, y);
		this.point = 5;
	}
	
	public void move(int x, int y){
		this.setxC(x);
		this.setyC(y);
	}
	
	public int getColour(){
		return colour;
	}
	
	public String getName() {
		return "knight";
	}
	
	
}
