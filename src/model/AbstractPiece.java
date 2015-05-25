package model;

/**
 * A base class for Chess pieces.
 * @author Fang Zhou He
 * @author Michael Kowalenko
 */
public abstract class AbstractPiece {
	
	protected int point;
	protected int xC;
	protected int yC;
	

	
	public AbstractPiece(int x, int y){
		this.setxC(x);
		this.setyC(y);
	}
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	public int getxC() {
		return xC;
	}
	
	public void setxC(int xC) {
		this.xC = xC;
	}
	
	public int getyC() {
		return yC;
	}
	
	public void setyC(int yC) {
		this.yC = yC;
	}
	
	
	public boolean containsUnmovablePiece() {
		AbstractPiece p = this;
		if (!(p instanceof AbstractCompositePiece)) {
			return true;
		}
		return false;
	}

	public abstract String getName();
	
}
