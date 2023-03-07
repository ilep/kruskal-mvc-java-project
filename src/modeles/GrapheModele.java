package modeles;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import javax.imageio.ImageIO;

public class GrapheModele extends Observable{

	private ArrayList<Point> listeDePoints= new ArrayList<Point>();//la liste de nos clics = points affichés à l'écran à  la suite d'un clic
	private ArrayList<Arete> tableauAretesGrapheComplet = new ArrayList<Arete>();//tableau où l'on stocke les aretes du graphe
	private ArrayList<Arete> tableauAretesArbreCouvrant = new ArrayList<Arete>();//	tableau ou l'on stockera les aretes de l'arbre couvrant de poids minimal
	private boolean algo = false;//pour savoir si on a lancé kruskal ou prim
	private final int infini =10000;//aucune longueur ne sera supérieure à  infini
	private String theme = "Maisons";//il y a aussi le thème "ordinateurs" et bientôt "arbres" pour un peu de verdure
	private boolean kruskal =true;
	private boolean prim =false;
	
	////////////////// ces attributs ne servent que pour kruskal et prim  par étapes /////////////////////////////////////
	
	boolean parEtapes =false ;//pour savoir si on applique les algorithmes par étapes ou non
	int Etape = 0;//numéro de l'étape
	private ArrayList<Arete> tableauAretesArbreCouvrantParEtapes = new ArrayList<Arete>();//	tableau ou l'on stockera les aretes de l'arbre couvrant de poids minimal

	////////////////////////////////////////////////////////////////////////////////////////////////
	
	public GrapheModele(){
		super();
	}
	
////////////////////////////////////////////////////////////////////////
    //GETTERS
	public ArrayList<Point> getListeDePoints(){
		return listeDePoints;
	}
	public ArrayList<Arete> getTableauAretesGrapheComplet(){
		return tableauAretesGrapheComplet;
	}
	public ArrayList<Arete> getTableauAretesArbreCouvrant(){
		return tableauAretesArbreCouvrant;
	}
	public ArrayList<Arete> getTableauAretesArbreCouvrantParEtapes(){
		return tableauAretesArbreCouvrantParEtapes;
	}
	public int getNombrePoints(){
		return listeDePoints.size();
	}
	public boolean getAlgo(){
		return this.algo;
	}
	public String getTheme(){
		return this.theme;
	}
	public boolean getParEtapes(){
		return this.parEtapes;
	}
    public int getEtape() {
		return Etape;
	}

	public boolean isKruskal() {
		return kruskal;
	}

	public boolean isPrim() {
		return prim;
	}

	////////////////////////////////////////////////////////////////////////
	//SETTERS 
	public void setTableauAretesGrapheComplet(ArrayList<Arete> l){
		this.tableauAretesGrapheComplet=l;
		setChanged();
		notifyObservers();
	}
	public void setListeDePoints(ArrayList<Point> pl){
		this.listeDePoints=pl;
	}
	public void setTableauAretesArbreCouvrant(ArrayList<Arete> acl){
		this.tableauAretesArbreCouvrant=acl;
		setChanged();
		notifyObservers();
	}
	public void setAlgo(boolean b){
		this.algo=b;
	}
	public void setTheme(String s){
		this.theme=s;
	}
	public void setParEtapes(boolean b ){
		this.parEtapes=b;
		this.setEtape(0);
		if(!b){
			this.setTableauAretesArbreCouvrantParEtapes(new ArrayList<Arete>());
		}
	}
	public void setEtape(int etape) {
		Etape = etape;
	}
	
    public void setTableauAretesArbreCouvrantParEtapes(ArrayList<Arete> tableauAretesArbreCouvrantParEtapes) {
		this.tableauAretesArbreCouvrantParEtapes = tableauAretesArbreCouvrantParEtapes;
		setChanged();
		notifyObservers();
	}

	public void setKruskal(boolean kruskal) {
		this.kruskal = kruskal;
	}

	public void setPrim(boolean prim) {
		this.prim = prim;
	}

