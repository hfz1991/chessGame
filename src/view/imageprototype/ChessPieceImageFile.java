package view.imageprototype;

import java.io.File;

public class ChessPieceImageFile extends File implements Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ChessPieceImageFile(String pathname) {
		super(pathname);
	}

	public ChessPieceImageFile clone() throws CloneNotSupportedException {
		return (ChessPieceImageFile)super.clone();
	}
	
}