package model;

import java.awt.Point;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Moves {
	
	private Point originPoint;
	private Point targetPoint;
	private ArrayList pointList;
	
	public Moves(){
		pointList = new ArrayList();
	}
	
	public void setPoint(Point op, Point tp){
		this.originPoint = op;
		this.targetPoint = tp;
		pointList.add(op);
		//test
		pointList.add("WK");
		
		pointList.add(tp);
	}
	
	public void fileHandling() throws IOException{
		
		PrintWriter writer = new PrintWriter("moves.dat", "UTF-8");
		
		for(int i = 0; i<pointList.size(); i++){
			System.out.print(pointList.get(i).toString()+ " ");
			writer.print(pointList.get(i).toString()+ " ");
			
		}
		
		
		writer.close();
	}
}
