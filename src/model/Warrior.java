package model;

public class Warrior extends AbstractCompositePiece{
	
	public Warrior(int colour, int x, int y) {
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
		return "warrior";
	}

}