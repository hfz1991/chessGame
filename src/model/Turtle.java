package model;

public class Turtle extends AbstractCompositePiece{
	
	public Turtle(int colour, int x, int y) {
		super(colour, x, y);
	}

	public void move(int x, int y) {
		this.setxC(x);
		this.setyC(y);
	}

	public int getColour() {
		return colour;
	}

	public String getName() {
		return "turtle";
	}

}