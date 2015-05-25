package view.imageprototype;

public class ColouredChessPieceImageFile extends ChessPieceImageFile {

	private int colour;
	
	public ColouredChessPieceImageFile(String pathname, int colour) {
		super(pathname);
		this.colour = colour;
	}
	
	public ChessPieceImageFile clone() throws CloneNotSupportedException {
		return (ChessPieceImageFile)super.clone();
	}
	
	public int getColour() {
		return this.colour;
	}

}