package controleurs;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

import modeles.GrapheModele;
import modeles.Point;

public class GrapheControleur implements MouseListener,MouseMotionListener{
	
	GrapheModele grapheModele;
	
///////////////////////////////////////////////////////////////////////////////////
	public GrapheControleur(GrapheModele m) {
		
		this.grapheModele=m;
	}
//////////////////////////////////////////////////////////////////////////////////
	
	
@Override
public void mouseClicked(MouseEvent E) {
	if(SwingUtilities.isLeftMouseButton(E)){
	grapheModele.ajouterPoint(E.getX(),E.getY());
	}
	else if( SwingUtilities.isRightMouseButton(E)){
		Point M = grapheModele.pointTouche(E.getX(), E.getY());
		if( M != null  )
		    grapheModele.supprimerPoint2(M);
	}
}


@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}


@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}


@Override
public void mousePressed(MouseEvent E) {
	
	
	
}


@Override
public void mouseReleased(MouseEvent E) {
	
	//grapheModele.ajouterPoint(E.getX(),E.getY());
	
}
//////////////////////////////////////////////////////////////////////////////
@Override
public void mouseDragged(MouseEvent E) {

	Point M = grapheModele.pointTouche(E.getX(), E.getY());
	if( M != null  ){
	    grapheModele.deplacer(M,E.getX(),E.getY());
	
	}
	
}


@Override
public void mouseMoved(MouseEvent arg0) {
	
	
}
////////////////////////////////////////////////////////////////////

}
