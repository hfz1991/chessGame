package model;

import java.util.Observable;

/**
 * Player information class.
 * @author Fang Zhou He
 * @author Michael Kowalenko
 */
public class Player extends Observable {
	
	private int playerNumber;
	private int numberOfMoves;
	private int score;
	private boolean currentTurn;
	private boolean hasRedone;
	private Moves moves;

	public Player(int playerNumber) {
		super();
		this.playerNumber = playerNumber;
		this.numberOfMoves = 0;
		this.score = 0;
		this.currentTurn = false;
		this.hasRedone = false;
		this.moves = new Moves();
		this.setChanged();
		this.notifyObservers(this.playerNumber);
	}
	
	public void addMove() {
		this.numberOfMoves++;
		this.setChanged();
		this.notifyObservers(this.playerNumber);
	}
	
	public int getNumberOfMoves() {
		return this.numberOfMoves;
	}
	
	public boolean addScore(int score){
		if(score<0)
			return false;
		this.score+=score;
		this.setChanged();
		this.notifyObservers(this.playerNumber);
		return true;
	}
	
	public Moves getMoves(){
		return moves;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public boolean substactScore(int score){
		if(score<0)
			return false;
		this.score-=score;
		return true;
	}
	
	public void setCurrentTurn(boolean currentTurn) {
		this.currentTurn = currentTurn;
		this.setChanged();
		this.notifyObservers(this.playerNumber);
	}
	
	public boolean getCurrentTurn() {
		return this.currentTurn;
	}
	
	public int getPlayerNumber() {
		return this.playerNumber;
	}
}
