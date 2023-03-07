package vues;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import modeles.*;
import controleurs.GrapheControleur;


public class Graphe extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GrapheModele m;
	Image fond;
	
	Graphe(GrapheModele modele){
		m= modele;
		m.addObserver(this);//mon panneau graphe éoute le modèle graphe associé
		this.setPreferredSize(new Dimension (750,700));
		this.setBackground(Color.WHITE);
		fond = getToolkit().getImage("fond.jpg");
		GrapheControleur gc = new GrapheControleur(m);
		this.addMouseListener(gc);
		this.addMouseMotionListener(gc);
		
	}
	
	
	
////////////////////////////////////////////////////////////	
	protected void paintComponent(Graphics arg0) {
		
		super.paintComponent(arg0);
		//arg0.drawImage(fond, 0, 0, getWidth(), getHeight(), this);
		
		if( m.getAlgo() == false ){
		    for(Arete a: m.getTableauAretesGrapheComplet() ){
			 a.dessiner(arg0);
		   }
		}
		else if( m.getAlgo() == true ){
			   if(!m.getParEtapes()){
		             for(Arete a: m.getTableauAretesArbreCouvrant()){
			          a.dessiner(arg0);
		             }
			   }else{
			         for(Arete a: m.getTableauAretesArbreCouvrantParEtapes()){
					 a.dessiner(arg0);
			         }
			   }    
		}
		
		for(Point P :m.getListeDePoints()){
			arg0.setColor(Color.BLACK);
		     P.dessinerImage(arg0, P.getX(), P.getY(), this,P.getNumero());
			//P.dessinerPoint(arg0, P.getX(), P.getY());
		}
	}
/////////////////////////////////////////////////////////////
	@Override
	public void update(Observable arg0, Object arg1) {
		this.repaint();
		
	}
/////////////////////////////////////////////////////////////
	
	
}
