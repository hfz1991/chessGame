package view;

import java.awt.*;
import java.util.*;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.AbstractPiece;
import model.Board;

/**
 * A view class to show the board panel.
 * 
 * @author Yidan Zhang
 * @author Chao Wang
 */

public class BoardPanel extends JPanel implements Observer 
{


	private int startupBoardSize = 6;
	
	private GridLayout layout;
	private SquarePanel squares[][];

	
	public SquarePanel[][] getSquares() 
	{
		return squares;
	}

	public void setSquares(SquarePanel[][] squares)
	{
		this.squares = squares;
	}

	public BoardPanel() 
	{
		// setup Chess board
		this.create();
	}

	// end of Chess board
	
	public void create() {
		// setup Chess board
		this.removeAll();
		this.layout = new GridLayout(this.startupBoardSize, this.startupBoardSize);
		this.squares = new SquarePanel[this.startupBoardSize][this.startupBoardSize];
		
		setLayout(layout);
		setPreferredSize(new Dimension(600, 600));
		setBorder(new LineBorder(new Color(0, 0, 0)));

		//paint the chess board

		for (int i = 0; i < this.startupBoardSize; i++) 
		{
			for (int j = 0; j < this.startupBoardSize; j++) 
			{
				squares[i][j] = new SquarePanel(j, i);
				squares[i][j].setPreferredSize(new Dimension(600 / this.startupBoardSize - 5, 600 / this.startupBoardSize - 5));


				squares[i][j].setBackground(Color.WHITE);
				
				if (((i + j) % 2) == 0) 
				{
					squares[i][j].setBackground(Color.DARK_GRAY);
				}
				add(squares[i][j]);
			}
		}
	}


	// end of Chess board

	
	public void recreate(int boardSize) {
		this.startupBoardSize = boardSize;
		this.create();
	}

	/**
	 * @pre a PieceGroup must exist
	 * @post set board pieces for squares
	 */
	private void setBoardPieces(AbstractPiece[][] pieces) 
	{

		if (this.squares.length != pieces.length) {
			this.recreate(pieces.length);
		}
		
		for (int i = 0; i < pieces.length; i++) 
		{
			for (int j = 0; j < pieces[i].length; j++) 
			{
				if (pieces[i][j] != this.squares[i][j].getCurrentPieceGroup()) 
				{
					this.squares[i][j].setCurrentPieceGroup(pieces[i][j]);
				}
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		Board gameBoard = (Board) o;
		AbstractPiece[][] pieces = gameBoard.getSquareArray();
		this.setBoardPieces(pieces);
	}
}