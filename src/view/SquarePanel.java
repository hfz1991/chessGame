package view;


import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import view.imageprototype.ImagePrototypeFactory;
import controller.PieceMovementListener;
import model.AbstractCompositePiece;
import model.AbstractPiece;
import model.CompositeInterface;
import model.GameManager;


/**
 * A view class to show the square panel.
 * @author Yidan Zhang
 * @author Chao Wang
 */

public class SquarePanel extends JPanel
{
	private Point location;
	
	private AbstractPiece currentPieceGroup;
	
	public SquarePanel(int x, int y) 
	{
			location = new Point(x,y);
			
			this.addMouseListener(new PieceMovementListener(this));
 
	}
	
	public AbstractPiece getCurrentPieceGroup() 
	{
		return this.currentPieceGroup;
	}
	
	public void setCurrentPieceGroup(AbstractPiece currentPieceGroup) 
	{
		this.currentPieceGroup = currentPieceGroup;
		repaint();
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		if(this.currentPieceGroup != null) 
		{
			if(this.currentPieceGroup instanceof CompositeInterface) {
				ArrayList<AbstractCompositePiece> pieces = ((AbstractCompositePiece)this.currentPieceGroup).getPieces();
				for(AbstractCompositePiece p : pieces) {
					File f = ImagePrototypeFactory.getPieceImageFile(p.getName(), p.getColour());
					try {
						g.drawImage(ImageIO.read(f), 0, 0, null);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
				}
			} else {
				File f = ImagePrototypeFactory.getPieceImageFile(this.currentPieceGroup.getName());
				try {
					g.drawImage(ImageIO.read(f), 0, 0, null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		}

	}
	
	public Point getGridLocation()
	{
		return this.location;
	}
	
	
	
}
