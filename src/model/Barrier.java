package model;

/**
 * Barrier chess piece.
 * @author Fang Zhou He
 * @author Michael Kowalenko
 */
public class Barrier extends AbstractPiece{

	public Barrier(int x, int y){
		super(x,y);
		this.point = 1;
	}
	
	public String getName() {
		return "star";
	}
}
