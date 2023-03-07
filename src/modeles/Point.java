package modeles;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class Point {

	private int x;
	private int y;
	private int rayon;
	private int numero;
	private Image image;

	Point(int x,int y,int num,int rayon,String nom,Image img){
		this.x=x;
		this.y=y;
		this.numero=num;
		this.rayon=rayon;
		this.image=img;
	}
	
	public int getNumero(){
		return numero;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	public int getRayon(){
		return this.rayon;
	}
	public int getDiagonaleImage(){
		return (int) ((int) 0.5*Math.sqrt(   image.getHeight(null)*image.getHeight(null) + image.getWidth(null)*image.getWidth(null)    ));
	}
	
	public void setNumero(int num){
		numero=num;
	}
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}

	public String toString(){
		return "Point " + this.getNumero() +" de coordonnéees (x,y)=("+this.getX()+","+this.getY()+")";
	}
	
	
	public void dessinerPoint(Graphics G,int x,int y){
		int reculX=2*rayon;
        int reculY =2*rayon;
		int rayon=this.getRayon();
		G.fillOval(x-rayon,y-rayon, 2*rayon, 2*rayon);
		G.drawString(String.valueOf(numero), x +reculX,y + reculY);
	}
	
	public void dessinerImage(Graphics G,int x,int y,JPanel p,int numero){
		
            int reculX=image.getWidth(p)/2;
            int reculY =image.getHeight(p)/2;
            G.drawImage(image, x-reculX, y-reculY, p);
            G.drawString(String.valueOf(numero), x +reculX,y + reculY);

	}
	
	
	public boolean est_dedans(int a,int b){
		return (a > x - 25) && (a < x + 25 ) && ( b > y - 25 ) && ( b < y + 25 );
	}
	
}
