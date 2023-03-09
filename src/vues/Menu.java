package vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controleurs.MenuControleur;

import modeles.GrapheModele;

public class Menu extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GrapheModele m;
	private JButton efface = new JButton("Erase all");
	private JRadioButton kruskal = new JRadioButton("Kruskal",true);
	private JRadioButton prim = new JRadioButton("Prim",false);
	private JButton sansEtapes =new JButton("Without steps");
	private JButton grapheComplet =new JButton("Complete");
	private JButton precedent =new JButton("Previous");
	private JButton suivant = new JButton("Next");
	private Image fondMenu;
	private JComboBox choixTheme = new JComboBox();

	
	
	Menu(GrapheModele m){
		this.m=m;
		m.addObserver(this);
		this.setBackground(Color.LIGHT_GRAY);
		this.setPreferredSize(new Dimension (150,700));	
		MenuControleur mc = new MenuControleur(m,this);
		/////////////////////////////////////////////////////////////////
		kruskal.setActionCommand("kruskal");
		kruskal.addItemListener(mc);
		prim.setActionCommand("prim");
		prim.addItemListener(mc);
		sansEtapes.addActionListener(mc);
		sansEtapes.setActionCommand("algo");
		precedent.setActionCommand("pr�c�dent");
		precedent.addActionListener(mc);
		suivant.setActionCommand("suivant");
		suivant.addActionListener(mc);
		efface.setActionCommand("efface");
		efface.addActionListener(mc);
		grapheComplet.setActionCommand("compl�ter");
		grapheComplet.addActionListener(mc);
		choixTheme.addItem("Maisons");
		choixTheme.addItem("Ordinateurs");
		choixTheme.addItem("Arbres");
		choixTheme.addItemListener(mc);
	    ///////////////////////////////////////////////////////////////
		JPanel panneauAlgo = new JPanel();
		panneauAlgo.setLayout(new GridLayout(5,1));
		panneauAlgo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panneauAlgo.add(kruskal);
		panneauAlgo.add(prim);
		panneauAlgo.add(sansEtapes);
		panneauAlgo.add(suivant);
		panneauAlgo.add(precedent);
		///////////////////////////////////////////////////////////////
		this.add(panneauAlgo);		
		this.add(efface);
		this.add(grapheComplet);
		this.add(choixTheme);
		////////////////////////////////////////////////////////////////
		ButtonGroup groupe = new ButtonGroup();
		groupe.add(kruskal);
		groupe.add(prim);
		/////////////////////////////////////////////////////////////////
		//un peu de deco !
		//fondMenu = getToolkit().getImage("images/imagesMaison/fondMenu2.jpg");
		
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//GETTERS
	public JComboBox getChoixTheme() {
		return choixTheme;
	}

    public JRadioButton getKruskal() {
		return kruskal;
	}

	public JRadioButton getPrim() {
		return prim;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		arg0.drawImage(fondMenu, 0, 0, getWidth(), getHeight(), this);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

		
	}
}
