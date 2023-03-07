package vues;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import modeles.GrapheModele;

public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GrapheModele m;
	Graphe graphe;
	Menu menu;
	
	Fenetre(GrapheModele m){
		super("Micro-projet d'Ivan Lepoutre");
		this.m=m;
		this.setLocation(100,20);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		graphe = new Graphe(m);
		menu = new Menu(m);
		
		this.add(graphe,BorderLayout.CENTER);
		this.add(menu,BorderLayout.EAST);
			
		this.pack();
		
	}
}