	/////////////////////////////////////////////////////////////////////
	public void ajouterPoint(int x,int y){
		this.setAlgo(false);
		this.setParEtapes(false);
		Random alea = new Random(System.currentTimeMillis());		
		try {
			    if( theme.equals("Maisons") ){
		        int n =new Integer(Math.abs(alea.nextInt()) % 5);
                Image img = ImageIO.read(new File("images/imagesMaison/dessinMaison"+ n +".jpg"));
                Point P =new Point(x,y,listeDePoints.size(),10,"dessinMaison"+ n +".jpg",img);
                listeDePoints.add(P);
                this.miseAJourGrapheComplet(P);
                setChanged();
    		    notifyObservers();
			    }
			    else if(theme.equals("Ordinateurs")){
			    int n =new Integer(Math.abs(alea.nextInt()) % 2);
		        Image img = ImageIO.read(new File("images/imagesOrdinateur/dessinOrdinateur"+ n +".jpg"));
	            Point P =new Point(x,y,listeDePoints.size(),10,"dessinOrdinateur"+ n +".jpg",img);
	            listeDePoints.add(P);
	            this.miseAJourGrapheComplet(P);
	            setChanged();
    		    notifyObservers();
			    }
			    else if(theme.equals("Arbres")){
				    int n =new Integer(Math.abs(alea.nextInt()) % 4);
			        Image img = ImageIO.read(new File("images/imagesMaison/dessinArbre"+ n +".jpg"));
		            Point P =new Point(x,y,listeDePoints.size(),10,"dessinArbre"+ n +".jpg",img);
		            listeDePoints.add(P);
		            this.miseAJourGrapheComplet(P);
		            setChanged();
	    		    notifyObservers();
				    }
         } catch (IOException e) {  
           e.printStackTrace();
         }
		
	}
//////////////////////////////////////////////////////////////////////
	private void miseAJourGrapheComplet(Point P){
		
	   if(listeDePoints.size()==1){
		return ;
	   }else{
			for(int i=0;i<listeDePoints.size()-1;i++){   
			Arete a= new Arete(listeDePoints.get(i),P,Color.BLACK);  
			tableauAretesGrapheComplet.add(a);
		   }
	    }
	}
////////////////////////////////////////////////////////////////////////
	public void completer(){
		this.setAlgo(false);
		setChanged();
		notifyObservers();
	}
///////////////////////////////////////////////////////////////////////
	private void trierAretes(){
		
		for(int i=0;i<tableauAretesGrapheComplet.size()-1;i++){
			int j=i+1;
			Arete arete= tableauAretesGrapheComplet.get(j);
			int cle =arete.getLongueur();
			while( (j >= 1) && (tableauAretesGrapheComplet.get(j-1).getLongueur() > cle) ){
				tableauAretesGrapheComplet.set(j,tableauAretesGrapheComplet.get(j-1) );
				j--;
			}
			tableauAretesGrapheComplet.set(j,arete);
		}
	}
//////////////////////////////////////////////////////////////////////////
	public void kruskal(){
		this.setAlgo(true);
		System.out.println("*****************DEBUT kruskal***********************");
		ArrayList<Arete> tableauAretesArbreCouvrant = new ArrayList<Arete>();//	tableau ou l'on stockera les aretes de l'arbre couvrant de poids minimal
		this.trierAretes();
		System.out.println("Aretes triées par poids croissant");
		for(Arete a :tableauAretesGrapheComplet){
			System.out.println(a);
		}
		int n =listeDePoints.size();
		int[] CC = new int[n];//composante connexe;
		for(int i=0;i<n;i++){
			CC[i]=i;
		}
		int compteurGrapheComplet = 0;
		int compteurArbreCouvrant=0;
		System.out.println("DEBUT ALGO ");
		
		while(compteurArbreCouvrant< n-1 ){
			
			Arete suivante =tableauAretesGrapheComplet.get(compteurGrapheComplet);
			Point x= suivante.getExtremite1();
			Point y= suivante.getExtremite2();
			
			compteurGrapheComplet++;
			
			if(CC[x.getNumero()] != CC[y.getNumero()]){
			 System.out.println(suivante+"  retenue ");
			 compteurArbreCouvrant++;
			 tableauAretesArbreCouvrant.add(compteurArbreCouvrant-1,suivante);
			 
			 int auxiliaire = CC[y.getNumero()] ;
			 for(int i =0;i<n;i++){
				if(CC[i] == auxiliaire ){ CC[i]=CC[x.getNumero()]; } 
			 }
			 
			}
			else{
				System.out.println(suivante+" rejetée ");
			}
				
		}
		System.out.println("*****************FIN kruskal***********************");
		this.setTableauAretesArbreCouvrant(tableauAretesArbreCouvrant);
	}
///////////////////////////////////////////////////////////////////////////
	public boolean conditionsEtape(){
		if(  Etape < 0  ){
			this.setEtape(0);
			return false;
		}else if(Etape  > listeDePoints.size()-1 ){
			this.setEtape(listeDePoints.size()-1);
			return false;
		}
		else{
			return true;
		}
	}
///////////////////////////////////////////////////////////////////////////
	public void kruskalParEtapes(){

		System.out.println("étape n° " + Etape );
		this.setAlgo(true);
		ArrayList<Arete> tableauAretesArbreCouvrantParEtapes2 = new ArrayList<Arete>();
	    if(Etape == 1){
          this.kruskal();
	    }
	    if(Etape > listeDePoints.size()-1){
	    	this.setEtape(listeDePoints.size()-1);
	    	//this.setParEtapes(false);
	    }
	    for(int i=0;i<=Etape-1;i++){
	    	Arete a = this.tableauAretesArbreCouvrant.get(i);
	    	System.out.println(a);
	    	tableauAretesArbreCouvrantParEtapes2.add(a);
	    }
		this.setTableauAretesArbreCouvrantParEtapes(tableauAretesArbreCouvrantParEtapes2);    
	}
///////////////////////////////////////////////////////////////////////////
	public void prim(){
		this.setAlgo(true);
		System.out.println("*********** Début de Prim *********************");
		ArrayList<Arete> aretesPrim = new ArrayList<Arete>();//arbre en cours de construction 
		ArrayList<Point> pointsPrim = new ArrayList<Point>();
		int n = listeDePoints.size();
		System.out.println("Il y a " + n +" points");
		
		int[] distance =new int[n];
		boolean[] dejaDansPointsPrim =new boolean[n];
	
		Point pivot = listeDePoints.get(0);
		
		for(Point p: listeDePoints){
			if(p != pivot){
				distance[p.getNumero()]=infini;
			}
		}
		Point[] lePusProche = new Point[n];
		pointsPrim.add(pivot);
		
		for(int i=0;i<n-1;i++){
			
			for(Point p : listeDePoints){
				if(!dejaDansPointsPrim[p.getNumero()] && p!=pivot ){
					if(distanceEntre(pivot,p) < distance[p.getNumero()]){
						lePusProche[p.getNumero()] =pivot;
						distance[p.getNumero()]=distanceEntre(pivot,p);
					}
				}
			}
			int min= infini;
			for(Point p : listeDePoints ){
				if(!pointsPrim.contains(p)){
					if( distance[p.getNumero()] < min){
						min = distance[p.getNumero()];
						pivot = p;
					}
				}
			}
			
			pointsPrim.add(pivot);
			Arete a = new Arete( pivot, lePusProche[pivot.getNumero()],Color.DARK_GRAY );
			System.out.println("Ajout de l'arête " + a);
			aretesPrim.add( a  );
			
		}
		System.out.println("***********Fin de Prim *********************");
		this.setTableauAretesArbreCouvrant(aretesPrim);
	}
////////////////////////////////////////////////////////////////////////////////////////////////////
	public void primParEtapes(){
		 System.out.println("***************** Début primParEtapes *******************");
		System.out.println("étape n° " + Etape );
		this.setAlgo(true);
		ArrayList<Arete> tableauAretesArbreCouvrantParEtapes4 = new ArrayList<Arete>();
	    if(Etape == 1){
          this.prim();
	    }
	    if(Etape > listeDePoints.size()-1){
	    	this.setEtape(listeDePoints.size()-1);
	    }
	    for(int i=0;i<=Etape-1;i++){
	    	Arete a = this.tableauAretesArbreCouvrant.get(i);
	    	System.out.println(a);
	    	tableauAretesArbreCouvrantParEtapes4.add(a);
	    }
		this.setTableauAretesArbreCouvrantParEtapes(tableauAretesArbreCouvrantParEtapes4);
		System.out.println("***************** Fin primParEtapes *******************");
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////
	private int distanceEntre(Point m1,Point m2){
		return (int) Math.sqrt( ( m1.getX()-m2.getX() ) * ( m1.getX()-m2.getX() )    +  ( m1.getY()-m2.getY() ) *  ( m1.getY()-m2.getY() )  );
	}
////////////////////////////////////////////////////////////////////////////	
	public void effacerTout(){
		//consiste simplement à réinitialiser tous les attributs d'instance <=> à  les faire pointer sur des listes vides
		
		ArrayList<Point> nouvelleListePointsVide =new ArrayList<Point>();
		ArrayList<Arete> nouvelleListeAretesVide =new ArrayList<Arete>();
		ArrayList<Arete> nouvelleListeAretesVide2 =new ArrayList<Arete>();
		ArrayList<Arete> nouvelleListeAretesVide3 =new ArrayList<Arete>();
		this.setTableauAretesGrapheComplet(nouvelleListeAretesVide);
		this.setListeDePoints(nouvelleListePointsVide);
		this.setTableauAretesArbreCouvrant(nouvelleListeAretesVide2);
		this.setTableauAretesArbreCouvrantParEtapes(nouvelleListeAretesVide3);
	}
///////////////////////////////////////////////////////////////////////////	
	public Point pointTouche(int a, int b){
		//on clique en (a,b); si un point est "touché", on renvoie ce point 
		Point trouve = null;
		for(Point p : listeDePoints)
			if( p.est_dedans(a, b)) trouve =p;
		
		return trouve;	
	}
//////////////////////////////////////////////////////////////////////
	public ArrayList<Arete> aretesPartantDe(Point P){
		
		ArrayList<Arete>  l = new ArrayList<Arete>();
		for(Arete a :tableauAretesGrapheComplet){
			if( (a.getExtremite1() == P) || (a.getExtremite2() == P)){
				l.add(a);
			}	
		}
		System.out.println("aretes partant du point" + P);
		for(Arete a :l){ 
			System.out.println(a);
		}
		return l;
	}
//Voici une premiere version d'effaçage que je voulais mettre en oeuvre et qui n'a pas bien marché : pourquoi= mystère
////////////////////////////////////////////////////////////////////////
	public void supprimerPoint(Point P){
		this.setParEtapes(false);
		System.out.println("********** DEBUT Effaçage point " + P.getNumero() + " **********");
		ArrayList<Arete>  l;
		l=aretesPartantDe(P);
		tableauAretesGrapheComplet.removeAll(l);
		System.out.println("AVANT : aretes de l'arbre couvrant de poids min.");
		for(Arete a : tableauAretesArbreCouvrant){
			System.out.println(a);
		}
		for(Arete a: l){
			
			if(tableauAretesArbreCouvrant.contains(a)){
				tableauAretesArbreCouvrant.remove(a);
			}
			if(tableauAretesArbreCouvrantParEtapes.contains(a)){
			    tableauAretesArbreCouvrantParEtapes.remove(a);
			}
		}
		System.out.println("APRES : les mêmes arêtes privées de celles partant du point effacé ");
		for(Arete a : tableauAretesArbreCouvrant){
			System.out.println(a);
		}
		listeDePoints.remove(P);
		this.reAssignerNumeros();
		this.setEtape(0);//si on reprend un algo par étapes, il faut reprendre depuis 0 si on a supprimé un point en cours
	    System.out.println("********** FIN Effaçage point " + P.getNumero() + " **********");
		setChanged();
		notifyObservers();
	}
//En voici donc une autre , qui sera celle utilisée
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void supprimerPoint2(Point P){
		this.setParEtapes(false);
		System.out.println("********** DEBUT Effaçage point " + P.getNumero() + " **********");
		ArrayList<Arete>  l;
		l=aretesPartantDe(P);
		tableauAretesGrapheComplet.removeAll(l);
		listeDePoints.remove(P);
		this.reAssignerNumeros();
        if(kruskal){
        	this.kruskal();
        }
        else if(prim){
        	this.prim();
        }
		this.setEtape(0);//si on reprend un algo par étapes, il faut reprendre depuis 0 si on a supprimé un point en cours
	    System.out.println("********** FIN Effaçage point " + P.getNumero() + " **********");
		//setChanged();
		//notifyObservers();
	}
///////////////////////////////////////////////////////////////////////////////
	public void reAssignerNumeros(){
		int c=0;
		for(Point p : listeDePoints ){
			p.setNumero(c);
			c++;
		}
	}
//////////////////////////////////////////////////////////////////////////////
	public void deplacer(Point M,int x,int y ){
		M.setX(x);
		M.setY(y);
		this.setEtape(0);
		this.setParEtapes(false);
		if(this.isKruskal()){
		kruskal();
		}
		else if(this.isPrim()){
	    prim();
		}
	}
//////////////////////////////////////////////////////////////////////
}

