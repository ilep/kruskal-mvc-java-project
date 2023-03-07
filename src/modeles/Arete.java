package modeles;

import java.awt.Color;
import java.awt.Graphics;

public class Arete {

	private Point extremite1;
	private Point extremite2;
	private Color couleur;
	
	
	Arete(Point M1,Point M2,Color c){
		extremite1=M1;
		extremite2=M2;
		couleur=c;
	}
	 
	//Les "getters"
	public int getLongueur(){
		return (int) Math.sqrt( ( extremite1.getX()-extremite2.getX() ) * ( extremite1.getX()-extremite2.getX() )     +     ( extremite1.getY()-extremite2.getY() ) *  ( extremite1.getY()-extremite2.getY() )    );
	}
	
	public Point getExtremite1(){
		return extremite1;
	}
	
	public Point getExtremite2(){
		return extremite2;
	}
	
	//Les "setters"
	
	public String toString(){
		return "("+this.getExtremite1().getNumero()+","+this.getExtremite2().getNumero()+") " + " , " +this.getLongueur();
	}
	
	public void dessiner(Graphics G){
		
		G.setColor(couleur);
		int x1=extremite1.getX();
		int y1=extremite1.getY();
		int x2=extremite2.getX();
		int y2=extremite2.getY();
		
		G.drawLine(x1, y1,x2,y2);		
	}
	

}
