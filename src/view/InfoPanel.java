package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.border.LineBorder;

import model.GameManager;
import model.Player;
import controller.controller;
import controller.MyTimerActionListener;
import controller.NewGameDialogueListener;
import controller.SaveGameListener;
import controller.SplitDialogueListener;
import controller.UndoDialogueListener;

/**
 * A view class to show the info panel.
 * @author Yidan Zhang
 * @author Chao Wang
 */

public class InfoPanel extends JPanel implements Observer 
{
	
	GridLayout layout2 = new GridLayout(3,1);
	
	private JLabel whiteLabel, moveW, scoreW, numMoveW, numScoreW;
	private JLabel blackLabel, moveB, scoreB, numMoveB, numScoreB;
	private JLabel time, timerLabel;
	private JButton newGame, goBack, split, save;
	private Box moveWhite, scoreWhite, scoreBlack, moveBlack, timerBox, buttonBox;
	private JPanel whitePanel, blackPanel;
	private MyTimerActionListener timerActionListener;
	private Timer timer;
	
	private int indexN;
	private boolean isSplited=false;

	public boolean getIsSplited() {
		return isSplited;
	}

	public void setSplited(boolean isSplited) {
		this.isSplited = isSplited;
	}

	public void setIndexN(int indexN) {
		this.indexN = indexN;
	}
	
	public int getIndexN() {
		return indexN;
	}
	
	public InfoPanel(final JFrame parent)
	{
	    setPreferredSize(new Dimension(240, 640));
	    
	    //White Player//
	    //setup information panel for white player
	    whitePanel = new JPanel(layout2);
	    whitePanel.setBackground(Color.WHITE);
	    whitePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
	    whitePanel.setPreferredSize(new Dimension(240, 210));
	    
	    //initialize for White player
	    moveWhite = Box.createHorizontalBox();
	    scoreWhite = Box.createHorizontalBox();
	    
	    whiteLabel = new JLabel("  White:");
	    moveW = new JLabel("  Moves:   ");
	    scoreW = new JLabel("  Score:   ");
	    numMoveW = new JLabel("0");
	    numScoreW = new JLabel("0");
	    
	    whitePanel.add(whiteLabel);
	    
	    moveWhite.add(moveW);
	    moveWhite.add(numMoveW);
	    whitePanel.add(moveWhite);
	  
	    scoreWhite.add(scoreW);
	    scoreWhite.add(numScoreW);
	    whitePanel.add(scoreWhite);
	    //End of White Player//
	    
	    
	    //Black Player//
	    //setup information panel for black player
	    blackPanel = new JPanel(layout2);
	    blackPanel.setBackground(Color.BLACK);
	    whitePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
	    blackPanel.setPreferredSize(new Dimension(240, 210));
	    
	    //initialize for White player
	    moveBlack = Box.createHorizontalBox();
	    scoreBlack = Box.createHorizontalBox();
	    
	    blackLabel = new JLabel("  Black:");
	    moveB = new JLabel("  Moves:    ");
	    scoreB = new JLabel("  Score:     ");
	    numMoveB = new JLabel("0");
	    numScoreB = new JLabel("0");
	    
	    blackLabel.setForeground(Color.WHITE);
	    moveB.setForeground(Color.WHITE);
	    scoreB.setForeground(Color.WHITE);
	    numMoveB.setForeground(Color.WHITE);
	    numScoreB.setForeground(Color.WHITE);
	    
	    blackPanel.add(blackLabel);
	    moveBlack.add(moveB);
	    moveBlack.add(numMoveB);
	    
	    blackPanel.add(moveBlack);
	  
	    scoreBlack.add(scoreB);
	    scoreBlack.add(numScoreB);
	    
	    blackPanel.add(scoreBlack);
	    
	    //End of Black Player//
	    
	    JPanel buttonP = new JPanel(layout2);
	    buttonP.setBackground(Color.WHITE);
	    buttonP.setPreferredSize(new Dimension(240, 200));
	    
	    time = new JLabel("Time:    ");
	    timerLabel = new JLabel("0:0");
	    newGame = new JButton("New Game");
	    goBack = new JButton("Undo");
	    split = new JButton("Split");
	    save = new JButton("Save");
	    
	    timerBox = Box.createHorizontalBox();
	    
	    timerBox.add(time);
	    timerBox.add(timerLabel);
	    
	    buttonBox = Box.createHorizontalBox();
	    
	    newGame.addActionListener(new NewGameDialogueListener(this));
	    
	    goBack.addActionListener(new UndoDialogueListener());
	    split.addActionListener(new SplitDialogueListener(this));
	    save.addActionListener(new SaveGameListener());
	    
	    buttonBox.add(goBack).setEnabled(false);
	    buttonBox.add(split).setEnabled(false);
	    buttonBox.add(save).setEnabled(false);
	    
	    buttonP.add(timerBox);
	    buttonP.add(newGame);
	    buttonP.add(buttonBox);
	    
	    add(blackPanel,BorderLayout.NORTH);
	    add(whitePanel,BorderLayout.CENTER);
	    add(buttonP,BorderLayout.SOUTH);

	}
	
	// Time Function
	
	int second = 0;
	int minute = 0;
	public void setTimer()
	{
		second = 0;
		minute = 0;
		if(timer != null)
			timer.cancel();
		timer = new Timer();
		timer.schedule(new TimerTask()
		{

			@Override
			public void run() {
				// TODO Auto-generated method stub
				second ++;
				if(second == 60){
					minute ++; 
					second = 0;
				}
				timerLabel.setText(minute + ":" + second);
				if(minute == 1)
				{
					if(timerActionListener != null)
					{
						timerActionListener.onTimerActionEvent();
					}
				}
			}
			
		}, 0, 1000);
	}
	
	public void stopTimer()
	{
		second = 0;
		minute = 0;
		timerLabel.setText("0:0");
		if(timer != null)
			timer.cancel();
	}
	
	public void setTimerActionListener(MyTimerActionListener listener)
	{
		timerActionListener = listener;
	}
	
	public JButton getGoBack() 
	{
		return goBack;
	}

	public void setGoBack(JButton goBack) 
	{
		this.goBack = goBack;
	}

	public JButton getSplit() 
	{
		return split;
	}

	public void setSplit(JButton split) 
	{
		this.split = split;
	}
	
	public JButton getSave() 
	{
		return save;
	}

	//player takes turn to move
	public void update(Observable o, Object arg) 
	{
		Player player = (Player)o;
		if((Integer)arg == GameManager.WHITE_PLAYER) 
		{
			String whiteString = "  White:";
			if(player.getCurrentTurn()) 
			{
				whiteString += " YOUR TURN";
			}
			this.whiteLabel.setText(whiteString);
			this.numMoveW.setText(String.valueOf(player.getNumberOfMoves()));
			this.numScoreW.setText(String.valueOf(player.getScore()));
		} else if((Integer)arg == GameManager.BLACK_PLAYER) 
		{
			String blackString = "  Black:";
			if(player.getCurrentTurn()) 
			{
				blackString += " YOUR TURN";
			}
			this.numMoveB.setText(String.valueOf(player.getNumberOfMoves()));
			this.blackLabel.setText(blackString);
			this.numScoreB.setText(String.valueOf(player.getScore()));
		}
	}
}
