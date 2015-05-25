package model;

import java.util.ArrayList;

public interface CompositeInterface {
	public abstract String getName();
	public abstract boolean containsUnmovablePiece();
	public abstract void move(int x, int y);
	public abstract void addPiece(AbstractCompositePiece piece);
	public abstract void removePiece(AbstractCompositePiece piece);
}
